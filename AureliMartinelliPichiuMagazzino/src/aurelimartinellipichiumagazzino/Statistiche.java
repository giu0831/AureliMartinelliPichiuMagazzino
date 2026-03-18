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
    private Prodotto prodotoEconomico;
    private ArrayList<Prodotto> prodottoSottoScorta; // salvatagio di tutti prodotti sotto scorta min, per vedere quanti sono si usa size()
    private Prodotto prodottoMaxVenduto;
    private Prodotto prodottoMinVenduto;

    public Statistiche(Magazzino magazzino) {
        this.magazzino = magazzino;
    }
    
    
    
}
