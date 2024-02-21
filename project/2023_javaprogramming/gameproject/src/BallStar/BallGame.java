package BallStar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BallGame extends JFrame implements Runnable, KeyListener {
    private int ballX = 500;
    private int ballY = 500;
    private double ballSpeedX = 0;
    private double ballSpeedY = 0;
    private int ballSize = 30;
    
    //장애물 초기 위치
    private int[] blockX = {800, 600, 400, 200, 1000, 140, 750, 300, 920, 500};
    private int[] blockY = {500, 400, 250, 800, 200, 130, 350, 750, 300, 900};
    private int blockSize = 40;
    
    //별 초기 위치
    private int[] starX = {800, 40, 500, 30, 800, 400, 900};
    private int[] starY = {200, 530, 900, 80, 900, 190, 480};
    private int starSize = 40;
    private int starLocate = 0; 
    
    private Image player1; //공
    private Image obs; //장애물(표창)
    private Image star; //별
    private boolean check = false; //별 다 먹었는지 체크
    
    private int starCheck = 0; //별 개수 확인용
    private JLabel starBoard;
    
    public BallGame() {
        setTitle("Ball Game!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        java.net.URL url =getClass().getClassLoader().getResource("player1.png");
        java.net.URL url1 =getClass().getClassLoader().getResource("obs.png");
        java.net.URL url2 =getClass().getClassLoader().getResource("star.png");
        player1 = Toolkit.getDefaultToolkit().getImage(url);
        obs = Toolkit.getDefaultToolkit().getImage(url1);
        star = Toolkit.getDefaultToolkit().getImage(url2);
        
        //화면에 플레이어, 장애물, 별 출력
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(player1, ballX, ballY, ballSize, ballSize, null);
                for (int i = 0; i < blockX.length; i++) {
                g.drawImage(obs, blockX[i], blockY[i], blockSize, blockSize, null);
                }
                g.drawImage(star, starX[starLocate], starY[starLocate], starSize, starSize, null);
                }};
        panel.setLayout(null);
        //점수판 추가
        starBoard = new JLabel("Remaining_stars: 7");
        starBoard.setFont(new Font("Arial", Font.BOLD, 20));
        starBoard.setForeground(Color.BLACK);
        starBoard.setBounds(750, 20, 200, 30);
        panel.add(starBoard);
        
        panel.setBackground(Color.WHITE);
        setContentPane(panel);

        setSize(1000, 1000);
        setVisible(true);
        addKeyListener(this);
    }
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
        //키보드 눌렀을 때 속도 변화, 최대 속도
            case KeyEvent.VK_UP:
                ballSpeedY = -2;
                break;
            case KeyEvent.VK_DOWN:
                ballSpeedY = +2; 
                break;
            case KeyEvent.VK_LEFT:
                ballSpeedX = -2; 
                break;
            case KeyEvent.VK_RIGHT:
                ballSpeedX = +2; 
                break;
            case KeyEvent.VK_SPACE:
                ballSpeedX = 0;
                ballSpeedY = 0; 
                break;
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void update() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;
        // 컴포넌트의 너비 반환하는 메서드 getWidth() 사용 (화면 양쪽으로 공을 못나가게 하기 위함)
        if (ballX < 0 || ballX > getWidth() - ballSize) {
            ballSpeedX = 0;
            ballSpeedY = 0;
            if (ballX < 0) {
                ballX = 0;
            } else {
                ballX = getWidth() - ballSize;
            }
        }
        // 컴포넌트의 높이 반환하는 메서드 getHeight() 사용 (화면 위아래로 공을 못나가게 하기 위함)
        if (ballY < 0 || ballY > getHeight() - ballSize) {
            ballSpeedX = 0;
            ballSpeedY = 0;
            if (ballY < 0) {
                ballY = 0;
            } else {
                ballY = getHeight() - ballSize;
            }
        }      
        //장애물(표창)
        for(int i = 0; i < blockX.length; i++){
        	//표창이 이동하도록 하기위함
            blockX[i] += 1;
            blockY[i] += 1;
            
            //표창의 X나 Y좌표 값이 0보다 작거나 너비,높이보다 큰 경우
            if(blockX[i] < 0 || blockX[i] > getWidth()-blockSize)
            {
            	blockX[i] = (int) Math.random();
            }
            if(blockY[i] < 0 || blockY[i] > getHeight()-blockSize)
            {
            	blockY[i] = (int) Math.random();    	
            }
            //공과 블럭, 공과 별의 충돌 표현
            Rectangle ballRect = new Rectangle(ballX, ballY, ballSize, ballSize);
            Rectangle blockRect = new Rectangle(blockX[i], blockY[i], blockSize, blockSize);
       
        	//공이 장애물에 닿으면 게임 재시작하기 위해 GameReset 메소드 호출
        	if (ballRect.intersects(blockRect)) 
        	{
        		JOptionPane.showMessageDialog(this, "PLAY AGAIN");
        		GameReset();
        	}
        }        
        //별
        for(int i = 0; i< starX.length; i++) {   	
        	Rectangle ballRect = new Rectangle(ballX, ballY, ballSize, ballSize);
        	Rectangle starRect = new Rectangle(starX[starLocate], starY[starLocate], starSize, starSize);
        	//공이 별에 닿으면 점수 1점씩 추가
        	if (ballRect.intersects(starRect)) 
        	{
        		 starCheck += 1; //별 개수 확인용
                 starBoard.setText("Remaining_stars: " + (7-starCheck)); //남아있는 별 개수 변경해 출력 
                 starLocate++;
            }
        	if(starLocate == starX.length) //마지막 공의 위치가 별의 x좌표 배열의 마지막 인덱스와 같으면 check값을 true로 변경
        	{
        		JOptionPane.showMessageDialog(this, "THANK YOU");	
                check = true;
        	}
        }
    }
    //장애물에 닿으면 게임 재시작하는 메소드
    public void GameReset() {
    	 ballX = 500;
         ballY = 500;
         ballSpeedX = 0;
         ballSpeedY = 0;

         //장애물 위치 재설정
         for (int i = 0; i < blockX.length; i++) {
             blockX[i] = (int) (Math.random() * getWidth());
             blockY[i] = (int) (Math.random() * getHeight());
         }
         starLocate = 0; //별 위치 재설정        
         starCheck = 0; //별 개수 초기화
         check = false; 
         starBoard.setText("Remaining_stars: 7"); //별을 먹고 난 후에 죽게되면 별 개수를 7로 다시 설정해줘야함
    }
    public void run() {
        while (true) {
            update();
            getContentPane().repaint();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(check) //별 다 먹었으면 게임 종료
            {   
            	break;
            }
        }
    }
    public static void main(String[] args) {
        BallGame game = new BallGame();
        game.run();
    }   
}
