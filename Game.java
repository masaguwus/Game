package ProjekPemdas;

import javax.print.MultiDocPrintService;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.*;

/**
 * The Game class represents the main game logic and functionality.
 * It provides methods for displaying game information, managing the store,
 * and handling user input.
 */
public class Game {
    // Create a currency formatter for Indonesian currency
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    /**
     * Returns the index of the account with the given username.
     *
     * @param username the username to search for
     * @return the index of the account, or -1 if not found
     */
    public int accountIndex(String username) {
        return Account.searchIndexAccountStr(username);
    }

    /**
     * Displays the current day of the game.
     *
     * @param username the username of the player
     */
    private void showDay(String username) {
        System.out.println("Day " + Account.player[accountIndex(username)].getDays());
    }

    /**
     * Displays the current level of the player.
     *
     * @param username the username of the player
     */
    private void showLevel(String username) {
        System.out.println("Level   : " + Account.player[accountIndex(username)].getLevel());
    }

    /**
     * Displays the current balance of the player.
     *
     * @param username the username of the player
     */
    private void showBalance(String username) {
        System.out.println("Balance : " + Account.player[accountIndex(username)].getBalance());
        System.out.println();
    }

    /**
     * Displays the main menu information, including day, level, and balance.
     *
     * @param username the username of the player
     */
    public void showMainMenuInfo(String username) {
        showDay(username);
        showLevel(username);
        showBalance(username);
    }

    /**
     * Displays the main menu and handles user input.
     *
     * @param username the username of the player
     */
    public void showMainMenu(String username) {
        Main.line();
        System.out.println("Main Menu");
        showMainMenuInfo(username);
        Product.product((int)Account.player[Account.searchIndexAccountStr(username)].getDays());
        System.out.print("""
                1. Manage Store
                2. Check Info
                3. TimeSkip (start selling books)
                Option : """);
        int option = Main.scanner.nextInt();
        Main.scanner.nextLine();
        switch (option) {
            case 1 -> manageStoreMenu(username);
            case 2 -> checkInfoMenu(username);
            case 3 -> timeSkip(username);
            default -> System.out.println("Invalid option!");
        }
    }

    /**
     * Displays the manage store menu and handles user input.
     */
    public void manageStoreMenu(String username) {
        Main.line();
        System.out.print("""
                Manage your store
                
                1. Upgrade Level
                2. Upgrade Storage
                3. Buy Goods
                4. Back
                Option : """);
        int option = Main.scanner.nextInt();
        Main.scanner.nextLine();
        switch (option) {
            case 1 -> upgradeLevelMenu(username);
            case 2 -> upgradeStorageMenu(username);
            case 3 -> buyGoodsMenu(username);
            case 4 -> showMainMenu(username);
            default -> System.out.println("Invalid option!");
        }
        if (option > 3) {
            showMainMenu(username);
        }
    }

    public void buyGoodsMenu(String username) {
        productBooksBuy(username);
    }

    public void upgradeLevelMenu(String username) {
        long level = Account.player[Account.searchIndexAccountStr(username)].getLevel();
        long price = (long) (Math.pow(level, 2)) * 2 / 10 * 100000;
        long balance = Account.getBalanceByUsername(username);
        if (price > balance) {
            System.out.println();
            System.out.println("You don't have enough balance to upgrade your level!");
            return;
        } else {
            Account.player[Account.searchIndexAccountStr(username)].setLevel(level + 1);
            Account.player[Account.searchIndexAccountStr(username)].setBalance(balance - price);
            System.out.println();
            System.out.println("You have successfully upgraded your level!");
        }
    }

    /**
     * Displays the upgrade storage menu and handles user input.
     */
    public void upgradeStorageMenu(String username) {
        Main.line();
        System.out.print("""
                Upgrade storage
                
                1. Upgrade Total Storage
                2. Upgrade Slots
                3. Back
                Option : """);
        int option = Main.scanner.nextInt();
        Main.scanner.nextLine();
        switch (option) {
            case 1 -> storageTotalUpgrade(username);
            case 2 -> slotsUpgrade(username);
            case 3 -> manageStoreMenu(username);
            default -> System.out.println("Invalid option!");
        }
        if (option > 3) {
            upgradeLevelMenu(username);
        }
    }

    /**
     * Displays the check info menu and handles user input.
     */
    public void checkInfoMenu(String username) {
        Main.line();
        System.out.print("""
                Check info
                
                1. Storage
                2. Books
                3. Back
                Option : """);
        int option = Main.scanner.nextInt();
        Main.scanner.nextLine();
        switch (option) {
            case 1 -> checkStorageInfoMenu(username);
            case 2 -> checkBookInfoMenu(username);
            case 3 -> showMainMenu(username);
            default -> System.out.println("Invalid option!");
        }
        if (option > 3) {
            checkInfoMenu(username);
        }
    }

