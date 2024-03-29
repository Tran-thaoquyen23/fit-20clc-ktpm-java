import java.awt.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Card9 extends JPanel implements ActionListener {
    Dictionary dictionary;
    JPanel panel1, panel2, panel3;
    JButton funQuesBtn, submitBtn, nextBtn;
    JLabel title;
    JLabel questionLabel;
    String result;
    JRadioButton a, b, c, d;
    JLabel message;
    boolean flag = false;
    ButtonGroup bg;

    Card9(Dictionary dict) {
        dictionary = dict;

        title = new JLabel("Question about definition");

        funQuesBtn = new JButton("Question");
        funQuesBtn.setActionCommand("funQues");
        funQuesBtn.addActionListener(this);

        questionLabel = new JLabel("");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        a = new JRadioButton("");
        b = new JRadioButton("");
        c = new JRadioButton("");
        d = new JRadioButton("");

        message = new JLabel("");
        message.setFont(new Font("Arial", Font.PLAIN, 15));

        bg = new ButtonGroup();
        bg.add(a);
        bg.add(b);
        bg.add(c);
        bg.add(d);

        submitBtn = new JButton("Submit");
        submitBtn.setActionCommand("submit");
        submitBtn.addActionListener(this);

        nextBtn = new JButton("Next");
        nextBtn.setActionCommand("funQues");
        nextBtn.addActionListener(this);

        JPanel button = new JPanel();
        button.setLayout(new BoxLayout(button, BoxLayout.X_AXIS));
        button.add(Box.createHorizontalGlue());
        button.add(submitBtn);
        button.add(Box.createRigidArea(new Dimension(10, 0)));
        button.add(nextBtn);
        button.add(Box.createHorizontalGlue());

        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(title);
        panel1.add(Box.createRigidArea(new Dimension(0, 10)));
        panel1.add(funQuesBtn);

        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        funQuesBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setMinimumSize(new Dimension(300, 150));
        questionPanel.setPreferredSize(new Dimension(300, 150));
        questionPanel.setMaximumSize(new Dimension(300, 150));

        questionPanel.add(Box.createVerticalGlue());
        questionPanel.add(questionLabel);
        questionPanel.add(a);
        questionPanel.add(b);
        questionPanel.add(c);
        questionPanel.add(d);
        questionPanel.add(Box.createVerticalGlue());

        JPanel wrapQuestionPanel = new JPanel();
        wrapQuestionPanel.setLayout(new BoxLayout(wrapQuestionPanel, BoxLayout.X_AXIS));
        wrapQuestionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        wrapQuestionPanel.add(questionPanel);
        wrapQuestionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(wrapQuestionPanel);
        panel2.add(Box.createRigidArea(new Dimension(0, 20)));
        panel2.add(button);
        panel2.add(Box.createRigidArea(new Dimension(0, 20)));

        panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(100, 23));
        panel3.setMaximumSize(new Dimension(100, 23));
        panel3.add(message);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        add(panel1);
        add(panel2);
        add(panel3);
        add(Box.createVerticalGlue());
        setVisible(true);

        panel1.setVisible(true);
        panel2.setVisible(false);
        message.setVisible(true);

        panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("funQues")) {
            List<String> funQ_slangWord = new ArrayList<>();
            funQ_slangWord = dictionary.funQuestionDefinition();
            questionLabel.setText(funQ_slangWord.get(0));

            bg.clearSelection();

            a.setText(funQ_slangWord.get(1));
            b.setText(funQ_slangWord.get(2));
            c.setText(funQ_slangWord.get(3));
            d.setText(funQ_slangWord.get(4));
            result = funQ_slangWord.get(5);

            // panel1.setVisible(true);
            funQuesBtn.setVisible(false);
            title.setVisible(true);
            panel2.setVisible(true);
            message.setText("");
        }
        if (e.getActionCommand().equals("submit")) {
            if (a.isSelected()) {
                if (a.getText().equals(result)) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            if (b.isSelected()) {
                if (b.getText().equals(result)) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            if (c.isSelected()) {
                if (c.getText().equals(result)) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            if (d.isSelected()) {
                if (d.getText().equals(result)) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            if (flag) {
                message.setText("Bingo!");
                message.setForeground(Color.GREEN);
            } else {
                message.setText("Wrong!");
                message.setForeground(Color.RED);
            }
        }
    }
}
