package main.java;


import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Phoenix on 10.04.2017.
 */
public class GameLogic{

    private final int BOARD_SIZE = 9;

    public static final char HUMAN_PLAYER = 'X';
    public static final char COMPUTER_PLAYER = 'O';

    private Random mRand;

    Field field = new Field();

    public void play(){

                mRand = new Random();

        char turn = HUMAN_PLAYER;
        int  win = 0;

        while (win == 0)
        {
            field.displayBoard();

            if (turn == HUMAN_PLAYER)
            {
                getUserMove();
                turn = COMPUTER_PLAYER;
            }
            else
            {
                getComputerMove();
                turn = HUMAN_PLAYER;
            }

            win = checkForWinner();
        }

        field.displayBoard();


        System.out.println();
        if (win == 1)
            System.out.println("It's a tie.");
        else if (win == 2)
            System.out.println(HUMAN_PLAYER + " wins!");
        else if (win == 3)
            System.out.println(COMPUTER_PLAYER + " wins!");
        else
            System.out.println("There is a logic problem!");
    }

    void getUserMove() {

        Scanner s = new Scanner(System.in);

        int move = -1;

        while (move == -1) {
            try {
                System.out.print("Enter your move: ");
                move = s.nextInt();

                while (move < 1 || move > BOARD_SIZE ||
                        field.mBoard[move-1] == HUMAN_PLAYER || field.mBoard[move-1] == COMPUTER_PLAYER) {

                    if (move < 1 || move > BOARD_SIZE)
                        System.out.println("Please enter a move between 1 and " + BOARD_SIZE + ".");
                    else
                        System.out.println("That space is occupied.  Please choose another space.");

                    System.out.print("Enter your move: ");
                    move = s.nextInt();
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("Please enter a number between 1 and " + BOARD_SIZE + ".");
                s.next();
                move = -1;
            }
        }

        field.mBoard[move-1] = HUMAN_PLAYER;
    }

    private int checkForWinner() {

        // Check horizontal wins
        for (int i = 0; i <= 6; i += 3)	{
            if (field.mBoard[i] == HUMAN_PLAYER &&
                    field.mBoard[i+1] == HUMAN_PLAYER &&
                    field.mBoard[i+2]== HUMAN_PLAYER)
                return 2;
            if (field.mBoard[i] == COMPUTER_PLAYER &&
                    field.mBoard[i+1]== COMPUTER_PLAYER &&
                    field.mBoard[i+2] == COMPUTER_PLAYER)
                return 3;
        }

        // Check vertical wins
        for (int i = 0; i <= 2; i++) {
            if (field.mBoard[i] == HUMAN_PLAYER &&
                    field.mBoard[i+3] == HUMAN_PLAYER &&
                    field.mBoard[i+6]== HUMAN_PLAYER)
                return 2;
            if (field.mBoard[i] == COMPUTER_PLAYER &&
                    field.mBoard[i+3] == COMPUTER_PLAYER &&
                    field.mBoard[i+6]== COMPUTER_PLAYER)
                return 3;
        }

        // Check for diagonal wins
        if ((field.mBoard[0] == HUMAN_PLAYER &&
                field.mBoard[4] == HUMAN_PLAYER &&
                field.mBoard[8] == HUMAN_PLAYER) ||
                (field.mBoard[2] == HUMAN_PLAYER &&
                        field.mBoard[4] == HUMAN_PLAYER &&
                        field.mBoard[6] == HUMAN_PLAYER))
            return 2;
        if ((field.mBoard[0] == COMPUTER_PLAYER &&
                field.mBoard[4] == COMPUTER_PLAYER &&
                field.mBoard[8] == COMPUTER_PLAYER) ||
                (field.mBoard[2] == COMPUTER_PLAYER &&
                        field.mBoard[4] == COMPUTER_PLAYER &&
                        field.mBoard[6] == COMPUTER_PLAYER))
            return 3;

        // Check for tie
        for (int i = 0; i < BOARD_SIZE; i++) {
            // If we find a number, then no one has won yet
            if (field.mBoard[i] != HUMAN_PLAYER && field.mBoard[i] != COMPUTER_PLAYER)
                return 0;
        }

        // If we make it through the previous loop, all places are taken, so it's a tie
        return 1;
    }

    private void getComputerMove()
    {
        int move;

        // First see if there's a move O can make to win
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (field.mBoard[i] != HUMAN_PLAYER && field.mBoard[i] != COMPUTER_PLAYER) {
                char curr = field.mBoard[i];
                field.mBoard[i] = COMPUTER_PLAYER;
                if (checkForWinner() == 3) {
                    System.out.println("Computer is moving to " + (i + 1));
                    return;
                }
                else
                    field.mBoard[i] = curr;
            }
        }

        // See if there's a move O can make to block X from winning
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (field.mBoard[i] != HUMAN_PLAYER && field.mBoard[i] != COMPUTER_PLAYER) {
                char curr = field.mBoard[i];   // Save the current number
                field.mBoard[i] = HUMAN_PLAYER;
                if (checkForWinner() == 2) {
                    field.mBoard[i] = COMPUTER_PLAYER;
                    System.out.println("Computer is moving to " + (i + 1));
                    return;
                }
                else
                    field.mBoard[i] = curr;
            }
        }

        // Generate random move
        do
        {
            move = mRand.nextInt(BOARD_SIZE);
        } while (field.mBoard[move] == HUMAN_PLAYER || field.mBoard[move] == COMPUTER_PLAYER);

        System.out.println("Computer is moving to " + (move + 1));

        field.mBoard[move] = COMPUTER_PLAYER;
    }

}

