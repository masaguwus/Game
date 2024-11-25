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
    public static void addProductToStorage(String username, int index1, long quantity) {
        Product[] storage = Account.searchStorageAccount(username);
        int index2 = checkStorage(storage);
        storage[index2] = Product.products[index1];
        storage[index2].setQuantity(quantity);
        Product.products[index1].setQuantity(Product.products[index1].getQuantity() - quantity);
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
        System.out.print(getSlot() + " Slot Availability : ");
        for (int i = 0; i < slots.length; i++) {
            String coma = (i == slots.length - 1) ? "" : ", ";
            if (slots[i] == null) {
                System.out.print("Slot " + (i + 1) + " " + coma);
            } else {
                System.out.print("Slot " + (i + 1) + " : " + slots[i].getNamaBarang() + "(" + slots[i].getQuantity() + "Barang) harga Rp." + slots[i].getSellPrice() + coma);
            }
        }
        System.out.println();
    }
    public void renewSlot(int slot) {
        setSlot(slot);
        Product[] temp = new Product[slot];
        System.arraycopy(slots, 0, temp, 0, slots.length);
        slots = temp;
    }
    public void addBookToSlot(Product[] storage, String username, String title) {
        int indexPlayer = Account.searchIndexAccountStr(username);
        Product[] slot = Account.player[indexPlayer].getSlots();
        Product temp = new Product();
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].getNamaBarang().contains(title)) {
                temp = storage[i];
                storage[i] = new Product();
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
    }
    public static Product getBookByTitle(String title) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].getNamaBarang().contains(title)) {
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
