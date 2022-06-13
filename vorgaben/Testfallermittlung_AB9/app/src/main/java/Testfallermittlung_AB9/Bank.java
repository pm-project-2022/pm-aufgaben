package Testfallermittlung_AB9;

public class Bank {
    
    public double getResult(Konto userKonto){
        if(checkAge(userKonto) && checkKontostand(userKonto)){
            return getZinsatz(userKonto) + getBonus(userKonto);
        }else{
            return -1;
        }
    }

    public boolean checkAge(Konto userKonto){
        if(userKonto.age() < 0){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkKontostand(Konto userKonto){
        if(userKonto.accBalance() <= 0){
            return false;
        }else{
            return true;
        }
    }

    public double getZinsatz(Konto userKonto){
        if(userKonto.accBalance() <= 100){
            return 1.0;
        }else if(userKonto.accBalance() <= 1000){
            return 3.0;
        }else{
            return 5.0;
        }
    }

    public double getBonus(Konto userKonto){
        if(userKonto.age() <= 5){
            return 0.5;
        }else if(userKonto.age() >= 65){
            return 1.0;
        }else{
            return 0;
        }
    }
}
