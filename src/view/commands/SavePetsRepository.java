package view.commands;

import view.ConsoleUI;

public class SavePetsRepository extends Command{
    public SavePetsRepository(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Сохранить список домашних животных";
    }

    @Override
    public void execute() {
        consoleUI.savePetsRepository();
    }
}
