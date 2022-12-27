package commands.main.bouquet.sort;

import commands.Command;
import commands.main.bouquet.MyBouquet;
import json.JsonConverterData;

public class SortFloversInBouquet extends Command {
    public SortFloversInBouquet(JsonConverterData jsonConverterData) {
        super(jsonConverterData);
    }

    @Override
    public Command execute() {
        data().getBouquet().sortByLength();
        System.out.println(
                """
                        \nКвіти відсортовано за рівнем свіжості
                        """);

        return new MyBouquet(data());
    }
}
