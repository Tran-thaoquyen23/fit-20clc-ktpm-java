import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class UI implements ActionListener {
    private static JFrame frame;
    JPanel cards;
    JLabel label;
    JPanel menuPanel, contentPanel;
    JButton searchBtn, addBtn, editBtn, deleteBtn, resetBtn, historyBtn, randomBtn, funQuestionBtn;
    JPanel searchContent;
    JPanel card1, card2, card3, card4, card5, card6, card7, card8;
    Dictionary dictionary;

    public UI() throws IOException {
        frame = new JFrame("20127305 - Dictionary App");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(650, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout(100, 10, 40));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        dictionary = new Dictionary();
        // dictionary.setVisible(false);

        menuPanel = new JPanel();
        // MenuItemListener menuItemListener = new MenuItemListener();

        // BoxLayout boxLayout = new BoxLayout(menu, BoxLayout.Y_AXIS);
        menuPanel.setLayout(new GridLayout(9, 1, 0, 10));
        menuPanel.setPreferredSize(new Dimension(120, 410));

        JLabel menuLabel = new JLabel("Menu", JLabel.CENTER);
        menuLabel.setLabelFor(menuPanel);
        menuLabel.setSize(100, 80);

        searchBtn = new JButton("Search");
        searchBtn.setActionCommand("search");
        // searchBtn.setBounds(50, 100, 200, 30);
        searchBtn.setBackground(Color.white);
        searchBtn.addActionListener(this);

        historyBtn = new JButton("History");
        historyBtn.setActionCommand("history");
        historyBtn.setPreferredSize(new Dimension(100, 20));
        historyBtn.addActionListener(this);

        addBtn = new JButton("Add");
        addBtn.setActionCommand("add");
        addBtn.setPreferredSize(new Dimension(100, 20));
        addBtn.addActionListener(this);

        editBtn = new JButton("Edit");
        editBtn.setActionCommand("edit");
        editBtn.setPreferredSize(new Dimension(100, 20));
        editBtn.addActionListener(this);

        deleteBtn = new JButton("Delete");
        deleteBtn.setActionCommand("delete");
        deleteBtn.setPreferredSize(new Dimension(100, 20));
        deleteBtn.addActionListener(this);

        resetBtn = new JButton("Reset");
        resetBtn.setActionCommand("reset");
        resetBtn.setPreferredSize(new Dimension(100, 20));
        resetBtn.addActionListener(this);

        randomBtn = new JButton("Random");
        randomBtn.setActionCommand("random");
        randomBtn.setPreferredSize(new Dimension(100, 20));
        randomBtn.addActionListener(this);

        funQuestionBtn = new JButton("Fun question");
        funQuestionBtn.setActionCommand("funQuestion");
        funQuestionBtn.setBounds(50, 100, 95, 20);
        funQuestionBtn.addActionListener(this);
        // funQuestionButton.setPreferredSize(new Dimension(100, 30));

        menuPanel.add(menuLabel);
        menuPanel.add(searchBtn);
        menuPanel.add(historyBtn);
        menuPanel.add(addBtn);
        menuPanel.add(editBtn);
        menuPanel.add(deleteBtn);
        menuPanel.add(resetBtn);
        menuPanel.add(randomBtn);
        menuPanel.add(funQuestionBtn);
        menuPanel.setBackground(Color.ORANGE);

        contentPanel = new JPanel(new CardLayout());
        contentPanel.setPreferredSize(new Dimension(480, 530));
        contentPanel.setBackground(Color.RED);
        card1 = new Card1(dictionary);
        card2 = new Card2(dictionary);
        card3 = new Card3(dictionary);
        contentPanel.add(card1, "search");
        contentPanel.add(card2, "history");
        contentPanel.add(card3, "add");
        // cards.add(card3);
        // cards.add(card4);
        // cards.add(card5);
        // cards.add(card6);
        // cards.add(card7);
        // cards.add(card8);

        frame.add(menuPanel);
        frame.add(contentPanel);

        frame.setVisible(true);

    }

    public void itemStateChanged(ItemEvent evt) {
        // card1 = new Card1(dictionary);
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }

    // chuyen card
    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, e.getActionCommand());
    }

    public static void main(String[] args) throws IOException {
        new UI();
        // ui.showMenu(frame);

    }
}
