package bad_smells_und_refactoring;

import java.util.Date;

public class CustomerData {
    private String customerName;
    private String nickname;
    private Date birthday;
    private String email;
    private String street;
    private String streetNumber;
    private int postalCode;
    private String city;

    public CustomerData(String cn, String n, Date b, String e, String s, String sn, int pc,  String c){
        this.customerName = cn;
        this.nickname = n;
        this.birthday = b;
        this.email = e;
        this.street = s;
        this.streetNumber = sn;
        this.postalCode = pc;
        this.city = c;
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
    
    public void printCustomerData(){
        System.out.print("Customername: " + this.customerName + "\nStreet: " + this.street + " " + this.streetNumber + "\nCity: " + this.postalCode + " " + this.city + "\nBirthday: " + this.birthday + "\nEmail: " + this.email + "\n\n");
    }
}
