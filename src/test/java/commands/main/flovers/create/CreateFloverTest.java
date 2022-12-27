package commands.main.flovers.create;

import Bouquet.Bouquet;
import Flower.Flover;
import Flower.FloverType;
import json.JsonConverterData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import test.ConsoleCheck;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CreateFloverTest {
    public static Stream<Arguments> source() {
        JsonConverterData jsonConverterData = new JsonConverterData(new ArrayList<>(), new Bouquet(new LinkedHashSet<>()),new ArrayList<>());
        jsonConverterData.getTypes().add(new FloverType("Роза"));
        jsonConverterData.getTypes().add(new FloverType("Тюльпан"));


        return Stream.of(
                Arguments.arguments(jsonConverterData, "name1\n12\n10\n0,2\n0", new Flover("name1",12,10,0.2, new FloverType("Роза"))),
                Arguments.arguments(jsonConverterData, "name2\n-1\n10\n-3\n10\n2,2\n0,3\n1", new Flover("name2",10,10,0.3, new FloverType("Тюльпан")))
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void createStoneTest(JsonConverterData jsonConverterData, String input, Flover expected){
        InputStream my = new ByteArrayInputStream(input.getBytes());
        System.setIn(my);
//        Scanner s = new Scanner(my);
//        while (s.hasNext())
//            System.out.println(s.next());
        CreateFlover createStone = new CreateFlover(jsonConverterData);
        createStone.execute();
        assertTrue(jsonConverterData.getFlovers().contains(expected));

    }

}