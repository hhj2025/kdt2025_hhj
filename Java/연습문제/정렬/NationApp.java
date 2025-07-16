package Stream;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class NationApp extends JFrame {
    /**
     * @wbp.nonvisual location=256,279
     */
    private final JButton btnSort ;
    private JTable jTable;
    JComboBox<String> cmbOrder;

    NationApp() {
        setTitle("국가");

        add(new JScrollPane(makeTable()), BorderLayout.CENTER);


        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);

        String[] cmbString = {"국가별", "인구수","GDP"};
        cmbOrder = new JComboBox<String>(cmbString);


        panel.add(cmbOrder);

        btnSort  = new JButton("정렬");
        panel.add(btnSort);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setVisible(true);

        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cmbOrder.getSelectedItem();

                Comparator<Nation> comparator = switch (selected) {
                    case "인구수" -> Comparator.comparing(Nation::getPopulation).reversed();
                    case "GDP" -> Comparator.comparing(Nation::getGdpRank);
                    default -> Comparator.comparing(Nation::getName);
                };

                DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
                tableModel.setRowCount(0);

                final int[] a = {1};
                Nation.nations.stream()
                        .sorted(comparator)
                        .forEach(nation -> {
                            Object[] rowdata = {
                                    Integer.toString(a[0]++),
                                    nation.getName(),
                                    nation.getType(),
                                    nation.getPopulation(),
                                    nation.getGdpRank()
                            };
                            tableModel.addRow(rowdata);
                        });
            }
        });

    }
    /**
     *
     * @return
     */
    JTable makeTable() {
        if ( jTable == null) {
            jTable = new JTable();

            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            tableModel.addColumn("번호");
            tableModel.addColumn("국가명");
            tableModel.addColumn("유형");
            tableModel.addColumn("인구수");
            tableModel.addColumn("GDP순위");

            jTable.getColumn("번호").setPreferredWidth(30);
            jTable.getColumn("국가명").setPreferredWidth(100);
            jTable.getColumn("유형").setPreferredWidth(30);
            jTable.getColumn("인구수").setPreferredWidth(30);
            jTable.getColumn("GDP순위").setPreferredWidth(30);

            CenterTableCellRenderer ctcr = new CenterTableCellRenderer();
            jTable.getColumn("번호").setCellRenderer(ctcr);
            jTable.getColumn("국가명").setCellRenderer(ctcr);
            jTable.getColumn("유형").setCellRenderer(ctcr);
            jTable.getColumn("인구수").setCellRenderer(ctcr);
            jTable.getColumn("GDP순위").setCellRenderer(ctcr);

            Stream<Nation> n = Nation.nations.stream();
            final int[] a = {1};
            n.forEach(nation -> {Object[] rowdata = {Integer.toString(a[0]), nation.getName(), nation.getType(), nation.getPopulation(), nation.getGdpRank()}; a[0]++; tableModel.addRow(rowdata);});

        }
        return jTable;
    }
    /**
     *
     */
    public class CenterTableCellRenderer extends JLabel implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setFont(new Font(null, Font.PLAIN, 12));
            setHorizontalAlignment(JLabel.CENTER);
            setOpaque(true);
            if(isSelected) { setBackground(Color.YELLOW); }
            else { setBackground(Color.WHITE); }
            return this;
        }
    }
    public static void main(String[] args) {
        new NationApp();

    }

}


