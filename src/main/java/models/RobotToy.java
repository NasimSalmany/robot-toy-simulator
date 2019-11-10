package models;

import java.util.Optional;

/**
 * @author Nasim Salmany
 */
public class RobotToy {

    private Optional<Position> position = Optional.empty();

    public Optional<Position> getPosition() {
        return position;
    }

    public void setPosition(Optional<Position> position) {
        this.position = position;
    }
}
