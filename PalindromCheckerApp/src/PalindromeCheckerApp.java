/**
 * MAIN CLASS - UseCase12PalindromeCheckerApp
 *
 * Use Case 12: Strategy Pattern for Palindrome Algorithms
 *
 * Description:
 * This class demonstrates how different palindrome
 * validation algorithms can be selected dynamically
 * at runtime using the Strategy Design Pattern.
 *
 * At this stage, the application:
 * - Defines a common PalindromeStrategy interface
 * - Implements a concrete Stack-based strategy
 * - Injects the strategy at runtime
 * - Executes the selected algorithm
 *
 * No performance comparison is done in this use case.
 * The focus is purely on algorithm interchangeability.
 *
 * The goal is to teach extensible algorithm design.
 *
 * Author: Developer
 * Version: 12.0
 */

import java.util.Scanner;
import java.util.Stack;

public class PalindromeCheckerApp {

    /**
     * Application entry point
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