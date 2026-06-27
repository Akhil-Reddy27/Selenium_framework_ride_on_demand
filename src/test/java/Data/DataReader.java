package Data;

import org.apache.commons.io.FileUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

//    public List<HashMap<String, String>> getJsondataToMap(String filepath) throws IOException {
//       String jsoncontent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
//
//       ObjectMapper mapper = new ObjectMapper();
//        List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
//        return data;
//    }

    // moved it to baseTest so that it can be reused when ever needed
}
