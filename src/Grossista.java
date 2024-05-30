import java.util.concurrent.Semaphore;

public class Grossista implements Runnable{

    private int nAcquisto;
    private int tempoEsaurimento;
    private Semaphore pieno;
    private Semaphore vuoto;
    private Semaphore denaroM;
    private Semaphore contenutoM;

    public Grossista(int nAcquisto, int tempoEsaurimento, Semaphore pieno, Semaphore vuoto, Semaphore contenutoM, Semaphore denaroM) {
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
            pieno.acquireUninterruptibly(nAcquisto);

            denaroM.acquireUninterruptibly();
            Main.denaro+=nAcquisto*Main.pV;
            System.out.println("Contenuto: " + Main.denaro);
            denaroM.release();

            contenutoM.acquireUninterruptibly();
            Main.contenuto-=nAcquisto;
            System.out.println("Contenuto: " + Main.contenuto);
            contenutoM.release();

            vuoto.release(nAcquisto);

            System.out.println(s);

            try {
                Thread.currentThread().sleep(this.tempoEsaurimento);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
