package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Pet extends Animal implements Serializable{
    protected Integer id;
    protected String commands;
//    public Pet(String name, LocalDate db){
//        this.name = name;
//        this.db = db;
//    }
    public Integer getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setBirthday(LocalDate date) {
        this.db = date;
    }

    public String getName(){
        return this.name;
    }

    public LocalDate getDb(){
        return this.db;
    }

    public String getCommands() {
        return commands;
    }
    public void addCommand(String command){
        if (this.commands.isEmpty()){
            this.commands = command;
        } else this.commands = this.commands + ", " + command;
    }
    void setId(Integer id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(commands, pet.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, commands);
    }

    @Override
    public String toString() {
        return "Pet{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                "db=" + db +
                ", commands='" + commands + '\'' +
                '}';
    }
    public Integer getAge(){
        return getDb().until(LocalDate.now()).getYears();
    }

}
