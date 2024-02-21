package GOMOKU;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RunGame extends JFrame {

    private Container c;
    MapSize size = new MapSize();
    GameMethod gm = new GameMethod();
    ShowMap m;
    MouseAction Mc;

    public RunGame() {
        setTitle("GOMOKU");
        createMenu();
        setBounds(200, 20, 650, 750);
        c = getContentPane();
        c.setLayout(null);
        m = new ShowMap(size, gm);
        setContentPane(m);
        Mc = new MouseAction(gm, size, m, this);
        addMouseListener(Mc);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "2인":
                    gm.setGameMode(2);
                    resetGame();
                    break;
                case "3인":
                    gm.setGameMode(3);
                    resetGame();
                    break;
                case "4인":
                    gm.setGameMode(4);
                    resetGame();
                    break;
            }
        }
    }

    public void createMenu() {
        JMenuBar mb = new JMenuBar();
        JMenuItem[] menuItem = new JMenuItem[3];
        String[] itemTitle = {"2인", "3인", "4인"};
        JMenu screenMenu = new JMenu("모드");
        MenuActionListener listener = new MenuActionListener();
        for (int i = 0; i < menuItem.length; i++) {
            menuItem[i] = new JMenuItem(itemTitle[i]);
            menuItem[i].addActionListener(listener);
            screenMenu.add(menuItem[i]);
        }
        mb.add(screenMenu);
        setJMenuBar(mb);
    }

    private void resetGame() {
        gm.init();
        m.resetMap(); // ShowMap 클래스에 resetMap() 메서드를 추가해야 함
        gm.reset(); // Gamemethod 클래스에 reset() 메서드를 추가해야 함
    }
}
