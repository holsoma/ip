package ted.task;

/**
 * Represents a task that must be completed by a specified date or time.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Creates an incomplete deadline with the given description and due date or time.
     *
     * @param description The deadline description.
     * @param by The date or time by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the date or time by which this task must be completed.
     *
     * @return The deadline as entered by the user.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the display form of this deadline.
     *
     * @return The deadline type, completion status, description, and due date or time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

