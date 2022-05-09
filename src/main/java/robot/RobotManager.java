package robot;

import com.fasterxml.jackson.databind.JsonNode;
import configuration.Json;

import java.io.FileReader;
import java.io.IOException;

public class RobotManager {
    private static RobotManager robotManager;
    private static Robot robotConfig;

    public static RobotManager getInstance() {
        if (robotManager == null) {
            robotManager = new RobotManager();
        }
        return robotManager;
    }

    public void loadConfiguration(String fileName) throws IOException {
        FileReader readFile = new FileReader(fileName);
        StringBuilder ab = new StringBuilder();
        int index;
        while ((index = readFile.read()) != -1) {
            ab.append((char) index);
        }
        // Searches json node and fetches jason data
        JsonNode sorting = Json.searchNode(ab.toString());
        robotConfig = Json.fetchingJson(sorting, Robot.class);
    }

    public Robot getRobotStats() throws RobotExceptions {
        if (robotManager == null) {
            throw new RobotExceptions("Command is not available.");
        }
        return robotConfig;
    }
}

