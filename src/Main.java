import java.util.concurrent.Semaphore;

public class Main {

    //dichiarazione mercato
    public static int denaro = 1000;
    public static final int m = 50;
    public static int contenuto = 0;
    public static int pV = 5;
    public static int pA = 2;

    public static void main(String[] args) {
        Semaphore pieno = new Semaphore(0);
        Semaphore vuoto = new Semaphore(m);
        Semaphore contenutoM = new Semaphore(1);
        Semaphore denaroM = new Semaphore(1);

        Produttore p1 = new Produttore(5, 3000, pieno, vuoto, contenutoM, denaroM);
        Produttore p2 = new Produttore(3, 2000, pieno, vuoto, contenutoM, denaroM);
        Produttore p3 = new Produttore(1, 2500, pieno, vuoto, contenutoM, denaroM);

        Grossista g1 = new Grossista(4, 1500, pieno, vuoto, contenutoM, denaroM);
        Grossista g2 = new Grossista(3, 2000, pieno, vuoto, contenutoM, denaroM);
        Grossista g3 = new Grossista(6, 4500, pieno, vuoto, contenutoM, denaroM);

        Thread th1 = new Thread(p1);
        Thread th2 = new Thread(p2);
        Thread th3 = new Thread(p3);
        Thread th4 = new Thread(g1);
        Thread th5 = new Thread(g2);
        Thread th6 = new Thread(g3);

        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
        th6.start();
    }
}