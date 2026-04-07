/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiumagazzino;

/**
 *
 * @author Aureli Giulia, Martinelli Alessandra e Pichiu Florin
 */
public class GestioneMagazzino {
    private static Magazzino magazzino = new Magazzino();
    private static int id;

    public static Magazzino getMagazzino() {
        return magazzino;
    }
    
    public static int getNuovoId(){
        return id;
    }
    
    public static void incrementaId(){
        id++;
    }

    public static void setId(int id) {
        GestioneMagazzino.id = id;
    }
}
