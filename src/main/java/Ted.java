import java.util.Scanner;

public class Ted {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Ted.\n\nWhat can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            System.out.println("    ____________________________________________________________");
            if (command.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
            } else {
                System.out.println("     " + command);
            }
            System.out.println("    ____________________________________________________________");

            if (command.equals("bye")) {
                break;
            }
        }
    }
}
