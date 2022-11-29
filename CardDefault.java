import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

// DEFAULT CONTENT PANEL
public class CardDefault extends JPanel {
    JLabel title, name, id;

    CardDefault() {
        JPanel wrap = new JPanel();
        wrap.setLayout(new BoxLayout(wrap, BoxLayout.Y_AXIS));

        title = new JLabel("Project 1 - DICTIONARY APP");
        name = new JLabel("Name: Tran Thao Quyen");
        id = new JLabel("ID: 20127305");

        title.setFont(new Font("Arial", Font.BOLD, 25));
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        id.setFont(new Font("Arial", Font.PLAIN, 20));

        wrap.add(Box.createRigidArea(new Dimension(0, 10)));
        wrap.add(title);
        wrap.add(Box.createRigidArea(new Dimension(0, 30)));
        wrap.add(name);
        wrap.add(id);
        wrap.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel wrapp = new JPanel();
        wrapp.setPreferredSize(new Dimension(450, 361));
        wrapp.setMaximumSize(new Dimension(450, 361));
        wrapp.setLayout(new BoxLayout(wrapp, BoxLayout.X_AXIS));
        wrapp.add(Box.createHorizontalGlue());
        wrapp.add(wrap);
        wrapp.add(Box.createHorizontalGlue());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        wrapp.setBorder(blackline);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 55)));
        add(wrapp);
    }
}
