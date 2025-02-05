package view.commands;

import view.ConsoleUI;

public class GetOlder extends Command{
    public GetOlder(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Показать домашних животных которые старше чем (указать возраст):";
    }


    @Override
    public void execute() {
        this.consoleUI.getOlder();
    }
}
