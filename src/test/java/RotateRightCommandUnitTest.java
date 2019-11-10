import enums.Direction;
import models.Position;
import models.RobotToy;
import org.junit.Test;
import service.RotateRightCommand;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Nasim Salmany
 */
public class RotateRightCommandUnitTest {
    private RotateRightCommand rotateRightCommand = new RotateRightCommand();
    private RobotToy robotToy;
    private Position position;

    @Test
    public void success_rotate_right_with_on_table_robot_toy() {
        position = new Position(0, 0, Direction.NORTH);
        robotToy = new RobotToy();
        robotToy.setPosition(Optional.of(position));

        RobotToy robotToyCommanded = rotateRightCommand.execute(robotToy);
        robotToyCommanded.getPosition().ifPresent(p -> {
            assertThat(p.getX(), equalTo(0));
            assertThat(p.getY(), equalTo(0));
            assertThat(p.getFacing(), equalTo(Direction.EAST));
        });
    }

    @Test
    public void fail_rotate_right_when_robot_toy_is_not_on_table() {
        robotToy = new RobotToy();

        RobotToy robotToyAfterCommand = rotateRightCommand.execute(robotToy);
        assertThat(robotToyAfterCommand.getPosition(), equalTo(Optional.empty()));
    }
}
