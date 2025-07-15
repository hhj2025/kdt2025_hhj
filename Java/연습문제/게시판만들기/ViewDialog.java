package GUI.boardapp.ui;

import GUI.boardapp.ui.BoardApp;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

public class ViewDialog extends JDialog {
    private BoardApp boardApp;
    private JPanel pCenter, pTitle, pContent, pWriter, pSouth;
    private JTextField txtTitle, txtWriter;
    private JTextArea txtContent;
    private JButton btnMod, btnDel, btnCancel;
    private int currentRow = -1;

    public ViewDialog(BoardApp boardApp) {
        super(boardApp);
        this.boardApp = boardApp;
        setTitle("게시물 보기");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setModal(true);
        setSize(400, 270);
        setLocation(
                boardApp.getLocationOnScreen().x + (boardApp.getWidth() - getWidth()) / 2,
                boardApp.getLocationOnScreen().y + (boardApp.getHeight() - getHeight()) / 2
        );
        getContentPane().add(getPCenter(), BorderLayout.CENTER);
        getContentPane().add(getPSouth(), BorderLayout.SOUTH);
    }

    public JPanel getPCenter() {
        if (pCenter == null) {
            pCenter = new JPanel();
            pCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
            pCenter.add(getPTitle());
            pCenter.add(getPWriter());
            pCenter.add(getPContent());
        }
        return pCenter;
    }

    public JPanel getPTitle() {
        if (pTitle == null) {
            pTitle = new JPanel(new BorderLayout());
            JLabel label = new JLabel("제목");
            label.setPreferredSize(new Dimension(70, 30));
            label.setHorizontalAlignment(JLabel.CENTER);
            pTitle.add(label, BorderLayout.WEST);
            txtTitle = new JTextField();
            txtTitle.setPreferredSize(new Dimension(300, 30));
            pTitle.add(txtTitle, BorderLayout.CENTER);
        }
        return pTitle;
    }

    public JPanel getPWriter() {
        if (pWriter == null) {
            pWriter = new JPanel(new BorderLayout());
            JLabel label = new JLabel("글쓴이");
            label.setPreferredSize(new Dimension(70, 30));
            label.setHorizontalAlignment(JLabel.CENTER);
            pWriter.add(label, BorderLayout.WEST);
            txtWriter = new JTextField();
            txtWriter.setPreferredSize(new Dimension(300, 30));
            pWriter.add(txtWriter, BorderLayout.CENTER);
        }
        return pWriter;
    }

    public JPanel getPContent() {
        if (pContent == null) {
            pContent = new JPanel(new BorderLayout());
            JLabel label = new JLabel("내용");
            label.setPreferredSize(new Dimension(70, 100));
            label.setHorizontalAlignment(JLabel.CENTER);
            pContent.add(label, BorderLayout.WEST);
            txtContent = new JTextArea();
            txtContent.setBorder(new EtchedBorder());
            txtContent.setPreferredSize(new Dimension(300, 100));
            pContent.add(txtContent, BorderLayout.CENTER);
        }
        return pContent;
    }

    public JPanel getPSouth() {
        if (pSouth == null) {
            pSouth = new JPanel();
            pSouth.setBackground(Color.WHITE);
            pSouth.add(getBtnMod());
            pSouth.add(getBtnDel());
            pSouth.add(getBtnCancel());
        }
        return pSouth;
    }

    public JButton getBtnMod() {
        if (btnMod == null) {
            btnMod = new JButton("수정");
            btnMod.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (currentRow == -1) {
                        JOptionPane.showMessageDialog(ViewDialog.this, "수정할 행을 선택해주세요.");
                        return;
                    }
                    DefaultTableModel tableModel = (DefaultTableModel) boardApp.getJTable().getModel();
                    tableModel.setValueAt(txtTitle.getText(), currentRow, 1);
                    tableModel.setValueAt(txtWriter.getText(), currentRow, 2);
                    tableModel.setValueAt(txtContent.getText(), currentRow, 5);
                    tableModel.fireTableRowsUpdated(currentRow, currentRow);
                    dispose();
                }
            });
        }
        return btnMod;
    }

    public JButton getBtnDel() {
        if (btnDel == null) {
            btnDel = new JButton("삭제");
            btnDel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (currentRow == -1) {
                        JOptionPane.showMessageDialog(ViewDialog.this, "삭제할 행을 선택해주세요.");
                        return;
                    }
                    DefaultTableModel tableModel = (DefaultTableModel) boardApp.getJTable().getModel();
                    tableModel.removeRow(currentRow);
                    dispose();
                }
            });
        }
        return btnDel;
    }

    public JButton getBtnCancel() {
        if (btnCancel == null) {
            btnCancel = new JButton("닫기");
            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }
        return btnCancel;
    }

    public void loadRowData(int row) {
        currentRow = row;
        DefaultTableModel tableModel = (DefaultTableModel) boardApp.getJTable().getModel();

        txtTitle.setText(tableModel.getValueAt(row, 1).toString());
        txtWriter.setText(tableModel.getValueAt(row, 2).toString());
        txtContent.setText(tableModel.getValueAt(row, 5).toString());
    }
}
