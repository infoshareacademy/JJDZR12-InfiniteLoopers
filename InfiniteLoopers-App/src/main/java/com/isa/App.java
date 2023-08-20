package com.isa;

import menu.ClearConsole;
import menu.Menu;

public class App
{
    public static void main( String[] args )
    {
        Menu menu =new Menu();
        ClearConsole.clearConsole();
        menu.menuInvoke();

    }
}
