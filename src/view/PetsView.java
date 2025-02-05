package view;

import model.Pet;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface PetsView {
        void showPets();
        void showMessage(String message);
        LocalDate getDateOfBirthdayInput();
        String getNameInput();
        String getFileNameInput();
        String getCommandInput();
}
