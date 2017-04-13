package main.java;


import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Phoenix on 10.04.2017.
 */
public class GameLogic{

    private char mBoard[] = {'1','2','3','4','5','6','7','8','9'};
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
                        mBoard[move-1] == HUMAN_PLAYER || mBoard[move-1] == COMPUTER_PLAYER) {

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

        mBoard[move-1] = HUMAN_PLAYER;
    }

    private int checkForWinner() {

        // Check horizontal wins
        for (int i = 0; i <= 6; i += 3)	{
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+1] == HUMAN_PLAYER &&
                    mBoard[i+2]== HUMAN_PLAYER)
                return 2;
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i+1]== COMPUTER_PLAYER &&
                    mBoard[i+2] == COMPUTER_PLAYER)
                return 3;
        }

        // Check vertical wins
        for (int i = 0; i <= 2; i++) {
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+3] == HUMAN_PLAYER &&
                    mBoard[i+6]== HUMAN_PLAYER)
                return 2;
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i+3] == COMPUTER_PLAYER &&
                    mBoard[i+6]== COMPUTER_PLAYER)
                return 3;
        }

        // Check for diagonal wins
        if ((mBoard[0] == HUMAN_PLAYER &&
                mBoard[4] == HUMAN_PLAYER &&
                mBoard[8] == HUMAN_PLAYER) ||
                (mBoard[2] == HUMAN_PLAYER &&
                        mBoard[4] == HUMAN_PLAYER &&
                        mBoard[6] == HUMAN_PLAYER))
            return 2;
        if ((mBoard[0] == COMPUTER_PLAYER &&
                mBoard[4] == COMPUTER_PLAYER &&
                mBoard[8] == COMPUTER_PLAYER) ||
                (mBoard[2] == COMPUTER_PLAYER &&
                        mBoard[4] == COMPUTER_PLAYER &&
                        mBoard[6] == COMPUTER_PLAYER))
            return 3;

        // Check for tie
        for (int i = 0; i < BOARD_SIZE; i++) {
            // If we find a number, then no one has won yet
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER)
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
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                char curr = mBoard[i];
                mBoard[i] = COMPUTER_PLAYER;
                if (checkForWinner() == 3) {
                    System.out.println("Computer is moving to " + (i + 1));
                    return;
                }
                else
                    mBoard[i] = curr;
            }
        }

        // See if there's a move O can make to block X from winning
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                char curr = mBoard[i];   // Save the current number
                mBoard[i] = HUMAN_PLAYER;
                if (checkForWinner() == 2) {
                    mBoard[i] = COMPUTER_PLAYER;
                    System.out.println("Computer is moving to " + (i + 1));
                    return;
                }
                else
                    mBoard[i] = curr;
            }
        }

        // Generate random move
        do
        {
            move = mRand.nextInt(BOARD_SIZE);
        } while (mBoard[move] == HUMAN_PLAYER || mBoard[move] == COMPUTER_PLAYER);

        System.out.println("Computer is moving to " + (move + 1));

        mBoard[move] = COMPUTER_PLAYER;
    }

}

