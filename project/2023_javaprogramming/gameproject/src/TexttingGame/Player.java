package TexttingGame;




public class Player {
	private String name;
	private int menu;
	private int score;
	
	public Player() {
		
	}
	//player�� ������ �����ϴ� �г� 
	public Player ( String name, int menu, int score ) {
		this.name = name;
		this.menu = menu;
		this.score = score;
	}
	
	//(Player�� �̸� ��������)
	public String getName() {
		return name;
	}
	//(Player�� ���� ��������)
	public int getScore() {
		return score;
	}
	//(player�� ������ ���� ��������)
	public int getMenu() {
		return menu;
	}
	//(player�� �̸�, ����, ������ ���� ����)
	public void setName(String name) {
		this.name = name;
	}	
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setMenu(int menu) {
		this.menu = menu;
	}

	
	
}