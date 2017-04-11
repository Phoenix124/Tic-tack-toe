package main.java;

/**
 * Created by Phoenix on 10.04.2017.
 */
public class Field {

    private int[][] board;
    int count = 1;

    /**
     * Доступ к полю
     *
     * */
    public int [][] getBoard() {
        return board;
    }
    /**
     * Конструктор
     *
     * */
    public Field(){
        board = new int[3][3];
        initializeField();
    }
    /**
     *
     * Инициализация/рестарт поля
     *
     * */
    public void initializeField(){
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
               board [i][j] = count;
               count++;
            }
        }
    }
    /**
     * Печать поля
     *
     * */
    public void printBoard(){

        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }
    /**
     * Проверка поля на заполнение
     *
     * */
    public boolean isBoardFull(){
        boolean isFull = true;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(board[i][j] == 1){
                    isFull = false;
                }
            }
        }
        return isFull;
    }
}
