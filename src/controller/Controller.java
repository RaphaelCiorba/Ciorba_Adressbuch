package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Person;
import model.Adressbuch;

public class Controller {

    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField phoneField;
    @FXML private Label posLabel;

    private Adressbuch phoneBook = new Adressbuch();
    private int position = 1;


    @FXML
    public void initialize() {
        updatePage();
    }


    private void updatePage(){
        posLabel.setText(position + "/" + phoneBook.getSize());

        Person person = phoneBook.getPerson(position);
        nameField.setText(person.getName());
        addressField.setText(person.getAddress());
        phoneField.setText(person.getTelNum());
    }


    @FXML
    private void nextPerson(){
        position++;

        if(position > phoneBook.getSize())
            position = 1;

        updatePage();
    }

    @FXML
    private void lastPerson(){
        position--;

        if(position < 1)
            position = phoneBook.getSize();

        updatePage();
    }


    @FXML
    private void addPerson(){
        phoneBook.addEmpty();
        position = phoneBook.getSize();
        updatePage();
    }


    @FXML
    private void deletePerson(){
        phoneBook.delete(position);
        if(phoneBook.getSize() == 0) {    //if list empty
            phoneBook.addEmpty();
            position = 1;
        }
        else if(position > phoneBook.getSize())
            position = phoneBook.getSize();
        updatePage();
    }


    @FXML
    private void saveChanges(){
        String name = nameField.getText();
        String address = addressField.getText();
        String telNum = phoneField.getText();
        phoneBook.changePerson(position, name, address, telNum);
    }


    @FXML
    private void loadFromCSV(){
        phoneBook.load();
        position = 1;

        if(phoneBook.getSize() == 0) {    //if list empty
            phoneBook.addEmpty();
        }

        updatePage();
    }


    @FXML
    private void saveToCSV(){
        phoneBook.saveCSV();
    }


}