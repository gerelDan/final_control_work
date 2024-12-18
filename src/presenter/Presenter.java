package presenter;

import model.Service;
import view.PetsView;

import java.io.*;

public class Presenter {
    Service service;
    public Presenter(Service service, PetsView view){
        this.service = service;

    }
    public void saveToFile(String path) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new
                FileOutputStream(path))) {

            objectOutputStream.writeObject(this.service);
        }
    }

    public void loadFromFile(String path) throws IOException, ClassNotFoundException{
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            this.service =  (Service) objectInputStream.readObject();
        }
    }
}
