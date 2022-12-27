package commands.main.bouquet;

import Bouquet.Bouquet;
import commands.Command;
import commands.main.bouquet.delete.DeleteBouquetes;
import commands.main.bouquet.edit.EditBouquet;
import commands.main.MainMenu;
import commands.main.bouquet.getBylength.FindByLength;
import commands.main.bouquet.sort.SortFloversInBouquet;
import json.JsonConverterData;

public class MyBouquet extends Command {

    public MyBouquet(JsonConverterData jsonConverterData) {
        super(jsonConverterData);
    }

    @Override
    public Command execute() {
        System.out.flush();
        if (data().getBouquet() == null)
            return necklaceIsNull();
        else if (data().getBouquet().getFlovers().size() == 0)
            return necklaceStoneBlank();
        else {
            logger().info("Bouquet {}", data().getBouquet());
            System.out.println(data().getBouquet());
            int i = 0;
            while (i < 1 || i > 6) {
                System.out.println(
                        """
                                1 -> редагувати
                                2 -> сортувати
                                3 -> ціна
                                4 -> знайти за довжиною стебла
                                5 -> видалити
                                6 -> назад""");
                i = scanner().nextInt();
            }
            switch (i) {
                case 1 -> {
                    return new EditBouquet(data());
                }
                case 2 -> {
                    return new SortFloversInBouquet(data());
                }
                case 3 -> {
                    if (!data().getBouquet().getFlovers().isEmpty())
                        System.out.printf("Ціна даного букету = %d\n\n", data().getBouquet().getValue());
                    else
                        System.out.println("В букеті немає жодної квітки");
                    this.execute();
                }
                case 4 -> {
                    return new FindByLength(data());
                }
                case 5 -> {
                    return new DeleteBouquetes(data());
                }
                case 6 -> {
                    return new MainMenu(data());
                }
            }
        }
        return null;
    }

    private Command necklaceIsNull(){
        logger().info("Bouquet null");
        data().newNecklace();
        return necklaceStoneBlank();
    }

    private Command necklaceStoneBlank(){
        logger().info("Bouquet.getStones().size() == 0");
        System.out.println("У букеті немає квітів");
        int i = 0;
        while (i<1 || i>2){
            System.out.println(
                    """
                            1 -> редагувати
                            2 -> назад""");
            i = scanner().nextInt();
        }
        switch (i){
            case 1 -> {
                return new EditBouquet(data());
            }
            case 2 -> {
                return new MainMenu(data());
            }
        }
        return null;
    }
}
