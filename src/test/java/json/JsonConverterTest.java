package json;

import Bouquet.Bouquet;
import Flower.Flover;
import Flower.FloverType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JsonConverterTest {

    public static Stream<Arguments> pathSource() {
        return Stream.of(Arguments.arguments("con.txt", false), Arguments.arguments("test.txt", true));
    }

    public static Stream<Arguments> toJson() {
        Flover s1 = Flover.builder()
                .type(new FloverType("das"))
                .length(10)
                .pricePerFlover(12)
                .levelOfFreshness(0.2)
                .name("s1")
                .build();
        Flover s2 = Flover.builder()
                .type(new FloverType("vrc"))
                .length(10)
                .pricePerFlover(12)
                .levelOfFreshness(0.2)
                .name("s2")
                .build();
        Flover s3 = Flover.builder()
                .type(new FloverType("hba"))
                .length(10)
                .pricePerFlover(12)
                .levelOfFreshness(0.2)
                .name("s3")
                .build();
        JsonConverterData jsonConverterData = new JsonConverterData(
                new ArrayList<>(Arrays.asList(s1,s2,s3)),
                new Bouquet(new LinkedHashSet<>(Arrays.asList(s2,s1))),
                new ArrayList<>(Arrays.asList(new FloverType("das"),new FloverType("das")))
        );
        return Stream.of(Arguments.arguments(json1, jsonConverterData));
    }

    @ParameterizedTest
    @MethodSource("pathSource")
    public void setPath (String path, boolean expected){
        assertEquals(expected, JsonConverter.setPath(path));
    }

    @ParameterizedTest
    @MethodSource("toJson")
    public void convertToJsonTest (String json, JsonConverterData data) throws FileNotFoundException {
        JsonConverter.convertToJson(data);
        File file = new File("testTest");
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine())
            sb.append(scanner.nextLine()).append("\n");
        assertEquals(json, sb.toString());
    }

    @ParameterizedTest
    @MethodSource("toJson")
    public void convertToJsonConverterDataTest (String json, JsonConverterData data) {
        JsonConverter.setPath("testTest");
        JsonConverter.convertToJson(data);
        JsonConverterData data1 = JsonConverter.convertToJsonConverterData();
        assertEquals(data1.toString(), data.toString());
    }

    @Test
    public void getPathTest(){
        assertEquals("test.json", JsonConverter.getPath());
        JsonConverter.setPath("testTest");
        assertEquals("testTest", JsonConverter.getPath());
    }



    private static final String json1 = "{\n" +
            "  \"stones\": [\n" +
            "    {\n" +
            "      \"name\": \"s1\",\n" +
            "      \"pricePerFlover\": 12,\n" +
            "      \"length\": 10,\n" +
            "      \"levelOfFreshness\": 0.2,\n" +
            "      \"type\": {\n" +
            "        \"name\": \"das\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"s2\",\n" +
            "      \"pricePerFlover\": 12,\n" +
            "      \"length\": 10,\n" +
            "      \"levelOfFreshness\": 0.2,\n" +
            "      \"type\": {\n" +
            "        \"name\": \"vrc\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"s3\",\n" +
            "      \"pricePerFlover\": 12,\n" +
            "      \"length\": 10,\n" +
            "      \"levelOfFreshness\": 0.2,\n" +
            "      \"type\": {\n" +
            "        \"name\": \"hba\"\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"bouquet\": {\n" +
            "    \"flovers\": [\n" +
            "      {\n" +
            "        \"name\": \"s2\",\n" +
            "        \"pricePerFlover\": 12,\n" +
            "        \"length\": 10,\n" +
            "        \"levelOfFreshness\": 0.2,\n" +
            "        \"type\": {\n" +
            "          \"name\": \"vrc\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"name\": \"s1\",\n" +
            "        \"pricePerFlover\": 12,\n" +
            "        \"length\": 10,\n" +
            "        \"levelOfFreshness\": 0.2,\n" +
            "        \"type\": {\n" +
            "          \"name\": \"das\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"types\": [\n" +
            "    {\n" +
            "      \"name\": \"das\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"das\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";

}