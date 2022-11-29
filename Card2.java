import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//HISTORY
public class Card2 extends JPanel {
    Dictionary dictionary;
    JScrollPane sp;
    DefaultTableModel dtb;

    public Card2(Dictionary dict, DefaultTableModel dtb) {
        JLabel title = new JLabel("History");
        title.setLabelFor(this);
        dictionary = dict;

        this.dtb = dtb;
        JTable HistoryTable = new JTable(dtb);
        String row[] = new String[2];
        row[0] = "Slang_word";
        row[1] = "Meaning";
        dtb.addColumn(row[0]);
        dtb.addColumn(row[1]);

        HistoryTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        HistoryTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        sp = new JScrollPane(HistoryTable);
        sp.setPreferredSize(new Dimension(470, 365));
        sp.setMaximumSize(new Dimension(470, 365));
        sp.setVisible(true);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(sp);
        add(Box.createVerticalGlue());

        title.setFont(new Font("Arial", Font.BOLD, 17));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        sp.setAlignmentX(Component.CENTER_ALIGNMENT);
        setVisible(true);
    }
}
