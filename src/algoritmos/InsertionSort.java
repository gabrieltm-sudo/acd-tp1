package algoritmos;
// TODO: Ajustar para receber o vetor de classes ao invés do int -> Mais especificamente o temp
public class InsertionSort{
    public static void ordenador(int[] vetor){
        for(int i=0; i<vetor.length-1; i++){
            int minimo = i;
            for(int j=i+1; j<vetor.length; j++){
                if(vetor[j]<vetor[minimo]){
                    minimo = j;
                }
            }
            int temp = vetor[i];
            vetor[i] = vetor[minimo];
            vetor[minimo] = temp;
        }
    }
}
