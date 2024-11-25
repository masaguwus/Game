package ProjekPemdas;
import java.util.Random;
import java.util.Scanner;

public class Customer {
    static Scanner in = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[]args) {
        int customerTotal = 3; //assume
        double storeRating = 8.0;
        int bookVariety = 10;
        double averagePopularity = 7.5;
        int days = 30;
        int playerLevel = 3;

        for (int i = 1; i <= customerTotal; i++) {
            System.out.println("\nHi, Customer " + i + "! Your feedback matters!");
            System.out.println("Yo, rate us between 1 and 10");
            System.out.println("----------------------------");
            System.out.println("Customer " + i + " feedback: ");
            int rating = getRating();
            int level = getCustomerLevel(storeRating, bookVariety, averagePopularity, days, playerLevel, rating);
            showCustomerFeedback(i, rating, level);
            System.out.println("\n---------------------------");
        }
    }
    public static int getRating() {
        System.out.print("Give a rating (1-10): ");
        int rating = in.nextInt();
        while (rating < 1 || rating > 10) {
            System.out.print("Oops! that rating is out of range :(");
            rating = in.nextInt();
        }
        return rating;
    }
    public static int getCustomerLevel(double storeRating, int bookVariety, double averagePopularity, int days, int playerLevel, int rating) {
        double score = storeRating * 0.3 + bookVariety * 0.2 + averagePopularity * 0.3 + days * 0.1 + playerLevel * 0.5 + rating * 1.0;
        if (score >= 50) {
            return 5;
        } else if (score >= 40) {
            return 4;
        } else if (score >= 30) {
            return 3;
        } else if (score >= 20) {
            return 2;
        } else {
            return 1;
        }
    }
    public static void showCustomerFeedback(int customerNumber, int rating, int level){
        System.out.println("Customer " + customerNumber + " gave a rating of " + rating + " / 10 ");
        System.out.println("Customer's level is: " + level);
        System.out.println("Thanks a lot for your feedback!");
    }
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
            if (slots[i].getPopularity() != 0) {
                sum += slots[i].getPopularity();
                length++;
            }
        }
        double randomByPopularity = sum / (45.0 * length);
        randomByPopularity = randomByPopularity > 25 ? 25 : randomByPopularity;
        double randomByLevel = level + 9.0;
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
            if (!rarity.contains(slots[i].getRarity())) {
                rarity += slots[i].getRarity();
            }
        }
        String[] kok = rarity.split(" ");
        int factor = random.nextInt(kok.length);
        String books = "";
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].getRarity().contains(kok[factor])) {
                books += slots[i].getNamaBarang() + ". ";
            }
        }
        long total = 0;
        String[] anotherBooks = books.split(". ");
        for (int i = 0; i < customer; i++) {
            int buyBook = random.nextInt(anotherBooks.length);
            String lucky = anotherBooks[buyBook];
            int quantity = customerBuyBook(username);
            Product book = Account.player[index].getSlotBookByTitle(lucky);
            long profit = book.getSellPrice() * quantity;
            book.setQuantity(book.getQuantity() - quantity);
            Product[] temp = Account.player[index].getSlots();
            for (int ii = 0; ii < temp.length; ii++) {
                if (temp[i].getNamaBarang().contains(lucky)) {
                    temp[i].setQuantity(temp[i].getQuantity() - quantity);
                    break;
                }
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
