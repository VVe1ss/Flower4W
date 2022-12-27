package comparators;

import Flower.Flover;

import java.util.Comparator;

public class MyComparator implements Comparator<Flover> {

    @Override
    public int compare(Flover o1, Flover o2) {
        if (o1.getLevelOfFreshness() == (o2.getLevelOfFreshness()))
            return o1.getName().compareTo(o2.getName());
        else
            return  Double.compare(o1.getLevelOfFreshness(), o2.getLevelOfFreshness());
    }
}
