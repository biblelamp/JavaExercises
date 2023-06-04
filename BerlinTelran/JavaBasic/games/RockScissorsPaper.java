import java.util.Random;
import java.util.Scanner;

class RockScissorsPaper {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        Scanner sca = new Scanner(System.in);
        int number = 0;
        String[] array1 = {"rock", "scissors", "paper"};
        int computer = -1;
        int player = -1;
        int computerO = 0;
        int playerO = 0;
        do {
            System.out.println("The game continues until 3 wins");
            do {
                number = random.nextInt(3);
                System.out.println("Enter what you thought: rock (R), scissors (S), paper (P)");
                switch (sca.nextLine()){
                    case "R":
                        player = 0;
                        break;
                    case "S":
                        player = 1;
                        break;
                    case "P":
                        player = 2;
                        break;
                    default:
                        System.out.println("Enter only: rock (R), scissors (S), paper (P)");
                        continue;
                }
                System.out.println("The computer guessed: " + array1[number]);
                if (number == player) {
                    System.out.println("Dead heat");
                } else if ((number == 0 && player == 2)||(number == 1 && player == 0)||(number == 2 && player == 1)) {
                    System.out.println("Player won this round");
                    playerO++;
                } else {
                    System.out.println("Computer won this round");
                    computerO++;
                }
                System.out.printf("Player - Computer: %d - %d\n", playerO, computerO);
            } while (computerO != 3 && playerO != 3);
            if (computerO == 3) {
                System.out.println("Computer WIN");
            } else {
                System.out.println("Player WIN");
            }
            computerO = 0;
            playerO = 0;
            System.out.println("Repeat game?:  Y/N: ");
        } while (sc.nextLine().equals("Y"));
    }
}