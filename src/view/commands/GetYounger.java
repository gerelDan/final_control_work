package view.commands;

import view.ConsoleUI;

public class GetYounger extends Command{
    public GetYounger(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Показать домашних животных которые моложе чем (указать возраст):";
    }

    @Override
    public void execute() {
        this.consoleUI.getYounger();
    }
}
