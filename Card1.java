import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.*;

// SEARCH
public class Card1 extends JPanel implements ActionListener {
    JTextField input;
    Dictionary dictionary;
    JTable resultTable;
    JScrollPane sp;
    DefaultTableModel dtb, dtbh;

    public Card1(Dictionary dict, DefaultTableModel dtbh) {
        this.dtbh = dtbh;
        input = new JTextField(20);
        JLabel title = new JLabel("Input the slang word");
        title.setLabelFor(input);
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
        add(input);
        add(options);

        dtb = new DefaultTableModel();
        resultTable = new JTable(dtb);
        String row[] = new String[2];
        row[0] = "Slang_word";
        row[1] = "Meaning";
        dtb.addColumn(row[0]);
        dtb.addColumn(row[1]);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        sp = new JScrollPane(resultTable);
        sp.setPreferredSize(new Dimension(400, 347));

        add(sp);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("bySlangWord")) {
            search(dictionary, 1);
            input.setText("");
        } else if (e.getActionCommand().equals("byDefinition")) {
            search(dictionary, 2);
            input.setText("");
        }
    }

    public void search(Dictionary dictionary, int type) {
        String row[] = new String[2];

        TreeMap<String, Set<String>> searchResult;
        if (type == 1) {
            searchResult = dictionary.search_BySlangWord(input.getText());
        } else {
            searchResult = dictionary.search_ByDefinition(input.getText());
        }
        Set<String> keys = searchResult.keySet();
        String mean = "";
        dtb.setRowCount(0);

        for (String item : keys) {
            row[0] = item;
            Set<String> meanings = searchResult.get(item);
            int length = meanings.size();
            int count = 0;
            for (String meaning : meanings) {
                mean = mean.concat(meaning);
                count++;
                if (count < length) {
                    mean = mean.concat(" ,  ");
                }
            }
            row[1] = mean;
            dtb.addRow(row);
            dtbh.addRow(row);
            mean = "";
        }
    }
};