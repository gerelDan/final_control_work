package view.commands;

import view.ConsoleUI;

public class LoadPetsRepository extends Command{
    public LoadPetsRepository(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Загрузить список домашних животных";
    }

    @Override
    public void execute() {
        consoleUI.loadPetsRepository();
    }
}

