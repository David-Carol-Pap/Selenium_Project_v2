package utils;

import ObjectData.WebTableEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {

    private JsonReader() {

    }

    public static List<WebTableEntity> readWebTableEntities(String filePath)
    {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<List<WebTableEntity>>() {
                    }
            );
        }catch (IOException e) {
            throw new RuntimeException("Unable to read JSON file: " + filePath, e);
        }
    }
}
