package commands.main.flovers.manage.delete;

import Bouquet.Bouquet;
import Flower.Flover;
import commands.Command;
import commands.main.flovers.MyFlovers;
import json.JsonConverterData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteFloverTest {

    @ParameterizedTest
    @MethodSource("source")
    void deleteStoneTest(DeleteFlover deleteStone, Flover remained) {

        assertEquals(2,deleteStone.data().getFlovers().size());
        Command command = deleteStone.execute();
        assertEquals(1,deleteStone.data().getFlovers().size());
        assertEquals(remained, deleteStone.data().getFlovers().get(0));
        assertEquals(MyFlovers.class, command.getClass());
    }

    private static Stream<Arguments> source(){
        JsonConverterData jsonConverterData1 = newData();
        JsonConverterData jsonConverterData2 = newData();
        return Stream.of(
                Arguments.arguments(new DeleteFlover(jsonConverterData1, jsonConverterData1.getFlovers().get(0)), jsonConverterData1.getFlovers().get(1)),
                Arguments.arguments(new DeleteFlover(jsonConverterData2, jsonConverterData2.getFlovers().get(1)), jsonConverterData2.getFlovers().get(0))
        );
    }

    private static JsonConverterData newData(){
        JsonConverterData jsonConverterData = Mockito.mock(JsonConverterData.class);

        Flover flover1 = mock(Flover.class);
        Flover flover2 = mock(Flover.class);
        ArrayList<Flover> stones = new ArrayList<>(Arrays.asList(flover1, flover2));

        Bouquet bouquet = new Bouquet(new LinkedHashSet<>(stones));
        when(jsonConverterData.getBouquet()).thenReturn(bouquet);
        when(jsonConverterData.getFlovers()).thenReturn(stones);
        return jsonConverterData;
    }

}