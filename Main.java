package ProjekPemdas;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void thickLine() {
        System.out.println("===================================================================================================================================================");
    }

    public static void line() {
        System.out.println("-----------------------------------------------------------");
    }

    public static void main(String[] args) {
        LoginSession logInSession = new LoginSession();
        Account account = new Account();
        Game game = new Game();
        Product product = new Product();
        Storage storage = new Storage();
        Customer customer = new Customer();
        String username;

        logInSession.welcome();
        logInSession.lobby();

        }
    }
