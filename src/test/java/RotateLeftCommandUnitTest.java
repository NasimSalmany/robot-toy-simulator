import enums.Direction;
import models.Position;
import models.RobotToy;
import org.junit.Test;
import service.RotateLeftCommand;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Nasim Salmany
 */
public class RotateLeftCommandUnitTest {
    private RotateLeftCommand rotateLeftCommand = new RotateLeftCommand();
    private RobotToy robotToy;
    private Position position;

    @Test
    public void success_rotate_left_with_on_table_robot_toy() {
        position = new Position(0, 0, Direction.NORTH);
        robotToy = new RobotToy();
        robotToy.setPosition(Optional.of(position));

        RobotToy robotToyAfterCommand = rotateLeftCommand.execute(robotToy);
        robotToyAfterCommand.getPosition().ifPresent(p -> {
            assertThat(p.getX(), equalTo(0));
            assertThat(p.getY(), equalTo(0));
            assertThat(p.getFacing(), equalTo(Direction.WEST));
        });
    }

    @Test
    public void fail_rotate_left_when_robot_toy_is_not_on_table() {
        robotToy = new RobotToy();

        RobotToy robotToyAfterCommand = rotateLeftCommand.execute(robotToy);
        assertThat(robotToyAfterCommand.getPosition(), equalTo(Optional.empty()));
    }
}
