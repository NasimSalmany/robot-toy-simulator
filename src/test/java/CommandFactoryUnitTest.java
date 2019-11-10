import org.junit.Test;
import service.*;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Nasim Salmany
 */
public class CommandFactoryUnitTest {

    @Test
    public void success_get_place_command_from_input_string() {
        Optional<Command> command = CommandFactory.getCommand("PLACE 0,0,NORTH");
        assertThat(command.isPresent(), equalTo(true));
        command.ifPresent(c -> {
            boolean isPlaceCommand = c instanceof PlaceCommand;

            assertThat(isPlaceCommand, equalTo(true));
        });
    }

    @Test
    public void success_get_move_command_from_input_string() {
        Optional<Command> command = CommandFactory.getCommand("MOVE");
        assertThat(command.isPresent(), equalTo(true));
        command.ifPresent(c -> {
            boolean isMoveCommand = c instanceof MoveCommand;

            assertThat(isMoveCommand, equalTo(true));

        });

    }

    @Test
    public void success_get_left_command_from_input_string() {
        Optional<Command> command = CommandFactory.getCommand("LEFT");
        assertThat(command.isPresent(), equalTo(true));
        command.ifPresent(c -> {
            boolean isRotateLeftCommand = c instanceof RotateLeftCommand;

            assertThat(isRotateLeftCommand, equalTo(true));
        });

    }

    @Test
    public void success_get_right_command_from_input_string() {
        Optional<Command> command = CommandFactory.getCommand("RIGHT");
        assertThat(command.isPresent(), equalTo(true));
        command.ifPresent(c -> {
            boolean isRotateRightCommand = c instanceof RotateRightCommand;

            assertThat(isRotateRightCommand, equalTo(true));
        });


    }

    @Test
    public void fail_get_right_command_from_invalid_input_string() {
        Optional<Command> command = CommandFactory.getCommand("RIGHT 0,0,NORTH");
        assertThat(command, equalTo(Optional.empty()));
    }

    @Test
    public void success_get_report_command_from_input_string() {
        Optional<Command> command = CommandFactory.getCommand("REPORT");

        assertThat(command.isPresent(), equalTo(true));
        command.ifPresent(c -> {
            boolean isReportCommand = c instanceof ReportCommand;

            assertThat(isReportCommand, equalTo(true));
        });
    }

    @Test
    public void fail_get_command_from_invalid_input_string() {
        Optional<Command> command = CommandFactory.getCommand("invalid");
        assertThat(command, equalTo(Optional.empty()));

    }

}
