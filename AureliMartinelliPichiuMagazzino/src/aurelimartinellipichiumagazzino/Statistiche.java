/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiumagazzino;

import java.util.ArrayList;

/**
 * Classe che contiene tutte le statistiche
 * @author aureli.giulia
 */
public class Statistiche {
    private Magazzino magazzino;
    private Prodotto prodottoCostoso;
    private Prodotto prodottoEconomico;
    private ArrayList<Prodotto> prodottiSottoScorta; // salvatagio di tutti prodotti sotto scorta min, per vedere quanti sono si usa size()
    private Prodotto prodottoMaxVenduto;
    private Prodotto prodottoMinVenduto;

    public Statistiche(Magazzino magazzino) {
        this.magazzino = magazzino;
    }
    
    private void trovaProdottoCostoso(){
        prodottoCostoso = null;
        for(Prodotto p : magazzino.getListaProdotti()){
            if(prodottoCostoso == null || prodottoCostoso.getPrezzoVendita() < p.getPrezzoVendita())prodottoCostoso = p;
        }
    }
    
    private void trovaProdottoEconomico(){
        prodottoEconomico = null;
        for(Prodotto p : magazzino.getListaProdotti()){
            if(prodottoEconomico == null || prodottoEconomico.getPrezzoVendita() > p.getPrezzoVendita())prodottoEconomico = p;
        }
    }
    
    public void trovaProdottiSottoScorta(){
        prodottiSottoScorta.removeAll(prodottiSottoScorta);
        for(Prodotto p : magazzino.getListaProdotti()){
            if(!p.controlloScortaMin()) prodottiSottoScorta.add(p);
        }
    }
    
    private void trovaProdottoMaxVenduto(){
        prodottoMaxVenduto = null;
        for(Prodotto p : magazzino.getListaProdotti()){
            if(prodottoMaxVenduto == null || prodottoMaxVenduto.getProdottiVenduti() < p.getProdottiVenduti())prodottoMaxVenduto = p;
        }
    }
    
    private void trovaProdottoMinVenduto(){
        prodottoMinVenduto = null;
        for(Prodotto p : magazzino.getListaProdotti()){
            if(prodottoMinVenduto == null || prodottoMinVenduto.getProdottiVenduti() > p.getProdottiVenduti())prodottoMinVenduto = p;
        }
    }
    
    public void aggiorna(){
        trovaProdottoCostoso();
        trovaProdottoEconomico();
        trovaProdottoMaxVenduto();
        trovaProdottoMinVenduto();
    }
    
    public ArrayList<Prodotto>vediStatistiche(){
        ArrayList<Prodotto> statistiche = new ArrayList<>();
        aggiorna();
        statistiche.add(prodottoCostoso);
        statistiche.add(prodottoEconomico);
        statistiche.add(prodottoMaxVenduto);
        statistiche.add(prodottoMinVenduto);
        return statistiche;
    }
}
