package com.isa;

import com.isa.account.RegistrationForm;
import com.isa.account.UserInputReader;
import com.isa.account.UserManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserInputReader userInputReader = new UserInputReader();
        UserManager userManager = new UserManager();
        RegistrationForm registrationForm = new RegistrationForm(userInputReader, userManager);
        registrationForm.registrationFrom();


    }
}
