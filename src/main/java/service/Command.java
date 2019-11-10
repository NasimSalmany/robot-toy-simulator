package service;

import models.RobotToy;

/**
 * @author Nasim Salmany
 * Command interface has PlaceCommand, MoveCommand, ReportCommand,
 * RotateRightCommand, RotateLeftCommand as deferent command type implementation
 */
public interface Command {
    RobotToy execute(RobotToy robotToy);
}