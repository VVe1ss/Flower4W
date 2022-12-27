package commands.main.bouquet.getBylength;

import Bouquet.Bouquet;
import Flower.Flover;
import Flower.FloverType;
import json.JsonConverterData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import test.ConsoleCheck;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FindByLengthTest extends ConsoleCheck {
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
                .length(7)
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
                new Bouquet(new LinkedHashSet<>(Arrays.asList(s2,s1,s3))),
                new ArrayList<>(Arrays.asList(new FloverType("das"),new FloverType("das")))
        );


        return Stream.of(
                Arguments.arguments(jsonConverterData, "0\n100\n1", new ArrayList<Flover>(Arrays.asList(s1,s2,s3))),
                Arguments.arguments(jsonConverterData, "0\n7\n1", new ArrayList<Flover>(Collections.singletonList(s2))),
                Arguments.arguments(jsonConverterData, "9\n15\n1", new ArrayList<Flover>(Arrays.asList(s1,s3)))
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void myTest(JsonConverterData data,String input, ArrayList<Flover> expected){
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        FindByLength findByLength = new FindByLength(data);

        findByLength.execute();
        for (Flover flo :
                expected) {
            assertTrue(console.toString().contains(flo.getName()));
        }

    }

}