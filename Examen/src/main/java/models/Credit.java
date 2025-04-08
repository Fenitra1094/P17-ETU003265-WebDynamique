package models;

public class Credit {
    private int Id;
    private String libelle;
    private double montant;
    private double reste;
    public Credit(String libelle, double montant, double reste) {
        this.libelle = libelle;
        this.montant = montant;
        this.reste = reste;
    }
    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    public double getReste() {
        return reste;
    }
    public void setReste(double reste) {
        this.reste = reste;
    }
}
