package algoritmos;
import java.util.List;
import java.util.ArrayList;

public class BucketSort {
    public static void ordenador(Integer[] vetor, int n){
        if (n<=0){
            return;
        }

        int maiorValor = vetor[0];
        for(int i = 1; i<n; i++){
            if (vetor[i]>maiorValor) {
                maiorValor = vetor[i];
            }
        }

        List<List<Integer>> B = new ArrayList<>(n);
        for(int i=0; i<n; i++){
            B.add(new ArrayList<Integer>());
        }

        for (int i=0; i<n; i++){
            int indiceBalde = (int) (((long) vetor[i]*n)/(maiorValor+1L));
            B.get(indiceBalde).add(vetor[i]);
        }

        int indiceVetor = 0;
        for (int i = 0; i < n; i++){
            List<Integer> baldeAtual = B.get(i);
            
            if (!baldeAtual.isEmpty()){
                Integer[] baldeArray = baldeAtual.toArray(new Integer[0]);
                
                InsertionSort.ordenador(baldeArray);
                
                for (int j = 0; j < baldeArray.length; j++){
                    vetor[indiceVetor++] = baldeArray[j];
                }
            }
        }
    }
}