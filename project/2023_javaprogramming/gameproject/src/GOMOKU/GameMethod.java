package GOMOKU;

import java.util.Scanner;

public class GameMethod {
    private int MaxSize = 20; 
    private int Map[][] = new int[MaxSize][MaxSize]; 
    private int GamePlay_cnt = 2; // 게임플레이어 숫자
    private int cun_GamePlayer = 1; // 게임플레이어 현재 순서

    public void init() {
        for (int i = 0; i < MaxSize; i++) {
            for (int j = 0; j < MaxSize; j++) {
                Map[i][j] = 0;
            }
        }
    }

    public void reset() {
        init();
        cun_GamePlayer = 1;
    }

    public void nextPlayer(int cun_p) {
        cun_p++;
        if (GamePlay_cnt < cun_p) {
            cun_GamePlayer = 1;
        } else {
            cun_GamePlayer = cun_p;
        }
    }

    public boolean endGame(Word w) {
        int nowColor = w.getColor();
        int dir[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, 1}, {1, -1}, {-1, -1}, {1, 1}};

        for (int i = 0; i < 8; i = i + 2) {
            int same_cnt = 1;
            int cunY = w.getY();
            int cunX = w.getX();

            for (int j = 0; j < 5; j++) {
                cunY = cunY + dir[i][0];
                cunX = cunX + dir[i][1];
                if (cunY < 0 || cunY >= MaxSize || cunX < 0 || cunX >= MaxSize)
                    break;
                if (nowColor != Map[cunY][cunX])
                    break;

                same_cnt++;
            }
            cunY = w.getY();
            cunX = w.getX();

            for (int j = 0; j < 5; j++) {
                cunY = cunY + dir[i + 1][0];
                cunX = cunX + dir[i + 1][1];
                if (cunY < 0 || cunY >= MaxSize || cunX < 0 || cunX >= MaxSize)
                    break;
                if (nowColor != Map[cunY][cunX])
                    break;
                same_cnt++;
            }

            if (same_cnt >= 5) {
                return true;
            }
        }
        return false;
    }

    public void inputWord(Word w) {
        Map[w.getY()][w.getX()] = w.getColor();
    }

    public boolean checkInput(int y, int x) {
        if (Map[y][x] != 0 || y < 0 || y >= MaxSize || x < 0 || x >= MaxSize) {
            return false;
        }
        return true;
    }

    public void setGameMode(int max) {
        this.GamePlay_cnt = max;
    }

    public int[][] getMap() {
        return Map;
    }

    public int getCun_GamePlayer() {
        return cun_GamePlayer;
    }
}


// 이 클래스는 게임 전반의 세팅과 오목 달성 조건을 검사해주는 코드
//마우스 이벤트를 통해 x,y값을 받으면 Map[][]변수에 현재 플레이어들의 돌(1234)을 입력해주는 방식을 이루어진 코드이다