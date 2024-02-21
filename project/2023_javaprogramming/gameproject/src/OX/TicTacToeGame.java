package OX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame{
	 private int SIZE;
	 private JButton[][] btn;
	 private int count = 0; // Ŭ�� Ƚ���� ���� ����
        
	    public TicTacToeGame(int size) {
	        this.SIZE = size;
	        this.setTitle("TicTacToe ����");
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
	        SwingUtilities.invokeLater(() -> { //���� GUI���ø����̼� ����
	            SetBoardSize s = new SetBoardSize(); 
	            int size = s.getBoardSize(); //����ڷκ��� ���� ���� ũ�� ��ȯ
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
	                JOptionPane.showMessageDialog(null, "���� ����! " + winner + "�� �̰���ϴ�.", "���� ���", JOptionPane.INFORMATION_MESSAGE);
	                resetGame();
	            } else if (count == SIZE * SIZE) {
	                JOptionPane.showMessageDialog(null, "���� ����! ���º��Դϴ�.", "���� ���", JOptionPane.INFORMATION_MESSAGE);
	                resetGame();
	            }
	            source.setEnabled(false); // Ŭ���� ��ư ��Ȱ��ȭ
	        }
	    }
	    private boolean checkWin(int row,int col) {
	    	String mark = btn[row][col].getText();
	    	
	    	// ���� üũ
	         boolean win = true;
	         for (int i = 0; i < SIZE; i++) {
	             if (!btn[row][i].getText().equals(mark)) {
	                 win = false;
	                 break;
	             }
	         }
	         if (win) return true;

	         // ���� üũ
	         win = true;
	         for (int i = 0; i < SIZE; i++) {
	             if (!btn[i][col].getText().equals(mark)) {
	                 win = false;
	                 break;
	             }
	         }
	         if (win) return true;
	         
	      // �밢�� üũ
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
	    	count = 0; // Ŭ�� Ƚ�� �ʱ�ȭ
	    	 dispose(); // ���� ���� â �ݱ�
	    	    SwingUtilities.invokeLater(() -> {
	    	        SetBoardSize s = new SetBoardSize(); // ����ڷκ��� ���� ���� ũ�� ���Է� ����
	    	        int size = s.getBoardSize(); // ����ڷκ��� ���� ���� ũ�� ��ȯ
	    	        new TicTacToeGame(size); // ���ο� ���� ����
	    	    });
	    }
	    public int getBoardSize() {
	        String input = JOptionPane.showInputDialog(null, "������ ũ�⸦ �Է��ϼ��� (3 �̻��� ����):", "���� ũ�� ����", JOptionPane.PLAIN_MESSAGE);
	        if (input == null) {
	            System.exit(0); // ��� ��ư�� ������ ��� ���α׷� ����
	        }
	        int size;
	        //����ó�� 3��
	        try {
	            size = Integer.parseInt(input);
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
	            return getBoardSize(); 
	        }
	        if (size < 3) {
	            JOptionPane.showMessageDialog(null, "���� ũ��� 3 �̻��̾�� �մϴ�. �ٽ� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
	            return getBoardSize();
	        }
	        return size;
	    }
}



