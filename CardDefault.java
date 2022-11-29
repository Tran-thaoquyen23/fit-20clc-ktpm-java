import java.awt.*;
import javax.swing.*;

// DEFAULT CONTENT PANEL
public class CardDefault extends JPanel {
    JLabel title, name, id;

    CardDefault() {
        JPanel wrap = new JPanel();
        wrap.setLayout(new BoxLayout(wrap, BoxLayout.Y_AXIS));

        title = new JLabel("Project 1 - DICTIONARY APP");
        name = new JLabel("Name: Tran Thao Quyen");
        id = new JLabel("ID: 20127305");

        title.setFont(new Font("Arial", Font.PLAIN, 25));
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        id.setFont(new Font("Arial", Font.PLAIN, 20));

        wrap.add(title);
        wrap.add(name);
        wrap.add(id);

        JPanel wrapp = new JPanel();
        wrapp.setLayout(new BoxLayout(wrapp, BoxLayout.Y_AXIS));
        wrapp.add(Box.createVerticalGlue());
        wrapp.add(wrap);
        wrapp.add(Box.createVerticalGlue());

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createRigidArea(new Dimension(80, 0)));
        add(wrapp);
    }
}
