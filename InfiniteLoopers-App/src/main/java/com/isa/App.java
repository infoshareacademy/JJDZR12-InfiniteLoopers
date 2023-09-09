package com.isa;

import com.isa.grades.GradesManagement;
import com.isa.grades.User;
import com.isa.menu.ClearConsole;
import static com.isa.menu.Menu.menuInvoke;

public class App
{
    public static void main( String[] args )
    {
       // ClearConsole.clearConsole();
        //menuInvoke();
        GradesManagement gradesManagement = new GradesManagement();

        User user1 = new User();

        user1.setUserID("2");

        gradesManagement.fillUserMap();
        gradesManagement.fillUserMap();
        gradesManagement.fillUserMap();





    }
}
