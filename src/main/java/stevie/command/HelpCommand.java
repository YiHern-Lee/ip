package stevie.command;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

public class HelpCommand extends Command {
    private static final String helpString = "\"list\": to display your activities.\n"
            + "\"bye\": to end our session.\n"
            + "\"mark <i>\" to mark the i-th task as done.\n"
            + "\"unmark <i>\" to unmark the i-th task as done.\n"
            + "\"delete <i>\" to delete the i-th task.\n"
            + "\"todo <task_name>\" to add a todo task.\n"
            + "\"deadline <task_name> /by <date>\" to add a deadline.\n"
            + "\"event <event_name> /at <date>\" to add an event.\n"
            + "Date should in format of dd/mm/yyyy HH:mm";

    /**
     * UI outputs a string to inform user on commands to operate Stevie with.
     * @param tasks   task list to make changes on
     * @param storage to handle the saving of data
     * @param ui      to pass a response string for output
     * @return string of instruction
     */
    @Override
    public String execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        ui.outputMessage(helpString);
        return helpString;
    }
}