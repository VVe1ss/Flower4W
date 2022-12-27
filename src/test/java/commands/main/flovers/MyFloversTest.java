package commands.main.flovers;

import Bouquet.Bouquet;
import Flower.Flover;
import Flower.FloverType;
import commands.Command;
import commands.main.MainMenu;
import commands.main.flovers.create.CreateFlover;
import commands.main.flovers.manage.ManageFlover;
import json.JsonConverterData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import test.ConsoleCheck;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MyFloversTest extends ConsoleCheck {
    public static Stream<Arguments> source() {
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
                new Bouquet(new LinkedHashSet<>()),
                new ArrayList<>()
        );

        return Stream.of(
                Arguments.arguments(jsonConverterData, "4 -> Нова квітка", "1", ManageFlover.class),
                Arguments.arguments(jsonConverterData, "4 -> Нова квітка", "4", CreateFlover.class),
                Arguments.arguments(jsonConverterData, "4 -> Нова квітка", "5", MainMenu.class)
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void myStonesTest(JsonConverterData data, String expected, String input, Class<Command> expectedClass){
        InputStream my = new ByteArrayInputStream(input.getBytes());
        System.setIn(my);

        MyFlovers myStones = new MyFlovers(data);
        assertEquals(expectedClass, myStones.execute().getClass());
        assertTrue(console.toString().contains(expected));

    }

}