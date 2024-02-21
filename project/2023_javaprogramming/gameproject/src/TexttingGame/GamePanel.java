package TexttingGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class GamePanel extends JPanel {
	
	//텍스트를 입력하고 표시
	private JLabel textLabel = new JLabel(); //표시
	private JTextField tf = new JTextField(10); //입력
	
	//생성된 단어를 저장하는 벡터
	//새롭게 생성된 단어의 정보를 추가하고 단어가 제거되는 경우 targetvector에서 
	//삭제하기위함.
	private Vector<JLabel> targetVector = new Vector<JLabel>();
	
	//word.txt에서 단어를 무작위로 내보내는 객체
	private WordVector wVector = new WordVector();
	
	private GroundPanel groundPanel = new GroundPanel(); //단어가 표시되는 패널
	private ScorePanel scorePanel = null; //점수 표시하는 패널
	private InputPanel inputPanel = new InputPanel(); //단어를 입력하는 패널
	
	private Player player = new Player(); //player 정보
	
	// 단어를 생성하는 스레드
	private GenerateWordThread generateWordThread = new GenerateWordThread(targetVector, player);
	// 단어를 떨어뜨리는 스레드
	private DropWordThread dropWordThread = new DropWordThread(targetVector,player);
	// 땅에 닿은 단어 감지하는 스레드
	private DetectBottomThread detectBottomThread = new DetectBottomThread(targetVector);
		
	//난이도 조절
	//단어 생성 속도 배열
	private int [] generationSpeed = {5000,3000,1000}; 
	//단어를 떨어뜨리는 속도 배열
	private int [] droppingSpeed = {500,300,100};
	
	
	public GamePanel() {
		
	}
	
	public GamePanel(Player player, ScorePanel scorePanel) {//게임이 진행되는 패널 (단어가 위에서 내려오는 패널이라고 생각하면 됨)
		this.player = player;
		this.scorePanel = scorePanel;
		
		//스레드 생성자
		generateWordThread = new GenerateWordThread(targetVector, player);
		dropWordThread = new DropWordThread(targetVector,player);
		wVector = new WordVector();//단어 벡터
		
		setLayout(new BorderLayout());
		add(groundPanel, BorderLayout.CENTER); // 단어 생성 패널
		add(inputPanel, BorderLayout.SOUTH); // 텍스트 입력 패널
		
		tf.setFont(new Font("함초롱바탕", Font.BOLD, 30));
		
		tf.addActionListener(new ActionListener() { // 텍스트 입력하고 enter 누르면 실행
			//Text를 입력하고 enter를 누를 때 발생하는 메소드
			
			public void actionPerformed(ActionEvent e) {
				synchronized(targetVector) { 
					JTextField t = (JTextField)e.getSource(); //입력한 텍스트를 가져와 저장
					
					for (int i=0; i < targetVector.size(); i++) {
						String text = targetVector.get(i).getText();
						if(text.equals(t.getText())) { //점수 증가 
							scorePanel.increase(player);
							scorePanel.repaintScore();
							groundPanel.remove(targetVector.get(i)); //맞춘 단어 삭제
							targetVector.remove(i);
							t.setText(null); //텍스트 필드에서 입력한 단어 삭제 
							//틀릴 때 마다 속도 증가
							if (droppingSpeed[player.getMenu()-1] > 2)
								droppingSpeed[player.getMenu()-1]--;
							if (generationSpeed[player.getMenu()-1] > 20)
								generationSpeed[player.getMenu()-1] -= 10;
							break;
	
						}
						if((i == (targetVector.size() - 1)) && !text.equals(t.getText())) {
							// 점수 감소
							scorePanel.decrease(player);
							scorePanel.repaintScore();
							t.setText(null);
						}
						t.requestFocus();
					}
				}
			}
		});
	}
	
	class InputPanel extends JPanel { // 단어 입력 
		public InputPanel() {
			setLayout(new FlowLayout());
			this.setBackground(new Color(245, 225, 221));
			add(tf);
		}
	}
	
	class GroundPanel extends JPanel { // 단어 생성
		
		public GroundPanel() {
			setLayout(null);
			this.setBackground(new Color(253, 242, 208));
		}
	}
	
	public void gameStart(Player player) { //게임을 시작
		//player에 정보 저장
		this.player = player;

		// 단어생성 시작
		generateWordThread.start();
		// 단어 떨어뜨리기 시작
		dropWordThread.start();
		// 땅에 닿은 단어 감지 시작
		detectBottomThread.start();
	}
	
	public void gameOver() { // 게임종료
		// 단어생성 중단
		generateWordThread.interrupt();
		// 단어 떨어뜨리기 중단
		dropWordThread.interrupt();
		// 땅에 닿은 단어 감지 중단
		detectBottomThread.interrupt();
	}
	
	public class GenerateWordThread extends Thread{ //단어 생성 스레드
		
		private Vector<JLabel> targetVector = null; 
		private Player player = null;
		
		// 단어 가져와 Label설정, 부착하는 메소드
		synchronized void generateWord(Player player) {
			JLabel targetLabel = new JLabel("");
			
			// 단어 한 개 선택해서 label에 표시
			String newWord = wVector.getWord();
			targetLabel.setText(newWord); 
			
			// targetLabel 모양
			targetLabel.setHorizontalAlignment(JLabel.CENTER); // JLabel 가운데정렬
			targetLabel.setSize(200, 40);
			targetLabel.setFont(new Font("함초롱바탕", 1, 21));
			
			// x좌표 랜덤 설정
			int startX = (int) (Math.random()*groundPanel.getWidth());
			while(true) {
				if ((startX + targetLabel.getWidth()) > groundPanel.getWidth()) 
					startX = (int) (Math.random()*groundPanel.getWidth());
				else
					break;
			}
			
			targetLabel.setLocation(startX,0);
			
			targetLabel.setOpaque(false); // 배경 투명하게
			targetVector.addElement(targetLabel); // targetVector에 생성한 newWord 추가
			groundPanel.add(targetLabel);
		}
		
		public GenerateWordThread(Vector<JLabel>targetVector, Player player) {
			this.targetVector = targetVector;
			this.player = player;
		}
		
		@Override
		public void run() {
			while(true) {
				int generationTime = generationSpeed[player.getMenu()-1];
				generateWord(player); //단어 생성
				groundPanel.repaint(); //다시 패널 호출
				try { //일정한 간격으로 스레드를 정지
		 		sleep(generationTime);
				} catch (InterruptedException e) {
					return;
				}
			} 
		} 
	} 
	
	// 단어 아래로 내리는 스레드
	public class DropWordThread extends Thread{
		
		private Vector<JLabel>targetVector = null;
		private Player player = null;
		
		public DropWordThread(Vector<JLabel>targetVector, Player player) {
			this.targetVector = targetVector;
			this.player = player;
		}
		
		// y좌표 증가해 단어 밑으로 내림
		synchronized void dropWord(Player player) {
			for (int i=0; i<targetVector.size(); i++) {
				//현재 좌표
				int x = targetVector.get(i).getX();
				int y = targetVector.get(i).getY();
				//밑으로 이동
				targetVector.get(i).setLocation(x, y+5);
				groundPanel.repaint(); //위치가 변경됐으므로 다시 패널을 호출
			} 
		}
		
		// targetVector에 들어있는 모든 JLabel들의 y좌표 증가
		@Override
		public void run() {
			 while (true){
				 int dropTime = droppingSpeed[player.getMenu()-1];
				 dropWord(player);
				 groundPanel.repaint();
				 try {
					 sleep(dropTime);
					} catch (InterruptedException e) {
						return;
					}
			} 
		} 
	} 

	
	public class DetectBottomThread extends Thread {
		
		private Vector<JLabel>targetVector = null;
		
		public DetectBottomThread(Vector<JLabel>targetVector) {
			this.targetVector = targetVector;
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					sleep(1);// 바닥에 닿은 단어 구하기 위함
					for(int i=0; i<targetVector.size(); i++) {
						
						int y = ((JLabel)targetVector.get(i)).getY();
						if (y > groundPanel.getHeight()-50) { //바닥에 닿았는지 여부 확인
							
							// true값이 반환되면 게임을 종료한다.
							boolean isGameOver =scorePanel.decreaseLife(player);
							if(isGameOver == true) { // 모든스레드 종료 (case:0)
								gameOver();
							}
							
							// 게임이 종료되지 않을 경우 패널에서 라벨 제거 게임 계속됨
							groundPanel.remove(targetVector.get(i)); // 패널에서 라벨 떼기
							targetVector.remove(i); // targetVector에서 삭제
						}
					}
				} catch (InterruptedException e) {
					return;
				}
			} 
		} 
	}
	
	public void setWord(String word) {
		textLabel.setText(word);
	}
}
