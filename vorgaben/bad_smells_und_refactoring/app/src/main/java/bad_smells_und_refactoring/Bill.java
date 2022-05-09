package bad_smells_und_refactoring;

import java.util.ArrayList;


public class Bill {
    private double totalPrice;
    private double articlePrice;
    private CustomerData customerData;
    private ArrayList<Article> articles;

    public Bill(CustomerData customerData, ArrayList<Article> articles) {
        this.customerData = customerData;
        this.articles = articles;
        this.totalPrice = 0;
        this.articlePrice = 0;
    }

    public boolean addArticle(Article a) {
        return articles.add(a);
    }

    public void printBill() {
        this.customerData.printCustomerData();
        calculateArticlePrice();
        printTotalPrice();
    }

    private void calculateArticlePrice(){
        for (Article article : this.articles) {
            this.articlePrice = 0;
            if(article.getBike().getBikeType().equals("Brompton") && article.getPurchaseAmount() > 1){
                articlePrice += (article.getPurchaseAmount() - 1) * article.getBike().getPrice() / 2;
            }else if(article.getBike().getBikeType().equals("Mountainbike") && article.getPurchaseAmount() > 2){
                articlePrice += article.getPurchaseAmount() * article.getBike().getPrice() * 0.9;
            }else{
                articlePrice += article.getBike().getPrice();
            }
            articlePrice = (articlePrice >= 1000) ? articlePrice *= 0.8 : articlePrice;
            this.totalPrice += articlePrice;
            printArticlePrice(article);
        }
    }

    private void printArticlePrice(Article article){
        System.out.println("\t" + article.getBike().getProductName() + "\tx\t" + article.getPurchaseAmount() + "\t=\t " + this.articlePrice);
    }
    private void printTotalPrice(){
        System.out.println("\ntotal price: \t" + this.totalPrice);
    }

}
