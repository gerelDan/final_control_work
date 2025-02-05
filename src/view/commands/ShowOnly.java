package view.commands;

import view.ConsoleUI;

public class ShowOnly extends Command{
    public ShowOnly(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Показать животных определенного типа";
}
    @Override
    public void execute() {
    consoleUI.showOnlyPetType();
}
}