    /**
     * Displays the check storage info menu and handles user input.
     */
    public void checkStorageInfoMenu (String username){
        Main.line();
        System.out.print("""
                    Check storage & slots
                    
                    1. Slots Info
                    2. Storage Info
                    3. Back
                    Option : """);
        int option = Main.scanner.nextInt();
        Main.scanner.nextLine();
        switch (option) {
            case 1 -> showSlotsInfo(Account.player[Account.searchIndexAccountStr(username)].getSlots(), username);
            case 2 -> showStorageInfo(Account.player[Account.searchIndexAccountStr(username)].getStorage(), username);
            case 3 -> checkInfoMenu(username);
            default -> System.out.println("Invalid option!");
        }
        if (option > 3) {
            checkStorageInfoMenu(username);
        }
    }

    /**
     * Displays the check book info menu and handles user input.
     */
    public void checkBookInfoMenu (String username){
        Main.line();
        System.out.print("""
                    Check Books
                    
                    1. My Books
                    2. Manufacturer's Books
                    3. Back
                    Option :                     """);
        int option = Main.scanner.nextInt();
        Main.scanner.nextLine();
        switch (option) {
            case 1 -> myBooksInfo(Account.player[Account.searchIndexAccountStr(username)].getStorage(), username);
            case 2 -> manufacturerBooksInfo(username);
            case 3 -> checkInfoMenu(username);
            default -> System.out.println("Invalid option!");
        }
        if (option > 3) {
            checkBookInfoMenu(username);
        }
    }

    /**
     * Displays the slots info.
     *
     * @param slots the array of slots
     */
    public void showSlotsInfo (Product[]slots, String username) {
        Slot.checkSlotAvailability(slots);
        showMainMenu(username);
    }

    /**
     * Displays the storage info.
     *
     * @param storage the array of storage
     */

