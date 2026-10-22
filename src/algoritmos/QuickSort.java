package algoritmos;

public class QuickSort{

    public static void ordenador(int[] vetor, int esq, int dir){
        if(esq < dir){
         int p = particao(vetor, esq, dir);
         ordenador(vetor, esq, p);
         ordenador(vetor, p + 1, dir);
        }
    }

    static int particao(int[] vetor, int esq, int dir){
        int meio = (int)((esq + dir)  / 2);
        int pivo = vetor[meio];
        int i = esq - 1;
        int j = dir + 1;

        while(true){
            do{
                i++;
            }while(vetor[i] < pivo);
            do{
                j--;
            }while(vetor[j] > pivo);
            if(i >= j){
                return j;
            }

            int aux = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = aux;

        }
    }
}
