package commands.main.bouquet;

import Bouquet.Bouquet;
import Flower.Flover;
import Flower.FloverType;
import commands.Command;
import commands.main.MainMenu;
import commands.main.bouquet.delete.DeleteBouquetes;
import commands.main.bouquet.edit.EditBouquet;
import commands.main.bouquet.getBylength.FindByLength;
import commands.main.bouquet.sort.SortFloversInBouquet;
import json.JsonConverterData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import test.ConsoleCheck;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MyBouquetTest extends ConsoleCheck{
    public static Stream<Arguments> source() {

        Flover s1 = Flover.builder()
                .type(new FloverType("das"))
                .length(12)
                .levelOfFreshness(0.3)
                .pricePerFlover(12)
                .name("s1")
                .build();
        Flover s2 = Flover.builder()
                .type(new FloverType("vrc"))
                .length(14)
                .levelOfFreshness(0.1)
                .pricePerFlover(12)
                .name("s2")
                .build();
        Flover s3 = Flover.builder()
                .type(new FloverType("hba"))
                .length(5)
                .levelOfFreshness(0.9)
                .pricePerFlover(12)
                .name("s3")
                .build();

        JsonConverterData jsonConverterData = mock(JsonConverterData.class);
        when(jsonConverterData.getBouquet()).thenReturn(new Bouquet(new LinkedHashSet<>(Arrays.asList(s1,s2,s3))));

        JsonConverterData jsonConverterData1 = mock(JsonConverterData.class);
        when(jsonConverterData1.getBouquet()).thenReturn(null);

        JsonConverterData jsonConverterData2 = mock(JsonConverterData.class);
        when(jsonConverterData2.getBouquet()).thenReturn(new Bouquet(new LinkedHashSet<>()));

        return Stream.of(
                Arguments.arguments(jsonConverterData,"1", EditBouquet.class),
                Arguments.arguments(jsonConverterData,"-1\n2", SortFloversInBouquet.class),

                Arguments.arguments(jsonConverterData,"7\n4", FindByLength.class),
                Arguments.arguments(jsonConverterData,"5", DeleteBouquetes.class),
                Arguments.arguments(jsonConverterData,"6", MainMenu.class),
                Arguments.arguments(jsonConverterData1,"1", EditBouquet.class),
                Arguments.arguments(jsonConverterData2,"2", MainMenu.class)
        );
    }

    public static Stream<Arguments> source2() {
        Flover s1 = Flover.builder()
                .type(new FloverType("das"))
                .length(12)
                .levelOfFreshness(0.3)
                .pricePerFlover(12)
                .name("s1")
                .build();
        Flover s2 = Flover.builder()
                .type(new FloverType("vrc"))
                .length(14)
                .levelOfFreshness(0.1)
                .pricePerFlover(12)
                .name("s2")
                .build();
        Flover s3 = Flover.builder()
                .type(new FloverType("hba"))
                .length(5)
                .levelOfFreshness(0.9)
                .pricePerFlover(12)
                .name("s3")
                .build();

        JsonConverterData jsonConverterData = mock(JsonConverterData.class);
        when(jsonConverterData.getBouquet()).thenReturn(new Bouquet(new LinkedHashSet<>(Arrays.asList(s1,s2,s3))));

        return Stream.of(
                Arguments.arguments(jsonConverterData,"0\n3\n6", "Ціна даного букету =")
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void myNecklaceTest(JsonConverterData jsonConverterData, String input, Class<Command> expected){
        InputStream my = new ByteArrayInputStream(input.getBytes());
        System.setIn(my);

        MyBouquet myNecklace = new MyBouquet(jsonConverterData);
        assertEquals(expected,myNecklace.execute().getClass());

    }



    @ParameterizedTest
    @MethodSource("source2")
//    Arguments.arguments(jsonConverterData,"0\n3", ConsoleCheck.class , "Ціна даного букету ="),
    public void myNecklaceTest1(JsonConverterData jsonConverterData, String input, String expected){
        InputStream my = new ByteArrayInputStream(input.getBytes());
        System.setIn(my);

        MyBouquet myNecklace = new MyBouquet(jsonConverterData);
        myNecklace.execute();
        assertTrue(console.toString().contains(expected));

    }
}