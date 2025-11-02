import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int score = 0;
        boolean playAgain;

        do {
            int numberToGuess = rand.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 7;
            boolean guessed = false;
            System.out.println("I have Selected an num between 1 and 100!");

            System.out.println("Guess the number between 1 and 100!");
            while (attempts < maxAttempts && !guessed) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if (guess == numberToGuess) {
                    System.out.println(" Correct! You guessed it in " + attempts + " tries!");
                    guessed = true;
                    score++;
                } else if (guess < numberToGuess) {
                    System.out.println(" Too low!");
                } else {
                    System.out.println(" Too high!");
                }
            }

            if (!guessed) {
                System.out.println("Out of attempts! The number was: " + numberToGuess);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = sc.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println(" Final Score: " + score);
        sc.close();
    }
}
 