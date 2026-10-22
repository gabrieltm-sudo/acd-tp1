package algoritmos;

public class MergeSort {
    
    public static <T extends Comparable<T>> void ordenador(T[] vetor){
        // Cria um array auxiliar seguro usando o tipo exato do vetor recebido, sem necessidade de cast
        T[] vetorAuxiliar = vetor.clone();

        recursivo(vetor, vetorAuxiliar, 0, vetor.length-1);
    }

    public static <T extends Comparable<T>> void recursivo(T[] vetor, T[] vetorAuxiliar, int inicio, int fim){
        if(inicio<fim){
            int meio = (inicio+fim)/2;
            
            recursivo(vetor, vetorAuxiliar, inicio, meio);
            recursivo(vetor, vetorAuxiliar, (meio+1), fim);

            merge(vetor, vetorAuxiliar, inicio, meio, fim);
        }
    }

    public static <T extends Comparable<T>> void merge(T[] vetor, T[] vetorAuxiliar, int inicio, int meio, int fim){
        for(int i=inicio; i<=fim; i++){
            vetorAuxiliar[i] = vetor[i];
        }

        int k = inicio;
        int j = meio+1;

        for(int i = inicio; i<=fim; i++){
            if(k>meio){
                vetor[i] = vetorAuxiliar[j++];
            }else if(j>fim){
                vetor[i] = vetorAuxiliar[k++];
            }else if(vetorAuxiliar[j].compareTo(vetorAuxiliar[k])<0){
                vetor[i] = vetorAuxiliar[j++];
            }else{
                vetor[i] = vetorAuxiliar[k++];
            }
        }
    }
}