package view.commands;

import view.ConsoleUI;

public class AddCommand extends Command{
    public AddCommand(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Добавить команду домашнему животному (необходимо знать id)";
    }

    @Override
    public void execute() {
        consoleUI.addCommand();
    }

}
