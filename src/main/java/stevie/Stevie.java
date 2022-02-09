package stevie;

import java.io.File;

import stevie.command.Command;
import stevie.exception.StevieException;
import stevie.parser.StevieParser;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

/**
 * stevie.Stevie is a class that serves as a user interface to allow access to an underlying
 * task manager. Users can use stevie.Stevie to add in their upcoming tasks/events/deadlines,
 * and to mark them as completed when necessary. If you want to use a different location for
 * your saved data, replace the path field.
 */
public class Stevie {
    /**
     * Path to the save file for task list
     */
    private static final String path = "src" + File.separator + "main"
            + File.separator + "data" + File.separator + "tasks.txt";

    /**
     * Task list to store all of user's upcoming tasks
     */
    private final TaskList tasks;

    /**
     * Ui to receive user inputs and output responses
     */
    private final StevieUi ui;

    /**
     * Handles saving and loading of user's tasks
     */
    private final TaskDataHandler storage;

    /**
     * Constructor for Stevie
     */
    public Stevie() {
        ui = new StevieUi();
        storage = new TaskDataHandler(path);
        tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Starts CLI for Stevie
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Stevie().run();
    }

    /**
     * Returns the responses from executing a command. Response can be a string to
     * indicate a successful command, or an exception message.
     * @param userInput string input by user
     * @return response string
     */
    public String getResponse(String userInput) {
        String out;
        try {
            Command command = StevieParser.parse(userInput);
            out = command.execute(tasks, storage, ui);
        } catch (StevieException ex) {
            ui.outputMessage(ex.getMessage());
            out = ex.getMessage();
        }
        return out;
    }

    /**
     * Method to start and sustain command-line session with user.
     */
    private void run() {
        ui.greet();
        boolean isExit = false;
        String userIn;
        while (!isExit) {
            try {
                userIn = ui.getUserInput();
                Command command = StevieParser.parse(userIn);
                command.execute(tasks, storage, ui);
                isExit = command.isExit();
            } catch (StevieException ex) {
                ui.outputMessage(ex.getMessage());
            }
        }
        ui.terminate();
    }
}
