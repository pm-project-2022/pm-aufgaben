package bad_smells_und_refactoring;

import java.util.Date;

/**
 * Bündelt die Kundendaten
 */

public class CustomerData {
    private String customerName;
    private String nickname;
    private Date birthday;
    private String email;
    private String street;
    private String streetNumber;
    private int postalCode;
    private String city;

    /**
     * Konstruktor
     * @param customerName Name des Kunden
     * @param nickname alias des Kunden
     * @param birthday Geburtsdatum des Kunden
     * @param email Email des Kunden
     * @param street Straße des Wohnorts des Kunden
     * @param streetNumber Hausnummer des Kunden
     * @param postalCode Postleitzahl des Kunden
     * @param city Wohnort des Kunden
     */
    public CustomerData(String customerName, String nickname, Date birthday, String email, String street,
            String streetNumber, int postalCode, String city) {
        this.customerName = customerName;
        this.nickname = nickname;
        this.birthday = birthday;
        this.email = email;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
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

    /**
     * Gibt die Kundendaten auf der Konsole aus
     */
    public void printCustomerData() {
        System.out.print("Customername: " + this.customerName + "\nStreet: " + this.street + " " + this.streetNumber
                + "\nCity: " + this.postalCode + " " + this.city + "\nBirthday: " + this.birthday + "\nEmail: "
                + this.email + "\n\n");
    }
}
