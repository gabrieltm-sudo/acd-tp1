import java.util.Random;

public class Vetores {

    public static int[] getVetorOrdenado(){
        return new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};
    }

    public static int[] getVetorMedio(){
        return new int[]{120, 10, 50, 60, 110, 80, 30, 20, 150, 100, 140, 70, 130, 90, 40};
    }

    public static int[] getVetorInverso(){
        return new int[]{150, 140, 130, 120, 110, 100, 90, 80, 70, 60, 50, 40, 30, 20, 10};
    }

    public static int[] getVetorRandomico(){
        int tamanho = 50;
        int[] vetor = new int[tamanho];
        Random random = new Random();

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(200);
        }

        return vetor;
    }
}
