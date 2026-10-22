package algoritmos;

public class InsertionSort {
    
    public static <T extends Comparable<T>> void ordenador(T[] vetor) {
        
        for(int i=1; i<vetor.length; i++){
            T key = vetor[i]; 
            int j = i-1;

            while(j>=0 && vetor[j].compareTo(key)>0){
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j+1] = key;
        }
    }
}