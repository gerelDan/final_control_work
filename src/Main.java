import model.Pet;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Pet tuzik = new Pet("Tuzik",
                LocalDate.parse("2022-11-11"));
        System.out.println(tuzik.getCommands());
        System.out.println(tuzik);
    }
}