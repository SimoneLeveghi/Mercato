public class Semaforo {
    int valore;                         //0 = rosso

    public Semaforo(int valore){
        this.valore = valore;
    }

    synchronized public void P(int x){
        while(valore < x){             // semaforo rosso
            try {
                wait();                 // il thread si sospende
            }catch (Exception e){}
        }
        valore-=x;                       // pone il semaforo a rosso
    }

    synchronized public void V(int x){
        valore+=x;                       // pone il semaforo a verde
        notify();                       // risveglia il thrad in coda
    }

    synchronized public void P(){
        while(valore == 0){             // semaforo rosso
            try {
                wait();                 // il thread si sospende
            }catch (Exception e){}
        }
        valore--;                       // pone il semaforo a rosso
    }

    synchronized public void V(){
        valore++;                       // pone il semaforo a verde
        notify();                       // risveglia il thrad in coda
    }
}
