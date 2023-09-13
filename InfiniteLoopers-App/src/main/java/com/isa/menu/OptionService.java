package com.isa.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static com.isa.menu.Menu.menuInvoke;

public class OptionService {

    private OptionService() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    protected static final List<Option> optionList = createOptionList();

    public static List <Option> createOptionList() {

        List <Option> optionList = new ArrayList<>();

        Option optionLogin = new Option(1,"Zaloguj sie");
        optionList.add(optionLogin);

        Option optionLogOut = new Option(2,"Zarejestruj sie");
        optionList.add(optionLogOut);

        Option optionQuit = new Option(3,"Zakończ program");
        optionList.add(optionQuit);

        Option optionLoginAdmin = new Option(4,"Zaloguj administratora");
        optionList.add(optionLoginAdmin);

        return optionList;

    }

    public static List<Option> getOptionList() {
        return optionList;
    }

    public static void backToMainMenu() {
       menuInvoke();
    }

    public static void closeApp(){
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("**************************************************");
        System.out.println("     Program został zakończony...Do widzenia!     ");
        System.out.println("**************************************************");
        System.out.println("\n");
        System.out.println("\n");
    }



}
