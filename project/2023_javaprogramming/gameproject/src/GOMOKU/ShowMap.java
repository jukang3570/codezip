package GOMOKU;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ShowMap extends JPanel {
    private MapSize size;
    private GameMethod gm;
    private final int STONE_SIZE = 28; //돌 사이즈

    public ShowMap(MapSize m, GameMethod gm) {
        setBackground(new Color(206, 167, 61));
        size = m;
        setLayout(null);
        this.gm = gm;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        board(g);
        drawStone(g);
    }

    public void resetMap() {
        gm.reset(); // GameMethod 클래스에 reset() 메서드를 추가해야 함
        repaint();
    }

    public void board(Graphics g) {
        for (int i = 1; i <= size.getSize(); i++) {
            g.drawLine(size.getCell(), i * size.getCell(), size.getCell() * size.getSize(), i * size.getCell());
            g.drawLine(i * size.getCell(), size.getCell(), i * size.getCell(), size.getCell() * size.getSize());
        }
    }

    public void drawStone(Graphics g) {
        for (int y = 0; y < size.getSize(); y++) {
            for (int x = 0; x < size.getSize(); x++) {
                if (gm.getMap()[y][x] == 1)
                    drawBlack(g, x, y);
                else if (gm.getMap()[y][x] == 2)
                    drawWhite(g, x, y);
                else if (gm.getMap()[y][x] == 3)
                    drawRed(g, x, y);
                else if (gm.getMap()[y][x] == 4)
                    drawBlue(g, x, y);

            }
        }
    }

    public void drawRed(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        g.fillOval(x * size.getCell() + 15, y * size.getCell() - 15, STONE_SIZE, STONE_SIZE);
    }

    public void drawBlue(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillOval(x * size.getCell() + 15, y * size.getCell() - 15, STONE_SIZE, STONE_SIZE);
    }

    public void drawBlack(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillOval(x * size.getCell() + 15, y * size.getCell() - 15, STONE_SIZE, STONE_SIZE);
    }

    public void drawWhite(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x * size.getCell() + 15, y * size.getCell() - 15, STONE_SIZE, STONE_SIZE);
    }
}
