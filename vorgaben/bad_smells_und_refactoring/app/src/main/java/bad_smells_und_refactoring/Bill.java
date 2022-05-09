package bad_smells_und_refactoring;

import java.util.ArrayList;

/**
 * Erzeugt aus einer Arrayliste an Artikeln und den Kundendaten eine Rechnung
 * für den Kunden
 */

public class Bill {
    private double totalPrice;
    private double articlePrice;
    private CustomerData customerData;
    private ArrayList<Article> articles;

    /**
     * Konstruktor 
     * @param customerData Kundendaten des Käufers
     * @param articles Warenkorb des Käufers
     */

    public Bill(CustomerData customerData, ArrayList<Article> articles) {
        this.customerData = customerData;
        this.articles = articles;
        this.totalPrice = 0;
        this.articlePrice = 0;
    }

    /**
     * Hängt ein weiteres Element an die Arraylist
     * @param article Neuer Artikel den der Kunde hinzufügen will
     * @return true
     */

    public boolean addArticle(Article article) {
        return articles.add(article);
    }

    /**
     * Gibt die Rechnung auf der Konsole aus
     */
    public void printBill() {
        this.customerData.printCustomerData();
        calculateTotalPrice();
        printTotalPrice();
    }

    //berechnet den Gesamt der Artikel
    private void calculateTotalPrice() {
        for (Article article : this.articles) {
            this.articlePrice = 0;
            if (article.getBike().getBikeType().equals("Brompton") && article.getPurchaseAmount() > 1) {
                articlePrice += (article.getPurchaseAmount() - 1) * article.getBike().getPrice() / 2;
            } else if (article.getBike().getBikeType().equals("Mountainbike") && article.getPurchaseAmount() > 2) {
                articlePrice += article.getPurchaseAmount() * article.getBike().getPrice() * 0.9;
            } else {
                articlePrice += article.getBike().getPrice();
            }
            articlePrice = (articlePrice >= 1000) ? articlePrice *= 0.8 : articlePrice;
            this.totalPrice += articlePrice;
            printArticlePrice(article);
        }
    }

    //gibt den artikel, die anzahl des gekauften artikel und den preis des artikels auf der konsole aus
    private void printArticlePrice(Article article) {
        System.out.println("\t" + article.getBike().getProductName() + "\tx\t" + article.getPurchaseAmount() + "\t=\t "
                + this.articlePrice);
    }

    //gibt den gesamtpreis auf der konsole aus
    private void printTotalPrice() {
        System.out.println("\ntotal price: \t" + this.totalPrice);
    }

}
