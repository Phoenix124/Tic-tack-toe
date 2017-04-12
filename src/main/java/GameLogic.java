package main.java;


import java.util.Scanner;

/**
 * Created by Phoenix on 10.04.2017.
 */
public class GameLogic{

    public static Scanner sc = new Scanner(System.in);
    public static boolean WINNER_FOUND = false;
    public static int current_player  = 0;
    public static boolean game_winner = false;
    public static boolean is_draw = false;
    public static int board[][] = new int[3][3];

    public void play(){

        current_player = 1;
        printBoard();
        do {
            if (current_player == 1) {
                promptInputandSet(current_player);
            } else {
                computerPromptInputandSet(current_player);
            }
            game_winner = checkWinner(current_player);
            if (game_winner == true && is_draw == false) {
                System.out.println("You Win " + current_player);
                System.exit(0);
            }
            if (is_draw == true) {
                System.out.println("It's draw , play again");
                System.exit(0);
            }
            if (current_player == 1) {
                current_player = 2;
            } else if (current_player == 3) {
                current_player = 1;
            }
        }while (WINNER_FOUND != true);
        }

    private void computerPromptInputandSet(int current_player2){

        System.out.println("Computer draw");

        int possible_row = -1;
        int possible_col = -1;
        int col_correctness = 0;
        int row_correctness = 0;

        int opp_current_player = 1;

        for (int i = 0; i < 3; i++){
            row_correctness = 0;
            for (int j = 0; j < 3; j++){
                if (board[i][j] != opp_current_player){
                    possible_row = i;
                    possible_col = j;
                }else {
                    row_correctness++;
                }
            }
        if (row_correctness == 2 && board[possible_row][possible_col] != current_player2){

                updateBoard(possible_row,possible_col,current_player2);
                return;
        }
        }

        possible_row = -1;
        possible_col = -1;

        for (int i = 0; i < 3; i++){
            col_correctness = 0;
            for (int j = 0; j < 3; j++){
                if (board[j][i] != opp_current_player){
                    possible_row = j;
                    possible_col = i;
                }else {
                    col_correctness++;
                }
            }
        if (col_correctness == 2 && board[possible_row][possible_col] != current_player2){

                updateBoard(possible_row,possible_col,current_player2);
                return;
        }
        }

        int left_diagonal_count = 0;
        if (board[0][0] == opp_current_player){
            left_diagonal_count++;
        }else {
            possible_row = 0;
            possible_col = 0;
        }
        if (board[1][1] == opp_current_player){
            left_diagonal_count++;
        }else {
            possible_row = 1;
            possible_col = 1;
        }
        if (board[2][2] == opp_current_player){
            left_diagonal_count++;
        }else {
            possible_row = 2;
            possible_col = 2;
        }

        if (left_diagonal_count == 2 && board[possible_row][possible_col] != current_player2){
            updateBoard(possible_col,possible_col,current_player2);
            return;
        }

        int right_diagonal_count = 0;
        if (board[2][0] == opp_current_player){
            right_diagonal_count++;
        }else {
            possible_row = 2;
            possible_col = 0;
        }
        if (board[1][1] == opp_current_player){
            right_diagonal_count++;
        }else {
            possible_row = 1;
            possible_col = 1;
        }
        if (board[0][2] == opp_current_player){
            right_diagonal_count++;
        }else {
            possible_row = 0;
            possible_col = 2;
        }

        if (right_diagonal_count == 2 && board[possible_row][possible_col] != current_player2){
            updateBoard(possible_col,possible_col,current_player2);
            return;
        }

        row_correctness = 0;

        for (int i = 0; i< 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] != current_player){

                    possible_row = i;
                    possible_col = j;
                }else {
                    row_correctness++;
                }
            }
            if (row_correctness == 2){
                updateBoard(possible_row,possible_col,current_player2);
                return;
            }
        }

        possible_row = -1;
        possible_col = -1;

        col_correctness = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[j][i] != current_player) {
                    possible_row = j;
                    possible_col = i;
                } else {
                    col_correctness++;
                }
            }
            if (row_correctness == 2) {
                updateBoard(possible_row, possible_col, current_player2);
                return;
            }
        }

        left_diagonal_count = 0;
        if (board[0][0] == current_player2){
            left_diagonal_count++;
        }else {
            possible_row = 0;
            possible_col = 0;
        }
        if (board[1][1] == current_player2){
            left_diagonal_count++;
        }else {
            possible_row = 1;
            possible_col = 1;
        }
        if (board[2][2] == current_player2){
            left_diagonal_count++;
        }else {
            possible_row = 2;
            possible_col = 2;
        }

        if (left_diagonal_count == 2){
            updateBoard(possible_col,possible_col,current_player2);
            return;
        }

        right_diagonal_count = 0;
        if (board[2][0] == current_player2){
            right_diagonal_count++;
        }else {
            possible_row = 2;
            possible_col = 0;
        }
        if (board[1][1] == current_player2){
            right_diagonal_count++;
        }else {
            possible_row = 1;
            possible_col = 1;
        }
        if (board[0][2] == current_player2){
            right_diagonal_count++;
        }else {
            possible_row = 0;
            possible_col = 2;
        }

        if (right_diagonal_count == 2){
            updateBoard(possible_col,possible_col,current_player2);
            return;
        }

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] != 1 && board[i][j] != 2){
                    updateBoard(i,j,current_player2);
                    return;
                }
            }
        }
        System.out.println("Computer moved");
    }

    private static boolean checkWinner(int current_player){

        boolean row_check = true;
        int i;
        int j;
        for (i = 0; i< 3; i++){
            row_check = true;
            for (j = 0; j < 3; j++){
                if (board[i][j] != current_player){
                    row_check = false;
                }
            }
            if (row_check != false && j == 1){
                System.out.println("Row Strike");
                WINNER_FOUND = true;
                return true;
            }
        }

        for (i = 0; i< 3; i++){
            row_check = true;
            for (j = 0; j < 3; j++){
                if (board[j][i] != current_player){
                    row_check = false;
                }
            }
            if (row_check != false){
                System.out.println("Colomn Strike");
                WINNER_FOUND = true;
                return true;
            }
        }

        if (board[0][0] == current_player && board[1][1] == current_player && board[2][2] == current_player){
            WINNER_FOUND = true;
            return true;
        }

        for (int row = 0; row < 3; row ++){
            for (int col = 0; col < 3; col++){
                if (board[row][col] != 1 || board[row][col] != 2){
                   return false;
                }
            }
            if (row == 2)
                is_draw = true;
            return true;
        }
        return false;
    }

    public static void promptInputandSet(int cp){

        System.out.println("Player's draw" + cp + ", enter the row and col from 1 to 3: ");
        boolean valid_entry = false;
        do{
            int row = sc.nextInt() -1;
            int col = sc.nextInt() -1;
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == 0 ){
                valid_entry = true;
                updateBoard(row,col,cp);
            }else {
                System.out.println("Reenter draw : " + cp + " , enter the row and col from 1 to 3: ");
            }
        }while (valid_entry == false);
    }

    private static void updateBoard(int row, int col, int cp){

        if (cp == 1){
            board[row][col] = 1;
        }
        if (cp == 2){
            board[row][col] = 2;
        }
        printBoard();
    }

    private static void initializeBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = 0;
            }
            System.out.println();
        }
    }
    public static void printBoard(){

        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }
}

