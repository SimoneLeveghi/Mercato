public class Produttore implements Runnable {
    private int quintaliProduzione;
    private int tempoProduzione;
    private Semaforo pieno;
    private Semaforo vuoto;
    private Semaforo denaroM;
    private Semaforo contenutoM;

    public Produttore(int quintaliProduzione, int tempoProduzione, Semaforo pieno, Semaforo vuoto, Semaforo contenutoM, Semaforo denaroM) {
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
            vuoto.P(quintaliProduzione);
            contenutoM.P();
            Main.contenuto += quintaliProduzione;
            System.out.println("Contenuto: " + Main.contenuto);
            contenutoM.V();
            denaroM.P();
            Main.denaro -= Main.pV*quintaliProduzione;
            System.out.println("Denaro: " + Main.denaro);
            denaroM.V();
            pieno.V(quintaliProduzione);
            System.out.println(Thread.currentThread().getName() + " ha prodotto " + this.quintaliProduzione + " per " + (Main.pV*this.quintaliProduzione));
            try {
                Thread.sleep(tempoProduzione);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
