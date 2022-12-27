package commands.main.bouquet.delete;

import commands.Command;
import commands.main.bouquet.MyBouquet;
import json.JsonConverterData;
import java.util.LinkedHashSet;

public class DeleteBouquetes extends Command {
    public DeleteBouquetes(JsonConverterData jsonConverterData) {
        super(jsonConverterData);
    }

    @Override
    public Command execute() {
        data().getBouquet().setFlovers(new LinkedHashSet<>());
        logger().info("Bouquet was cleared");
        System.out.println("Букет було видалено");

//        this.updateData();

        return new MyBouquet(data());

    }
}
