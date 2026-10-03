package algoritmos;

public class BubbleSort{

    public static void ordenador(int[] vetor){

        int i, j, aux;

        for(i = 0; i < vetor.length - 1; i++){
            for(j = 0; j < vetor.length - 1 - i; j++){
                if(vetor[j] > vetor[j + 1]){
                    aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;

                }
            }
        }
    }
}
