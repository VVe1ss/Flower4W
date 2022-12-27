package commands.main.bouquet.edit.flover.add;

import commands.Command;
import commands.main.bouquet.edit.EditBouquet;
import json.JsonConverterData;
import Flower.Flover;

import java.util.List;

public class AddFloverToBouquet extends Command {

    public AddFloverToBouquet(JsonConverterData data) {
        super(data);
    }

    @Override
    public Command execute() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        System.out.println("Виберіть квітку, яку хочете добавити");
        List<Flover> unusedFlovers = data().getFlovers().stream()
                .filter(x -> ! data().getBouquet().getFlovers().contains(x))
                .toList();

        while (i<unusedFlovers.size()){
            sb.append(String.format("%d: %s\n", i, unusedFlovers.get(i).toString()));
            i++;
        }

        if (unusedFlovers.size() == 0)
            sb.append("\nУсі доступні квітки вже доданодо букету\n");
        sb.append(String.format("%d -) Назад",unusedFlovers.size()));

        do {
            System.out.println(sb);
            i = scanner().nextInt();
        } while (i < 0 || i > unusedFlovers.size());

        if (i == unusedFlovers.size())
            return new EditBouquet(data());

        data().getBouquet().addFlover(unusedFlovers.get(i));
        return new EditBouquet(data());
    }



}
