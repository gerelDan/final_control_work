package model;

import java.time.LocalDate;

public class PetCreator {

    protected Pet createNewPet (PetType type) {

        return switch (type) {
            case Cat -> new Cat();
            case Dog -> new Dog();
            case Hamster -> new Hamster();
        };
    }

    public Pet createPet(PetType type, String name, LocalDate date) {
        Pet pet = createNewPet(type);
        pet.setName(name);
        pet.setBirthday(date);
        return pet;
    }
}