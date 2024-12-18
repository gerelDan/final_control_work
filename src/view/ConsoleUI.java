package view;

import model.Pet;
import model.Service;
import presenter.Presenter;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements PetsView{
    private Scanner scanner = new Scanner(System.in);
    private final Presenter presenter;
    private boolean exit;
    private final Menu menu;
    public ConsoleUI(){
        this.presenter = new Presenter(new Service(), this);
        this.menu = new Menu();
    }
    @Override
    public void showPets(List<Pet> notes) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public LocalDate getDateOfBirthdayInput() {
        return null;
    }

    @Override
    public String getNameInput() {
        return "";
    }

    @Override
    public String getFileNameInput() {
        return "";
    }

    @Override
    public String getCommandInput() {
        return "";
    }
}
