package com.isa.createaccount;

import java.util.Scanner;

public class RegistrationForm {
    Scanner scanner = new Scanner(System.in);
    public void registrationFrom () {

        System.out.println("Rejestracja nowego użytkownika");
        System.out.print("Wprowadz Login: ");
        String loginNewUser = scanner.nextLine();
        System.out.print("Wprowadz haslo: ");
        String passwordNewUser = scanner.nextLine();
        String emailNewUser;

            do {
                System.out.print("Wprowadz adres e-mail: ");
                emailNewUser = scanner.nextLine();

                if (!emailNewUser.contains("@")) {
                    System.out.println("Adres email ma zawierac @. Prosze wprowadzic poprawny email");
                }
            } while (!emailNewUser.contains("@"));

        System.out.print("Wprowadz Imie: ");
        String firstNameNewUser = scanner.nextLine();
        System.out.print("Wporawdz Nazwisko: ");
        String lastNameNewUser = scanner.nextLine();
        System.out.print("Wporawdz nazwe szkoly: ");
        String nameSchoolNemUser = scanner.nextLine();

    }
}
