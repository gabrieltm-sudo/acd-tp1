package algoritmos;

public class HeapSort {

    public static <T extends Comparable<T>> void ordenador(T[] vetor){

        int n = vetor.length;

        for (int i = n / 2 - 1; i >= 0; i--){
            aplicarHeap(vetor, n, i);
        }

        for (int i = n - 1; i > 0; i--){
            T aux = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = aux;

            aplicarHeap(vetor, i, 0);
        }
    }

    private static <T extends Comparable<T>> void aplicarHeap(T[] vetor, int n, int i){
        int raiz = i;
        int esq, dir;

        while (true){
            raiz = i;
            esq = 2 * i + 1;
            dir = 2 * i + 2;

            if (esq < n && vetor[esq].compareTo(vetor[raiz]) > 0){
                raiz = esq;
            }

            if (dir < n && vetor[dir].compareTo(vetor[raiz]) > 0){
                raiz = dir;
            }

            if (raiz == i){
                break;
            }

            T aux = vetor[i];
            vetor[i] = vetor[raiz];
            vetor[raiz] = aux;
            i = raiz;
        }
    }
}
