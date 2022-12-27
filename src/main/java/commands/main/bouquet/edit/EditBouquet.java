package commands.main.bouquet.edit;

import commands.Command;
import commands.main.bouquet.MyBouquet;
import commands.main.bouquet.edit.flover.add.AddFloverToBouquet;
import commands.main.bouquet.edit.flover.EditFloverInBouquet;
import json.JsonConverterData;
import Flower.Flover;

import java.util.List;

public class EditBouquet extends Command {
    public EditBouquet(JsonConverterData jsonConverterData) {
        super(jsonConverterData);
    }

    @Override
    public Command execute() {
        StringBuilder sb = new StringBuilder();
        System.out.println("виберіть квітку, яку потрібно модифікувати/замінити/видалити");
        int i = 0;
        List<Flover> stones = data().getBouquet().getFlovers().stream().toList();
        while (i < data().getBouquet().getFlovers().size()){
            sb.append(String.format("%d: %s \n",i,stones.get(i).toString()));
            i++;
        }
        sb.append(String.format("%d -) додати квітку\n",i));
        sb.append(String.format("%d -) назад",i+1));
        int count = i;
        i = -1;
        while (i<0 || i > count+1){
            System.out.println(sb);
            i = scanner().nextInt();
        }
        if (i < data().getBouquet().getFlovers().size())
            return new EditFloverInBouquet(data(), i);
        if (i == count)
            return new AddFloverToBouquet(data());
        else
            return new MyBouquet(data());
    }
}
