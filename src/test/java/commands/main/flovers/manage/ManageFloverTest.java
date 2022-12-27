package commands.main.flovers.manage;

import Bouquet.Bouquet;
import Flower.Flover;
import Flower.FloverType;
import commands.Command;
import commands.main.flovers.MyFlovers;
import commands.main.flovers.manage.delete.DeleteFlover;
import json.JsonConverterData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ManageFloverTest {

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
                Arguments.arguments(jsonConverterData, s1,"1", DeleteFlover.class),
                Arguments.arguments(jsonConverterData, s2,"2", MyFlovers.class)
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void manageFloverTest(JsonConverterData jsonConverterData, Flover stone, String input, Class<Command> expected){
        InputStream my = new ByteArrayInputStream(input.getBytes());
        System.setIn(my);

        ManageFlover manageStone = new ManageFlover(jsonConverterData,stone);
        assertEquals(expected, manageStone.execute().getClass());
    }

}