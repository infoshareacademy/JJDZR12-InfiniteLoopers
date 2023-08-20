package com.isa;

import com.isa.menu.ClearConsole;
import com.isa.menu.Menu;

public class App
{
    public static void main( String[] args )
    {
        Menu menu =new Menu();
        ClearConsole.clearConsole();
        menu.menuInvoke();

    }
}
