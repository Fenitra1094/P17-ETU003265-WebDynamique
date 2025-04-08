package models;

public class Depense {
    private int Id;
    private int idCredit;
    private double depense;
    public Depense(int idCredit, double depense) {
        this.idCredit = idCredit;
        this.depense = depense;
    }
    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public int getIdCredit() {
        return idCredit;
    }
    public void setiDCredit(int idCredit) {
        this.idCredit = idCredit;
    }
    public double getDepense() {
        return depense;
    }
    public void setDepense(double depense) {
        this.depense = depense;
    }
}
