import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

//RESET
public class Card6 extends JPanel implements ActionListener {
    Dictionary dictionary;
    JButton confirmBtn;
    DefaultTableModel dtb;

    Card6(Dictionary dict, DefaultTableModel dtb) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Reset");

        confirmBtn = new JButton("Confirm");
        confirmBtn.setActionCommand("confirm");
        confirmBtn.addActionListener(this);

        dictionary = dict;
        this.dtb = dtb;

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(confirmBtn);
        add(Box.createVerticalGlue());

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        dictionary.reset();
        JOptionPane.showMessageDialog(this, "Reset successfully", "Successful message",
                JOptionPane.INFORMATION_MESSAGE);

        this.dtb.setRowCount(0);
    }
}