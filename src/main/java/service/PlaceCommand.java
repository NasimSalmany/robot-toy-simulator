package service;

import enums.CommandType;
import enums.Direction;
import models.Position;
import models.RobotToy;

import java.util.Optional;

import static constants.TableTopConstants.X_SIZE;
import static constants.TableTopConstants.Y_SIZE;

/**
 * @author Nasim Salmany
 */
public class PlaceCommand implements Command {

    private String commandString;

    public PlaceCommand(String commandString) {
        this.commandString = commandString;
    }

    @Override
    public RobotToy execute(RobotToy robotToy) {
        String placementArgs = commandString.replace(CommandType.PLACE.name(), "").trim();
        String[] args = placementArgs.split(",");

        Integer newX = Integer.parseInt(args[0]);
        Integer newY = Integer.parseInt(args[1]);

        if (newX < X_SIZE && newX >= 0
                && newY < Y_SIZE && newY >= 0) {
            Position robotToyPosition = new Position();
            robotToyPosition.setX(newX);
            robotToyPosition.setY(newY);
            robotToyPosition.setFacing(Direction.valueOf(args[2].trim()));
            robotToy.setPosition(Optional.of(robotToyPosition));
        } else {
            System.out.println("WARNING! Toy robot decided to ignore Place command.");
        }

        return robotToy;
    }
}
