package commands.main.settings;

import commands.Command;
import json.JsonConverterData;

public class Settings extends Command {
    public Settings(JsonConverterData jsonConverterData) {
        super(jsonConverterData);
    }

    // TODO: 27.10.2022  
    @Override
    public Command execute() {
        System.out.println("поки пусто");
        return null;
    }
}
