package Galag;

import javax.swing.*;

import java.util.Vector;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class main extends JFrame implements KeyListener {
	private int life = 5;
	private int playerX = 400;
	private int playerY = 800;
	private int playerSpeed = 10;
	private int Missilex;
	private int Missiley = 780;
	private int enemycount = 5;
	private Image player;
	private Image enemy;
	private Image mg;
	private int[] enemystatus = { 1, 1, 1, 1, 1 };
	private int[] enemyX = { 0, 170, 370, 570, 770 };
	private int[] enemyY = { 10, 60, 120, 180, 240 };
	Vector<Integer> missileX = new Vector<Integer>(5);
	Vector<Integer> missileY = new Vector<Integer>(5);
	Thread missilemove = new MissileMoveThread();
	Thread enemymove = new enemy();

	public main() {
		java.net.URL url =getClass().getClassLoader().getResource("player.png");
		java.net.URL url1 =getClass().getClassLoader().getResource("enemy.png");
		java.net.URL url2 =getClass().getClassLoader().getResource("laser.png");
		setTitle("galag");
		player = Toolkit.getDefaultToolkit().getImage(url);
		enemy = Toolkit.getDefaultToolkit().getImage(url1);
		mg = Toolkit.getDefaultToolkit().getImage(url2);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				enemymove.run();
			}
		});
		Thread g = new Thread(new Runnable() {
			@Override
			public void run() {
				missilemove.run();
			}
		});
		g.start();
		t.start();
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(player, playerX, playerY, 30, 30, null);
				for (int i = 0; i < enemyX.length; i++) {
					if (enemystatus[i] == 1) {
						g.drawImage(enemy, enemyX[i], enemyY[i], 30, 30, null);
					}
					for (int i1 = 0; i1 < missileX.size(); i1++) {
						g.drawImage(mg, missileX.get(i1), missileY.get(i1), 5, 10, null);
					}
				}
			}
		};
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 900);
		setVisible(true);
		addKeyListener(this);
	}

	@Override
	public void paint(Graphics g) {

	}

	public class enemy extends Thread {
		synchronized void enmeymove() {
			for (int i = 0; i < enemyX.length; i++) {
				Random random = new Random();
				int choice = random.nextInt(100) % 2;
				if (enemystatus[i] == 1) {
					if (choice == 0) {
						if (enemyX[i] < 760) {
							enemyX[i] += 30;
						}
					} else if (choice == 1) {
						if (enemyX[i] > 1) {
							enemyX[i] += -30;
						}
					}
					try {
						Thread.sleep(100); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public void run() {
			while (true) {
				enmeymove();
				getContentPane().repaint();
				try {
					sleep(20);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			if(playerX>5)
				playerX -= playerSpeed;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			if(playerX<750)
				playerX += playerSpeed;
		} else if (keyCode == KeyEvent.VK_SPACE) {
			missileX.add(playerX + 10);
			missileY.add(Missiley);
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public void run() {
		while (true) {
			enemymove.run();
			getContentPane().repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public class MissileMoveThread extends Thread {
		synchronized void dropWord() {
			for (int i = 0; i < missileX.size(); i++) {
				int y = missileY.get(i) - 5; 
				missileY.set(i, y); 

				for (int j = 0; j < enemyX.length; j++) {
					Rectangle playerRect = new Rectangle(missileX.get(i), missileY.get(i), 5, 10);
					Rectangle enemyRect = new Rectangle(enemyX[j], enemyY[j], 30, 30);
					if (playerRect.intersects(enemyRect) && enemystatus[j] == 1) {
						enemystatus[j] = 0;
						missileX.remove(i);
						missileY.remove(i);
						enemycount--;
						break;
					}
				}
				if (missileY.get(i) >= 790) {
					missileX.remove(i);
					missileY.remove(i);
				}
				if (enemycount == 0) {
					gameover gameover = new gameover();
					gameover.gameover();
				}
			}
		}

		public void run() {
			while (true) {
				dropWord();
				getContentPane().repaint();
				try {
					sleep(20);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public class gameover extends JPanel {
		public void gameover() {
			enemymove.interrupt();
			missilemove.interrupt();
			JOptionPane.showMessageDialog(this, "THANK YOU");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		main game = new main();
		game.run();
	}
}