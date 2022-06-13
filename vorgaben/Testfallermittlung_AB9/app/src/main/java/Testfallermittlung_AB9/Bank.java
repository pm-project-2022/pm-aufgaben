package Testfallermittlung_AB9;

public class Bank {
    
    public float getResult(Konto userKonto){
        if(checkAccBalance(userKonto) && checkAge(userKonto)){
            return getBonus(userKonto) + getZinsatz(userKonto);
        }else{
            return -1f;
        }
    }

    public boolean checkAge(Konto userKonto){
        if(userKonto.age() < 0 ){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkAccBalance(Konto userKonto){
        if(userKonto.accBalance() < 1){
            return false;
        }else{
            return true;
        }
    }

    public float getZinsatz(Konto userKonto){
        if(userKonto.accBalance() <= 100){
            return 1.0f;
        }else if(userKonto.accBalance() <= 1000){
            return 3.0f;
        }else{
            return 5.0f;
        }
    }

    public float getBonus(Konto userKonto){
        if(userKonto.age() <= 5){
            return 0.5f;
        }else if(userKonto.age() >= 65){
            return 1.0f;
        }else{
            return 0f;
        }
    }
}
