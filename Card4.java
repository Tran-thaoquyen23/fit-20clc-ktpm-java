import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;

//EDIT

public class Card4 extends JPanel implements ActionListener {
    JTextField slangWordInput;
    JTextField meaningInput;
    Dictionary dictionary;
    JRadioButton yesBtn, noBtn;
    JLabel notiOptions;
    JLabel message;
    JPanel optionFrame;

    public Card4(Dictionary dict) {
        dictionary = dict;

        // Input slang word
        JLabel slangWordTitle = new JLabel("Slang word");
        slangWordTitle.setLabelFor(slangWordInput);
        slangWordTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        slangWordInput = new JTextField();
        slangWordInput.setPreferredSize(new Dimension(300, 20));
        slangWordInput.setMaximumSize(new Dimension(300, 20));
        slangWordInput.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Input meaning
        JLabel meaningTitle = new JLabel("Meaning");
        meaningTitle.setLabelFor(meaningInput);
        meaningTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        meaningInput = new JTextField(30);
        meaningInput.setPreferredSize(new Dimension(300, 20));
        meaningInput.setMaximumSize(new Dimension(300, 20));
        meaningInput.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton addBtn = new JButton("Edit");
        addBtn.setActionCommand("edit");
        addBtn.addActionListener(this);
        addBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(slangWordTitle);
        topPanel.add(slangWordInput);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(meaningTitle);
        topPanel.add(meaningInput);
        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        topPanel.add(addBtn);
        topPanel.setPreferredSize(new Dimension(480, 150));

        message = new JLabel();

        optionFrame = new JPanel();
        optionFrame.setLayout(new BoxLayout(optionFrame, BoxLayout.Y_AXIS));

        notiOptions = new JLabel();
        notiOptions.setText("This word already exists in dictionary! Choose option: ");
        yesBtn = new JRadioButton("yes");
        noBtn = new JRadioButton("no");

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(yesBtn);
        btnGroup.add(noBtn);

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setActionCommand("confirm");
        confirmBtn.addActionListener(this);

        optionFrame.add(notiOptions);
        optionFrame.add(yesBtn);
        optionFrame.add(noBtn);
        optionFrame.add(Box.createRigidArea(new Dimension(0, 10)));
        optionFrame.add(confirmBtn);
        optionFrame.setVisible(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setPreferredSize(new Dimension(480, 100));
        bottomPanel.add(optionFrame);
        bottomPanel.add(message);

        JPanel wrap = new JPanel();
        wrap.setLayout(new BoxLayout(wrap, BoxLayout.Y_AXIS));
        wrap.add(Box.createVerticalGlue());
        wrap.add(topPanel);
        wrap.add(Box.createRigidArea(new Dimension(0, 20)));
        wrap.add(bottomPanel);
        wrap.add(Box.createVerticalGlue());

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createRigidArea(new Dimension(80, 0)));
        add(wrap);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String key = dictionary.checkExist(slangWordInput.getText());

        if (e.getActionCommand().equals("edit")) {
            if (key == null) {
                message.setText("This word doesn't exist in dictionary");
                optionFrame.setVisible(false);
            } else {
                message.setText("");
                optionFrame.setVisible(true);
            }
        } else if (e.getActionCommand().equals("confirm")) {
            Set<String> means = new HashSet<>();
            means.add(meaningInput.getText());

            if (yesBtn.isSelected()) {
                if (dictionary.edit(key, means)) {
                    slangWordInput.setText("");
                    message.setText("Edit successfully!");
                } else
                    message.setText("Edit failure!");
            }
            optionFrame.setVisible(false);
        }
    }
}