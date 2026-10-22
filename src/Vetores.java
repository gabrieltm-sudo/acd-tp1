import java.util.Random;

public class Vetores {

    public static Integer[] getVetorOrdenado(int n) {
        Integer[] vetor = new Integer[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = i; 
        }
        return vetor;
    }

    public static Integer[] getVetorInverso(int n) {
        Integer[] vetor = new Integer[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = n - i; 
        }
        return vetor;
    }

    public static Integer[] getVetorAleatorio(int n) {
        Random randomize = new Random();
        Integer[] vetor = new Integer[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = randomize.nextInt(n * 10); 
        }
        return vetor;
    }

    public static Integer[] getVetorRepetido(int n) {
        Random randomize = new Random();
        Integer[] vetor = new Integer[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = randomize.nextInt(10); 
        }
        return vetor;
    }
}