import java.util.concurrent.Semaphore;

public class Produttore implements Runnable {
    private int quintaliProduzione;
    private int tempoProduzione;
    private Semaphore pieno;
    private Semaphore vuoto;
    private Semaphore denaroM;
    private Semaphore contenutoM;

    public Produttore(int quintaliProduzione, int tempoProduzione, Semaphore pieno, Semaphore vuoto, Semaphore contenutoM, Semaphore denaroM) {
        this.quintaliProduzione = quintaliProduzione;
        this.tempoProduzione = tempoProduzione;
        this.pieno = pieno;
        this.vuoto = vuoto;
        this.contenutoM = contenutoM;
        this.denaroM = denaroM;
    }

    @Override
    public void run() {
        while(true) {
            vuoto.acquireUninterruptibly(quintaliProduzione);
            contenutoM.acquireUninterruptibly();
            Main.contenuto += quintaliProduzione;
            contenutoM.release();
            denaroM.acquireUninterruptibly();
            Main.denaro -= Main.pV;
            denaroM.release();
            System.out.println(Thread.currentThread().getName() + " ha prodotto " + this.quintaliProduzione + " per " + Main.pV);
            try {
                Thread.sleep(tempoProduzione);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pieno.release(quintaliProduzione);
        }
    }
}
