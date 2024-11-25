package ProjekPemdas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginSession {
    static Scanner scanner = new Scanner(System.in);
    static Account obj = new Account();
    static Game game = new Game();
    public static void welcome() {
        System.out.print("""
                                               ▒█░░▒█ █▀▀ █░░ █▀▀ █▀▀█ █▀▄▀█ █▀▀\s
                                               ▒█▒█▒█ █▀▀ █░░ █░░ █░░█ █░▀░█ █▀▀\s
                                               ▒█▄▀▄█ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀▀ ▀░░░▀ ▀▀▀
                """);
        Main.thickLine();
        System.out.print("""
                ▀██▀  ▀██▀                                  █             ▄▄█▀▀▀▄█                     ██             ▀██▀  ▀█▀                  ▀██    ▄  \s
                 ██    ██   ▄▄▄▄    ▄▄▄▄   ▄▄▄▄   ▄▄ ▄▄▄       ▄▄▄▄     ▄█▀     ▀    ▄▄▄   ▄▄ ▄▄ ▄▄   ▄▄▄    ▄▄▄▄      ▀█▄  ▄▀   ▄▄▄▄   ▄▄▄ ▄▄▄   ██  ▄██▄ \s
                 ██▀▀▀▀██  ▀▀ ▄██  ██▄ ▀  ▀▀ ▄██   ██  ██     ██▄ ▀     ██         ▄█  ▀█▄  ██ ██ ██   ██  ▄█   ▀▀      ██  █   ▀▀ ▄██   ██  ██   ██   ██  \s
                 ██    ██  ▄█▀ ██  ▄ ▀█▄▄ ▄█▀ ██   ██  ██     ▄ ▀█▄▄    ▀█▄      ▄ ██   ██  ██ ██ ██   ██  ██            ███    ▄█▀ ██   ██  ██   ██   ██  \s
                ▄██▄  ▄██▄ ▀█▄▄▀█▀ █▀▄▄█▀ ▀█▄▄▀█▀ ▄██▄ ██▄    █▀▄▄█▀     ▀▀█▄▄▄▄▀   ▀█▄▄█▀ ▄██ ██ ██▄ ▄██▄  ▀█▄▄▄▀        █     ▀█▄▄▀█▀  ▀█▄▄▀█▄ ▄██▄  ▀█▄▀                                                                                                                                     \s
                """);
        Main.thickLine();

    }
    public static void lobby() {
        String username;
        if (Account.player[0] == null) {
            System.out.println("You are new to the game, please register first!");
            System.out.print("Press enter to register...");
            scanner.nextLine();
            Main.line();
            signUp();
        } else {
            username = Account.player[Account.latestIndexPlayer()].getUsername();
            System.out.println("Welcome back, " + username + "!");
        }
        System.out.println("==========================================================================================================");
    }
    public static void login() {
        String username;
        String password;
        System.out.println("Please enter your username and password to login!");
        try {
            System.out.print("Username : ");
            username = scanner.nextLine();
            System.out.print("Password : ");
            password = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, try again!");
            System.out.println("==========================================================================================================");
            login();
            return;
        }
        username = obj.searchUsernameAccount(username);
        password = obj.searchPasswordAccount(password);
        if (username == null || password == null) {
            if (username == null) {
                System.out.println("Username not found, pls try again!");
            }
            if (password == null) {
                System.out.println("Password doesn't match, pls try again!");
            }
            Main.line();
            login();
        } else {
            System.out.println("Login success!");
        }
    }
    public static void signUp() {
        String username;
        String password;
        System.out.print("Please enter your username and password to register!");
        try {
            System.out.print("\nUsername : ");
            username = scanner.nextLine();
            System.out.print("Password : ");
            password = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, try again!");
            System.out.println("==========================================================================================================");
            return;
        }
        obj.addAccount(username, password);
        System.out.println("Your account has been created, now you can login!");
        Main.thickLine();
        login();
        game.showMainMenu(username);
    }
}
