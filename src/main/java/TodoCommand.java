/**
 * A Command subclass for the todo command.
 */
public class TodoCommand extends Command{
    @Override
    public void execute(Ui ui, TaskList list, String command) throws DukeException{
        String[] parts = command.split("\\s+");
        if (parts.length == 1) {
            throw new DukeException("ToDo description cannot be empty.");
        }

        // Get task name.
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }

        Task task = new ToDo(taskName.toString());
        list.addTask(task);
        ui.pixlPrint("Added new todo!\n" +
                "\t" + task.formatTask() +
                "\nYou now have " + list.getSize() + " task(s) in the list.");
    }
}
