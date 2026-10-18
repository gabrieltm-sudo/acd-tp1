package algoritmos;
// TODO: Ajustar para receber o vetor de classes ao invés do int -> Mais especificamente o temp
public class InsertionSort{
    public static void ordenador(int[] vetor){
        for(int i=0; i<=vetor.length-1; i++){
            int key = vetor[i], j = i-1;

            while(j>=0 && vetor[j]>key){
                vetor[j+1] = vetor[j];
                j--;
            }

            vetor[j+1] = key;
        }
    }
}
