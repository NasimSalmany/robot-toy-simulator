import enums.Direction;
import models.Position;
import models.RobotToy;
import org.junit.Test;
import service.MoveCommand;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Nasim Salmany
 */
public class MoveCommandUnitTest {
    private MoveCommand moveCommand = new MoveCommand();
    private RobotToy robotToy;
    private Position position;

    @Test
    public void success_move_with_on_table_robot_toy() {
        position = new Position(0, 0, Direction.NORTH);
        robotToy = new RobotToy();
        robotToy.setPosition(Optional.of(position));

        RobotToy robotToyAfterCommand = moveCommand.execute(robotToy);
        robotToyAfterCommand.getPosition().ifPresent(p -> {
            assertThat(p.getX(), equalTo(0));
            assertThat(p.getY(), equalTo(1));
            assertThat(p.getFacing(), equalTo(Direction.NORTH));
        });
    }

    @Test
    public void fail_move_when_robot_toy_is_not_on_table() {
        robotToy = new RobotToy();
        RobotToy robotToyAfterCommand = moveCommand.execute(robotToy);

        assertThat(robotToyAfterCommand.getPosition(), equalTo(Optional.empty()));
    }

    @Test
    public void fail_move_robot_toy_when_next_position_is_not_on_table() {
        position = new Position(4, 4, Direction.NORTH);
        robotToy = new RobotToy();
        robotToy.setPosition(Optional.of(position));

        RobotToy robotToyAfterCommand = moveCommand.execute(robotToy);
        robotToyAfterCommand.getPosition().ifPresent(p -> {
            assertThat(p.getX(), equalTo(4));
            assertThat(p.getY(), equalTo(4));
            assertThat(p.getFacing(), equalTo(Direction.NORTH));
        });
    }
}
