package com.awesomeproject.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestHelper {

    public static final String TEST_DATA_PATH = "src/test/resources";

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
            .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
            .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));

    static String readJsonFromFile(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String currentLine = reader.readLine();

            while (currentLine != null) {
                currentLine = currentLine.trim();
                stringBuilder.append(currentLine);
                currentLine = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    static <T> T fromJsonToObject(String withdrawalItemJSON, Class<T> clazz) throws IOException {
        return objectMapper.readValue(withdrawalItemJSON, clazz);
    }

    static <T> String toJsonObject(T t) throws JsonProcessingException {
        return objectMapper.writeValueAsString(t);
    }

    static String getContentFromFile(String path) {
        File file = new File(TEST_DATA_PATH + path);
        try {
            JsonNode jsonNode = objectMapper.readTree(file);
            return objectMapper.writeValueAsString(jsonNode);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Not able to read / parse JSON content from file by given path: '%s'", path), e);
        }
    }
}
