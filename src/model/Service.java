package model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service implements Serializable {
    private List<Pet> registry;
    private Counter counter;
    private PetCreator petCreator;
    public Service(){
        this.registry = new ArrayList<>();
        this.counter = new Counter();
        this.petCreator = new PetCreator();
    }

    public Pet newPet(int intPetType, String name, LocalDate db){
        PetType petType = PetType.getType(intPetType);
        return petCreator.createPet(petType, name, db);
    }


    public void addPet(Pet pet){
        try {
            pet.setId(this.counter.add());
            this.registry.add(pet);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public List<Pet> getAllPets(){
        return this.registry;
    }

    public Pet getPetById(Integer id){
        return registry.stream()
                .filter(pet ->
                        pet.getId().equals(id))
                .toList().getFirst();
    }
    public List<Pet> getPetsOlder(Integer age){
        return registry.stream()
                .filter(pet ->
                        pet.getAge() >= age)
                .sorted(Comparator.comparing(Pet::getDb))
                .collect(Collectors.toList());
    }

    public List<Pet> getYounger(Integer age){
        return registry.stream()
                .filter(pet ->
                        pet.getAge() <= age)
                .sorted(Comparator.comparing(Pet::getDb))
                .collect(Collectors.toList());
    }
    public List<Pet> getOnlyPetType(Integer petType){
        return registry.stream()
                .filter(pet ->
                        pet.getPetType() == PetType.getType(petType))
                .sorted(Comparator.comparing(Pet::getDb))
                .collect(Collectors.toList());
    }
    public List<Pet> getOnlyCommand(String command){
        return registry.stream()
                .filter(pet ->
                        pet.getCommands().contains(command))
                .sorted(Comparator.comparing(Pet::getDb))
                .collect(Collectors.toList());
    }

}
