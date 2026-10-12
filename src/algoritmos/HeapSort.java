package algoritmos;

public class HeapSort{

    public static void ordenador(int[] vetor){

        int n = vetor.length;

        for(int i = n / 2 - 1; i >= 0; i--){ //vai até metade (n/2) do vetor pois assim ja encontrou todos os filhos pelo metodo de calculo: 2*i + 1
            aplicarHeap(vetor, n, i);
         }

         for(int i = n - 1; i > 0; i--){
            int aux = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = aux;

            aplicarHeap(vetor, i, 0);
         }
    }

    public static void aplicarHeap(int[] vetor, int n, int i){

        int raiz = i;
        int esq, dir;

        while(true){

            raiz = i;
            esq = 2 * i + 1;
            dir = 2 * i + 2;

            if(esq < n && vetor[esq] > vetor[raiz]){
                raiz = esq;
            }
            if(dir < n && vetor[dir] > vetor[raiz]){
                raiz = dir;
            }
            if(raiz == i){
                break;
            }

            int aux = vetor[i];
            vetor[i] = vetor[raiz];
            vetor[raiz] = aux;

            i = raiz;
        }

    }
}
