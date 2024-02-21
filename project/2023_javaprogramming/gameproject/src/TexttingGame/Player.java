package TexttingGame;




public class Player {
	private String name;
	private int menu;
	private int score;
	
	public Player() {
		
	}
	//player의 정보를 저장하는 패널 
	public Player ( String name, int menu, int score ) {
		this.name = name;
		this.menu = menu;
		this.score = score;
	}
	
	//(Player의 이름 가져오기)
	public String getName() {
		return name;
	}
	//(Player의 점수 가져오기)
	public int getScore() {
		return score;
	}
	//(player가 선택한 레벨 가져오기)
	public int getMenu() {
		return menu;
	}
	//(player의 이름, 점수, 선택한 레벨 저장)
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