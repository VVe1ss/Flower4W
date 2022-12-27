package Bouquet;

import comparators.MyComparator;
import lombok.extern.slf4j.Slf4j;
import Flower.Flover;

import java.util.*;
@Slf4j
public class Bouquet {
    private LinkedHashSet<Flover> flovers;

    public Bouquet(LinkedHashSet<Flover> flovers) {
        this.flovers = flovers;
    }

    public LinkedHashSet<Flover> getFlovers() {
        return flovers;
    }

    public void setFlovers(LinkedHashSet<Flover> flovers) {
        this.flovers = flovers;
    }

    public void sortByLength(){
        // сортування через компаратор
        TreeSet<Flover> treeSet = new TreeSet<>(new MyComparator());
        treeSet.addAll(flovers);
        this.flovers = new LinkedHashSet<>(treeSet);
        System.out.printf("Посортовані квіти : %s", flovers);
    }

    public void addFlover(Flover stone){
        this.flovers.add(stone);
    }
    public void replaceFlover(int index, Flover stone){
        List<Flover> list = new ArrayList<>(this.flovers.stream().toList());
        list.remove(index);
        list.add(index,stone);
        log.info("replaced flover in necklace from "+list.get(index).getName()+" to " + stone.getName());
        this.flovers = new LinkedHashSet<>(list);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Букет з :");
        for (Flover stone: flovers) {
            sb.append(String.format("\n%s - %s",stone.getType().getName(), stone.getName()));
        }
        return sb.toString();
    }

    public int getValue (){
        int sum = 0;
        for (Flover flo:
             flovers) {
            sum += flo.getPricePerFlover();
        }
        return sum;
    }
}
