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

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void thickLine() {
        System.out.println("===================================================================================================================================================");
    }

    public static void line() {
        System.out.println("-----------------------------------------------------------");
    }

    public static void main(String[] args) throws InterruptedException {
        LoginSession logInSession = new LoginSession();

        logInSession.welcome();
        ConsoleProgressBar.main(null);
        System.out.println();
        System.out.println();
        logInSession.lobby();

        }
    }
