package ProjekPemdas;

class Account {
    private static String username;
    private static String Password;
    private static long days;
    private static long level;
    private static long balance;
    private static int rating;
    private static Product[] storage;
    private static Product[] slots;
    static Account[] player = new Account[1000000001];

    public void setUsername(String username) { this.username = username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public void setBalance(long balance) {
        Account.balance = balance;
    }

    public void setRating(int rating) {
        if (rating > 5) {
            this.rating = 5;
        } else {
            this.rating = Math.max(rating, 0);
        }
    }

    public void setStorage(Product[] storage) {
        this.storage = storage;
    }

    public void setSlots(Product[] slots) {
        this.slots = slots;
    }

    public void register(String username, String Password, long days, long level, long balance, int rating, Product[] storage, Product[] slots) {
        setUsername(username);
        setPassword(Password);
        setDays(days);
        setLevel(level);
        setBalance(balance);
        setRating(rating);
        setStorage(storage);
        setSlots(slots);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return Password;
    }

    public long getDays() {
        return days;
    }

    public long getLevel() {
        return level;
    }

    public long getBalance() {
        return balance;
    }

    public int getRating() {
        return rating;
    }

    public static Product[] getStorage() {
        return storage;
    }

    public Product[] getSlots() {
        return slots;
    }

    public void addAccount(String username, String Password) {
        for (int i = 0; i < this.player.length; i++) {
            if (this.player[i] == null) {
                this.player[i] = new Account();
                this.player[i].register(username, Password, 0, 1, 500000, 5, (new Storage(10)).getStorage(), (new Slot(5)).getSlots());
                break;
            }
        }
        System.out.println("Register success!");
    }

    public static int latestIndexPlayer() {
        for (int i = 0; i < player.length - 1; i++) {
            if (player[i + 1] == null) {
                return i;
            }
        }
        return -1;
    }

    public String searchUsernameAccount(String username) {
        for (int i = 0; i < player.length; i++) {
            if (player[i] != null) {
                if (player[i].getUsername().equals(username)) {
                    return player[i].getUsername();
                }
            }
        }
        return null;
    }
    public static Product[] searchStorageAccount(String username) {
        for (int i = 0; i < player.length; i++) {
            if (player[i] != null) {
                if (player[i].getUsername().equals(username)) {
                    return player[i].getStorage();
                }
            }
        }
        return null;
    }
    public static int searchIndexAccountStr(String username) {
        for (int i = 0; i < player.length; i++) {
            if (player[i] != null) {
                if (player[i].getUsername().equals(username)) {
                    return i;
                }
            }
        }
        return -1;
    }
    public String searchPasswordAccount(String Password) {
        for (int i = 0; i < player.length; i++) {
            if (player[i] != null) {
                if (player[i].getPassword().equals(Password)) {
                    return player[i].getPassword();
                }
            }
        }
        return null;
    }
    public static long getBalanceByUsername(String username) {
        for (int i = 0; i < player.length; i++) {
            if (player[i] != null) {
                if (player[i].getUsername().equals(username)) {
                    return player[i].getBalance();
                }
            }
        }
        return -1;
    }
    public static Product getStorageBooksByTitle(String title) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                continue;
            }
            if (storage[i].getNamaBarang().equalsIgnoreCase(title)) {
                return storage[i];
            }
        }
        return null;
    }
    public static Product getSlotBookByTitle(String title) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].getNamaBarang().contains(title)) {
                return slots[i];
            }
        }
        return null;
    }
    public void showAccount(String username, long days, long level, long balance, long rating){
        getUsername();
        getDays();
        getLevel();
        getBalance();
        getRating();
    }
}