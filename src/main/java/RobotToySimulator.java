import models.Position;
import models.RobotToy;
import service.Command;
import service.PlaceCommand;
import service.ReportCommand;
import util.FileProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nasim Salmany
 */
public class RobotToySimulator {
    private String commandsFileName;

    public RobotToySimulator(String commandsFileName) {
        this.commandsFileName = commandsFileName;
    }

    /**
     * the robot to simulator will start the game with calling of start() method
     *
     * @return
     */
    public List<Position> start() {
        // create robot toy
        RobotToy robotToy = new RobotToy();
        // seenPositions stores positions after Report command
        List<Position> seenPositions = new ArrayList<>();

        // process and find the commands from input file
        List<Command> commands = FileProcessor.processFile(commandsFileName);

        for (Command command : commands) {
            if (command instanceof ReportCommand) {
                if (robotToy.getPosition().isPresent()) {
                    command.execute(robotToy)
                            .getPosition()
                            .map(seenPositions::add);
                }
            }
            if (command instanceof PlaceCommand) {
                command.execute(robotToy);
            } else if (robotToy.getPosition().isPresent()) {
                command.execute(robotToy);
            }
        }

        // print all posi
        seenPositions.stream().forEach(position -> {
            System.out.println("Output: "
                    + position.getX() + "," + position.getY() + "," + position.getFacing());
        });

        return seenPositions;
    }
}