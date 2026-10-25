package algoritmos;

public class SelectionSort{

    public static <T extends Comparable<T>> void ordenador(T[] vetor){

        int tam_vet = vetor.length;

        for (int i = 0; i < tam_vet - 1; i++){
            int indiceMin = i;

            for (int j = i + 1; j < tam_vet; j++){
                if (vetor[j].compareTo(vetor[indiceMin]) < 0){
                    indiceMin = j;
                }
            }

            T aux = vetor[indiceMin];
            vetor[indiceMin] = vetor[i];
            vetor[i] = aux;
        }
    }
}
