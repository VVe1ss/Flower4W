package commands.main.bouquet.edit.flover.delete;

import Bouquet.Bouquet;
import Flower.Flover;
import json.JsonConverterData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DeleteFloverFromBouquetTest {
    public static Stream<Arguments> source() {
        Flover flover = mock(Flover.class);
        Flover flover1 = mock(Flover.class);

        JsonConverterData jsonConverterData = JsonConverterData.builder().bouquet(new Bouquet(new LinkedHashSet<>(Arrays.asList(flover, flover1)))).build();

        JsonConverterData jsonConverterData1 = JsonConverterData.builder().bouquet(new Bouquet(new LinkedHashSet<>(Arrays.asList(flover, flover1)))).build();

        return Stream.of(
                Arguments.arguments(jsonConverterData, flover, flover1),
                Arguments.arguments(jsonConverterData1, flover1, flover)
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void deleteFloverFromNecklessTest(JsonConverterData jsonConverterData, Flover delete, Flover remaining){
        DeleteFloverFromBouquet command = new DeleteFloverFromBouquet(jsonConverterData, delete);
        command.execute();
        assertTrue(jsonConverterData.getBouquet().getFlovers().contains(remaining));
        assertFalse(jsonConverterData.getBouquet().getFlovers().contains(delete));
    }

}