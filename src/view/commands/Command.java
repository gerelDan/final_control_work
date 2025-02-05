package view.commands;

import view.ConsoleUI;

public abstract class Command {
    String description;
    ConsoleUI consoleUI;

    public Command(ConsoleUI consoleUI){
        this.consoleUI = consoleUI;
    }

    public String getDescription(){
        return this.description;
    }
    public abstract void execute();
}
