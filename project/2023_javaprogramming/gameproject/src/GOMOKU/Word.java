package GOMOKU;

public class Word {
		private int y;
		private int x;
		private int color;
		
		public Word(int y,int x,int color) {
			this.y=y;
			this.x=x;
			this.color=color;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getColor() {
			return color;
		}
		public void setColor(int color) {
			this.color = color;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
	}

