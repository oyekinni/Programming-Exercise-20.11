import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MatchGroupingSymbols {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MatchGroupingSymbols <JavaSourceFile>");
            return;
        }

        String fileName = args[0];
        File sourceFile = new File(fileName);

        if (!sourceFile.exists()) {
            System.out.println("Error: File not found.");
            return;
        }

        Stack<Character> stack = new Stack<>();

        try (Scanner input = new Scanner(sourceFile)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();

                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);

                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty()) {
                            System.out.println("The grouping symbols are not matched.");
                            return;
                        }

                        char top = stack.pop();

                        if (!isMatchingPair(top, ch)) {
                            System.out.println("The grouping symbols are not matched.");
                            return;
                        }
                    }
                }
            }

            if (stack.isEmpty()) {
                System.out.println("The grouping symbols are matched.");
            } else {
                System.out.println("The grouping symbols are not matched.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
        }
    }

    public static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
