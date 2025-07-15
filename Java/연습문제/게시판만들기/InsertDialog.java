package GUI.boardapp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class InsertDialog extends JDialog {
    private BoardApp boardApp;
    private JPanel pCenter, pTitle, pContent, pWriter, pSouth;
    private JTextField txtTitle, txtWriter;
    private JTextArea txtContent;
    private JButton btnOk, btnCancel;



    public InsertDialog(BoardApp boardApp) {
        super(boardApp);
        this.boardApp = boardApp;

        this.setTitle("게시물 작성");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setModal(true);
        this.setSize(400, 270);

        this.setLocation(
                boardApp.getLocationOnScreen().x + (boardApp.getWidth()-this.getWidth())/2,
                boardApp.getLocationOnScreen().y + (boardApp.getHeight()-this.getHeight())/2
        );

        this.getContentPane().add(getPCenter(), BorderLayout.CENTER);
        this.getContentPane().add(getPSouth(), BorderLayout.SOUTH);
    }


    public JPanel getPCenter() {
        if(pCenter==null) {
            pCenter = new JPanel();
            pCenter.setBorder(new EmptyBorder(10,10,10,10));
            pCenter.add(getPTitle());
            pCenter.add(getPWriter());
            pCenter.add(getPContent());
        }
        return pCenter;
    }

    public JPanel getPTitle() {
        if(pTitle==null) {
            pTitle = new JPanel();
            pTitle.setLayout(new BorderLayout());
            JLabel label = new JLabel("제목");
            label.setAlignmentX(JLabel.CENTER);
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
        if(pWriter==null) {
            pWriter = new JPanel();
            pWriter.setLayout(new BorderLayout());
            JLabel label = new JLabel("글쓴이");
            label.setAlignmentX(JLabel.CENTER);
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
        if(pContent == null) {
            pContent = new JPanel();
            pContent.setLayout(new BorderLayout());
            JLabel label = new JLabel("내용");
            label.setAlignmentX(JLabel.CENTER);
            label.setPreferredSize(new Dimension(70, 30));
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
        if(pSouth == null) {
            pSouth = new JPanel();
            pSouth.setBackground(Color.WHITE);
            pSouth.add(getBtnOk());
            pSouth.add(getBtnCancel());
        }
        return pSouth;
    }



    public JButton getBtnOk() {
        if(btnOk == null) {
            btnOk = new JButton();
            btnOk.setText("저장");
            btnOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean a = txtContent.getText().isEmpty();
                    boolean b = txtTitle.getText().isEmpty();
                    boolean c = txtWriter.getText().isEmpty();

                    if (a && b && c) {
                        txtContent.setText("내용을 적으세요!!");
                        txtTitle.setText("제목을 적으세요!!");
                        txtWriter.setText("작성자를 적으세요!!");
                    } else if (a && b) {
                        txtContent.setText("내용을 적으세요!!");
                        txtTitle.setText("제목을 적으세요!!");
                    } else if (b && c) {
                        txtTitle.setText("제목을 적으세요!!");
                        txtWriter.setText("작성자를 적으세요!!");
                    } else if (a && c) {
                        txtContent.setText("내용을 적으세요!!");
                        txtWriter.setText("작성자를 적으세요!!");
                    } else if (a) {
                        txtContent.setText("내용을 적으세요!!");
                    } else if (b) {
                        txtTitle.setText("제목을 적으세요!!");
                    } else if (c) {
                        txtWriter.setText("작성자를 적으세요!!");
                    } else {
                        int no = boardApp.getNo();
                        int click = boardApp.getClick();
                        LocalDate today = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                        String formattedDate = today.format(formatter);
                        Object[] rowData = {
                                Integer.toString(no),
                                txtTitle.getText(),
                                txtWriter.getText(),
                                formattedDate,
                                Integer.toString(click),
                                txtContent.getText()
                        };
                        DefaultTableModel tableModel = (DefaultTableModel) boardApp.getJTable().getModel();
                        tableModel.addRow(rowData);

                        txtTitle.setText("");
                        txtContent.setText("");
                        txtWriter.setText("");
                    }
                }
            });
        }

            return btnOk;
    }

    public JButton getBtnCancel() {
        if(btnCancel == null) {
            btnCancel = new JButton();
            btnCancel.setText("취소");
            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InsertDialog.this.dispose();
                }
            });
        }
        return btnCancel;
    }
}

