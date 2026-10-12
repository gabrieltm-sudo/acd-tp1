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

        int meio = (esq + dir) / 2;

        int primeiro = vetor[esq];
        int central = vetor[meio];
        int ultimo = vetor[dir];

        int pivo;

        if ((primeiro >= central && primeiro <= ultimo) || (primeiro <= central && primeiro >= ultimo)){
            pivo = primeiro;
        }
        else if ((central >= primeiro && central <= ultimo) || (central <= primeiro && central >= ultimo)){
            pivo = central;
        }
        else {
            pivo = ultimo;
        }

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
