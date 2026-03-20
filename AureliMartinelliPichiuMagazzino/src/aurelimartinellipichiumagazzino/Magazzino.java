/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiumagazzino;
import java.util.*;
/**
 *
 * @author aureli.giulia
 */
public class Magazzino {
    HashSet<Prodotto> listaProdotti;

    public Magazzino() {
        listaProdotti = new HashSet<>();
    }

    public HashSet<Prodotto> getListaProdotti() {
        return listaProdotti;
    }
    
    public boolean registraProdotto(Prodotto p){
        if(controlloPresenzaProdotto(p)) return false;
        listaProdotti.add(p);
        return true;
    }
    
    private boolean controlloPresenzaProdotto(Prodotto p){
        return listaProdotti.contains(p);
    }
    
    public boolean rimuoviProdotto(int id){
        Prodotto p = trovaProdottoPerId(id);
        if(p == null) return false;
        listaProdotti.remove(p);
        return true;
    }
    
    private Prodotto trovaProdottoPerId(int id){
        for(Prodotto p : listaProdotti){
            if(p.getId() == id) return p;
        }
        return null;
    }
}
