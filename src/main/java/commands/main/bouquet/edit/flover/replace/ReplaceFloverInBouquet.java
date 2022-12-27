package commands.main.bouquet.edit.flover.replace;

import commands.Command;
import commands.main.bouquet.edit.EditBouquet;
import json.JsonConverterData;
import Flower.Flover;

import java.util.List;

public class ReplaceFloverInBouquet extends Command {
    private final int stoneIndexInNeckless;
    public ReplaceFloverInBouquet(JsonConverterData jsonConverterData, int stoneIndexInNeckless) {
        super(jsonConverterData);
        this.stoneIndexInNeckless = stoneIndexInNeckless;
    }

    @Override
    public Command execute() {
        StringBuilder sb = new StringBuilder("Виберіть Квітку на який хочете замінити\n");

        List<Flover> flovers = data().getFlovers().stream()
                .filter(x -> ! data().getBouquet().getFlovers().contains(x))
                .toList();

        int i = 0;
        while (i<flovers.size()){
            sb.append(String.format("%d: %s\n", i, flovers.get(i).toString()));
            i++;
        }

        if (flovers.size() == 0)
            sb.append("\nУсі доступні Квіти вже доданодо букету\n");
        sb.append(String.format("%d -> Назад",i));

        do {
            System.out.println(sb);
            i = scanner().nextInt();
        } while (i<0 || i>flovers.size());

        if (i == flovers.size())
            return new EditBouquet(data());

        data().getBouquet().replaceFlover(stoneIndexInNeckless, flovers.get(i));


        return new EditBouquet(data());
    }
}
