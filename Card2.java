import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Card2 extends JPanel implements ActionListener {
    JTextField input;
    Dictionary dictionary;

    public Card2(Dictionary dict) {
        JTextField searchInput = new JTextField(20);
        // input.setActionCommand("input");
        JLabel title = new JLabel("Input 2");
        title.setLabelFor(searchInput);
        JPanel options = new JPanel(new FlowLayout());
        JButton bySlangWord = new JButton("By slang word");
        bySlangWord.setActionCommand("bySlangWord");
        bySlangWord.addActionListener(this);

        JButton byDefinition = new JButton("By definition");
        byDefinition.setActionCommand("byDefinition");
        byDefinition.addActionListener(this);

        options.add(bySlangWord);
        options.add(byDefinition);

        dictionary = dict;
        add(title);
        add(searchInput);
        add(options);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("bySlangWord")) {
            JTable resultTable = new JTable();
            TreeMap<String, Set<String>> searchResult = dictionary.search_BySlangWord(input.getText());
            Set<String> column = searchResult.keySet();
            // resultTable.addColumn(column);

        }
    }

}
