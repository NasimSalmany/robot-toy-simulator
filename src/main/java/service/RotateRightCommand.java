package service;

import enums.Direction;
import models.Position;
import models.RobotToy;

import java.util.Optional;

import static enums.Direction.*;

/**
 * @author Nasim Salmany
 */
public class RotateRightCommand implements Command {

    public RobotToy execute(RobotToy robotToy) {
        robotToy.getPosition().ifPresent(robotToyPosition -> {
            Direction facing = robotToyPosition.getFacing();

            switch (facing) {
                case NORTH:
                    robotToyPosition.setFacing(EAST);
                    break;
                case SOUTH:
                    robotToyPosition.setFacing(WEST);
                    break;
                case WEST:
                    robotToyPosition.setFacing(NORTH);
                    break;
                case EAST:
                    robotToyPosition.setFacing(SOUTH);
                    break;
                default:
                    robotToyPosition.setFacing(facing);
            }
            robotToy.setPosition(Optional.of(robotToyPosition));

        });

        return robotToy;
    }
}
