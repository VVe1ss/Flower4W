package commands.main.bouquet.getBylength;

import commands.Command;
import commands.main.bouquet.MyBouquet;
import json.JsonConverterData;

public class FindByLength extends Command {
    @Override
    public Command execute() {
        System.out.println("Введіть діапазон значень довжини стебла");
        System.out.println("Введіть 1 границю");
        int lenMin = scanner().nextInt();
        System.out.println("Введіть 2 границю");
        int lenMax = scanner().nextInt();
        if (lenMax < lenMin){
            int lenTemp = lenMax;
            lenMax = lenMin;
            lenMin = lenTemp;
        }
        int finalLenMin = lenMin;
        int finalLenMax = lenMax;
        System.out.println("Квіти з стеблами по заданому діапазоні:\n");
         data().getFlovers().stream()
                 .filter(flover -> flover.getLength()>= finalLenMin && flover.getLength()<= finalLenMax)
                 .forEach(flover ->
                         System.out.printf("ім'я: %s, тип: %s, довжина стебла %d\n", flover.getName(), flover.getType().getName(), flover.getLength()));
        int i;
         do {
            System.out.println("\n1 -> назад");
            i = scanner().nextInt();
        } while (i!=1);
        return new MyBouquet(data());
    }

    public FindByLength(JsonConverterData data) {
        super(data);
    }
}
