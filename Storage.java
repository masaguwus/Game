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

public class Storage {
    private int num;
    static private Product[] storage;
    public void setNum(int num) {
        this.num = num;
    }
    public Storage() {

    }
    public Storage(int num) {
        setNum(num);
        storage = new Product[num];
    }
    // id match as the player level, like for player with level 1 - 10 they only can buy products that has id up to 1.
    public void upgradeStorage(Product[] storage, int num) {
        this.num = num;
        this.storage = new Product[num];
        System.arraycopy(storage, 0, this.storage, 0, storage.length);
    }
    public static void addProductToStorage(String username, String title, long quantity) {
        if (username == null || title == null) {
            throw new NullPointerException("Username and title cannot be null");
        }

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        try {
            Account player = Account.player[Account.searchIndexAccountStr(username)];
            if (player == null) {
                throw new NullPointerException("Player not found");
            }

            Product[] storage = player.getStorage();
            if (storage == null) {
                throw new NullPointerException("Storage is null");
            }

            int index2 = findNextAvailableIndex(storage);
            if (index2 == -1) {
                throw new RuntimeException("Storage is full");
            }

            Product product = Product.getBooksByTitle(title);
            if (product == null) {
                throw new RuntimeException("Product not found");
            }

            storage[index2] = product;
            player.setStorage(storage);
            product.setQuantity(quantity);
        } catch (Exception e) {
            System.err.println("Error adding product to storage: " + e.getMessage());
        }
    }

    private static int findNextAvailableIndex(Product[] storage) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return -1;
    }
    public static int checkStorage(Product[] storage) {
        int ave = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                ave = storage.length - i - 1;
                break;
            }
        }
        return ave;
    }
    public static void showStorage(Product[] storage) {
        int j = 1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                continue;
            }
            System.out.println((j) + ". " + storage[i].getNamaBarang() + ", (Quantity : " + storage[i].getQuantity() + ")");
            j++;
        }
    }
    public static Product[] getStorage() {
        return storage;
    }

    public int getNum() {
        return num;
    }
}

class Slot{
    private static int slot;
    private static Product[] slots;
    public void setSlot(int slot) {
        this.slot = slot;
    }
    public void setSlots(Product[] slots) {
        this.slots = slots;
    }
    public Slot(int slot) {
        setSlot(slot);
        slots = new Product[slot];
    }
    public static void checkSlotAvailability(Product[] slots) {
        System.out.print(getSlot() + " Slot Availability\n");
        for (int i = 0; i < slots.length; i++) {
            String coma = (i == slots.length - 1) ? "" : ", ";
            if (slots[i] == null) {
                System.out.print("Slot " + (i + 1) + " Available");
            }else if (slots[i].getQuantity() <= 0 && slots[i] != null) {
                slots[i] = null;
                System.out.print("Slot " + (i + 1) + " Available");
            } else {
                System.out.print("Slot " + (i + 1) + " : " + slots[i].getNamaBarang() + "( " + slots[i].getQuantity() + " Barang ) harga Rp." + slots[i].getSellPrice() + coma + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void renewSlot(int slot) {
        setSlot(slot);
        Product[] temp = new Product[slot];
        System.arraycopy(slots, 0, temp, 0, slots.length);
        slots = temp;
    }
    public static void addBookToSlot(Product[] storage, String username, String title) {
        int indexPlayer = Account.searchIndexAccountStr(username);
        Product[] slot = Account.player[indexPlayer].getSlots();
        Product temp = new Product();
        if (storage == null) {
            System.out.println("Storage is empty!");
            return;
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                continue;
            }
            if (!storage[i].getNamaBarang().toLowerCase().contains(title.toLowerCase())) {
                continue;
            }
            if (temp != null) {
                temp = storage[i];
                storage[i] = null;
                break;
            }
        }
        for (int i = 0; i < slot.length; i++) {
            if (slot[i] == null) {
                slot[i] = temp;
                break;
            }
        }
        Account.player[indexPlayer].setSlots(slot);
        System.out.println("Book has been succsessfully added to your slot!");
    }
    public static Product getBooksByTitle(String title) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].getNamaBarang().toLowerCase().contains(title.toLowerCase())) {
                return slots[i];
            }
        }
        return null;
    }
    public static int getSlot() {
        return slot;
    }

    public Product[] getSlots() {
        return slots;
    }
}
