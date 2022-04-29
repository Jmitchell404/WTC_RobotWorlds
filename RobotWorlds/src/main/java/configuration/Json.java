package configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    /*
    The configuration.Json class handles configuration.Json Objects using Jackson.
    Mapping Objects and handling the data that is sent and fetched
    from raw data.
    */
    private static final ObjectMapper jsonObjMapper = initJsonObject();

    //   Instantiates our protocol by creating a new object mapper
    public static ObjectMapper initJsonObject() {
        ObjectMapper protocol = new ObjectMapper();
        protocol.configure((DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES), false);
        return protocol;
    }

    // Searching configuration.Json nodes for obj parse
    public static JsonNode searchNode(String jsonNode) throws IOException {
        return jsonObjMapper.readTree(jsonNode);
    }

    // Fetching node with class Attributes from
    public static <obj>  obj fetchingJson(JsonNode node, Class<obj> classObj ) throws JsonProcessingException {
        return jsonObjMapper.treeToValue(node, classObj);
    }

    // Sending configuration.Json data with obj attributes.
    public static JsonNode dispatchJson(Object nodeObj) throws JsonProcessingException {
        return jsonObjMapper.valueToTree(nodeObj);
    }

    public static String renderConfigSerialise(Object nodeObj, Boolean bool) throws JsonProcessingException {
        return renderJson(nodeObj,true);
    }

    public static String renderConfig(Object nodeObj, Boolean bool) throws JsonProcessingException {
        return renderJson(nodeObj,false);
    }

    // Rendering data as string of interpretation with a boolean state. generate
    public static String renderJson(Object rawData, Boolean serialise) throws JsonProcessingException {
        ObjectWriter render = jsonObjMapper.writer();
        if (serialise){
            render = render.with(SerializationFeature.INDENT_OUTPUT);
        }
        return render.writeValueAsString(rawData);
    }

}