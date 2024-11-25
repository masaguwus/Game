package ProjekPemdas;

class Restock {
    public void restock(int index) {
        Storage storage = new Storage();
        Product[] storageTemp = Storage.getStorage().clone();
        for (int i = 0; i < storageTemp.length - 1; i++) {
            if (storageTemp[i] == null) {
                storageTemp[i] = Product.products[index];
                storage.upgradeStorage(storageTemp, index);
            }
        }
    }
}
public class Product {
    private String namaBarang;
    private long purchase;
    private long sellPrice;
    private long quantity;
    private int id;
    private String rarity;
    private int popularity;
    static Product[] products = new Product[51];
    public void setQuantity(long quantity)  {
        this.quantity = Math.max(quantity, 0);
    }
    public void setNamaBarang(String namaBarang) {
        if (namaBarang.isEmpty()) {
            this.namaBarang = "none";
        } else {
            this.namaBarang = namaBarang;
        }
    }
    public void setPurchase(long purchase) {
        this.purchase = Math.max(purchase, 0);
    }
    public void setId(int id) {
        this.id = Math.max(id, 0);
    }
    public void setRarity(String rarity) {
        rarity = rarity.toLowerCase();
        switch (rarity) {
            case "common":
                this.rarity = "Common";
                break;
            case "uncommon":
                this.rarity = "Uncommon";
                break;
            case "rare":
                this.rarity = "Rare";
                break;
            case "epic":
                this.rarity = "Epic";
                break;
            case "legendary":
                this.rarity = "Legendary";
                break;
            default:
                this.rarity = "none";
        }
    }
    public void setPopularity(int popularity) {
        this.popularity = Math.max(popularity, 0);
    }
    public void setSellPrice(long sellPrice) {
        this.sellPrice = Math.max(sellPrice, 0);
    }
    public Product() {

    }
    public Product(String namaBarang, long quantity, long purchase, long sellPrice, int id, String rarity, int popularity) {
        setNamaBarang(namaBarang);
        setQuantity(quantity);
        setPurchase(purchase);
        setSellPrice(sellPrice);
        setId(id);
        setRarity(rarity);
        setPopularity(popularity);
    }
    public Product(String namaBarang, long purchase, long sellPrice, int id, String rarity, int popularity) {
        setNamaBarang(namaBarang);
        setPurchase(purchase);
        setSellPrice(sellPrice);
        setId(id);
        setRarity(rarity);
        setPopularity(popularity);
    }
    public static void product(int days) {
        if (days % 10 == 0) {
            // Profit for common = 10% - 12.5% , popularity  = 1 - 10
            // Profit for uncommon = 12.5% - 15% , popularity = 10 - 20
            // Profit for rare = 15% - 17.5% , popularity = 20 - 30
            // Profit for epic = 17.5% - 20% , popularity = 30 - 40
            // Profit for legendary = 20% - 22.5% , popularity = 40 - 50
            products[0] = new Product("Solo Leveling",10000 , 17000, 20000, 1, "Common", 10);
            products[1] = new Product("Berserk",10000, 35000 , 40000, 1, "Rare", 25);
            products[2] = new Product("One Piece",10000 ,13000, 15000, 1, "Common", 15);
            products[3] = new Product("The Beginning After The End",1000 , 52000, 65000, 1, "Legendary", 50);
            products[4] = new Product("Blue Lock",10000 , 28500, 35000, 2, "Epic", 35);
            products[5] = new Product("Vagabond",10000 , 49500, 60000, 2, "Epic", 40);
            products[6] = new Product("Haikyuu!!",10000 , 26000,30000, 1, "Uncommon", 20);
            products[7] = new Product("The Apothecarry Diaries", 10000, 10000, 1, "Rare", 25);
            products[8] = new Product("My Hero Academia", 10000, 22000, 25000, 1, "Common", 5);
            products[9] = new Product("Naruto", 10000, 38000, 45000, 1, "Rare", 30);
            products[10] = new Product("Daytime Star", 10000, 34000, 40000, 3, "Uncommon", 15);
            products[11] = new Product("One Punch Man", 10000, 26500, 30000, 1, "Common", 5);
            products[12] = new Product("Frieren: Beyond Journey's End", 10000, 54000, 60000, 1, "Epic", 40);
            products[13] = new Product("Chainsaw Man", 10000, 54000, 60000, 3, "Common",17);
            products[14] = new Product("Tokyo Ghoul", 1000, 35000, 40000, 4, "Rare", 18);
            products[15] = new Product("Tokyo Revengers", 10000, 38000, 43500, 1, "Uncommon", 17);
            products[16] = new Product("Seven Deadly Sins", 10000, 35000, 39000, 1, "Common", 10);
            products[17] = new Product("Sailor Moon", 10000, 54000, 63000, 1, "Epic", 35);
            products[18] = new Product("Neon Genesis Evangelion", 10000, 60000, 72000, 1, "Legendary", 40);
            products[19] = new Product("Promise Neverland", 10000, 45000, 72000, 1, "Uncommon", 40);
            products[20] = new Product("Toilet-bound Hanako-kun", 10000, 27500, 30500, 1, "Uncommon", 14);
            products[21] = new Product("Fruits Basket", 10000, 24000, 27000, 1, "Common", 11);
            products[22] = new Product("Juvenile Offender", 10000, 60000, 72000, 1, "Epic", 40);
            products[23] = new Product("The Real Lesson", 10000, 60000, 72000, 1, "Uncommon", 40);
            products[24] = new Product("Neon Revenge", 10000, 60000, 72000, 1, "Common", 40);
            products[25] = new Product("Another", 10000, 20000, 22500, 5, "Legendary", 45);
            products[26] = new Product("Dragon Ball", 10000, 60000, 72000, 1, "Legendary", 40);
            products[27] = new Product("Bleach", 10000, 60000, 72000, 1, "Legendary", 40);
            products[28] = new Product("Kaguya-sama: Love is War", 10000, 60000, 72000, 1, "Epic", 40);
            products[29] = new Product("Fairy Tale", 10000, 60000, 72000, 1, "Rare", 40);
            products[30] = new Product("Your Lie in April", 10000, 60000, 72000, 1, "Legendary", 40);
            products[31] = new Product("The Toilet Bound Hanako-san", 10000, 60000, 72000, 1, "Uncommon", 40);
            products[32] = new Product("Spy x Family", 10000, 60000, 72000, 1, "Epic", 40);
            products[33] = new Product("Junji Ito: Uzumaki", 10000, 35000, 40500, 3, "Rare", 25);
            products[34] = new Product("Bungo Stray Dogs", 10000, 60000, 72000, 1, "Epic", 40);
            products[35] = new Product("Moriarty The Patriot", 10000, 28000, 31500, 4, "Rare", 20);
            products[36] = new Product("Black Butler", 10000, 27500, 31000, 4, "Uncommon", 14);
            products[37] = new Product("Seraph of The End", 10000, 30000, 34500, 4, "Rare", 23);
            products[38] = new Product("Black Clover", 10000, 35000, 31000, 4, "Rare", 30);
            products[39] = new Product("Mob Psycho 100", 10000, 28000, 31500, 4, "Uncommon", 15);
            products[40] = new Product("Mashle: Magic & Muscle", 10000, 28000, 31500, 4, "Uncommon", 15);
            products[41] = new Product("The Disastrous Life of Saiki K.", 10000, 25000, 27500, 4, "Common", 13);
            products[42] = new Product("Wotakoi: Love is Hard for Otaku", 10000, 45000, 53500, 3, "Epic", 36);
            products[43] = new Product("The Secret of Angel", 10000, 28000, 31500, 4, "Uncommon", 15);
            products[44] = new Product("Omniscient Reader", 10000, 28000, 31500, 4, "Uncommon", 15);
            products[45] = new Product("Trickster", 10000, 28000, 31500, 4, "Common", 15);
            products[46] = new Product("Death Note", 10000, 28000, 31500, 4, "Epic", 15);
            products[47] = new Product("Anohana: The Flower we Saw That Day", 10000, 28000, 31500, 4, "Common", 15);
            products[48] = new Product("Maquia: When the Promised Flower Blooms", 10000, 28000, 31500, 4, "Common", 15);
            products[49] = new Product("Gangsta", 10000, 60000, 72000, 4, "Epic", 20);
            products[50] = new Product("91 Days", 10000, 28000, 31500, 4, "Legendary", 45);
        }
    }
    public long getPurchase() {
        return purchase;
    }
    public long getQuantity() {
        return quantity;
    }
    public long getSellPrice() { return sellPrice; }
    public String getRarity() {
        return rarity;
    }
    public int getPopularity() {
        return popularity;
    }
    public String getNamaBarang() {
        return namaBarang;
    }
    public int getId() {
        return id;
    }
    public static Product[] getProduct() {
        return products;
    }
    public static Product getBooksByTitle(String title) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getNamaBarang().equalsIgnoreCase(title)) {
                return products[i];
            }
        }
        return null;
    }

}