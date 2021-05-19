package tpsit.mensaaziendale;
import java.util.ArrayList;

/**
 *
 * @author Dardan Matias Berisha
 */
public class Main {
    public static void main(String[] args) {
        
        Bancone b = new Bancone(2);
        
        ArrayList<Dipendente> listaDipendenti = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            listaDipendenti.add(new Dipendente("dip" + (i+1), b));
        }
        
        Cuoco cuoco = new Cuoco("cuoco", b, listaDipendenti.size());
        
        cuoco.start();
        
        for (Dipendente d : listaDipendenti) {
            d.start();
        }
        
        for (Dipendente d : listaDipendenti) {
            try {
                d.join();
            } catch (InterruptedException ex) {}
        }
        
        System.out.println("\nFine pausa pranzo");
        
    }
}
