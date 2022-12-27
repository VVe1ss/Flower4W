package Flower;

import json.JsonConverter;
import json.JsonConverterData;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
@Data
@Builder
public class Flover {
    private String name;
    private int pricePerFlover;
    private int length;
    double levelOfFreshness;
    private FloverType type;

    public Flover(String name, int pricePerFlover,int length, double levelOfFreshness, FloverType type) {
        this.name = name;
        this.pricePerFlover = pricePerFlover;
        this.length = length;
        this.levelOfFreshness = levelOfFreshness;
        this.type = type;

    }




    @Override
    public String toString() {
        return String.format("тип = %s,\t ім'я = %s,\t ціна за квітку = %d,\t свіжість = %s \t довжина стебла = %d",
                type.toString(), name, pricePerFlover, levelOfFreshness, length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flover flover = (Flover) o;
        return pricePerFlover == flover.pricePerFlover && length == flover.length && Double.compare(flover.levelOfFreshness, levelOfFreshness) == 0 && Objects.equals(name, flover.name) && Objects.equals(type, flover.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pricePerFlover, length, levelOfFreshness, type);
    }
}
