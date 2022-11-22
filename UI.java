import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class UI {
    private static JFrame frame;
    JLabel headerLabel;
    JLabel statusLabel;
    JPanel controlPanel;
    JPanel cards;

    public UI() {
        frame = new JFrame("20127305 - Dictionary App");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3, 1));
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        statusLabel.setSize(350, 100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        frame.add(headerLabel);
        frame.add(controlPanel);
        frame.add(statusLabel);
        frame.setVisible(true);
    }

    public void showMenu(Container contentPane) {
        final JMenuBar menuBar = new JMenuBar(); // tao menubar
        JMenu menu = new JMenu("Menu"); // add menuItem vao menubar

        // add cai item vao menu

        JMenuItem searchMenuItem = new JMenuItem("Search");
        // searchMenuItem.setMnemonic(KeyEvent.VK_N);
        searchMenuItem.setActionCommand("Search");
        JMenuItem historyMenuItem = new JMenuItem("History");
        historyMenuItem.setActionCommand("history");
        JMenuItem addMenuItem = new JMenuItem("Add");
        addMenuItem.setActionCommand("add");
        JMenuItem editMenuItem = new JMenuItem("Edit");
        editMenuItem.setActionCommand("edit");
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.setActionCommand("delete");
        JMenuItem resetMenuItem = new JMenuItem("Reset");
        resetMenuItem.setActionCommand("reset");
        JMenuItem randomMenuItem = new JMenuItem("Random");
        randomMenuItem.setActionCommand("random");
        JMenuItem funQuestionMenuItem = new JMenuItem("Fun question");
        funQuestionMenuItem.setActionCommand("funQuestion");

        MenuItemListener menuItemListener = new MenuItemListener();
        searchMenuItem.addActionListener(menuItemListener);
        historyMenuItem.addActionListener(menuItemListener);
        addMenuItem.addActionListener(menuItemListener);
        editMenuItem.addActionListener(menuItemListener);
        deleteMenuItem.addActionListener(menuItemListener);
        resetMenuItem.addActionListener(menuItemListener);
        randomMenuItem.addActionListener(menuItemListener);
        funQuestionMenuItem.addActionListener(menuItemListener);

        // them item vao menu
        menu.add(searchMenuItem);
        menu.add(historyMenuItem);
        menu.add(addMenuItem);
        menu.add(editMenuItem);
        menu.add(deleteMenuItem);
        menu.add(resetMenuItem);
        menu.add(randomMenuItem);
        menu.add(funQuestionMenuItem);

        // them menu vao menuBar;
        menuBar.add(menu);

        // them menuBar vao frame
        frame.add(menuBar);
        frame.setJMenuBar(menuBar);

        // ---------------create cards;
        JPanel searchCard = new JPanel();
        searchCard.add(new Label("Search by slang word"));
        searchCard.add(new JButton("Submit"));

        JPanel historyCard = new JPanel();
        historyCard.add(new JTextField("History for seach"));

        cards = new JPanel(new CardLayout());
        cards.add(searchCard);
        cards.add(historyCard);

        searchMenuItem.add(cards, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }

    class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            statusLabel.setText(e.getActionCommand() + " JMenuItem clicked.");
        }
    }

    class menuItem implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String item = e.getActionCommand();
            if (item.equals("search")) {

            }
        }
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.showMenu(frame);

    }
}
