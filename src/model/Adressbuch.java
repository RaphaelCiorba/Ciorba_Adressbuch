package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Adressbuch {

    private List<Person> persons = new ArrayList<Person>();

    public Adressbuch(){

        //Vorgefertigte Personen, die schon in der CSV Liste vorhanden sind
        addDistinct("Raphael Ciorba", "Laakirchen 4663", "+43 699 10777447");
        addDistinct("Chuj Dupa", "Newport CF3", "+44 14378 74328");
    }

    public Person getPerson(int index){
        return persons.get(index - 1);
    }

    public int getSize(){
        return persons.size();
    }

    public void load(){
        try {
            Scanner sc = new Scanner(new File("addressBook.csv"));
            persons.clear();

            String line;
            while(sc.hasNextLine() && (line = sc.nextLine()) != null){
                String[] data = line.split(";");
                if(data.length == 3) {
                    if (!data[0].isEmpty() && !data[1].isEmpty() && !data[2].isEmpty() && data[0].matches("^\\p{Lu}\\p{L}*(?:[\\s-]\\p{L}*)*$") && data[1].matches("^\\p{Lu}\\p{L}*(?:[\\s-]\\p{L}*)*,?\\s\\d{3,10}$") && data[2].matches("^\\+\\d{1,3}/\\d{1,3}/\\d{7}$")) {
                        addDistinct(data[0], data[1], data[2].replaceAll("/", " "));
                    }
                }
            }
            sc.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveCSV(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("addressBook.csv"))) {

            for(int i = 0; i < persons.size(); i++){
                bw.write(persons.get(i).toString());
                bw.newLine();
            }
            System.out.println("Save successful!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePerson(int pos, String name, String address, String telNum){
        persons.get(pos-1).setName(name);
        persons.get(pos-1).setAddress(address);
        persons.get(pos-1).setTelNum(telNum);
    }


    public void addDistinct(String name, String address, String telNum){
        Person pers = new Person();
        pers.setName(name);
        pers.setAddress(address);
        pers.setTelNum(telNum);

        persons.add(pers);
    }

    public void addEmpty(){
        Person pers = new Person();
        pers.setName("");
        pers.setAddress("");
        pers.setTelNum("");
        persons.add(pers);
    }

    public void delete(int pos){
        persons.remove(pos-1);  //starts at 0
    }

}