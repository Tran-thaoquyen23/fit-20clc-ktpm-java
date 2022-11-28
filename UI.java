import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.io.IOException;

public class UI implements ActionListener {
    private static JFrame frame;
    JPanel cards;
    JLabel label;
    JPanel menuPanel, contentPanel;
    JButton searchBtn, addBtn, editBtn, deleteBtn, resetBtn, historyBtn, randomBtn, funQuestionBtn_slangWord,
            funQuestionBtn_definition;

    JButton buttons[];

    JPanel searchContent;
    JPanel cardDefault, card1, card2, card3, card4, card5, card6, card7, card8, card9;
    Dictionary dictionary;
    DefaultTableModel dtbh;

    public UI() throws IOException {
        frame = new JFrame("20127305 - Dictionary App");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(650, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout(100, 10, 40));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        dictionary = new Dictionary();
        dtbh = new DefaultTableModel();
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(10, 1, 0, 6));
        menuPanel.setPreferredSize(new Dimension(120, 410));

        JLabel menuLabel = new JLabel("Menu", JLabel.CENTER);
        menuLabel.setLabelFor(menuPanel);
        menuLabel.setSize(100, 80);

        searchBtn = new JButton("Search");
        searchBtn.setActionCommand("searchBtn");
        searchBtn.addActionListener(this);

        historyBtn = new JButton("History");
        historyBtn.setActionCommand("historyBtn");
        historyBtn.addActionListener(this);

        addBtn = new JButton("Add");
        addBtn.setActionCommand("addBtn");
        addBtn.addActionListener(this);

        editBtn = new JButton("Edit");
        editBtn.setActionCommand("editBtn");
        editBtn.addActionListener(this);

        deleteBtn = new JButton("Delete");
        deleteBtn.setActionCommand("deleteBtn");
        deleteBtn.addActionListener(this);

        resetBtn = new JButton("Reset");
        resetBtn.setActionCommand("resetBtn");
        resetBtn.addActionListener(this);

        randomBtn = new JButton("Random");
        randomBtn.setActionCommand("randomBtn");
        randomBtn.addActionListener(this);

        funQuestionBtn_slangWord = new JButton("Quiz 1");
        funQuestionBtn_slangWord.setActionCommand("funQuestionBtn_slangWord");
        funQuestionBtn_slangWord.addActionListener(this);

        funQuestionBtn_definition = new JButton("Quiz 2");
        funQuestionBtn_definition.setActionCommand("funQuestionBtn_definition");
        funQuestionBtn_definition.addActionListener(this);

        menuPanel.add(menuLabel);
        menuPanel.add(searchBtn);
        menuPanel.add(historyBtn);
        menuPanel.add(addBtn);
        menuPanel.add(editBtn);
        menuPanel.add(deleteBtn);
        menuPanel.add(resetBtn);
        menuPanel.add(randomBtn);
        menuPanel.add(funQuestionBtn_slangWord);
        menuPanel.add(funQuestionBtn_definition);

        contentPanel = new JPanel(new CardLayout());
        contentPanel.setPreferredSize(new Dimension(480, 430));
        contentPanel.setBackground(Color.RED);
        cardDefault = new CardDefault();
        card1 = new Card1(dictionary, dtbh);
        card2 = new Card2(dictionary, dtbh);
        card3 = new Card3(dictionary);
        card4 = new Card4(dictionary);
        card5 = new Card5(dictionary);
        card6 = new Card6(dictionary);
        card7 = new Card7(dictionary);
        card8 = new Card8(dictionary);
        card9 = new Card9(dictionary);

        contentPanel.add(cardDefault);
        contentPanel.add(card1, "searchBtn");
        contentPanel.add(card2, "historyBtn");
        contentPanel.add(card3, "addBtn");
        contentPanel.add(card4, "editBtn");
        contentPanel.add(card5, "deleteBtn");
        contentPanel.add(card6, "resetBtn");
        contentPanel.add(card7, "randomBtn");
        contentPanel.add(card8, "funQuestionBtn_slangWord");
        contentPanel.add(card9, "funQuestionBtn_definition");

        frame.add(menuPanel);
        frame.add(contentPanel);

        frame.setVisible(true);

        buttons = new JButton[9];
        buttons[0] = searchBtn;
        buttons[1] = addBtn;
        buttons[2] = editBtn;
        buttons[3] = deleteBtn;
        buttons[4] = resetBtn;
        buttons[5] = historyBtn;
        buttons[6] = randomBtn;
        buttons[7] = funQuestionBtn_slangWord;
        buttons[8] = funQuestionBtn_definition;
    }

    // chuyen card
    public void actionPerformed(ActionEvent e) {
        clearColorButton();

        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, e.getActionCommand());

        AbstractButton button = (AbstractButton) e.getSource();
        button.setBackground(Color.WHITE);
    }

    public void clearColorButton() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setBackground(null);
        }
    }

    public static void main(String[] args) throws IOException {
        new UI();
    }
}
