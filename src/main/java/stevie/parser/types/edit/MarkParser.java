package stevie.parser.types.edit;

import stevie.command.Command;
import stevie.command.MarkCommand;
import stevie.exception.ParserException;
import stevie.exception.TaskException;

public class MarkParser extends IndexParser {
    private final boolean isDone;

    /**
     * Constructor for MarkParser.
     * @param input string to be parsed as index
     * @param isDone mark task as done if true else mark as undone
     */
    public MarkParser(String input, boolean isDone) {
        super(input);
        this.isDone = isDone;
    }

    @Override
    public Command parse() throws ParserException, TaskException {
        return new MarkCommand(isDone, getIndex());
    }
}