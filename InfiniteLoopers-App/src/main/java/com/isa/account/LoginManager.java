package com.isa.account;

import java.util.List;

import static com.isa.menu.Menu.menuInvoke;

public class LoginManager {
    public LoggedUser getLoggedUser() {
        return loggedUser;
    }

    private LoggedUser loggedUser = new LoggedUser();
    private static AuthenticationService authService;
    private static UserInputReader userInputReader;

    public LoginManager(AuthenticationService authService) {
        this.authService = authService;
        this.userInputReader = new UserInputReader();
    }
    public void login() {
        System.out.println("Witam. Prosze wprowadzic login i haslo.");

        String loginUser = userInputReader.readNonEmptyString("Login: ");
        String passwordUser = userInputReader.readNonEmptyString("Haslo: ");

        User user = authService.login(loginUser, passwordUser);


        if (user != null  ) {
            if (user.getUserRole() == null){
                System.out.println("Twoje konto nie jest jeszcze aktywne. Sprobuj ponownie pozniej lub skontaktuj sie z administratorem.");
                menuInvoke();
            }
            else{
            loggedUser.logUser(user);
            System.out.println("Jestes zalogowany");
        }
        }

        else {
            System.out.println("Niepoprawne dane! Sprobuj jeszcze raz!");
            login();
        }
    }
    public void loginAdmin() {
        UserManager userManager = new UserManager();

        AdminUser admin = new AdminUser();
        userManager.addUser(admin);

        String enterLogin = userInputReader.readNonEmptyString("Wprowadz login administratora: ");
        String enteredPassword = userInputReader.readNonEmptyString("Wprowadz haslo administratora: ");

        if (admin.getLoginUser().equals(enterLogin) && admin.authenticate(enteredPassword)) {
            System.out.println("Administrator zalogowany!");
            RoleAssinger roleAssinger = new RoleAssinger();
            List<User> userList = userManager.loadUsersFromFile();
            if (userList.stream().filter(user -> user.getUserRole() == null).toArray().length != 0){
                roleAssinger.assingRoles(userList, admin);
            } else {
                System.out.println("Wszyscy urzytkownicy posiadaja role. Powrot do glownego menu.");
                menuInvoke();
            }

        } else {
            System.out.println("Administrator nie jest zalogowany.");
        }
    }
}
