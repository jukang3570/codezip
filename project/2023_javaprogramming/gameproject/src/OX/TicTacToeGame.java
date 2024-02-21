package OX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame{
	 private int SIZE;
	 private JButton[][] btn;
	 private int count = 0; // 클릭 횟수를 세는 변수
        
	    public TicTacToeGame(int size) {
	        this.SIZE = size;
	        this.setTitle("TicTacToe 게임");
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        Container c = getContentPane();
	     
	        btn = new JButton[SIZE][SIZE];

	        c.setLayout(new GridLayout(SIZE, SIZE));
	        
	        for (int i = 0; i < SIZE; i++) {
	            for (int j = 0; j < SIZE; j++) {
	                btn[i][j] = new JButton();
	                btn[i][j].setText("");
	                c.add(btn[i][j]);
	                btn[i][j].addActionListener(new ButtonClickListener(i, j));
	            }
	        }

	        this.setSize(600, 600);
	        this.setVisible(true);
	    }
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> { //스윙 GUI애플리케이션 실행
	            SetBoardSize s = new SetBoardSize(); 
	            int size = s.getBoardSize(); //사용자로부터 게임 보드 크기 반환
	            new TicTacToeGame(size);
	        });
	    }

	    private class ButtonClickListener implements ActionListener {
	    	private int row;
	    	private int col;
	    	
	    	public ButtonClickListener(int row, int col) {
	            this.row = row;
	            this.col = col;
	        }
	    	
	    	public void actionPerformed(ActionEvent e) {
	            JButton source = (JButton) e.getSource();
	            if(count % 2 ==0) {
	            	source.setText("X");
	           
	            } else {
	            	source.setText("O");
	            
	            }
	            count++;
	            
	            if (checkWin(row,col)) {
	                String winner = count % 2 == 0 ? "O" : "X";
	                JOptionPane.showMessageDialog(null, "게임 종료! " + winner + "가 이겼습니다.", "게임 결과", JOptionPane.INFORMATION_MESSAGE);
	                resetGame();
	            } else if (count == SIZE * SIZE) {
	                JOptionPane.showMessageDialog(null, "게임 종료! 무승부입니다.", "게임 결과", JOptionPane.INFORMATION_MESSAGE);
	                resetGame();
	            }
	            source.setEnabled(false); // 클릭한 버튼 비활성화
	        }
	    }
	    private boolean checkWin(int row,int col) {
	    	String mark = btn[row][col].getText();
	    	
	    	// 가로 체크
	         boolean win = true;
	         for (int i = 0; i < SIZE; i++) {
	             if (!btn[row][i].getText().equals(mark)) {
	                 win = false;
	                 break;
	             }
	         }
	         if (win) return true;

	         // 세로 체크
	         win = true;
	         for (int i = 0; i < SIZE; i++) {
	             if (!btn[i][col].getText().equals(mark)) {
	                 win = false;
	                 break;
	             }
	         }
	         if (win) return true;
	         
	      // 대각선 체크
	         win = true;
	         if (row == col) {
	             for (int i = 0; i < SIZE; i++) {
	                 if (!btn[i][i].getText().equals(mark)) {
	                     win = false;
	                     break;
	                 }
	             }
	             if (win) return true;
	         }

	         win = true;
	         if (row + col == SIZE - 1) {
	             for (int i = 0; i < SIZE; i++) {
	                 if (!btn[i][SIZE - 1 - i].getText().equals(mark)) {
	                     win = false;
	                     break;
	                 }
	             }
	             if (win) return true;
	         }
	         return false;
	    }

	    	 
	    private void resetGame() {
	    	count = 0; // 클릭 횟수 초기화
	    	 dispose(); // 현재 게임 창 닫기
	    	    SwingUtilities.invokeLater(() -> {
	    	        SetBoardSize s = new SetBoardSize(); // 사용자로부터 게임 보드 크기 재입력 받음
	    	        int size = s.getBoardSize(); // 사용자로부터 게임 보드 크기 반환
	    	        new TicTacToeGame(size); // 새로운 게임 시작
	    	    });
	    }
	    public int getBoardSize() {
	        String input = JOptionPane.showInputDialog(null, "보드의 크기를 입력하세요 (3 이상의 정수):", "보드 크기 설정", JOptionPane.PLAIN_MESSAGE);
	        if (input == null) {
	            System.exit(0); // 취소 버튼이 눌렸을 경우 프로그램 종료
	        }
	        int size;
	        //예외처리 3장
	        try {
	            size = Integer.parseInt(input);
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "잘못된 입력입니다. 다시 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
	            return getBoardSize(); 
	        }
	        if (size < 3) {
	            JOptionPane.showMessageDialog(null, "보드 크기는 3 이상이어야 합니다. 다시 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
	            return getBoardSize();
	        }
	        return size;
	    }
}



