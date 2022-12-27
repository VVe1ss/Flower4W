package commands.main.flovers.create;

import Flower.FloverType;
import commands.Command;
import commands.main.flovers.MyFlovers;
import json.JsonConverter;
import json.JsonConverterData;
import Flower.Flover;

import java.util.List;
import java.util.Scanner;

public class CreateFlover extends Command {

    private String name;
    private int pricePerFlover;
    private int length;
    double levelOfFreshness = -1;
    private FloverType type;
    public CreateFlover(JsonConverterData jsonConverterData) {
        super(jsonConverterData);
    }

    @Override
    public Command execute() {
        validate(data());

        Flover flover = new Flover(name,pricePerFlover,length,levelOfFreshness,type);
        data().getFlovers().add(flover);
        if (data().getFlovers().contains(flover)){
            System.out.println("Квітку додано успішно");
            logger().info("Created flover "+ flover);
        }

        return new MyFlovers(data());
    }

    private void validate(JsonConverterData jsonConverterData){

        while (this.name == null){
            System.out.println("Введіть ім'я квітки: ");
            this.name = scanner().next();
        }
        while (pricePerFlover <= 0){
            System.out.println("Введіть ціну за квітку: ");
            this.pricePerFlover = scanner().nextInt();
        }
        while (length <= 0){
            System.out.println("Введіть довжену стебла: ");
            this.length = scanner().nextInt();
        }
        while (this.levelOfFreshness < 0 || this.levelOfFreshness > 1){
            System.out.println("Введіть відсоток свіжості квітки де 10% = 0,1 свіжості");
            this.levelOfFreshness = scanner().nextDouble();

        }
        typeValidator(jsonConverterData);
    }


    private void typeValidator (JsonConverterData jcd){
        if (type == null) {
            List<FloverType> types = jcd.getTypes();
            System.out.println(jcd.getTypes());
            while (type == null) {
                System.out.println("Виберіть тип квітки\n");
                int i = 0;
                while (i < types.size()) {
                    System.out.printf("%d: %s\n", i, types.get(i).getName());
                    i++;
                }
                System.out.printf("%d -> Додати новий тип квітки: ", i);

                int index = scanner().nextInt();
                if (index == i) {
                    FloverType floverType = new FloverType(null);

                    jcd.getTypes().add(floverType);
                    System.out.println(jcd.getTypes());
                    JsonConverter.convertToJson(jcd);
                    this.type = floverType;
                } else
                    this.type = types.get(index);
            }
        }
    }
}
