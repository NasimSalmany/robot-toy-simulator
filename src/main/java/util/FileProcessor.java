package util;

import service.Command;
import service.CommandFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Nasim Salmany
 */
public class FileProcessor {

    public static List<Command> processFile(String fileName) {
        List<Command> commands = new ArrayList<>(Collections.emptyList());
        File file = getFileFromResources(fileName);

        FileReader reader = null;
        try {
            reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);

            String commandString;
            while ((commandString = br.readLine()) != null) {
                CommandFactory.getCommand(commandString.trim()).ifPresent(commands::add);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return commands;
    }

    public static File getFileFromResources(String fileName) {
        ClassLoader classLoader = FileProcessor.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
