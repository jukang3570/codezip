package OX;

import javax.swing.JOptionPane;

public class SetBoardSize {
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
