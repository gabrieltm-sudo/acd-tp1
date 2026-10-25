package algoritmos;

public class BubbleSort {

    public static <T extends Comparable<T>> void ordenador(T[] vetor){
        int i, j;
        T aux;

        for (i = 0; i < vetor.length - 1; i++){
            for (j = 0; j < vetor.length - 1 - i; j++){
                if (vetor[j].compareTo(vetor[j + 1]) > 0){
                    aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                }
            }
        }
    }
}
