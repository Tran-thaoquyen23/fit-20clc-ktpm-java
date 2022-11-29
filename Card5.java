import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//delete

public class Card5 extends JPanel implements ActionListener {
    JTextField slangWordInput;
    Dictionary dictionary;
    JRadioButton yesBtn, noBtn;
    JLabel notiOptions;
    JLabel message;
    JPanel optionFrame;

    public Card5(Dictionary dict) {
        dictionary = dict;

        JLabel slangWordTitle = new JLabel("Slang word");
        slangWordTitle.setLabelFor(slangWordInput);
        slangWordTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        slangWordInput = new JTextField();
        slangWordInput.setPreferredSize(new Dimension(300, 20));
        slangWordInput.setMaximumSize(new Dimension(300, 20));
        slangWordInput.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setActionCommand("delete");
        deleteBtn.addActionListener(this);
        deleteBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(slangWordTitle);
        topPanel.add(slangWordInput);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(deleteBtn);

        topPanel.setPreferredSize(new Dimension(480, 80));

        message = new JLabel();

        optionFrame = new JPanel();
        optionFrame.setLayout(new BoxLayout(optionFrame, BoxLayout.Y_AXIS));

        notiOptions = new JLabel();

        notiOptions.setText("Are you sure delete this word?");
        yesBtn = new JRadioButton("Yes");
        noBtn = new JRadioButton("No");

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

        if (e.getActionCommand().equals("delete")) {
            if (key == null) {
                slangWordInput.setText("");
                message.setText("This word doesn't exist in dictionary");
                message.setText("");
                optionFrame.setVisible(false);
            } else {
                message.setText("");
                optionFrame.setVisible(true);
            }
        } else if (e.getActionCommand().equals("confirm")) {
            if (yesBtn.isSelected()) {
                dictionary.delete(key);
                JOptionPane.showMessageDialog(this, "Delete successfully", "Successful message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            slangWordInput.setText("");
            optionFrame.setVisible(false);
        }
    }
}