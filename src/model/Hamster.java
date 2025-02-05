package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Hamster extends Pet implements Serializable {

//    public Hamster(String name, LocalDate db) {
//        super(name, db);
//    }
    public Hamster() {
        this.petType = PetType.Hamster;
    }
}
