package commands.main;

import commands.Command;
import commands.main.bouquet.MyBouquet;
import commands.main.flovers.MyFlovers;
import commands.main.settings.Settings;
import json.JsonConverterData;

public class MainMenu extends Command {
    public MainMenu(JsonConverterData jsonConverterData) {
        super(jsonConverterData);
    }

    @Override
    public Command execute() {
        System.out.flush();
        System.out.println("Для вибору команди впишіть номер пункту меню, під якии ввона знаходиться");
        int i = 0;
        while (i<1 || i >3){
            System.out.println("1 -> букет\n2 -> мої квіти\n3 -> налаштування");
            i = scanner().nextInt();
        }
        switch (i){
            case 1 -> {
                return new MyBouquet(data());
            }
            case 2 -> {
                return new MyFlovers(data());
            }
            case 3 -> {
                return new Settings(data());
            }
        }
        return null;
    }
}
