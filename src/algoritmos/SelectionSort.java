package algoritmos;

public class SelectionSort{

    public static void ordenador(int[] vetor){

        int tam_vet = vetor.length;

        for(int i = 0; i < tam_vet - 1; i++){
            int indiceMin = i;

            for(int j = i + 1; j < tam_vet; j++){
                if(vetor[j] < vetor[indiceMin] ){
                    indiceMin = j;
                }

            }

            int aux = vetor[indiceMin];
            vetor[indiceMin] = vetor[i];
            vetor[i] = aux;

        }


    }

}
