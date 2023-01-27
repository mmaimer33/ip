import java.time.LocalDate;

/**
 * A Command subclass for the deadline command.
 */
public class DeadlineCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList list, String command) throws DukeException{
        String[] parts = command.split("\\s+");
        int byIndex = Parser.indexOf(parts, "/by");

        // Get name of task.
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < byIndex; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }
        // Get due date.
        StringBuilder dueDate = new StringBuilder();
        for (int i = byIndex+1; i < parts.length; i++) {
            dueDate.append(i == byIndex+1 ? "" : Values.SPACE);
            dueDate.append(parts[i]);
        }

        if (taskName.length() == 0 || dueDate.length() == 0) {
            throw new DukeException("Please provide both a deadline description and a due date.\n" +
                    "\tFormat: deadline <description> /by <due_date>");
        }

        // Convert date string to LocalDate
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dueDate.toString());
        } catch (RuntimeException re) {
            throw new DukeException("Could not parse date. Please use format 'yyyy-mm-dd'.");
        }

        Task task = new Deadline(taskName.toString(), localDate);
        list.addTask(task);
        ui.pixlPrint("Added new deadline!\n" +
                "\t" + task.formatTask() +
                "\nYou now have " + list.getSize() + " task(s) in the list.");
    }
}
