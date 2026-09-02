package ted;

import java.util.Scanner;

/**
 * Runs the text-based Ted task manager.
 */
public class Ted {
    private static final int MAX_TASKS = 100;
    private static final String SEPARATOR = "    ____________________________________________________________";
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";

    /**
     * Starts Ted and processes commands from standard input.
     *
     * @param args Command-line arguments. Ted ignores these arguments.
     */
    public static void main(String[] args) {
        System.out.println("Hello! I'm Ted.\n\nWhat can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            boolean isBye = command.equals("bye");

            System.out.println(SEPARATOR);
            if (isBye) {
                System.out.println("     Bye. Hope to see you again soon!");
            } else if (command.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("     " + (i + 1) + "." + tasks[i]);
                }
            } else if (command.equals("mark") || command.startsWith("mark ")) {
                String taskNumber = command.substring("mark".length()).trim();
                try {
                    int taskIndex = Integer.parseInt(taskNumber) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsDone();
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       " + tasks[taskIndex]);
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
                        tasks[taskIndex].unmarkAsDone();
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("       " + tasks[taskIndex]);
                    } else {
                        System.out.println("     That task number is not in the list.");
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("     Please provide a valid task number.");
                }
            } else if (command.equals("todo") || command.startsWith("todo ")) {
                String description = command.substring("todo".length()).trim();
                if (description.isEmpty()) {
                    System.out.println("     The description of a todo cannot be empty.");
                } else if (taskCount >= tasks.length) {
                    System.out.println("     Ted cannot store any more tasks.");
                } else {
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    printTaskAdded(tasks[taskCount - 1], taskCount);
                }
            } else if (command.equals("deadline") || command.startsWith("deadline ")) {
                String details = command.substring("deadline".length()).trim();
                int separatorIndex = details.indexOf(DEADLINE_SEPARATOR);
                if (separatorIndex <= 0
                        || separatorIndex + DEADLINE_SEPARATOR.length() >= details.length()) {
                    System.out.println("     Use: deadline DESCRIPTION /by DATE_OR_TIME");
                } else if (taskCount >= tasks.length) {
                    System.out.println("     Ted cannot store any more tasks.");
                } else {
                    String description = details.substring(0, separatorIndex).trim();
                    String by = details.substring(separatorIndex + DEADLINE_SEPARATOR.length()).trim();
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    printTaskAdded(tasks[taskCount - 1], taskCount);
                }
            } else if (command.equals("event") || command.startsWith("event ")) {
                String details = command.substring("event".length()).trim();
                int fromSeparatorIndex = details.indexOf(EVENT_FROM_SEPARATOR);
                int toSeparatorIndex = details.indexOf(EVENT_TO_SEPARATOR,
                        fromSeparatorIndex + EVENT_FROM_SEPARATOR.length());
                boolean hasInvalidEventDetails = fromSeparatorIndex <= 0
                        || toSeparatorIndex <= fromSeparatorIndex + EVENT_FROM_SEPARATOR.length()
                        || toSeparatorIndex + EVENT_TO_SEPARATOR.length() >= details.length();
                if (hasInvalidEventDetails) {
                    System.out.println("     Use: event DESCRIPTION /from START /to END");
                } else if (taskCount >= tasks.length) {
                    System.out.println("     Ted cannot store any more tasks.");
                } else {
                    String description = details.substring(0, fromSeparatorIndex).trim();
                    String from = details.substring(fromSeparatorIndex + EVENT_FROM_SEPARATOR.length(),
                            toSeparatorIndex).trim();
                    String to = details.substring(toSeparatorIndex + EVENT_TO_SEPARATOR.length()).trim();
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    printTaskAdded(tasks[taskCount - 1], taskCount);
                }
            } else {
                System.out.println("     I do not understand that command.");
            }
            System.out.println(SEPARATOR);

            if (isBye) {
                break;
            }
        }
    }

    /**
     * Prints confirmation that a task was added and reports the new task count.
     *
     * @param task The task that was added.
     * @param taskCount The number of stored tasks after the addition.
     */
    private static void printTaskAdded(Task task, int taskCount) {
        String taskNoun = taskCount == 1 ? "task" : "tasks";
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskCount + " " + taskNoun + " in the list.");
    }
}
