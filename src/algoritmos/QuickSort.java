package algoritmos;

public class QuickSort {

    public static <T extends Comparable<T>> void ordenador(T[] vetor, int esq, int dir){
        if (esq < dir) {
            int p = particao(vetor, esq, dir);
            ordenador(vetor, esq, p);
            ordenador(vetor, p + 1, dir);
        }
    }

    private static <T extends Comparable<T>> int particao(T[] vetor, int esq, int dir){
        int meio = (esq + dir) / 2;
        T pivo = vetor[meio];
        int i = esq - 1;
        int j = dir + 1;

        while (true){
            do{
                i++;
            } while (vetor[i].compareTo(pivo) < 0);

            do{
                j--;
            } while (vetor[j].compareTo(pivo) > 0);

            if (i >= j){
                return j;
            }

            T aux = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = aux;
        }
    }
}
