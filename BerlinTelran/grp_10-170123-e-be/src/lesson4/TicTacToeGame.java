package lesson4;

import cz.javageek.games.TicTacToe;

import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        while (!game.isGameOver()) {
            System.out.print("Enter coordinates x and y [1..3]: ");
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            game.turn(x, y);
            System.out.println(game);
        }
        System.out.println(game.getGameOverStatus());
    }
}
