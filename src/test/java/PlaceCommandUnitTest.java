import enums.Direction;
import models.Position;
import models.RobotToy;
import org.junit.Test;
import service.PlaceCommand;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Nasim Salmany
 */
public class PlaceCommandUnitTest {

    private PlaceCommand placeCommand;
    private RobotToy robotToy;
    private Position position;

    @Test
    public void success_place_robot_toy_on_table() {
        robotToy = new RobotToy();
        placeCommand = new PlaceCommand("PLACE 0,0,NORTH");
        RobotToy commandedRobotToy = placeCommand.execute(robotToy);

        commandedRobotToy.getPosition().ifPresent(p -> {
            assertThat(p.getX(), equalTo(0));
            assertThat(p.getY(), equalTo(0));
            assertThat(p.getFacing(), equalTo(Direction.NORTH));
        });
    }

    @Test
    public void success_place_again_robot_toy_on_table() {
        robotToy = new RobotToy();
        placeCommand = new PlaceCommand("PLACE 0,0,NORTH");
        RobotToy commandedRobotToy = placeCommand.execute(robotToy);
        commandedRobotToy.getPosition().ifPresent(p -> {
            assertThat(p.getX(), equalTo(0));
            assertThat(p.getY(), equalTo(0));
            assertThat(p.getFacing(), equalTo(Direction.NORTH));
        });


        // replace robot toy
        placeCommand = new PlaceCommand("PLACE 1,2,EAST");
        commandedRobotToy = placeCommand.execute(robotToy);
        commandedRobotToy.getPosition().ifPresent(p -> {
            assertThat(p.getX(), equalTo(1));
            assertThat(p.getY(), equalTo(2));
            assertThat(p.getFacing(), equalTo(Direction.EAST));
        });
    }

    @Test
    public void fail_place_robot_toy_out_of_table() {
        robotToy = new RobotToy();
        placeCommand = new PlaceCommand("PLACE 5,5,NORTH");
        RobotToy commandedRobotToy = placeCommand.execute(robotToy);

        assertThat(robotToy.getPosition(), equalTo(Optional.empty()));
    }

    @Test
    public void fail_place_again_robot_toy_out_of_table() {
        robotToy = new RobotToy();
        placeCommand = new PlaceCommand("PLACE 0,0,NORTH");
        RobotToy commandedRobotToy = placeCommand.execute(robotToy);
        commandedRobotToy.getPosition().ifPresent(p -> {
            assertThat(p.getX(), equalTo(0));
            assertThat(p.getY(), equalTo(0));
            assertThat(p.getFacing(), equalTo(Direction.NORTH));
        });

        // replace robot toy
        placeCommand = new PlaceCommand("PLACE 1,6,EAST");
        commandedRobotToy = placeCommand.execute(robotToy);
        commandedRobotToy.getPosition().ifPresent(p -> {
            assertThat(p.getX(), equalTo(0));
            assertThat(p.getY(), equalTo(0));
            assertThat(p.getFacing(), equalTo(Direction.NORTH));
        });
    }

}
