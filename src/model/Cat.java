package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Cat extends Pet implements Serializable {
//    public Cat(String name, LocalDate db) {
//        super(name, db);
//    }
    public Cat() {
        this.petType = PetType.Cat;
}
}
