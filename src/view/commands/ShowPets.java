package view.commands;

import view.ConsoleUI;

public class ShowPets extends Command{
    public ShowPets(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Показать Всех животных";
    }
    @Override
    public void execute() {
        consoleUI.showPets();
    }
}
