package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Values;

/**
 * A Command subclass for the find command.
 */
public class FindCommand extends Command{

    @Override
    public void execute(Ui ui, TaskList list, String command) throws DukeException {
        String[] parts = command.split(Values.SPACEX);
        if (parts.length == 1) {
            throw new DukeException("Please enter a keyword to search for.");
        }

        TaskList foundTasks = list.find(parts[1]);
        if (foundTasks.getSize() == 0) {
            ui.pixlPrint("No matching tasks found :/");
            return;
        }
        StringBuilder output = new StringBuilder("These are the matching tasks from your list:\n");
        for (int i = 0; i < foundTasks.getSize(); i++) {
            output.append("\t").append(i + 1).append(". ").append(foundTasks.getTask(i).formatTask()).append("\n");
        }
        ui.pixlPrint(output.toString());
    }
}
