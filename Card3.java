import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.Position;

import java.awt.event.*;
import java.util.*;

public class Card3 extends JPanel implements ActionListener {
    JTextField input;
    Dictionary dictionary;

    public Card3(Dictionary dict) {
        JPanel input = new JPanel();
        // input.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField slangword = new JTextField(20);
        // input.setActionCommand("input");
        JLabel slangWordTitle = new JLabel("Slang word");
        slangWordTitle.setLabelFor(slangword);

        JPanel input2 = new JPanel();

        JTextField meaning = new JTextField(30);
        // input.setActionCommand("input");
        JLabel meaningTitle = new JLabel("Meaning");
        meaningTitle.setLabelFor(meaning);
        // JPanel options = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add");
        addBtn.setActionCommand("add");
        addBtn.addActionListener(this);

        dictionary = dict;
        input.add(slangWordTitle);
        input.add(slangword);

        input2.add(meaningTitle);
        input2.add(meaning);

        add(input);
        add(input2);

        add(addBtn);
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