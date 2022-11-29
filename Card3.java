import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

//ADD

public class Card3 extends JPanel implements ActionListener {
    JTextField slangWordInput;
    JTextField meaningInput;
    Dictionary dictionary;
    JRadioButton overwrite, duplicate;
    JLabel notiOptions;
    JLabel message;
    JPanel optionFrame;

    public Card3(Dictionary dict) {
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

        JButton addBtn = new JButton("Add");
        addBtn.setActionCommand("add");
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
        overwrite = new JRadioButton("Overwrite");
        duplicate = new JRadioButton("Duplicate");

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(overwrite);
        btnGroup.add(duplicate);

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setActionCommand("confirm");
        confirmBtn.addActionListener(this);

        optionFrame = new JPanel();
        optionFrame.setLayout(new BoxLayout(optionFrame, BoxLayout.Y_AXIS));

        optionFrame.add(notiOptions);
        optionFrame.add(overwrite);
        optionFrame.add(duplicate);
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
        setVisible(true);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createRigidArea(new Dimension(80, 0)));
        add(wrap);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String key = dictionary.checkExist(slangWordInput.getText());

        if (e.getActionCommand().equals("add")) {
            if (key == null) {
                Set<String> means = new HashSet<>();
                means.add(meaningInput.getText());
                key = slangWordInput.getText();
                dictionary.add(key, means);
                JOptionPane.showMessageDialog(this, "Add successfully", "Successful message",
                        JOptionPane.INFORMATION_MESSAGE);
                slangWordInput.setText("");
                meaningInput.setText("");
                optionFrame.setVisible(false);
            } else {
                optionFrame.setVisible(true);
                message.setText("");
            }
        } else if (e.getActionCommand().equals("confirm")) {
            Set<String> means = new HashSet<>();
            means.add(meaningInput.getText());

            if (duplicate.isSelected()) {
                dictionary.add_duplicate(key, meaningInput.getText());
            }
            if (overwrite.isSelected()) {
                dictionary.add_overwrite(key, means);
            }
            JOptionPane.showMessageDialog(this, "Add successfully", "Successful message",
                    JOptionPane.INFORMATION_MESSAGE);
            optionFrame.setVisible(false);
            slangWordInput.setText("");
            meaningInput.setText("");
        }
    }
}