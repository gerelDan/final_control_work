package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Dog extends Pet implements Serializable {
//    public Dog(String name, LocalDate db) {
//        super(name, db);
//    }
    public Dog() {
        this.petType = PetType.Dog;
    }
}
