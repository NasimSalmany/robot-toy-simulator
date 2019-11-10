import org.junit.Test;
import service.Command;
import service.PlaceCommand;
import util.FileProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * @author Nasim Salmany
 */
public class FileReaderUnitTest {

    @Test
    public void success_read_file() {
        File fileFromResources = FileProcessor.getFileFromResources("command_set");
        assertThat(fileFromResources, notNullValue());
    }

    @Test
    public void success_process_file() throws IOException {
        List<Command> commandResult = FileProcessor.processFile("place_command");
        Optional<Command> placeCommand = commandResult.stream()
                .filter(command -> command instanceof PlaceCommand).findFirst();

        assertThat(placeCommand, notNullValue());

        assertThat(placeCommand.isPresent(), equalTo(true));
        placeCommand.ifPresent(c -> {
            boolean isPlaceCommand = c instanceof PlaceCommand;

            assertThat(isPlaceCommand, equalTo(true));
        });
    }
}
