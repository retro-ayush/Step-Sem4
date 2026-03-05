
/**
 *
 * MAIN CLASS - PalindromeCheckerApp
 *
 * Use Case 1: Application Entry & Welcome Message
 *
 * Description:
 * This class represents the entry point of the
 * Palindrome Checker Management System.
 *
 * At this stage, the application:
 * - Starts execution from the main() method
 * - Displays a welcome message
 * - Shows application version
 *
 * No palindrome logic is implemented yet.
 *
 * The goal is to establish a clear startup flow.
 *
 * @author Developer
 * @version 1.0
 */

import java.util.Scanner;
import java.util.Stack;

public class PalindromeCheckerApp {
     /**
     * Application entry point for UC11.
     * @param args Command-line arguments
     */
 public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Inject strategy at runtime
        PalindromeStrategy strategy = new StackStrategy();

        // Execute algorithm
        boolean result = strategy.checkPalindrome(input);

        if (result) {
            System.out.println("The given string is a Palindrome.");
        } else {
            System.out.println("The given string is NOT a Palindrome.");
        }

        scanner.close();
    }
}
/**
 * INTERFACE - PalindromeStrategy
 *
 * Defines a contract for palindrome checking algorithms.
 */
interface PalindromeStrategy {

    boolean checkPalindrome(String input);
}

/**
 * CLASS - StackStrategy
 *
 * Concrete implementation using Stack.
 */
class StackStrategy implements PalindromeStrategy {

    @Override
    public boolean checkPalindrome(String input) {

        Stack<Character> stack = new Stack<>();

        // Push all characters to stack
        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        // Compare characters with stack pop
        for (char c : input.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }

        return true;
    }
}