    public void showStorageInfo (Product[]storage, String username){
        int berak = Storage.checkStorage(storage);
        System.out.println("Storage Available : " + berak);
        showMainMenu(username);
    }
    public void myBooksInfo (Product[]storage, String username){
        storageBooksInfo(storage, username);
        checkBookInfoMenu(username);
    }
    public void manufacturerBooksInfo (String username){
        productBooksInfo(username);
        checkBookInfoMenu(username);
    }
    public void bookInfo() {
        System.out.print("Book's title you want to inspect more : ");
        String title = Main.scanner.nextLine();
        Product book = Account.getStorageBooksByTitle(title);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        System.out.println("Title          : " + book.getNamaBarang());
        System.out.println("Purchase price : " + book.getPurchase());
        System.out.println("Sell price     : " + book.getSellPrice());
        System.out.println("Quantity       : " + book.getQuantity());
        System.out.println("Rarity         : " + book.getRarity());
        System.out.println("Popularity     : " + book.getPopularity());
    }
    public void bookInfoMan() {
        System.out.print("Book's title you want to inspect more : ");
        String title = Main.scanner.nextLine();
        Product book = Product.getBooksByTitle(title);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        System.out.println("Title          : " + book.getNamaBarang());
        System.out.println("Purchase price : " + book.getPurchase());
        System.out.println("Sell price     : " + book.getSellPrice());
        System.out.println("Quantity       : " + book.getQuantity());
        System.out.println("Rarity         : " + book.getRarity());
        System.out.println("Popularity     : " + book.getPopularity());
    }
    public void storageBooksInfo (Product[]storage, String username){
        String header = username + "'s books in storage : ";
        System.out.println(header);
        int j = 1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                continue;
            }
            long price = storage[i].getSellPrice();
            System.out.print((j+1) + ". " + storage[i].getNamaBarang() + ", (Rp." + price + ")\n");
            j++;
        }
        Main.line();
        bookInfo();
        showMainMenu(username);
    }
    public void storageTotalUpgrade (String username){
        int index = Account.searchIndexAccountStr(username);
        Account player = Account.player[index];
        int storageLevel = 0;
        long balance = player.getBalance();
        int storageTotal = player.getStorage().length;
        if (storageTotal > 45) {
            storageLevel = 7;
        } else if (storageTotal > 40) {
            storageLevel = 6;
        } else if (storageTotal > 35) {
            storageLevel = 5;
        } else if (storageTotal > 30) {
            storageLevel = 4;
        } else if (storageTotal > 20) {
            storageLevel = 3;
        } else if (storageTotal > 10) {
            storageLevel = 2;
        } else {
            storageLevel = 1;
        }
        System.out.println("Storage level : " + storageLevel);
        System.out.println("Storage space : " + storageTotal);
        System.out.print("Do you want to upgrade to the next level ? (y/n) : ");
        boolean flag = Main.scanner.next().equalsIgnoreCase("y");
        long price = (long) (Math.pow(storageLevel, 2) - storageLevel) * 10000 + 50000;
        if (balance > price) {
            Account.player[index].setBalance(balance - price);
        } else {
            System.out.println("Not enough balance!");
            upgradeStorageMenu(username);
            return;
        }
        int plus = storageLevel > 2 ? 5 : 10;
        if (flag) {
            Product[] temp = new Product[storageTotal + plus];
            System.arraycopy(player.getStorage(), 0, temp, 0, storageTotal);
            Account.player[index].setStorage(temp);
            System.out.println("Storage level has been increased");
        }
        Main.line();
        upgradeStorageMenu(username);
    }
    public void slotsUpgrade (String username){
        int index = Account.searchIndexAccountStr(username);
        Account player = Account.player[index];
        int slotsLevel = 0;
        long balance = player.getBalance();
        int slotsTotal = player.getSlots().length;
        if (slotsTotal > 30) {
            slotsLevel = 7;
        } else if (slotsTotal > 25) {
            slotsLevel = 6;
        } else if (slotsTotal > 20) {
            slotsLevel = 5;
        } else if (slotsTotal > 15) {
            slotsLevel = 4;
        } else if (slotsTotal > 10) {
            slotsLevel = 3;
        } else if (slotsTotal > 5) {
            slotsLevel = 2;
        } else {
            slotsLevel = 1;
        }
        System.out.println("Slots level   : " + slotsLevel);
        System.out.println("Slots space   : " + slotsTotal);
        long price = (long) (Math.pow(slotsLevel, 2) - slotsLevel) * 15000 + 50000;
        System.out.println("Upgrade price : " + price);
        System.out.println("Your balance  : " + Account.player[index].getBalance());
        System.out.print("Do you want to upgrade to the next level ? (y/n) : ");
        boolean flag = Main.scanner.next().equalsIgnoreCase("y");
        if (balance > price) {
            Account.player[index].setBalance(balance - price);
        } else {
            System.out.println("Not enough balance!");
            System.out.println("Your balance : " + balance);
            System.out.println("Upgrade price : " + price);
            upgradeStorageMenu(username);
            return;
        }
        int plus = 5;
        if (flag) {
            Product[] temp = new Product[slotsLevel + plus];
            System.arraycopy(player.getSlots(), 0, temp, 0, slotsLevel);
            Account.player[index].setStorage(temp);
            System.out.println("Storage level has been increased");
        }
        Main.line();
        upgradeStorageMenu(username);
    }
    public void productBooksBuy (String username){
        String rarity = "";
        long level = Account.player[Account.searchIndexAccountStr(username)].getLevel();
        if (level > -1) {
            rarity += "Common";
        }
        if (level > 2) {
            rarity += ", Uncommon";
        }
        if (level > 4) {
            rarity += ", Rare";
        }
        if (level > 6) {
            rarity += ", Epic";
        }
        if (level > 9) {
            rarity += ", Legendary";
        }
        System.out.println("Book available : " + rarity);
        int j = 0;
        for (int i = 0; i < 50; i++) {
            if (rarity.contains(Product.products[i].getRarity())) {
                System.out.println((j + 1) + ". " + Product.products[i].getNamaBarang() + ", (Sell : " + Product.products[i].getSellPrice() + "), (Buy : " + Product.products[i].getPurchase() + ")");
                j++;
            }
        }
        Main.line();
        int option = 0;
        boolean flag = true;
        if (flag) {
            System.out.print("Book you want to buy : ");
            option = Main.scanner.nextInt();
            Main.scanner.nextLine();
            System.out.print("Are you sure ? (y/n) : ");
            flag = Main.scanner.nextLine().equalsIgnoreCase("y");
        }
        option--;
        int i = 50;
        while (j != option && i > 0) {
            if (rarity.contains(Product.products[i].getRarity())) {
                j--;
            }
            i--;
        }
        long balance = Account.getBalanceByUsername(username);
        long price = 0;
        int quantity;
        i++;
        if (price > balance) {
            System.out.println("Not enough balance!");
            return;
        }
        while (true) {
            System.out.print("Quantity : ");
            quantity = Main.scanner.nextInt();
            Main.scanner.nextLine();
            price = Product.products[i].getPurchase() * quantity;
            if (quantity > Product.products[i].getQuantity()) {
                System.out.println("Out of stock!");
                continue;
            }
            if (price > balance) {
                System.out.println("Not enough balance!");
                continue;
            }
            break;
        }
        Storage.addProductToStorage(username, i, quantity);
        Account.player[Account.searchIndexAccountStr(username)].setBalance(balance - price);
        System.out.println("Your purchase : " + quantity + " Books titled '" + Product.products[i].getNamaBarang() + "' for " + price);
        showMainMenu(username);
    }
    public void productBooksInfo (String username) {
        Main.line();
        String header = "Manufacturer's Books : ";
        System.out.println(header);
        for (int i = 0; i < 51; i++) {
            String price = currencyFormatter.format(Product.products[i].getSellPrice());
            System.out.print((i + 1) + ". " + Product.products[i].getNamaBarang() + ", (" + price + ")\n");
        }
        System.out.println();
        bookInfoMan();
        showMainMenu(username);
    }
    public void timeSkip (String username){
        int index = Account.searchIndexAccountStr(username);
        int bookVariety = Account.player[index].getSlots().length;
        int level = (int) Account.player[index].getLevel();
        int days = (int) Account.player[index].getDays();
        Account.player[Account.searchIndexAccountStr(username)].setDays(Account.player[Account.searchIndexAccountStr(username)].getDays() + 1);
        Customer.randomBuyCustomer(Account.player[index].getSlots(), username, bookVariety, level, days);
        showMainMenu(username);
    }
}