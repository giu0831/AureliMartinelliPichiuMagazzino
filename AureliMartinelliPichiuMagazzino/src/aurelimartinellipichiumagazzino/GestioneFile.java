/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aurelimartinellipichiumagazzino;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Aureli Giulia, Martinelli Alessandra e Pichiu Florin
 */
public class GestioneFile {

    public GestioneFile() {
    }
    
    /**
     * Metodo che salva un prodotto nel file
     * @param p prodotto da salvare nel file
     * @return record del prodotto
     */
    public int scriviProdotto(Prodotto p){
        int recordProdotto = 0;
        try {
            RandomAccessFile file = new RandomAccessFile("elenco_prodotti.dat", "rw");
            file.seek(file.length());
            recordProdotto = (int) file.length();
            file.writeInt(p.getId());
            file.writeChars(aggiustaLunghezzaStringa(p.getNome(), 15));
            file.writeInt(p.getPrezzoAcquisto());
            file.writeInt(p.getPrezzoVendita());
            file.writeInt(p.getScorta());
            file.writeInt(p.getScortaMin());
            file.writeInt(p.getProdottiVenduti());
            file.close();

            System.out.println("prodotto aggiunto al file");

        } catch (FileNotFoundException ex) {
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("Problema in lettura-scrittura file");
        }
        return recordProdotto;
    }
    
    /**
     * Metodo che carica i dati dei prodotti dal file ad accesso diretto
     * @param recordProdotto byte inizio record prodotto
     */
    private void leggiProdotto(int recordProdotto) {
        try {
            RandomAccessFile file = new RandomAccessFile("elenco_prodotti.dat", "r");
            
                    // Mi posiziono all'inizio del record corretto
                    file.seek(recordProdotto);
                    
                    //Leggo l'ID (4 byte)
                    int idLetto = file.readInt();
                    
                    //Leggo il nome ricordando che è di 15 caratteri (30 byte)
                    String nomeLetto = "";
                    for (int i = 0; i < 15; i++) {
                        nomeLetto += file.readChar();
                    }
                    
                    //Leggo il prezzo di acquisto (4 byte)
                    int prezzoAcqLetto = file.readInt();
                    
                    //Leggo il prezzo di vendita (4 byte)
                    int prezzoVenLetto = file.readInt();
                    
                    //Leggo la scorta (4 byte)
                    int scortaLetta = file.readInt();
                    
                    //Leggo la scorta minima (4 byte)
                    int scortaMinLetta = file.readInt();
                    
                    //Leggo i prodotti venduti (4 byte)
                    int vendutiLetti = file.readInt();
                    
                    GestioneMagazzino.getMagazzino().registraProdotto(new Prodotto(idLetto, nomeLetto.replace("*", ""), prezzoAcqLetto, prezzoVenLetto, scortaLetta, scortaMinLetta, vendutiLetti));
                file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("Problema in lettura-scrittura file");
        }
    }
    
    /**
     * Metodo che aggiusta la lunghezza di una stringa in modo da farla rientrare nella dimensione corretta
     * @param s stringa
     * @param dimensione dimensione da rispettare
     * @return stringa modificata con la lunghezza corretta
     */
    private String aggiustaLunghezzaStringa(String s, int dimensione) {
        String aggiustata=s;
        //se la stringa e' piu' piccola si aggiunge *
        if (s.length() < dimensione) {
            for (int i = 0; i < (dimensione - s.length()); i++) {
                aggiustata += "*";
            }
            return aggiustata;
         //se la stringa e' piu' grande si taglia
        } else if (s.length() > dimensione) { 
            aggiustata = s.substring(0, dimensione);
            return aggiustata;
        }
        return s;
    }
    
    /**
     * Metodo che salva  l'id, il record e lo stato di un prodotto
     * @param p prodotto da aggiungere
     */
    public void salvaKeyFile(Prodotto p){
        FileWriter fw = null;
        PrintWriter pw = null;
        try{
        fw = new FileWriter("key_file.txt", true);
        pw = new PrintWriter(fw);
        pw.println(p.getId() + ";" + scriviProdotto(p) + ";1");
        //si chiude il printWriter
        pw.flush();
        }catch(IOException ioe){
            //messaggio di errore
            System.out.println("Problemi nella lettura del file di testo");
        }finally{
            try {
                pw.close();
                fw.close();
            } catch (IOException ioe) {
                //messaggio di errore
                System.out.println("Problemi nella chiusura del file di testo");
            }
        }
    }
    
    /**
     * Metodo che carica i prodotti dal file
     */
    public void caricaProdottiDaFile(){
        //stringa dove verrano salvati i campi di una riga
        String[] stringaLetta = new String[3];
        try{
            FileReader fr = new FileReader("key_file.txt");
            BufferedReader br = new BufferedReader(fr);
            //si legge una riga
            String riga = br.readLine();
            //finche' si riesce a leggere una riga si continua a caricare gli studenti
            while(riga != null){
                //si inserisce la riga letta divisa per campi nell'array stringa letta
                stringaLetta = riga.split(";");
                if(stringaLetta[2].equals("1")) leggiProdotto(Integer.parseInt(stringaLetta[1]));
                //si legge la prossima riga
                riga = br.readLine();
                //si aumenta l'id dei prodotti
                GestioneMagazzino.incrementaId();
            }
        }catch (IOException ioe) {
            //messaggio di errore
            System.out.println("Problemi nella lettura del file di testo");
        }
    }
    
    /**
     * Metodo per effettuare l'eliminazione logica (cambia lo stato da 1 a 0 nel key_file)
     * @param idDaEliminare l'ID del prodotto da rimuovere
     */
    public void eliminaProdotto(int idDaEliminare) {
        ArrayList<String> righeAggiornate = new ArrayList<>();
        boolean trovato = false;

        // si legge tutto il file
        try {
            FileReader fr = new FileReader("key_file.txt");
            BufferedReader br = new BufferedReader(fr);
            String riga = br.readLine();

            while (riga != null) {
                String[] stringaLetta = riga.split(";");

                if (Integer.parseInt(stringaLetta[0]) == idDaEliminare) {
                    // Se la riga corrisponde all'ID da eliminare si ricostruisce la riga mettendo lo 0 finale
                    righeAggiornate.add(stringaLetta[0] + ";" + stringaLetta[1] + ";0");
                    trovato = true;
                } else {
                    // Altrimenti tengo la riga così com'è
                    righeAggiornate.add(riga);
                }
                riga = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException ioe) {
            System.out.println("Problemi nella lettura del file indice durante l'eliminazione");
        }

        //Se si e' trovato il prodotto da eliminare si riscrive il file
        if (trovato) {
            riscriviFile(righeAggiornate);
        }
    }
    
    private void riscriviFile(ArrayList<String> righe){
        try {
                FileWriter fw = new FileWriter("key_file.txt", false); 
                PrintWriter pw = new PrintWriter(fw);

                // Scrivo tutte le righe dell'ArrayList nel file
                for (String r : righe) {
                    pw.println(r);
                }
                
                pw.flush();
                pw.close();
                fw.close();
                System.out.println("Prodotto eliminato");
                
            } catch (IOException ioe) {
                System.out.println("Problemi nella scrittura del file indice durante l'eliminazione");
            }
}
}
