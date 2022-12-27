package commands.main.bouquet.edit.flover.add;

import Bouquet.Bouquet;
import Flower.Flover;
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
import static org.mockito.Mockito.mock;

class AddFloverToBouquetTest extends ConsoleCheck {

    public static Stream<Arguments> source() {
        Flover s1 = mock(Flover.class);
        Flover s2 = mock(Flover.class);
        Flover s3 = mock(Flover.class);
        Flover s4 = mock(Flover.class);
        Flover s5 = mock(Flover.class);

        JsonConverterData jsonConverterData1 = JsonConverterData.builder()
                .stones(new ArrayList<>())
                .bouquet(new Bouquet(new LinkedHashSet<>()))
                .build();

        JsonConverterData jsonConverterData2 = JsonConverterData.builder()
                .stones(new ArrayList<>(Arrays.asList(s1,s2,s3,s4,s5)))
                .bouquet(new Bouquet(new LinkedHashSet<>(Arrays.asList(s1,s2,s5))))
                .build();

        return Stream.of(
                Arguments.arguments(jsonConverterData1, "0", "Усі доступні квітки вже доданодо букету", 0),
                Arguments.arguments(jsonConverterData2, "1", "2 -) Назад", jsonConverterData2.getBouquet().getFlovers().size()+1)
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void AddFloverTest(JsonConverterData data, String input,String expected,int expectedSize){
        InputStream def = System.in;
        InputStream my = new ByteArrayInputStream(input.getBytes());
        System.setIn(my);

        AddFloverToBouquet command = new AddFloverToBouquet(data);
        command.execute();

        assertTrue(console.toString().contains(expected));
        assertEquals(expectedSize, data.getBouquet().getFlovers().size());

        System.setIn(def);

    }

}