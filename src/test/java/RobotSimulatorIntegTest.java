import enums.Direction;
import models.Position;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Nasim Salmany
 */
public class RobotSimulatorIntegTest {
    private RobotToySimulator robotSimulator;

    @Test
    public void fail_move_when_robot_is_not_on_table() {
        robotSimulator = new RobotToySimulator("move_left_command");
        List<Position> positionResult = robotSimulator.start();

        assertThat(positionResult.size(), equalTo(0));
    }

    @Test
    public void success_place_robot_on_table() {
        robotSimulator = new RobotToySimulator("place_command");
        List<Position> positionResult = robotSimulator.start();

        assertThat(positionResult.size(), equalTo(1));
        positionResult.stream().forEach(p -> {
            assertThat(p.getX(), equalTo(0));
            assertThat(p.getY(), equalTo(0));
            assertThat(p.getFacing(), equalTo(Direction.NORTH));
        });
    }

    @Test
    public void success_place_move_robot_on_table_with_several_command_set() {
        robotSimulator = new RobotToySimulator("command_set");
        List<Position> positionResult = robotSimulator.start();

        assertThat(positionResult.size(), equalTo(3));
    }

    @Test
    public void success_place_move_robot_on_table_with_command_set_2() {
        robotSimulator = new RobotToySimulator("command_set_2");
        List<Position> positionResult = robotSimulator.start();

        assertThat(positionResult.size(), equalTo(2));
    }

    @Test
    public void success_move_robot_on_table() {
        robotSimulator = new RobotToySimulator("place_move_command");
        List<Position> positionResult = robotSimulator.start();

        assertThat(positionResult.size(), equalTo(1));
    }

    @Test
    public void success_place_rotate_left_robot_on_table() {
        robotSimulator = new RobotToySimulator("place_left_command");
        List<Position> positionResult = robotSimulator.start();

        assertThat(positionResult.size(), equalTo(1));
    }

    @Test
    public void success_place_rotate_right_robot_on_table() {
        robotSimulator = new RobotToySimulator("place_right_command");
        List<Position> positionResult = robotSimulator.start();

        assertThat(positionResult.size(), equalTo(1));
    }

    @Test
    public void success_get_report_of_robot_on_table() {
        robotSimulator = new RobotToySimulator("place_move_report_command");
        List<Position> positionResult = robotSimulator.start();

        assertThat(positionResult.size(), equalTo(1));
    }
}
