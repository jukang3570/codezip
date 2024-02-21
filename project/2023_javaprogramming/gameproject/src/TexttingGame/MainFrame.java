package TexttingGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainFrame extends JFrame {
	private Player player = new Player();
	private int score = 0;
	private MyPanel panel = new MyPanel();
	private ScorePanel scorePanel = new ScorePanel();
	private GamePanel gamePanel = new GamePanel(player, scorePanel);
	java.net.URL url =getClass().getClassLoader().getResource("bg.png");
	private ImageIcon image = new ImageIcon(url);
	private Image img = image.getImage();
	
	
	public MainFrame() {
		super("Typing game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setLayout(null);
		
		setSize(1000, 750);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	
	
	class MyPanel extends JPanel{
		
		private JTextField inputName = new JTextField();
		private JButton startBtn = new JButton("START");
		
		private String [] menu = {"LEVEL1","LEVEL2","LEVEL3"};
		private JComboBox<String> menuCombo = new JComboBox<String>(menu);
		
		@Override
		//그냥 배경화면 설정 하는 메소드
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this); // 화면에 꽉 차게 설정
			}
		public MyPanel() {
			this.setLayout(null);
			setLocationRelativeTo(null);
			inputName.setBounds(350, 500, 200, 60);
			inputName.setFont(new Font("Gothic", Font.BOLD, 20));
			startBtn.setBounds(550, 500, 100, 60);
			startBtn.setFont(new Font("Gothic", Font.BOLD, 20));
			menuCombo.setBounds(350, 550, 200, 60);
			menuCombo.setFont(new Font("Gothic", Font.BOLD, 20));
			add(inputName);
			add(startBtn);
			add(menuCombo);
			
			//시작버튼 누르면 게임 Start로 가는 이벤트 
			startBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {//player정보 저장
					player = new Player(inputName.getText(),
							menuCombo.getSelectedIndex()+1, score); 
					player.setName(inputName.getText()); //이름 저장
					player.setMenu(menuCombo.getSelectedIndex()+1); //선택한 Level 저장

					
					// gamePanel생성
					gamePanel = new GamePanel(player, scorePanel);
					Hidden();
					getContentPane().setLayout(new BorderLayout());
					makeSplitPane();
					repaint();
					
					gamePanel.gameStart(player);
				}
			});
	}
		public void Hidden() {
			
			inputName.setVisible(false);
			startBtn.setVisible(false);
			menuCombo.setVisible(false);
		}

		
		private void makeSplitPane() {
			JSplitPane hPane = new JSplitPane();
			hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			hPane.setDividerLocation(700);
			getContentPane().add(hPane, BorderLayout.CENTER);
			
			JSplitPane vPane = new JSplitPane();
			vPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			vPane.setDividerLocation(300);
			vPane.setTopComponent(scorePanel);
			
			hPane.setLeftComponent(gamePanel);
			hPane.setRightComponent(vPane);
		}
	}
	
	public static void main(String[] args) {
		new MainFrame();

	}
}
