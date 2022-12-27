package commands.main.bouquet.edit;

import Bouquet.Bouquet;
import Flower.Flover;
import commands.Command;
import commands.main.bouquet.MyBouquet;
import commands.main.bouquet.edit.flover.EditFloverInBouquet;
import commands.main.bouquet.edit.flover.add.AddFloverToBouquet;
import json.JsonConverterData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import test.ConsoleCheck;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EditBouquetTest extends ConsoleCheck {
    public static Stream<Arguments> source() {
        Flover flover = mock(Flover.class);
        Flover flover1 = mock(Flover.class);

        JsonConverterData jsonConverterData1 = mock(JsonConverterData.class);
        when(jsonConverterData1.getBouquet()).thenReturn(new Bouquet(new LinkedHashSet<>(Arrays.asList(flover, flover1))));

        JsonConverterData jsonConverterData2 = mock(JsonConverterData.class);
        when(jsonConverterData2.getBouquet()).thenReturn(new Bouquet(new LinkedHashSet<>(Collections.singleton(flover))));

        JsonConverterData jsonConverterData3 = mock(JsonConverterData.class);
        when(jsonConverterData3.getBouquet()).thenReturn(new Bouquet(new LinkedHashSet<>()));


        return Stream.of(
                Arguments.arguments(jsonConverterData1, "2 -) додати квітку","2", AddFloverToBouquet.class),
                Arguments.arguments(jsonConverterData2, "1 -) додати квітку","1", AddFloverToBouquet.class),
                Arguments.arguments(jsonConverterData3, "0 -) додати квітку","0", AddFloverToBouquet.class),
                Arguments.arguments(jsonConverterData1, "2 -) додати квітку","1", EditFloverInBouquet.class),
                Arguments.arguments(jsonConverterData1, "2 -) додати квітку","3", MyBouquet.class)
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void editNecklessMenuTest(JsonConverterData data, String contains, String input, Class<Command> expected){
        InputStream base = System.in;
        InputStream my = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(my);

        EditBouquet editNeckless = new EditBouquet(data);
        assertEquals(expected, editNeckless.execute().getClass());
        assertTrue(console.toString().contains(contains));
        System.setIn(base);

    }


}