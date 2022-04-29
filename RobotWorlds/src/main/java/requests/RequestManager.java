package requests;

import com.fasterxml.jackson.databind.JsonNode;
import configuration.Json;

import java.io.FileReader;
import java.io.IOException;

public class RequestManager {
    private static RequestManager request;
    private static Requests protocol;

    private RequestManager() {
    }

    public static RequestManager getInstance() {
        if (request == null) {
            request = new RequestManager();
        }
        return request;
    }

    public void loadProtocol(String fileName) throws IOException {
        FileReader readFile = new FileReader(fileName);
        StringBuilder state = new StringBuilder();
        int index;
        while ((index = readFile.read())!= -1){
            state.append((char)index);
        }
        // Searches json node and fetches jason data
        JsonNode parsing = Json.searchNode(state.toString());
        protocol = Json.fetchingJson(parsing ,Requests.class);
    }

    public Requests getConfiguration() throws RequestException {
        if (request == null){
            throw new RequestException("This action is invalid.");
        }
        return protocol;
    }

}