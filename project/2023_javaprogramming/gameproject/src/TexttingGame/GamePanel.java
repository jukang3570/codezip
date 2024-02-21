package TexttingGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class GamePanel extends JPanel {
	
	//�ؽ�Ʈ�� �Է��ϰ� ǥ��
	private JLabel textLabel = new JLabel(); //ǥ��
	private JTextField tf = new JTextField(10); //�Է�
	
	//������ �ܾ �����ϴ� ����
	//���Ӱ� ������ �ܾ��� ������ �߰��ϰ� �ܾ ���ŵǴ� ��� targetvector���� 
	//�����ϱ�����.
	private Vector<JLabel> targetVector = new Vector<JLabel>();
	
	//word.txt���� �ܾ �������� �������� ��ü
	private WordVector wVector = new WordVector();
	
	private GroundPanel groundPanel = new GroundPanel(); //�ܾ ǥ�õǴ� �г�
	private ScorePanel scorePanel = null; //���� ǥ���ϴ� �г�
	private InputPanel inputPanel = new InputPanel(); //�ܾ �Է��ϴ� �г�
	
	private Player player = new Player(); //player ����
	
	// �ܾ �����ϴ� ������
	private GenerateWordThread generateWordThread = new GenerateWordThread(targetVector, player);
	// �ܾ ����߸��� ������
	private DropWordThread dropWordThread = new DropWordThread(targetVector,player);
	// ���� ���� �ܾ� �����ϴ� ������
	private DetectBottomThread detectBottomThread = new DetectBottomThread(targetVector);
		
	//���̵� ����
	//�ܾ� ���� �ӵ� �迭
	private int [] generationSpeed = {5000,3000,1000}; 
	//�ܾ ����߸��� �ӵ� �迭
	private int [] droppingSpeed = {500,300,100};
	
	
	public GamePanel() {
		
	}
	
	public GamePanel(Player player, ScorePanel scorePanel) {//������ ����Ǵ� �г� (�ܾ ������ �������� �г��̶�� �����ϸ� ��)
		this.player = player;
		this.scorePanel = scorePanel;
		
		//������ ������
		generateWordThread = new GenerateWordThread(targetVector, player);
		dropWordThread = new DropWordThread(targetVector,player);
		wVector = new WordVector();//�ܾ� ����
		
		setLayout(new BorderLayout());
		add(groundPanel, BorderLayout.CENTER); // �ܾ� ���� �г�
		add(inputPanel, BorderLayout.SOUTH); // �ؽ�Ʈ �Է� �г�
		
		tf.setFont(new Font("���ʷչ���", Font.BOLD, 30));
		
		tf.addActionListener(new ActionListener() { // �ؽ�Ʈ �Է��ϰ� enter ������ ����
			//Text�� �Է��ϰ� enter�� ���� �� �߻��ϴ� �޼ҵ�
			
			public void actionPerformed(ActionEvent e) {
				synchronized(targetVector) { 
					JTextField t = (JTextField)e.getSource(); //�Է��� �ؽ�Ʈ�� ������ ����
					
					for (int i=0; i < targetVector.size(); i++) {
						String text = targetVector.get(i).getText();
						if(text.equals(t.getText())) { //���� ���� 
							scorePanel.increase(player);
							scorePanel.repaintScore();
							groundPanel.remove(targetVector.get(i)); //���� �ܾ� ����
							targetVector.remove(i);
							t.setText(null); //�ؽ�Ʈ �ʵ忡�� �Է��� �ܾ� ���� 
							//Ʋ�� �� ���� �ӵ� ����
							if (droppingSpeed[player.getMenu()-1] > 2)
								droppingSpeed[player.getMenu()-1]--;
							if (generationSpeed[player.getMenu()-1] > 20)
								generationSpeed[player.getMenu()-1] -= 10;
							break;
	
						}
						if((i == (targetVector.size() - 1)) && !text.equals(t.getText())) {
							// ���� ����
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
	
	class InputPanel extends JPanel { // �ܾ� �Է� 
		public InputPanel() {
			setLayout(new FlowLayout());
			this.setBackground(new Color(245, 225, 221));
			add(tf);
		}
	}
	
	class GroundPanel extends JPanel { // �ܾ� ����
		
		public GroundPanel() {
			setLayout(null);
			this.setBackground(new Color(253, 242, 208));
		}
	}
	
	public void gameStart(Player player) { //������ ����
		//player�� ���� ����
		this.player = player;

		// �ܾ���� ����
		generateWordThread.start();
		// �ܾ� ����߸��� ����
		dropWordThread.start();
		// ���� ���� �ܾ� ���� ����
		detectBottomThread.start();
	}
	
	public void gameOver() { // ��������
		// �ܾ���� �ߴ�
		generateWordThread.interrupt();
		// �ܾ� ����߸��� �ߴ�
		dropWordThread.interrupt();
		// ���� ���� �ܾ� ���� �ߴ�
		detectBottomThread.interrupt();
	}
	
	public class GenerateWordThread extends Thread{ //�ܾ� ���� ������
		
		private Vector<JLabel> targetVector = null; 
		private Player player = null;
		
		// �ܾ� ������ Label����, �����ϴ� �޼ҵ�
		synchronized void generateWord(Player player) {
			JLabel targetLabel = new JLabel("");
			
			// �ܾ� �� �� �����ؼ� label�� ǥ��
			String newWord = wVector.getWord();
			targetLabel.setText(newWord); 
			
			// targetLabel ���
			targetLabel.setHorizontalAlignment(JLabel.CENTER); // JLabel �������
			targetLabel.setSize(200, 40);
			targetLabel.setFont(new Font("���ʷչ���", 1, 21));
			
			// x��ǥ ���� ����
			int startX = (int) (Math.random()*groundPanel.getWidth());
			while(true) {
				if ((startX + targetLabel.getWidth()) > groundPanel.getWidth()) 
					startX = (int) (Math.random()*groundPanel.getWidth());
				else
					break;
			}
			
			targetLabel.setLocation(startX,0);
			
			targetLabel.setOpaque(false); // ��� �����ϰ�
			targetVector.addElement(targetLabel); // targetVector�� ������ newWord �߰�
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
				generateWord(player); //�ܾ� ����
				groundPanel.repaint(); //�ٽ� �г� ȣ��
				try { //������ �������� �����带 ����
		 		sleep(generationTime);
				} catch (InterruptedException e) {
					return;
				}
			} 
		} 
	} 
	
	// �ܾ� �Ʒ��� ������ ������
	public class DropWordThread extends Thread{
		
		private Vector<JLabel>targetVector = null;
		private Player player = null;
		
		public DropWordThread(Vector<JLabel>targetVector, Player player) {
			this.targetVector = targetVector;
			this.player = player;
		}
		
		// y��ǥ ������ �ܾ� ������ ����
		synchronized void dropWord(Player player) {
			for (int i=0; i<targetVector.size(); i++) {
				//���� ��ǥ
				int x = targetVector.get(i).getX();
				int y = targetVector.get(i).getY();
				//������ �̵�
				targetVector.get(i).setLocation(x, y+5);
				groundPanel.repaint(); //��ġ�� ��������Ƿ� �ٽ� �г��� ȣ��
			} 
		}
		
		// targetVector�� ����ִ� ��� JLabel���� y��ǥ ����
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
					sleep(1);// �ٴڿ� ���� �ܾ� ���ϱ� ����
					for(int i=0; i<targetVector.size(); i++) {
						
						int y = ((JLabel)targetVector.get(i)).getY();
						if (y > groundPanel.getHeight()-50) { //�ٴڿ� ��Ҵ��� ���� Ȯ��
							
							// true���� ��ȯ�Ǹ� ������ �����Ѵ�.
							boolean isGameOver =scorePanel.decreaseLife(player);
							if(isGameOver == true) { // ��罺���� ���� (case:0)
								gameOver();
							}
							
							// ������ ������� ���� ��� �гο��� �� ���� ���� ��ӵ�
							groundPanel.remove(targetVector.get(i)); // �гο��� �� ����
							targetVector.remove(i); // targetVector���� ����
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
