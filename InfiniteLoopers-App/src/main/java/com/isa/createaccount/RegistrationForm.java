package com.isa.createaccount;

import java.io.FileWriter;
import java.io.IOException;

public class RegistrationForm {
    private UserInputReader userInputReader = new UserInputReader();

    public void registrationFrom() {

        System.out.println("Rejestracja nowego użytkownika");
        String loginNewUser = userInputReader.readString("Wprowadz Login: ");
        String passwordNewUser = userInputReader.readString("Wprowadz haslo: ");
        String emailNewUser;

        do {
            emailNewUser = userInputReader.readString("Wprowadz adres e-mail: ");
            if (!emailNewUser.contains("@")) {
                System.out.println("Adres email ma zawierac @. Prosze wprowadzic poprawny email");
            }
        } while (!emailNewUser.contains("@"));

        String firstNameNewUser = userInputReader.readString("Wprowadz Imie: ");
        String lastNameNewUser = userInputReader.readString("Wporawdz Nazwisko: ");
        int ageNewUser;

        do {
            ageNewUser = Integer.parseInt(userInputReader.readString("Wprowadz wiek: "));
            if (ageNewUser < 6 || ageNewUser > 99) {
                System.out.println("Wiek ma byc w przedzialie od 7 do 99");
            }
        } while (ageNewUser < 6 || ageNewUser > 99);

        String nameSchoolNewUser = userInputReader.readString("Wporawdz nazwe szkoly: ");

        try {
            FileWriter writer = new FileWriter("users.txt", true);
            writer.write("FirstName: " + firstNameNewUser + "\n");
            writer.write("LastName: " + lastNameNewUser + "\n");
            writer.write("Age: " + ageNewUser + "\n");
            writer.write("School: " + nameSchoolNewUser + "\n");
            writer.write("Email: " + emailNewUser + "\n");
            writer.write("Login: " + loginNewUser + "\n");
            writer.write("Pass: " + passwordNewUser + "\n");
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error podczas rejestracji, sproboj pozniej.");
        }

        System.out.println("Rejestracja przebiegła pomyślnie.");

    }
}