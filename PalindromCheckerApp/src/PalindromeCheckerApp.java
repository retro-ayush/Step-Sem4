
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
import java.util.Deque;
import java.util.ArrayDeque;

public class PalindromeCheckerApp {
    /**
     * Application entry point.
     *
     * This is the first method executed by the JVM
     * when the program starts.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        String input = "refer";
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : input.toCharArray()) {
            deque.addLast(c);
        }
        boolean isPalindrome = true;
        while (deque.size() > 1) {
            char front = deque.removeFirst();
            char back = deque.removeLast();  
            if (front != back) {
                isPalindrome = false;
                break;
            }
        }
        System.out.println("Input: "+input);
        System.out.println("is a palindrome: "+isPalindrome);
    }
}
