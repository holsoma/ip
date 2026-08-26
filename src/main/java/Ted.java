import java.util.Scanner;

public class Ted {
    private static final int MAX_TASKS = 100;
    private static final String SEPARATOR = "    ____________________________________________________________";

    public static void main(String[] args) {
        System.out.println("Hello! I'm Ted.\n\nWhat can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[MAX_TASKS];
        int taskCount = 0;

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            boolean isBye = command.equals("bye");

            System.out.println(SEPARATOR);
            if (isBye) {
                System.out.println("     Bye. Hope to see you again soon!");
            } else if (command.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
            } else if (taskCount < tasks.length) {
                tasks[taskCount] = command;
                taskCount++;
                System.out.println("     added: " + command);
            } else {
                System.out.println("     Ted cannot store any more tasks.");
            }
            System.out.println(SEPARATOR);

            if (isBye) {
                break;
            }
        }
    }
}
