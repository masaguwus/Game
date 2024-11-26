/*
 Abdullah Hasan Muhajir
 NIM : 245150200111075
 Diny Eka Zharafah
 NIM : 245150207111088
 Jason Manuel
 NIM : 245150201111050
 Ni Putu Nadiendha Nirzanova Dewi
 NIM : 245150200111067
 */
package ProjekPemdas;

import java.util.InputMismatchException;
import java.util.Scanner;

class ConsoleProgressBar {
    public static void main(String[] args) {
        int total = 147;
        for (int i = 0; i <= total; i++) {
            printProgress(i, total);
            try {
                if (i > 140) {
                    Thread.sleep(150);
                } else {
                    Thread.sleep(20);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printProgress(int current, int total) {
        int barLength = 73;
        int progress = (int) ((double) current / total * barLength);

        String bar = "           ⟦";
        for (int i = 0; i < barLength; i++) {
            if (i < progress) {
                bar += "▰";
            } else {
                bar += "▱";
            }
        }
        bar += "⟧ " + (int)((current * 68.5 / 100) * (147 * 68.5 / 100) / (total * 68.5 / 100)) + "%";
        System.out.print("\r" + bar);
    }
}


public class LoginSession {
    static Scanner scanner = new Scanner(System.in);
    static Account obj = new Account();
    static Game game = new Game();
    public static void welcome() throws InterruptedException {
        String[] welcomeA = {
                "                                                 ▒█░░▒█ █▀▀ █░░ █▀▀ █▀▀█ █▀▄▀█ █▀▀\s",
                "                                                 ▒█▒█▒█ █▀▀ █░░ █░░ █░░█ █░▀░█ █▀▀\s",
                "                                                 ▒█▄▀▄█ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀▀ ▀░░░▀ ▀▀▀"
        };
        Main.thickLine();
        for (int i = 0; i < welcomeA.length; i++) {
            System.out.println(welcomeA[i]);
            Thread.sleep(500);
        }
        Main.thickLine();
        String[] logo = {
                "    ▀██▀  ▀██▀                                  █             ▄▄█▀▀▀▄█                     ██            ", " ▀██▀  ▀█▀                  ▀██    ▄  \n",
                "     ██    ██   ▄▄▄▄    ▄▄▄▄   ▄▄▄▄   ▄▄ ▄▄▄       ▄▄▄▄", "     ▄█▀     ▀    ▄▄▄   ▄▄ ▄▄ ▄▄   ▄▄▄    ▄▄▄▄      ▀█▄  ▄▀   ▄▄▄▄   ▄▄▄ ▄▄▄   ██  ▄██▄ \n",
                "     ██▀▀▀▀██  ▀▀ ▄██  ██▄ ▀  ▀▀ ▄██   ██  ██     ██▄ ▀     ██         ▄█  ▀█▄  ██ ██ ██   ██  ▄█   ▀▀", "      ██  █   ▀▀ ▄██   ██  ██   ██   ██  \n",
                "     ██    ██  ▄█▀ ██  ▄ ▀█▄▄ ▄█▀ ██   ██  ██     ▄ ▀█▄▄", "    ▀█▄      ▄ ██   ██  ██ ██ ██   ██  ██            ███    ▄█▀ ██   ██  ██   ██   ██  \n",
                "    ▄██▄  ▄██▄ ▀█▄▄▀█▀ █▀▄▄█▀ ▀█▄▄▀█▀ ▄██▄ ██▄    █▀▄▄█▀ ", "    ▀▀█▄▄▄▄▀   ▀█▄▄█▀ ▄██ ██ ██▄ ▄██▄  ▀█▄▄▄▀        ", "█     ▀█▄▄▀█▀  ▀█▄▄▀█▄ ▄██▄  ▀█▄▀"
        };
        for (int i = 0; i < logo.length; i++) {
            System.out.print(logo[i]);
            Thread.sleep(350);
        }
        System.out.println();
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
