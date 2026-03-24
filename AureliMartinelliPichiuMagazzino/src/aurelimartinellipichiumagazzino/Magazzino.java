/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiumagazzino;
import java.util.*;
/**
 *
 * @author Aureli Giulia, Martinelli Alessandra e Pichiu Florin
 */
public class Magazzino {
    HashSet<Prodotto> listaProdotti;

    public Magazzino() {
        listaProdotti = new HashSet<>();
    }

    public HashSet<Prodotto> getListaProdotti() {
        return listaProdotti;
    }
    
    /**
     * Metodo per registrare ujn nuovo prodotto
     * @param p prodotto
     * @return true se e' stato registrato il prodotto, false se non e' stato registrato
     */
    public boolean registraProdotto(Prodotto p){
        if(controlloPresenzaProdotto(p)) return false;
        listaProdotti.add(p);
        return true;
    }
    
    /**
     * Metodo per controllare se il prodotto e' presente nella lista di prodotti
     * @param p prodotto
     * @return true se e' presente, false se non e' presente
     */
    private boolean controlloPresenzaProdotto(Prodotto p){
        return listaProdotti.contains(p);
    }
    
    /**
     * Metodo per rimuovere un prodotto
     * @param id id del prodotto
     * @return true se e' stato rimosso, false se non e' stato rimosso
     */
    public boolean rimuoviProdotto(int id){
        Prodotto p = trovaProdottoPerId(id);
        if(p == null) return false;
        listaProdotti.remove(p);
        return true;
    }
    
    /**
     * Metodo per trovare un prodotto attraverso il suo id
     * @param id id del prodotto
     * @return prodotto corrispondente all'id
     */
    public Prodotto trovaProdottoPerId(int id){
        for(Prodotto p : listaProdotti){
            if(p.getId() == id) return p;
        }
        return null;
    }
}
