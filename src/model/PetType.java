package model;

import java.io.Serializable;

public enum PetType implements Serializable {

    Cat,
    Dog,
    Hamster;

    public static PetType getType (int typeId){
        return switch (typeId) {
            case 1 -> PetType.Cat;
            case 2 -> PetType.Dog;
            case 3 -> PetType.Hamster;
            default -> null;
        };
    }
}
