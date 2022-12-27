package commands;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandProcessor {
    Command command;

    public CommandProcessor(Command command) {
        this.command = command;
    }

    public void execute() {
        if (command != null){
            command.updateData();
            command = command.execute();
            this.execute();
        } else {
            log.info("program finished");
            System.out.println("program finished");
        }
    }
}
