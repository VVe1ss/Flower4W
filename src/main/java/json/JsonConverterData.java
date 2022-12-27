package json;

import Bouquet.Bouquet;
import Flower.Flover;
import Flower.FloverType;
import lombok.Builder;

import java.util.*;

@Builder
public class JsonConverterData {
    private final List<Flover> stones;
    private Bouquet bouquet;
    private final List<FloverType> types;

    public JsonConverterData(List<Flover> stones, Bouquet bouquet, List<FloverType> types) {
        this.bouquet = Objects.requireNonNullElseGet(bouquet, () -> new Bouquet(new LinkedHashSet<>()));
        this.stones = Objects.requireNonNullElseGet(stones, ArrayList::new);
        this.types = Objects.requireNonNullElseGet(types, ArrayList::new);
    }

    public List<Flover> getFlovers() {
        return stones;
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public List<FloverType> getTypes() {
        return types;
    }

    public void newNecklace (){
        this.bouquet = new Bouquet(new LinkedHashSet<>());
    }

    @Override
    public String toString() {
        return "JsonConverterData{" +
                "stones=" + stones +
                ", bouquet=" + bouquet +
                ", types=" + types +
                '}';
    }
}
