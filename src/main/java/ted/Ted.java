package ted;

import java.util.Scanner;

/**
 * Runs the text-based Ted task manager.
 */
public class Ted {
    private static final int MAX_TASKS = 100;
    private static final String SEPARATOR = "    ____________________________________________________________";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";

    private final Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    /**
     * Starts Ted and processes commands from standard input.
     *
     * @param args Command-line arguments. Ted ignores these arguments.
     */
    public static void main(String[] args) {
        new Ted().run();
    }

    /**
     * Starts the command loop and processes commands from standard input.
     */
    private void run() {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            System.out.println(SEPARATOR);
            boolean isBye = processCommand(scanner.nextLine());
            System.out.println(SEPARATOR);
            if (isBye) {
                break;
            }
        }
    }

    /**
     * Prints the initial greeting shown when Ted starts.
     */
    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm Ted.\n\nWhat can I do for you?\n");
    }

    /**
     * Processes one command and reports whether the command ends the session.
     *
     * @param command The command entered by the user.
     * @return {@code true} when the command is {@code bye}; {@code false} otherwise.
     */
    private boolean processCommand(String command) {
        if (command.equals(BYE_COMMAND)) {
            System.out.println("     Bye. Hope to see you again soon!");
            return true;
        }

        if (command.equals(LIST_COMMAND)) {
            printTaskList();
        } else if (isCommand(command, MARK_COMMAND)) {
            updateTaskStatus(command, true);
        } else if (isCommand(command, UNMARK_COMMAND)) {
            updateTaskStatus(command, false);
        } else if (isCommand(command, TODO_COMMAND)) {
            handleTodo(command);
        } else if (isCommand(command, DEADLINE_COMMAND)) {
            handleDeadline(command);
        } else if (isCommand(command, EVENT_COMMAND)) {
            handleEvent(command);
        } else {
            System.out.println("     I do not understand that command.");
        }

        return false;
    }

    /**
     * Returns whether a command matches a command name with an optional argument.
     */
    private static boolean isCommand(String command, String commandName) {
        return command.equals(commandName) || command.startsWith(commandName + " ");
    }

    /**
     * Prints all tasks currently stored by Ted.
     */
    private void printTaskList() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
        }
    }

    /**
     * Updates a task's completion status based on a mark or unmark command.
     */
    private void updateTaskStatus(String command, boolean shouldMarkAsDone) {
        String commandName = shouldMarkAsDone ? MARK_COMMAND : UNMARK_COMMAND;
        String taskNumber = command.substring(commandName.length()).trim();
        try {
            int taskIndex = Integer.parseInt(taskNumber) - 1;
            if (taskIndex < 0 || taskIndex >= taskCount) {
                System.out.println("     That task number is not in the list.");
                return;
            }

            if (shouldMarkAsDone) {
                tasks[taskIndex].markAsDone();
                System.out.println("     Nice! I've marked this task as done:");
            } else {
                tasks[taskIndex].unmarkAsDone();
                System.out.println("     OK, I've marked this task as not done yet:");
            }
            System.out.println("       " + tasks[taskIndex]);
        } catch (NumberFormatException exception) {
            System.out.println("     Please provide a valid task number.");
        }
    }

    /**
     * Handles a todo command.
     */
    private void handleTodo(String command) {
        String description = command.substring(TODO_COMMAND.length()).trim();
        if (description.isEmpty()) {
            System.out.println("     The description of a todo cannot be empty.");
            return;
        }

        addTask(new Todo(description));
    }

    /**
     * Handles a deadline command.
     */
    private void handleDeadline(String command) {
        String details = command.substring(DEADLINE_COMMAND.length()).trim();
        int separatorIndex = details.indexOf(DEADLINE_SEPARATOR);
        if (separatorIndex <= 0
                || separatorIndex + DEADLINE_SEPARATOR.length() >= details.length()) {
            System.out.println("     Use: deadline DESCRIPTION /by DATE_OR_TIME");
            return;
        }

        String description = details.substring(0, separatorIndex).trim();
        String by = details.substring(separatorIndex + DEADLINE_SEPARATOR.length()).trim();
        addTask(new Deadline(description, by));
    }

    /**
     * Handles an event command.
     */
    private void handleEvent(String command) {
        String details = command.substring(EVENT_COMMAND.length()).trim();
        int fromSeparatorIndex = details.indexOf(EVENT_FROM_SEPARATOR);
        int toSeparatorIndex = details.indexOf(EVENT_TO_SEPARATOR,
                fromSeparatorIndex + EVENT_FROM_SEPARATOR.length());
        boolean hasInvalidEventDetails = fromSeparatorIndex <= 0
                || toSeparatorIndex <= fromSeparatorIndex + EVENT_FROM_SEPARATOR.length()
                || toSeparatorIndex + EVENT_TO_SEPARATOR.length() >= details.length();
        if (hasInvalidEventDetails) {
            System.out.println("     Use: event DESCRIPTION /from START /to END");
            return;
        }

        String description = details.substring(0, fromSeparatorIndex).trim();
        String from = details.substring(fromSeparatorIndex + EVENT_FROM_SEPARATOR.length(),
                toSeparatorIndex).trim();
        String to = details.substring(toSeparatorIndex + EVENT_TO_SEPARATOR.length()).trim();
        addTask(new Event(description, from, to));
    }

    /**
     * Adds a task when capacity remains and prints the addition confirmation.
     */
    private void addTask(Task task) {
        if (taskCount >= tasks.length) {
            System.out.println("     Ted cannot store any more tasks.");
            return;
        }

        tasks[taskCount] = task;
        taskCount++;
        printTaskAdded(task, taskCount);
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
