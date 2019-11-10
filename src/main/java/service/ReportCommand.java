package service;

import models.RobotToy;

/**
 * @author Nasim Salmany
 */
public class ReportCommand implements Command {

    public RobotToy execute(RobotToy robotToy) {
        if (robotToy.getPosition().isPresent()) {
            return robotToy;
        } else {
            System.out.println("WARNING! Place Toy robot on tabletop to execute Report command.");
        }

        return robotToy;
    }
}
