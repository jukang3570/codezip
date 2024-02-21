package OX;

import javax.swing.JOptionPane;

public class SetBoardSize {
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
