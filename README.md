# Ted

Ted is a command-line task manager for keeping track of todos, deadlines, and
events. It is an individual project (iP) for CS2113 and is implemented in
Java using object-oriented design.

## Features

- Add todos with a description.
- Add deadlines with a due date in `yyyy-MM-dd` format.
- Add events with a start and end date or time.
- List all tasks in the order they were added.
- Mark tasks as done or not done.
- Save tasks automatically in `data/ted.txt` and restore them when Ted starts.
- Reject incomplete deadline and event commands with a usage message.
- Report invalid commands and task numbers with clear error messages.

## Quick start

### Prerequisites

- Java Development Kit (JDK) 25 to build Ted. The resulting JAR runs on Java 17
  or newer.

### Run Ted

Build the executable JAR from the project root with Java 25:

```text
powershell -ExecutionPolicy Bypass -File scripts/build-jar.ps1
```

The JAR is created at `build/libs/ted-1.0.0.jar`. Copy it into an empty
folder, open a command window in that folder, and run:

```text
java -jar "ted-1.0.0.jar"
```

Ted stores its task data in a `data` folder beside the JAR's working folder.
The JAR is generated output and must not be committed. To distribute it,
attach it to a GitHub release for the corresponding version.

## Usage

| Command | Example | Description |
| --- | --- | --- |
| `todo DESCRIPTION` | `todo borrow book` | Adds a todo. |
| `deadline DESCRIPTION /by yyyy-MM-dd` | `deadline return book /by 2026-10-15` | Adds a deadline. |
| `event DESCRIPTION /from START /to END` | `event project meeting /from Mon 2pm /to 4pm` | Adds an event. |
| `list` | `list` | Displays all tasks. |
| `mark TASK_NUMBER` | `mark 2` | Marks a task as done. |
| `unmark TASK_NUMBER` | `unmark 2` | Marks a task as not done. |
| `bye` | `bye` | Exits Ted. |

Deadline dates are stored as `LocalDate` values and displayed as `MMM dd yyyy`
in English. Invalid dates, such as `2026-02-30`, are rejected without adding a
task. Event details are kept as entered. For example:

```text
todo borrow book
    Got it. I've added this task:
      [T][ ] borrow book

deadline return book /by 2026-10-15
    Got it. I've added this task:
      [D][ ] return book (by: Oct 15 2026)

event project meeting /from Mon 2pm /to 4pm
    Got it. I've added this task:
      [E][ ] project meeting (from: Mon 2pm to: 4pm)
```

Saved deadlines use `yyyy-MM-dd` dates. Older deadline records containing
free text, such as `Sunday`, are reported as invalid and skipped when loading.

## Documentation and testing

- [Ted User Guide](docs/README.md)
- [UI test plan](test/ui-test-plan.md)
- [Latest UI test transcript](test/ui-test-session.txt)

## Acknowledgements

OpenAI Codex was used extensively during development to review and refactor
command handling, draft documentation, and run UI regression checks. The
resulting changes were checked with Java 25 compilation and the documented UI
test cases.
