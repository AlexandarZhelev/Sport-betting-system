import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SportsBettingApp {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        System.out.println("\nWelcome to the Sports Betting App TOTO 1!\n");
        System.out.println("The string must contain 5 '1's, 3 'X's, and 2 '2's");
        System.out.println("There should not be more than two consecutive '1's or 'X's\n");
        System.out.println("Copy the example and paste it as input to the program");
        System.out.println("Example input 11X11X1X22\n");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string of 10 characters (1, 2, and X) and press ENTER: ");
        String input = scanner.nextLine();
        System.out.println();

        if (!isValidInput(input)) {
            System.out.println("Invalid input! Only 1, 2, and X characters are allowed.");
            return;
        }

        if (!isValidLength(input)) {
            System.out.println("Invalid length! The string must contain exactly 10 characters.");
            return;
        }

        if (!isValidComposition(input)) {
            System.out.println("Invalid composition! The string must contain 5 '1's, 3 'X's, and 2 '2's.");
            return;
        }

        if (hasConsecutiveDuplicates(input)) {
            System.out.println("Invalid sequence! There should not be more than two consecutive '1's or 'X's.");
            return;
        }

        List<String> combinations = generateCombinations(input);
        for (String combination : combinations) {
            System.out.println(combination);
        }
    }

    public static boolean isValidInput(String input) {
        return input.matches("[12X]+");
    }

    public static boolean isValidLength(String input) {
        return input.length() == 10;
    }

    public static boolean isValidComposition(String input) {
        int count1 = countOccurrences(input, '1');
        int countX = countOccurrences(input, 'X');
        int count2 = countOccurrences(input, '2');

        return count1 == 5 && countX == 3 && count2 == 2;
    }

    public static boolean hasConsecutiveDuplicates(String input) {
        for (int i = 0; i < input.length() - 2; i++) {
            if ((input.charAt(i) == '1' && input.charAt(i + 1) == '1' && input.charAt(i + 2) == '1')
                    || (input.charAt(i) == 'X' && input.charAt(i + 1) == 'X' && input.charAt(i + 2) == 'X')) {
                return true;
            }
        }

        return false;
    }

    public static List<String> generateCombinations(String input) {
        List<String> combinations = new ArrayList<>();
        generateCombinationsHelper(input.toCharArray(), 0, combinations);

        return combinations;
    }

    public static void generateCombinationsHelper(char[] chars, int index, List<String> combinations) {
        if (index == chars.length) {
            combinations.add(new String(chars));
            return;
        }

        if (chars[index] != '1' && chars[index] != 'X') {
            generateCombinationsHelper(chars, index + 1, combinations);
            return;
        }

        generateCombinationsHelper(chars, index + 1, combinations);
        swapWithNext2(chars, index, combinations);
        swapWithNext2(chars, index, combinations);
    }

    public static void swapWithNext2(char[] chars, int index, List<String> combinations) {
        for (int i = index + 1; i < chars.length - 1; i++) {
            if (chars[i] == '2') {
                swap(chars, index, i);
                swap(chars, i, i + 1);
                combinations.add(new String(chars));
                swap(chars, i, i + 1); // Backtrack
                swap(chars, index, i); // Backtrack
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static int countOccurrences(String input, char target) {
        int count = 0;
        for (char c : input.toCharArray()) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }
}