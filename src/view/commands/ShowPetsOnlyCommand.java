package view.commands;

import view.ConsoleUI;

public class ShowPetsOnlyCommand extends Command{
    public ShowPetsOnlyCommand(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Показать животных выполняющих определенную команду";
    }
    @Override
    public void execute() {
        consoleUI.showPetsOnlyCommand();
    }
}

