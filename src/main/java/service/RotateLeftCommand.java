package service;

import enums.Direction;
import models.RobotToy;

import java.util.Optional;

import static enums.Direction.*;

/**
 * @author Nasim Salmany
 */
public class RotateLeftCommand implements Command {

    public RobotToy execute(RobotToy robotToy) {
        robotToy.getPosition().ifPresent(robotToyPosition -> {
            Direction facing = robotToyPosition.getFacing();

            switch (facing) {
                case NORTH:
                    robotToyPosition.setFacing(WEST);
                    break;
                case SOUTH:
                    robotToyPosition.setFacing(EAST);
                    break;
                case WEST:
                    robotToyPosition.setFacing(SOUTH);
                    break;
                case EAST:
                    robotToyPosition.setFacing(NORTH);
                    break;
                default:
                    robotToyPosition.setFacing(facing);
            }
            robotToy.setPosition(Optional.of(robotToyPosition));
        });

        return robotToy;
    }
}
