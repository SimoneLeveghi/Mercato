public class Grossista implements Runnable{

    private int nAcquisto;
    private int tempoEsaurimento;
    private Semaforo pieno;
    private Semaforo vuoto;
    private Semaforo denaroM;
    private Semaforo contenutoM;

    public Grossista(int nAcquisto, int tempoEsaurimento, Semaforo pieno, Semaforo vuoto, Semaforo contenutoM, Semaforo denaroM) {
        this.nAcquisto = nAcquisto;
        this.tempoEsaurimento = tempoEsaurimento;
        this.pieno = pieno;
        this.vuoto = vuoto;
        this.contenutoM = contenutoM;
        this.denaroM = denaroM;
    }

    @Override
    public void run() {
        String s = "";
        while(true){
            s="Il venditore " + Thread.currentThread().getName() + " ha pagato " + nAcquisto*Main.pV + " denari per " + nAcquisto + " quintali di ciliegie";
            pieno.P(nAcquisto);

            denaroM.P();
            Main.denaro+=nAcquisto*Main.pA;
            System.out.println("Denaro: " + Main.denaro);
            denaroM.V();

            contenutoM.P();
            Main.contenuto-=nAcquisto;
            System.out.println("Contenuto: " + Main.contenuto);
            contenutoM.V();

            vuoto.V(nAcquisto);

            System.out.println(s);

            try {
                Thread.currentThread().sleep(this.tempoEsaurimento);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
