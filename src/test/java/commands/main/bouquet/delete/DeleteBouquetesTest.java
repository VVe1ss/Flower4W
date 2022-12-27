package commands.main.bouquet.delete;

import Bouquet.Bouquet;
import Flower.Flover;
import json.JsonConverterData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DeleteBouquetesTest {
    @Test
    public void necklaceDeleteTest(){
        JsonConverterData data = JsonConverterData.builder().bouquet(
                new Bouquet(new LinkedHashSet<>(
                        Arrays.asList(
                                mock(Flover.class),
                                mock(Flover.class)
                        )
                ))
        ).build();
        DeleteBouquetes command = new DeleteBouquetes(data);
        command.execute();
        assertEquals(0 ,data.getBouquet().getFlovers().size() );

    }

}