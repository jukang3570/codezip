package TexttingGame;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

//���Ͽ��� �ܾ �о�� wVector�� �����ϴ� Ŭ����
public class WordVector {
	private Vector<String> wVector = new Vector<String>();
	
	public WordVector() {
		
		try { //���� �б�
			InputStream is = WordVector.class.getClassLoader().getResourceAsStream("word.txt");
			Scanner scanner = new Scanner(is);
			while(scanner.hasNext()) {
				String word = scanner.nextLine();
				wVector.add(word);
			}
			scanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//wVector���� �������� �����ؼ� �ܾ ��ȯ�ϴ� �޼ҵ�
	public String getWord() {
		int index = (int)(Math.random()*wVector.size());
		return wVector.get(index);
	}
}
