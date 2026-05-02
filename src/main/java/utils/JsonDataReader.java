  package utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {

    public static List<Map<String, String>> getProductData() {
        try {
            String path = System.getProperty("user.dir")
                    + "/src/test/resources/testdata/products.json";

            String jsonContent = new String(Files.readAllBytes(Paths.get(path)));

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(jsonContent,
                    new TypeReference<List<Map<String, String>>>() {});

        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }
}  

