import java.util.Scanner;

public class Ted {
    private static final int MAX_TASKS = 100;
    private static final String SEPARATOR = "    ____________________________________________________________";

    public static void main(String[] args) {
        System.out.println("Hello! I'm Ted.\n\nWhat can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[MAX_TASKS];
        boolean[] completed = new boolean[MAX_TASKS];
        int taskCount = 0;

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            boolean isBye = command.equals("bye");

            System.out.println(SEPARATOR);
            if (isBye) {
                System.out.println("     Bye. Hope to see you again soon!");
            } else if (command.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    String status = completed[i] ? "[X]" : "[ ]";
                    System.out.println("     " + (i + 1) + "." + status + " " + tasks[i]);
                }
            } else if (command.equals("mark") || command.startsWith("mark ")) {
                String taskNumber = command.substring("mark".length()).trim();
                try {
                    int taskIndex = Integer.parseInt(taskNumber) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        completed[taskIndex] = true;
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       [X] " + tasks[taskIndex]);
                    } else {
                        System.out.println("     That task number is not in the list.");
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("     Please provide a valid task number.");
                }
            } else if (command.equals("unmark") || command.startsWith("unmark ")) {
                String taskNumber = command.substring("unmark".length()).trim();
                try {
                    int taskIndex = Integer.parseInt(taskNumber) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        completed[taskIndex] = false;
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("       [ ] " + tasks[taskIndex]);
                    } else {
                        System.out.println("     That task number is not in the list.");
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("     Please provide a valid task number.");
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
