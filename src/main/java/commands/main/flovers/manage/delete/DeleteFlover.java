package commands.main.flovers.manage.delete;

import commands.Command;
import commands.main.flovers.MyFlovers;
import json.JsonConverterData;
import Flower.Flover;

public class DeleteFlover extends Command {
    private final Flover stone;
    public DeleteFlover(JsonConverterData jsonConverterData, Flover stone) {
        super(jsonConverterData);
        this.stone = stone;

    }
    @Override
    public Command execute() {
        data().getBouquet().getFlovers().remove(getStone());
        data().getFlovers().remove(getStone());
        logger().info("removed flover {}",getStone().getName());
        System.out.println("Квітку видалено");
        return new MyFlovers(data());
    }

    public Flover getStone() {
        return stone;
    }
}
