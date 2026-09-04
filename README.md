# Ted

Ted is a command-line task manager for keeping track of todos, deadlines, and
events. It is an individual project (iP) for CS2113 and is implemented in
Java using object-oriented design.

## Features

- Add todos with a description.
- Add deadlines with a due date or time.
- Add events with a start and end date or time.
- List all tasks in the order they were added.
- Mark tasks as done or not done.
- Reject incomplete deadline and event commands with a usage message.

## Quick start

### Prerequisites

- Java Development Kit (JDK) 25.

### Run Ted

Compile the source files from the project root:

```text
javac -d out src/main/java/ted/*.java
```

Start Ted:

```text
java -cp out ted.Ted
```

Ted also runs directly from IntelliJ IDEA by opening
`src/main/java/ted/Ted.java` and running `Ted.main()`.

## Usage

| Command | Example | Description |
| --- | --- | --- |
| `todo DESCRIPTION` | `todo borrow book` | Adds a todo. |
| `deadline DESCRIPTION /by DATE_OR_TIME` | `deadline return book /by Sunday` | Adds a deadline. |
| `event DESCRIPTION /from START /to END` | `event project meeting /from Mon 2pm /to 4pm` | Adds an event. |
| `list` | `list` | Displays all tasks. |
| `mark TASK_NUMBER` | `mark 2` | Marks a task as done. |
| `unmark TASK_NUMBER` | `unmark 2` | Marks a task as not done. |
| `bye` | `bye` | Exits Ted. |

Descriptions and dates are kept as entered. For example:

```text
todo borrow book
    Got it. I've added this task:
      [T][ ] borrow book

deadline return book /by Sunday
    Got it. I've added this task:
      [D][ ] return book (by: Sunday)

event project meeting /from Mon 2pm /to 4pm
    Got it. I've added this task:
      [E][ ] project meeting (from: Mon 2pm to: 4pm)
```

## Documentation and testing

- [Ted User Guide](docs/README.md)
- [UI test plan](test/ui-test-plan.md)
- [Latest UI test transcript](test/ui-test-session.txt)

## Acknowledgements

OpenAI Codex was used extensively during development to review and refactor
command handling, draft documentation, and run UI regression checks. The
resulting changes were checked with Java 25 compilation and the documented UI
test cases.
