package configuration;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManagement {

    private static ConfigurationManagement configuration;
    private static Configuration config;

    private ConfigurationManagement() {
    }

    public static ConfigurationManagement getInstance() {
        if (configuration == null) {
            configuration = new ConfigurationManagement();
        }
        return configuration;
    }

    public void loadConfiguration(String fileName) throws IOException {
        FileReader readFile = new FileReader(fileName);
        StringBuilder ab = new StringBuilder();
        int index;
        while ((index = readFile.read())!= -1){
            ab.append((char)index);
        }
        // Searches json node and fetches jason data
        JsonNode sorting = Json.searchNode(ab.toString());
        config = Json.fetchingJson(sorting ,Configuration.class);
    }

    public Configuration getConfiguration() throws ConfigurationException {
        if (configuration == null){
            throw new ConfigurationException("Configuration is not available.");
        }
        return config;
    }
}