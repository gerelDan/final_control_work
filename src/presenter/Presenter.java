package presenter;

import model.Pet;
import model.PetType;
import model.Service;
import view.PetsView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import java.io.*;

public class Presenter {
    Service service;
    String PATH = "petsRepository";
    public Presenter(Service service, PetsView view){
        this.service = service;

    }
    public void saveToFile(String path) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new
                FileOutputStream(path))) {

            objectOutputStream.writeObject(this.service);
        }
    }

    public void loadFromFile(String path) throws IOException, ClassNotFoundException{
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            this.service =  (Service) objectInputStream.readObject();
        }
    }
    public void loadFromFile() throws IOException, ClassNotFoundException{
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH))) {
            this.service =  (Service) objectInputStream.readObject();
        }
    }
    public List<Pet> getAllPets(){
        return this.service.getAllPets();
    }
    public String getPATH(){
        return PATH;
    }
    public List<Pet> getOnlyPetType(Integer petType){
        return service.getOnlyPetType(petType);
    }

    public List<Pet> getOnlyCommand(String command){
        return service.getOnlyCommand(command);
    }

    public List<Pet> getYounger(Integer age){
        return service.getYounger(age);
    }

    public List<Pet> getOlder(Integer age){
        return service.getPetsOlder(age);
    }
    public void NewPet(int intPetType, String name, LocalDate db) {
        service.addPet(service.newPet(intPetType, name, db));
    }
    public void addCommand(int id, String command){
        service.getPetById(id).addCommand(command);
    }

}
