package duke.command;

import duke.DukeException;
import duke.Values;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * A Command subclass for the mark command.
 */
public class UnmarkCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList list, String command) throws DukeException {
        try {
            Task task = list.getTask(Integer.parseInt(command.split(Values.SPACEX)[1]) - 1);
            task.uncomplete();
            ui.pixlPrint("Un-doing the task...\n" +
                    "\t" + task.formatTask());
        } catch (Exception e) {
            throw new DukeException("Please provide a valid task number to unmark.");
        }
    }
}
