package commands.main.flovers;

import commands.Command;
import commands.main.flovers.create.CreateFlover;
import commands.main.MainMenu;
import commands.main.flovers.manage.ManageFlover;
import json.JsonConverterData;
import Flower.Flover;

import java.util.List;

public class MyFlovers extends Command {

    public MyFlovers(JsonConverterData jsonConverterData) {
        super(jsonConverterData);
    }

    @Override
    public Command execute() {
        System.out.flush();
        List<Flover> stones = data().getFlovers();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        logger().info("Список квітів {}",stones.toString());
        while (i<stones.size()){
            sb.append(String.format("%d: назва квітки = %s, тип = %s\n",
                    i,
                    stones.get(i).getName(),
                    stones.get(i).getType().getName()));
            i++;
        }
        sb.append(String.format("%d -> Нова квітка\n", i+1));
        sb.append(String.format("%d -> Назад", i+2));
        int count = i;
        i=-1;
        while (i<0||i>count+2){
            System.out.println(sb);
            i = scanner().nextInt();
        }

        if (i < stones.size())
            return new ManageFlover(data(),stones.get(i));
        if (i == stones.size()+1)
            return new CreateFlover(data());
        else
            return new MainMenu(data());
    }
}
