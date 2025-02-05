package view.commands;

import view.ConsoleUI;

public class AddPet extends Command{
    public AddPet(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Добавить домашнее животное";
    }
    @Override
    public void execute() {
        consoleUI.addPet();
    }
}
