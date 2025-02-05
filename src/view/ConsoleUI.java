package view;

import model.Pet;
import model.PetCreator;
import model.PetType;
import model.Service;
import presenter.Presenter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements PetsView{
    private final Scanner scanner = new Scanner(System.in);
    private final Presenter presenter;
    private boolean exit;
    private final Menu menu;
    public ConsoleUI(){
        this.presenter = new Presenter(new Service(), this);
        this.menu = new Menu(this);
        exit = true;
    }


    @Override
    public void showPets() {
        List<Pet> pets = presenter.getAllPets();
        if (pets.isEmpty()){
            System.out.println("Реестр пуст");
        } else {
            for (Pet pet: pets){
                System.out.println(pet);
            }
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public LocalDate getDateOfBirthdayInput() {
        System.out.println("Enter pet birthday (yyyy-MM-dd):");
        String input = scanner.nextLine();
        DateTimeFormatter formatter =
                DateTimeFormatter.ISO_LOCAL_DATE;
        return LocalDate.parse(input, formatter);
    }

    @Override
    public String getNameInput() {
        System.out.println("Enter pet name:");
        return scanner.nextLine();
    }

    @Override
    public String getFileNameInput() {
        System.out.println("Enter file name:");
        return scanner.nextLine();
    }

    @Override
    public String getCommandInput() {
        return scanner.nextLine();
    }
    public Presenter getPresenter(){
        return this.presenter;
    }

    public void loadPetsRepository() {
        String path = this.getFileNameInput();
        try {
            this.presenter.loadFromFile(path);
        }
        catch (IOException e){
            showMessage("Указанный путь неверен");
            showMessage("Файл будет прочитан из стандартного пути " + presenter.getPATH());
            try {
                this.presenter.loadFromFile();
            }
            catch (IOException | ClassNotFoundException er){
                showMessage(er.getMessage());
            }
        } catch (ClassNotFoundException e) {
            showMessage("Класс в считанном файле и в программе не совпал, проверьте версионность");

        }
    }

    public void savePetsRepository(){
        String path = getFileNameInput();
        try{
            presenter.saveToFile(path);
        }
        catch (IOException e){
            showMessage("Не возможно сохранить в файл " + path +
                    ". Файл будет сохранен в стандартный путь " + presenter.getPATH());
            try {
                presenter.saveToFile(presenter.getPATH());
            }
            catch (IOException er){
                showMessage(er.getMessage());
            }
        }
    }
    private void showPetType(){
        int num = 1;
        for (PetType type :PetType.values()) {
            showMessage(num++ + ". " + type);
        }
    }

    public void showOnlyPetType(){
        showMessage("Выберите тип животного введя номер, или нажмите enter для возврата в меню выше");
        showPetType();
        String answer = getCommandInput();
        if (checkTextForInt(answer)){
            int intAnswer = Integer.parseInt(answer);
            if (intAnswer <= PetType.values().length && intAnswer > 0){
                for (Pet pet: presenter.getOnlyPetType(intAnswer)) {
                    System.out.println(pet);
                }
            }
        }
    }
    private boolean checkCommand(int numCommand) {
        return numCommand <= menu.getSize();
    }

    private boolean checkTextForInt(String text) {
        return text.matches("[0-9]+");
    }
    private void printMenu() {
        showMessage(menu.menu());
    }

    public void showPetsOnlyCommand(){
        showMessage("Введите название команды или ее часть");
        String command = getCommandInput();
        for (Pet pet: presenter.getOnlyCommand(command)){
            System.out.println(pet);
        }

    }

    public void getYounger(){
        showMessage("Введите предельный возраст животного");
        String answer = getCommandInput();
        if (checkTextForInt(answer)) {
            int intAnswer = Integer.parseInt(answer);
            if (intAnswer > 0) {
                for (Pet pet : presenter.getYounger(intAnswer)) {
                    System.out.println(pet);
                }
            }
        }
    }
    public void getOlder(){
        showMessage("Введите начальный возраст животного");
        String answer = getCommandInput();
        if (checkTextForInt(answer)) {
            int intAnswer = Integer.parseInt(answer);
            if (intAnswer > 0) {
                for (Pet pet : presenter.getOlder(intAnswer)) {
                    System.out.println(pet);
                }
            }
        }
    }

    public void exit(){
        System.exit(0);
    }
    private void execute() {
        String line = scanner.nextLine();
        if (checkTextForInt(line)) {
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)) {
                menu.execute(numCommand);
            }
        }
    }
    public void start() {
        while (exit) {
            showMessage("Добрый день! Выберите одно из действий:");
            printMenu();
            execute();
        }
    }
    public void addPet(){
        showMessage("Выберите тип домашнего животного:");
        showPetType();
        String answer = getCommandInput();
        if (checkTextForInt(answer)){
            int intAnswer = Integer.parseInt(answer);
            if (intAnswer <= PetType.values().length && intAnswer > 0){
                String namePet = getNameInput();
                try {
                    LocalDate bd = getDateOfBirthdayInput();
                    presenter.NewPet(intAnswer, namePet, bd);
                    showMessage("Животное добавлено");
                    return;
                }
                catch (DateTimeParseException e) {
                    showMessage("Введен неверный формат даты рождения");
                    return;
                }
            }
        }
    }
    public void addCommand(){
        showMessage("Введите id домашнего животного:");
        String answer = scanner.nextLine();
        if (checkTextForInt(answer)){
            int id = Integer.parseInt(answer);
            showMessage("Введите команду:");
            String command = scanner.nextLine();
            presenter.addCommand(id, command);
        }
    }
}
