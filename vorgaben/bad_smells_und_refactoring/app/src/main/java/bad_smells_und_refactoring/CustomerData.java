package bad_smells_und_refactoring;

import java.util.Date;

public class CustomerData {
    public String customerName;
    public String nickname;
    public Date birthday;
    public String email;
    public String street;
    public String streetNumber;
    public int postalCode;
    public String city;

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
}
