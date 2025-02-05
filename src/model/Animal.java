package model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Animal implements Serializable {
    String name;
    LocalDate db;
}
