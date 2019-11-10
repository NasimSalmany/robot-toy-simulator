import enums.Direction;
import models.Position;
import models.RobotToy;
import org.junit.Test;
import service.ReportCommand;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Nasim Salmany
 */
public class ReportCommandUnitTest {
    private ReportCommand reportCommand = new ReportCommand();
    private RobotToy robotToy;
    private Position position;
    private ByteArrayOutputStream testOut = new ByteArrayOutputStream();
    ;

    @Test
    public void success_get_report_of_robot_on_table() {
        position = new Position(4, 3, Direction.EAST);
        robotToy = new RobotToy();
        robotToy.setPosition(Optional.of(position));

        RobotToy robotToyCommanded = reportCommand.execute(robotToy);
        robotToyCommanded.getPosition().ifPresent(p -> {
            assertThat(p.getX(), equalTo(4));
            assertThat(p.getY(), equalTo(3));
            assertThat(p.getFacing(), equalTo(Direction.EAST));
        });

    }

    @Test
    public void fail_get_report_when_robot_is_not_on_table() {
        robotToy = new RobotToy();
        RobotToy robotToyAfterCommand = reportCommand.execute(this.robotToy);
        assertThat(robotToy.getPosition(), equalTo(Optional.empty()));
    }
}
