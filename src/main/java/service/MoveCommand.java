package service;

import enums.Direction;
import models.Position;
import models.RobotToy;

import java.util.Optional;

import static constants.TableTopConstants.X_SIZE;
import static constants.TableTopConstants.Y_SIZE;

/**
 * @author Nasim Salmany
 */

public class MoveCommand implements Command {

    public RobotToy execute(RobotToy robotToy) {
        robotToy.getPosition().ifPresent(position -> {
            Direction direction = position.getFacing();
            switch (direction) {
                case NORTH:
                    position = incrementPositionY(position);
                    break;
                case SOUTH:
                    position = decrementPositionY(position);
                    break;
                case WEST:
                    position = decrementPositionX(position);
                    break;
                case EAST:
                    position = incrementPositionX(position);
                    break;
            }
            robotToy.setPosition(Optional.of(position));
        });
        return robotToy;
    }

    private static Position incrementPositionY(Position position) {
        if (position.getY() < Y_SIZE -1 )
            position.setY(position.getY() + 1);
        else
            System.out.println("WARNING! Toy robot decided to ignore Move command.");
        return position;
    }

    private static Position decrementPositionY(Position position) {
        if (position.getX() > 0)
            position.setY(position.getY() - 1);
        else
            System.out.println("WARNING! Toy robot decided to ignore Move command.");
        return position;
    }

    private static Position incrementPositionX(Position position) {
        if (position.getX() < X_SIZE -1)
            position.setX(position.getX() + 1);
        else
            System.out.println("WARNING! Toy robot decided to ignore Move command.");
        return position;
    }

    private static Position decrementPositionX(Position position) {
        if (position.getX() > 0)
            position.setX(position.getX() - 1);
        else
            System.out.println("WARNING! Toy robot decided to ignore Move command.");
        return position;
    }
}
