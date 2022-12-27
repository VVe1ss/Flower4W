package commands.main.bouquet.edit.flover.delete;

import commands.Command;
import commands.main.bouquet.edit.EditBouquet;
import json.JsonConverterData;
import Flower.Flover;

public class DeleteFloverFromBouquet extends Command {
    private final Flover stone;
    public DeleteFloverFromBouquet(JsonConverterData data, Flover stone) {
        super(data);
        this.stone = stone;
    }

    @Override
    public Command execute() {
        data().getBouquet().getFlovers().remove(stone);
        System.out.println("видалено");
        logger().info("deleted flover {} from Bouquet", stone.getName());
        return new EditBouquet(data());
    }
}
