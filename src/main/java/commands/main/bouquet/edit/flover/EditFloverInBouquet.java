package commands.main.bouquet.edit.flover;

import commands.Command;
import commands.main.bouquet.edit.flover.delete.DeleteFloverFromBouquet;
import commands.main.bouquet.edit.flover.replace.ReplaceFloverInBouquet;
import json.JsonConverterData;
import Flower.Flover;

import java.util.List;

public class EditFloverInBouquet extends Command {
    private final int floverIndexInNeckless;
    public EditFloverInBouquet(JsonConverterData jsonConverterData, int index) {
        super(jsonConverterData);
        this.floverIndexInNeckless = index;
    }

    @Override
    public Command execute() {
        int i;
        List<Flover> stones = data().getBouquet().getFlovers().stream().toList();
        do {
            System.out.printf("%s\n1 -) замінити\n2 -) видалити", stones.get(floverIndexInNeckless));
            i = scanner().nextInt();
        } while (i < 1 || i > 2);
        switch (i){
            case 1 -> {
                return new ReplaceFloverInBouquet(data(), floverIndexInNeckless);
            }
            case 2 -> {
                return new DeleteFloverFromBouquet(data(),stones.get(floverIndexInNeckless));
            }
        }
        return null;
    }
}
