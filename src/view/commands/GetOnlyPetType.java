package view.commands;

import view.ConsoleUI;

public class GetOnlyPetType extends Command{
    public GetOnlyPetType(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Отфильтровать по виду домашнего животного:";
    }

    @Override
    public void execute() {
        this.consoleUI.showOnlyPetType();
    }
}
