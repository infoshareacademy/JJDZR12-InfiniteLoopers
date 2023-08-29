package com.isa;

import com.isa.menu.ClearConsole;
import static com.isa.menu.Menu.menuInvoke;

public class App
{
    public static void main( String[] args )
    {
        ClearConsole.clearConsole();
        menuInvoke();

    }
}
