/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiumagazzino;

/**
 *
 * @author Aureli Giulia, Martinelli Alessandra e Pichiu Florin
 */
public class Prodotto {
    private int id;
    private String nome;
    private int prezzoAcquisto;
    private int prezzoVendita;
    private int scorta;
    private int scortaMin;
    private int prodottiVenduti;

    public Prodotto(int id, String nome, int prezzoAcquisto, int prezzoVendita, int scorta, int scorteMin) {
        this.id = id;
        this.nome = nome;
        this.prezzoAcquisto = prezzoAcquisto;
        this.prezzoVendita = prezzoVendita;
        this.scorta = scorta;
        this.scortaMin = scorteMin;
        prodottiVenduti = 0;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getPrezzoAcquisto() {
        return prezzoAcquisto;
    }

    public int getPrezzoVendita() {
        return prezzoVendita;
    }

    public int getScorta() {
        return scorta;
    }

    public int getScortaMin() {
        return scortaMin;
    }

    public int getProdottiVenduti() {
        return prodottiVenduti;
    }

    public void setPrezzoVendita(int prezzoVendita) {
        this.prezzoVendita = prezzoVendita;
    }
    
    /**
     * Metodo per comprare il prodotto 
     * @param quantita quantita che si vuole comprare
     * @return 0 se non c'erano scorte sufficenti, 1 se si e' comprato e 2 se si e' comprato ma la scorta e' scesa sotto la soglia minima
     */
    public int compraProdotto(int quantita){
        //controllo se ci sono scorte sufficenti
        if(!controlloScorta(quantita))return 0;
        scorta =-quantita;
        prodottiVenduti =+ quantita;
        //controllo se i prodotti sono scesi sotto la soglia minima
        if(controlloScortaMin())return 1;
        return 2;
        
    }
    
    /**
     * Metodo che controlla se ci sono scorte sufficenti per l'acquisto
     * @param quantita quantita di prodotti da comprare
     * @return true se la scorta e' sufficente per l'acquisto, false se non e' sufficente
     */
    public boolean controlloScorta(int quantita){
        return scorta >= quantita;
    }
    
    /**
     * Metodo che controlla se le scorte sono oltre la soglia minima
     * @return true se le scorte sono oltre la soglia minima, false se sono meno
     */
    public boolean controlloScortaMin(){
        return scorta >= scortaMin;
    }
    
    /**
     * Metodo per rifornire il prodotto
     * @param quantita quantita da rifornire
     */
    public void rifornisci(int quantita){
        scorta += quantita;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Equals che controlla guardando l'id
     * @param obj ogetto da comparare
     * @return true se i due oggetti sono uguali, false se sono diversi
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prodotto other = (Prodotto) obj;
        return this.id == other.id;
    }
}