package commands.main.bouquet.edit.flover.replace;

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

class ReplaceFloverInBouquetTest extends ConsoleCheck {
    public static Stream<Arguments> source() {
        Flover s1 = mock(Flover.class);
        Flover s2 = mock(Flover.class);
        Flover s3 = mock(Flover.class);
        Flover s4 = mock(Flover.class);
        Flover s5 = mock(Flover.class);

        JsonConverterData jsonConverterData1 = JsonConverterData.builder()
                .stones(new ArrayList<>(Arrays.asList(s1,s2,s3,s4,s5)))
                .bouquet(new Bouquet(new LinkedHashSet<>(Arrays.asList(s3,s2))))
                .build();

        JsonConverterData jsonConverterData2 = JsonConverterData.builder()
                .stones(new ArrayList<>(Arrays.asList(s1,s2,s3,s4,s5)))
                .bouquet(new Bouquet(new LinkedHashSet<>(Arrays.asList(s1,s2,s5))))
                .build();
        JsonConverterData jsonConverterData3 = JsonConverterData.builder()
                .stones(new ArrayList<>(Arrays.asList(s1,s2,s3,s4,s5)))
                .bouquet(new Bouquet(new LinkedHashSet<>(Arrays.asList(s1,s2,s3,s4,s5))))
                .build();
        return Stream.of(
                Arguments.arguments(jsonConverterData1, 0, "0", s1, "3 -> Назад"),
                Arguments.arguments(jsonConverterData2, 2, "0", s3, "2 -> Назад"),
                Arguments.arguments(jsonConverterData3, 0, "0", null, "Усі доступні Квіти вже доданодо букету")
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void replaceFloverInBouquetTest(JsonConverterData data, int indexWhichReplace, String replaceIndex, Flover expectedReplaced, String expectedString){
        InputStream def = System.in;
        InputStream my = new ByteArrayInputStream(replaceIndex.getBytes());
        System.setIn(my);

        ReplaceFloverInBouquet command = new ReplaceFloverInBouquet(data, indexWhichReplace);
        Flover toReplace = data.getBouquet().getFlovers().stream().toList().get(indexWhichReplace);
        command.execute();
        assertTrue(console.toString().contains(expectedString));
        if (data.getBouquet().getFlovers().size() != data.getFlovers().size()){
            assertTrue(data.getBouquet().getFlovers().contains(expectedReplaced));
            assertFalse(data.getBouquet().getFlovers().contains(toReplace));
        }

        System.setIn(def);

    }

}