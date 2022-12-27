package commands.main.bouquet.edit.flover;

import Bouquet.Bouquet;
import Flower.Flover;
import commands.Command;
import commands.main.bouquet.edit.flover.delete.DeleteFloverFromBouquet;
import commands.main.bouquet.edit.flover.replace.ReplaceFloverInBouquet;
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
import static org.mockito.Mockito.mock;

class EditFloverInBouquetTest {

    public static Stream<Arguments> source() {

        Flover s1 = mock(Flover.class);
        Flover s2 = mock(Flover.class);
        Flover s3 = mock(Flover.class);
        Flover s4 = mock(Flover.class);
        Flover s5 = mock(Flover.class);

        JsonConverterData jsonConverterData1 = JsonConverterData.builder()
                .stones(new ArrayList<>())
                .bouquet(new Bouquet(new LinkedHashSet<>(Arrays.asList(s3,s2,s1))))
                .build();

        JsonConverterData jsonConverterData2 = JsonConverterData.builder()
                .stones(new ArrayList<>())
                .bouquet(new Bouquet(new LinkedHashSet<>(Arrays.asList(s1,s2,s5))))
                .build();

        return Stream.of(
                Arguments.arguments(jsonConverterData1, 0, "1", ReplaceFloverInBouquet.class),
                Arguments.arguments(jsonConverterData2, 2, "2", DeleteFloverFromBouquet.class)
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void editEditFloverInBouquetTest(JsonConverterData data, int index, String input, Class<Command> expected){
        InputStream my = new ByteArrayInputStream(input.getBytes());
        System.setIn(my);
        EditFloverInBouquet editStoneInNeckless = new EditFloverInBouquet(data,index);
        assertEquals(expected, editStoneInNeckless.execute().getClass());


    }

}