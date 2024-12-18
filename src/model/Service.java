package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service implements Serializable {
    private List<Pet> registry;
    private Counter counter;
    public Service(){
        this.registry = new ArrayList<>();
        this.counter = new Counter();
    }

    public void addPet(Pet pet){
        pet.setId(this.counter.add());
        this.registry.add(pet);
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

}
