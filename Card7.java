import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//RANDOM
public class Card7 extends JPanel implements ActionListener {
    Dictionary dictionary;
    JButton randomBtn;
    JLabel result;

    Card7(Dictionary dict) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        result = new JLabel("");

        randomBtn = new JButton("Random");
        randomBtn.setActionCommand("random");
        randomBtn.addActionListener(this);

        dictionary = dict;

        add(Box.createVerticalGlue());
        add(result);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(randomBtn);
        add(Box.createVerticalGlue());

        result.setAlignmentX(Component.CENTER_ALIGNMENT);
        randomBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        TreeMap<String, Set<String>> randomResult = new TreeMap<>();
        randomResult = dictionary.random();
        String key = randomResult.firstKey();
        String meaning = "";
        int length = randomResult.get(randomResult.firstKey()).size();
        int i = 0;

        for (String itemMean : randomResult.get(randomResult.firstKey())) {
            meaning = meaning.concat(itemMean);
            i++;
            if (i < length) {
                meaning = meaning.concat(" ,  ");
            }
        }

        result.setText(key + ":  " + meaning);
        result.setFont(new Font("Arial", Font.PLAIN, 15));
    }
}