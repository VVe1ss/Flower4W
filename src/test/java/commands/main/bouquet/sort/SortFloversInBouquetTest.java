package commands.main.bouquet.sort;

import Bouquet.Bouquet;
import Flower.Flover;
import Flower.FloverType;
import json.JsonConverterData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SortFloversInBouquetTest {
    public static Stream<Arguments> source() {

        Flover s1 = Flover.builder()
                .type(new FloverType("das"))
                .levelOfFreshness(0.6)
                .pricePerFlover(7)
                .length(15)
                .name("s1")
                .build();
        Flover s2 = Flover.builder()
                .type(new FloverType("vrc"))
                .levelOfFreshness(0.3)
                .pricePerFlover(12)
                .length(12)
                .name("s2")
                .build();
        Flover s3 = Flover.builder()
                .type(new FloverType("hba"))
                .levelOfFreshness(0.2)
                .pricePerFlover(13)
                .length(7)
                .name("s3")
                .build();

        JsonConverterData jsonConverterData1 = JsonConverterData.builder()
                .stones(new ArrayList<>())
                .bouquet(new Bouquet(new LinkedHashSet<>(Arrays.asList(s3,s2,s1))))
                .build();

        JsonConverterData jsonConverterData2 = JsonConverterData.builder()
                .stones(new ArrayList<>())
                .bouquet(new Bouquet(new LinkedHashSet<>(Arrays.asList(s1,s2,s3))))
                .build();

        ArrayList<Flover> expected = new ArrayList<>(Arrays.asList(s3,s2,s1));

        return Stream.of(
                Arguments.arguments(jsonConverterData1, expected),
                Arguments.arguments(jsonConverterData2, expected)
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void sortStonesInNecklessTest(JsonConverterData jsonConverterData, List<Flover> expected){
        SortFloversInBouquet sortStonesInNeckless = new SortFloversInBouquet(jsonConverterData);
        sortStonesInNeckless.execute();
        assertEquals(expected,jsonConverterData.getBouquet().getFlovers().stream().toList());
    }

}