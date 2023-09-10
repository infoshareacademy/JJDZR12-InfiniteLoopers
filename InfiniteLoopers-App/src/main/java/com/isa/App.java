package com.isa;

import com.isa.grades.GradesManagement;
import com.isa.grades.User;
import com.isa.menu.ClearConsole;
import static com.isa.menu.Menu.menuInvoke;
import com.isa.account.RegistrationForm;
import com.isa.account.UserInputReader;
import com.isa.account.UserManager;

public class App
{
    public static void main( String[] args )
    {
       // ClearConsole.clearConsole();
        menuInvoke();
        GradesManagement gradesManagement = new GradesManagement();

        User user1 = new User();

        user1.setUserID("2");

        gradesManagement.fillUserMap();
        gradesManagement.fillUserMap();
        gradesManagement.fillUserMap();
      
       UserInputReader userInputReader = new UserInputReader();
        UserManager userManager = new UserManager();
        RegistrationForm registrationForm = new RegistrationForm(userInputReader, userManager);
        registrationForm.registrationFrom();
    }
}