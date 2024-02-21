package TexttingGame;
import javax.swing.*;
import java.awt.*;


public class ScorePanel extends JPanel {
	private GamePanel gamePanel = new GamePanel();
	private int score = 0;
	private int life = 5;
	private String playerName;
	private Player player = new Player();
	private JLabel textLabel = new JLabel("Score");
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private JLabel [] lifeLabel = new JLabel[life];
	
	private JLabel warningLabel = new JLabel("<html>생명이 모두 없어지면<br> GAME OVER</html>");
	java.net.URL url =getClass().getClassLoader().getResource("heart.png");
	private ImageIcon lifeIcon = new ImageIcon(url);
	
	
	public ScorePanel() {
		setLayout(null);
		setBackground(new Color(217, 238, 251));
		
		warningLabel.setFont(new Font("Gothic",Font.BOLD,20));
		warningLabel.setSize(200,50);
		warningLabel.setLocation(35,130);
		warningLabel.setForeground(new Color(130, 160, 170));
		
		textLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
		textLabel.setSize(80, 30);
		textLabel.setLocation(20, 240);
		
		scoreLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
		scoreLabel.setSize(80, 30);
		scoreLabel.setLocation(100, 240);
		
		for (int i=0; i<life; i++) {
			
			lifeLabel[i] = new JLabel(lifeIcon);
			lifeLabel[i].setSize(lifeIcon.getIconWidth(),lifeIcon.getIconHeight());
			lifeLabel[i].setLocation(40*i+40,50);
			
			add(lifeLabel[i]);
		}

		add(textLabel);
		add(scoreLabel);
		add(warningLabel);
	}
	
	public void increase(Player player) {
		score += 10;
		player.setScore(score);
		scoreLabel.setText(Integer.toString(score));
		scoreLabel.getParent().repaint();
	}
	
	public void decrease(Player player) {
		score -= 10;
		player.setScore(score);
		scoreLabel.setText(Integer.toString(score));
		scoreLabel.getParent().repaint();
	}
	//점수 업데이트하는 메소드
	public void repaintScore() {
		scoreLabel.getParent().repaint();
	}
	
	public void initPlayerInfo(String name, int menu, int score) {
		player = new Player(name, menu, score);

	}
	
	synchronized boolean decreaseLife(Player player) {
		life--;
		boolean isTrue = false;
		
		switch(life) {
		case 4: 
			lifeLabel[0].setVisible(false);
			break;
		case 3: 
			lifeLabel[1].setVisible(false);
			break;
		
		case 2: 
			lifeLabel[2].setVisible(false);
			break;
		case 1: 
			lifeLabel[3].setVisible(false);
			break;
		
		case 0: 
			lifeLabel[4].setVisible(false);
			// 현재 Panel안보이게
			warningLabel.setText("GAME OVER");
			warningLabel.setLocation(100,100);
			
			// 게임 종료 후 정보 저장
			Player p = new Player(player.getName(), player.getMenu(),
					player.getScore());
			
			
			// 종료할것인지 물어보는 JOptionPane
			String [] answer = {"종료", "다시시작"};
			int choice = JOptionPane.showOptionDialog(gamePanel, player.getName()+ "은(는) " + player.getScore() + "점 입니다.\n게임을 종료하시겠습니까?",
					"게임 종료", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null);
			
			if(choice == 0) { // "종료" 선택. 창 종
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
			else if(choice == 1) { // "다시시작" 선택. 시작화면으로 이동
			
				MainFrame game = new MainFrame();
				isTrue = true;
			}
			
			break;
		}
		return isTrue;
	}
}