package Flower;

import java.util.Objects;
import java.util.Scanner;

public class FloverType {
    private String name;


    public FloverType(String name ) {
        this.name = name;
        init();
    }

    void init (){
        Scanner s = new Scanner(System.in);
        while (name == null){
        System.out.println("Введіть ім'я типу");
        this.name = s.nextLine();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloverType that = (FloverType) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
