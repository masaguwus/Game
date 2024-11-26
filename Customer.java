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
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Customer {
    static Random random = new Random();

    public static int randomnessLevel(Product[] slots, int days, int bookVariety, int level, String username){
        int customer;
        // total percentage 100%
        // days 20%
        // books variety 25%
        // popularity 25%
        // player level 30%
        double randomByDays = 2.0 / 10 * days + (8.0 / 10);
        randomByDays = randomByDays > 20 ? 20 : randomByDays;
        double randomByBooks = 7.0 / 10 * bookVariety + (3.0 / 10);
        randomByBooks = randomByBooks > 25 ? 25 : randomByBooks;
        double sum = 0;
        int length = 0;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                continue;
            }
            if (slots[i].getPopularity() != 0) {
                sum += slots[i].getPopularity();
                length++;
            }
        }
        double randomByPopularity = sum / (45.0 * length);
        randomByPopularity = randomByPopularity > 25 ? 25 : randomByPopularity;
        double randomByLevel = level * 1.3 + 3.0;
        randomByLevel = randomByLevel > 30 ? 30 : randomByLevel;
        double percentage = randomByLevel + randomByBooks + randomByDays + randomByPopularity;
        if (level > 30) {
            customer = Math.max((int) (Math.random() * percentage), 25);
        } else if (level > 25) {
            customer = Math.max((int) (Math.random() * percentage), 20);
        } else if (level > 20) {
            customer  = Math.max((int) (Math.random() * percentage), 15);
        } else if (level > 15) {
            customer = Math.max((int) (Math.random() * percentage), 10);
        } else if (level > 10) {
            customer = Math.max((int) (Math.random() * percentage), 5);
        } else {
            customer = Math.max((int) (Math.random() * percentage), 1);
        }
        System.out.println("Customer today : " + customer);
        return customer;
    }
    public static void randomBuyCustomer(Product[] slots, String username, int bookVariety, int level, int days) {
        String rarity = "";
        int customer = randomnessLevel(slots, days, bookVariety, level, username);
        int index = Account.searchIndexAccountStr(username);
        // Legendary > 30
        // Epic > 20
        // rare > 20
        // uncommon > 15
        // common > 15
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                continue;
            }
            if (!rarity.contains(slots[i].getRarity())) {
                rarity += slots[i].getRarity();
            }
        }
        String[] kok = rarity.split(" ");
        if (kok.length == 0) {
            System.out.println("Error: no rarity found");
            return;
        }
        int factor = random.nextInt(kok.length);
        String books = "";
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                continue;
            }
            if (slots[i].getRarity().equalsIgnoreCase(kok[factor])) {
                books += slots[i].getNamaBarang() + "    ";
            }
        }
        long total = 0;
        String[] anotherBooks = books.split("    ");
        for (int i = 0; i < customer; i++) {
            int buyBook = random.nextInt(anotherBooks.length);
            String lucky = anotherBooks[buyBook];
            if (Account.player[index].getSlotBookByTitle(lucky) == null) {
                System.out.println("Error: book not found");
                continue;
            }
            int slotsQuantity = (int)Account.player[index].getSlotBookByTitle(lucky).getQuantity();
            int quantity = Math.min(slotsQuantity, customerBuyBook(username));
            if (quantity <= 0) {
                continue;
            }
            Product book = Account.player[index].getSlotBookByTitle(lucky);
            long profit = book.getSellPrice() * quantity;
            Objects.requireNonNull(Account.player[index].getSlotBookByTitle(lucky)).setQuantity(slotsQuantity - quantity);
            Product[] temp = Account.player[index].getSlots();
            if (temp == null) {
                System.out.println("Error: temp array is null");
                continue;
            }
            Account.player[index].setSlots(temp);
            Account.player[index].setBalance(Account.player[index].getBalance() + profit);
            System.out.println("Customer " + (i + 1) + " bought " + lucky + " " + quantity + "x");
            total += profit;
        }
        System.out.println("Total selling today : " + total);
    }
    public static int customerBuyBook(String username) {
        int book;
        if (username == null) {
            System.out.println("Error: username is null");
            return 0;
        }
        long level = Account.player[Account.searchIndexAccountStr(username)].getLevel();
        if (level > 15) {
            book = random.nextInt(15) + 1;
        } else if (level > 9) {
            book = random.nextInt(10) + 1;
        } else {
            book = random.nextInt(5) + 1;
        }
        return book;
    }
}
