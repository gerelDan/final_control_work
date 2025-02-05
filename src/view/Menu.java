package view;

import java.util.ArrayList;
import java.util.List;
import view.commands.*;

public class Menu {
    List<Command> commandList;

    public Menu(ConsoleUI consoleUi) {
        commandList = new ArrayList<>();
        commandList.add(new AddPet(consoleUi));
        commandList.add(new ShowPets(consoleUi));
        commandList.add(new ShowOnly(consoleUi));
        commandList.add(new ShowPetsOnlyCommand(consoleUi));
        commandList.add(new SavePetsRepository(consoleUi));
        commandList.add(new LoadPetsRepository(consoleUi));
        commandList.add(new AddCommand(consoleUi));
        commandList.add(new GetOnlyPetType(consoleUi));
        commandList.add(new GetYounger(consoleUi));
        commandList.add(new GetOlder(consoleUi));
        commandList.add(new Exit(consoleUi));
    }
    public int getSize(){
        return commandList.size();
    }
    String menu(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(i+1);
            sb.append(". ");
            sb.append(commandList.get(i).getDescription());
            sb.append("\n");
        }
        return sb.toString();
    }
    public void execute(int choice){
        commandList.get(choice-1).execute();
    }
}
