package service;

import enums.CommandType;
import java.util.Optional;

/**
 * @author Nasim Salmany
 * create and return command class from command String in the command file
 */
public class CommandFactory {

    // PLACE command pattern based on document
    private static final String PLACE_COMMAND_REGEX = "^(PLACE)\\s\\d+,\\d+,(NORTH|WEST|EAST|SOUTH)$";

    public static Optional<Command> getCommand(String commandString) {
        Optional<Command> command = Optional.empty();
        if (commandString.matches(PLACE_COMMAND_REGEX)) {
            command = Optional.of(new PlaceCommand(commandString));
        } else if (commandString.equals(CommandType.LEFT.name())) {
            command = Optional.of(new RotateLeftCommand());

        } else if (commandString.equals(CommandType.RIGHT.name())) {
            command = Optional.of(new RotateRightCommand());

        } else if (commandString.equals(CommandType.REPORT.name())) {
            command = Optional.of(new ReportCommand());

        } else if (commandString.equals(CommandType.MOVE.name())) {
            command = Optional.of(new MoveCommand());

        }
        return command;
    }
}
