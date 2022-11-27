import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.*;

public class Card1 extends JPanel implements ActionListener {
    JTextField input;
    Dictionary dictionary;
    JTable resultTable;
    JButton retry;
    JScrollPane sp;

    public Card1(Dictionary dict) {
        input = new JTextField(20);
        // input.setActionCommand("input");
        JLabel title = new JLabel("Input 1");
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

        resultTable = new JTable();
        resultTable = new JTable();
        DefaultTableModel dtb = (DefaultTableModel) resultTable.getModel();
        String row[] = new String[2];
        row[0] = "Slang_word";
        row[1] = "Meaning";
        dtb.addColumn(row[0]);
        dtb.addColumn(row[1]);
        resultTable.setSize(350, 50);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        // Frame Size
        resultTable.setVisible(false);
        add(resultTable);
        JButton retry = new JButton("Retry");
        retry.setActionCommand("retry");
        retry.addActionListener(this);
        // sp = new JScrollPane(resultTable);

        add(retry);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("bySlangWord")) {
            // remove(sp);
            // resultTable.setVisible(false);
            search(dictionary, 1);
        } else if (e.getActionCommand().equals("byDefinition")) {
            resultTable.setVisible(false);
            search(dictionary, 2);
        }
    }

    public void search(Dictionary dictionary, int type) { // type: 1: slang word 2: definition
        String row[] = new String[2];

        // result.setPreferredSize(new Dimension(450, 400));
        DefaultTableModel dtb = (DefaultTableModel) resultTable.getModel();
        // dtb.addColumn(row[1]);
        // dtb.addRow(row);
        TreeMap<String, Set<String>> searchResult;
        if (type == 1) {
            searchResult = dictionary.search_BySlangWord(input.getText());
        } else {
            searchResult = dictionary.search_ByDefinition(input.getText());
        }
        Set<String> keys = searchResult.keySet();
        String mean = "";
        for (String item : keys) {
            row[0] = item;
            Set<String> meanings = searchResult.get(item);
            int length = meanings.size();
            int count = 0;
            for (String meaning : meanings) {
                mean = mean.concat(meaning);
                count++;
                if (count < length) {
                    mean.concat("| ");
                }
            }
            row[1] = mean;
            System.out.println("mean: " + row[1]);
            dtb.addRow(row);
            mean = "";
        }
        resultTable.setVisible(true);
        sp = new JScrollPane(resultTable);
        add(sp);

    }

}
