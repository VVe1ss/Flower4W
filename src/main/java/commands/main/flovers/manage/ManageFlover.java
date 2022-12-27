package commands.main.flovers.manage;

import commands.Command;
import commands.main.flovers.MyFlovers;
import commands.main.flovers.manage.delete.DeleteFlover;
import json.JsonConverterData;
import Flower.Flover;

public class ManageFlover extends Command {
    Flover stone;
    public ManageFlover(JsonConverterData data, Flover flover) {
        super(data);
        stone = flover;
    }

    @Override
    public Command execute() {
        System.out.println(stone);
        int i = -1;
        while (i<1 || i>3) {
            System.out.println(stone);
            System.out.println("1 -> видалити\n2 -> назад");
            i = scanner().nextInt();
        }
        switch (i) {
            case 1 -> {
                return new DeleteFlover(data(), stone);
            }
            case 2 -> {
                return new MyFlovers(data());
            }

        }
        return null;
    }
}
