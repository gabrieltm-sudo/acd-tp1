package algoritmos;

public class MergeSort {
    public static void ordenador(int[] vetor){
        int[] vetorAuxiliar = new int[vetor.length];

        recursivo(vetor, vetorAuxiliar, 0, vetor.length-1);
    }

    public static void recursivo(int[] vetor, int[] vetorAuxiliar, int inicio, int fim){
        if(inicio<fim){
            int meio = (inicio+fim)/2;
            
            recursivo(vetor, vetorAuxiliar, inicio, meio);
            recursivo(vetor, vetorAuxiliar, (meio+1), fim);

            merge(vetor, vetorAuxiliar, inicio, meio, fim);
        }
    }

    public static void merge(int[] vetor, int[] vetorAuxiliar, int inicio, int meio, int fim){
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
            }else if(vetorAuxiliar[j]<vetorAuxiliar[k]){
                vetor[i] = vetorAuxiliar[j++];
            }else{
                vetor[i] = vetorAuxiliar[k++];
            }
        }
    }
}
