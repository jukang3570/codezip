package GOMOKU;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class MouseAction extends MouseAdapter{
    private GameMethod gm; 
    private MapSize m; 
    private ShowMap sm;
    private RunGame g;

    public MouseAction(GameMethod gm, MapSize m, ShowMap mm, RunGame g) {
        this.gm = gm;
        this.m = m;
        this.sm = mm;
        this.g = g;
    }

    @Override
    public void mousePressed(MouseEvent me) {
        int x = (int) Math.round(me.getX() / (double) m.getCell()) - 1;
        int y = (int) Math.round(me.getY() / (double) m.getCell()) - 2;

        if (x < 0 || x > 19 || y < 1 || y > 19) {
            return;
        }

        if (gm.checkInput(y, x) == false) {
            return;
        }

        Word w = new Word(y, x, gm.getCun_GamePlayer());
        gm.inputWord(w);
        gm.nextPlayer(gm.getCun_GamePlayer());
        sm.repaint();
        if (gm.endGame(w) == true) {
            String ms;
            if (w.getColor() == 1) {
                ms = "검돌 승리!";
            } else if (w.getColor() == 2) {
                ms = "백돌 승리!";
            } else if (w.getColor() == 3) {
                ms = "적돌 승리!";
            } else {
                ms = "청돌 승리!";
            }
            showWin(ms);
            gm.reset();
        }
    }

    public void showWin(String msg) {
        JOptionPane.showMessageDialog(g, msg, "", JOptionPane.INFORMATION_MESSAGE);
    }
}


// 이 클레스랑 showmap클레스는 마우스를 클릭하면 지정된 자표값을 Gamemethod로 전달하는 코드