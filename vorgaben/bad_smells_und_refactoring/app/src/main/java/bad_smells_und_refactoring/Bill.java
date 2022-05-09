package bad_smells_und_refactoring;

import java.util.ArrayList;
import java.util.Date;

public class Bill {

    private String customerName;
    private String nickname;
    private Date birthday;
    private String email;
    private String street;
    private String streetNumber;
    private int postalCode;
    private String city;
    private ArrayList<Article> articles;

    public Bill(String cn, String n, String s, String sn, int pc, Date b, String e, String c) {
        customerName = cn;
        nickname = n;
        street = s;
        streetNumber = sn;
        postalCode = pc;
        birthday = b;
        email = e;
        city = c;
        articles = new ArrayList<Article>();
    }


    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Article> getArticles() {
        return this.articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }


    public boolean addArticle(Article a) {
        return articles.add(a);
    }

    /*public String getDetails() {
        double total = 0;

        String result = "Details for \"" + customerName + "\"\n";
        result += street + " " + streetNumber + "\n";
        result += postalCode + " " + city + "\n";
        result += "Geburtstag: " + birthday + "\n";
        result += "Email: " + email + "\n\n";
        result += "Article: \n";
        for (Article article : articles) {
            double price = 0;
            if (article.bike instanceof Brompton) {
                if (article.purchaseAmount > 1) {
                    price += (article.purchaseAmount - 1) * article.bike.price / 2;
                }
                price += article.bike.price * article.purchaseAmount;
            } else if (article.bike instanceof EBike) {
                price += article.bike.price * article.purchaseAmount;
            } else if (article.bike instanceof Mountainbike) {
                if (article.purchaseAmount > 2) {
                    price += article.purchaseAmount * article.bike.price * 9 / 10;
                } else {
                    price += article.bike.price * article.purchaseAmount;
                }
            }
            if (price > 1000f || price == 1000.0) {
                price = price * 0.8;
            }

            result += "\t" + article.bike.productName + "\tx\t" + article.purchaseAmount + "\t=\t" + String.valueOf(price) + "\n";
            total += price;
        }

        result += "\nTotal price:\t" + String.valueOf(total) + "\n";

        return result;
    }*/

}
