package main.java;

/**
 * Created by Phoenix on 10.04.2017.
 */
public class Field {
    private char[][] board;
    private char currentPlayer;

    public Field(){
        board = new char[3][3];
        currentPlayer = 'x';
        InitializeField();
    }

    public void InitializeField(){
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
               board [i][j] = '-';
            }
        }
    }
    public void PrintBoard(){
        System.out.println("----------");

        for (int i = 0; i < 3; i++){
            System.out.println("| ");
            for (int j = 0; j < 3; j++){
                System.out.println(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("------------");
        }
    }
}
