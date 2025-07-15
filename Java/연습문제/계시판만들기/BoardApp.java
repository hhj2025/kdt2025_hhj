package GUI.boardapp.ui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class BoardApp extends JFrame {
    private JTable jTable;
    private JPanel pSouth;
    private JButton btnInsert;
    private int no = 1;
    private int click = 0;

    public int getNo() {
        return no++;
    }

    public int getClick() {
        return click;
    }

    public JTable getjTable() {
        return jTable;
    }

    BoardApp() {
        setTitle("게시판 리스트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new JScrollPane(getJTable()), BorderLayout.CENTER);
        getContentPane().add(getPSouth(), BorderLayout.SOUTH);
        setSize(600, 450);
        setVisible(true);
    }

    JTable getJTable() {
        if (jTable == null) {
            jTable = new JTable();

            DefaultTableModel tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            tableModel.addColumn("번호");
            tableModel.addColumn("제목");
            tableModel.addColumn("글쓴이");
            tableModel.addColumn("날짜");
            tableModel.addColumn("조회수");
            tableModel.addColumn("내용");

            jTable.setModel(tableModel);

            jTable.getColumn("번호").setPreferredWidth(20);
            jTable.getColumn("제목").setPreferredWidth(200);
            jTable.getColumn("글쓴이").setPreferredWidth(50);
            jTable.getColumn("날짜").setPreferredWidth(70);
            jTable.getColumn("조회수").setPreferredWidth(40);
            jTable.getColumn("내용").setPreferredWidth(0);

            jTable.getColumn("내용").setMinWidth(0);
            jTable.getColumn("내용").setMaxWidth(0);
            jTable.getColumn("내용").setWidth(0);

            CenterTableCellRenderer ctcr = new CenterTableCellRenderer();
            jTable.getColumn("번호").setCellRenderer(ctcr);
            jTable.getColumn("제목").setCellRenderer(ctcr);
            jTable.getColumn("글쓴이").setCellRenderer(ctcr);
            jTable.getColumn("날짜").setCellRenderer(ctcr);
            jTable.getColumn("조회수").setCellRenderer(ctcr);

            jTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int rowIndex = jTable.getSelectedRow();
                        if (rowIndex != -1) {
                            int columnIndex = 4;
                            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                            Object value = model.getValueAt(rowIndex, columnIndex);
                            int currentHit = 0;
                            try {
                                currentHit = Integer.parseInt(value.toString());
                            } catch (NumberFormatException ex) {
                                currentHit = 0;
                            }
                            model.setValueAt(currentHit + 1, rowIndex, columnIndex);

                            ViewDialog viewDialog = new ViewDialog(BoardApp.this);
                            viewDialog.loadRowData(rowIndex);
                            viewDialog.setVisible(true);
                        }
                    }
                }
            });
        }
        return jTable;
    }


    public JPanel getPSouth() {
        if (pSouth == null) {
            pSouth = new JPanel();
            pSouth.add(getBtnInsert());
        }
        return pSouth;
    }

    public JButton getBtnInsert() {
        if (btnInsert == null) {
            btnInsert = new JButton();
            btnInsert.setText("추가");
            btnInsert.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    InsertDialog insertDialog = new InsertDialog(BoardApp.this);
                    insertDialog.setVisible(true);
                }
            });
        }
        return btnInsert;
    }

    public static void main(String[] args) {
        new BoardApp();
    }

    // 내부 클래스: 셀 가운데 정렬
    public class CenterTableCellRenderer extends JLabel implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText(value.toString());
            setFont(new Font(null, Font.PLAIN, 12));
            setHorizontalAlignment(JLabel.CENTER);
            setOpaque(true);
            setBackground(isSelected ? Color.YELLOW : Color.WHITE);
            return this;
        }
    }
}
