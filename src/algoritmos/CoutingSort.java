package algoritmos;

public class CoutingSort {
    public static void ordenador(int[] vetor, int n, int k){
        int[] B = new int[n], C = new int[k+1];

        for(int i=0; i<k; i++){
            C[i] = 0;
        }

        for(int j=0; j<n; j++){
            C[vetor[j]] = C[vetor[j]]+1;
        }

        for(int i=1; i<=k; i++){
            C[i] = C[i]+C[i-1];
        }

        for(int j=n-1; j>=0; j--){
            B[C[vetor[j]]-1] = vetor[j];
            C[vetor[j]] = C[vetor[j]]-1;
        }
        // TODO: Verificar se verdadeiramente é necessário esse for - Como proceder com o vetor cópia B
        for (int j=0; j<n; j++) {
            vetor[j] = B[j];
        }
    }

    public static int getMaiorValor(int[] vetor){
        int k=0;
        for(int i=0; i<vetor.length; i++){
            if(vetor[i]>k){
                k = vetor[i];
            }
        }
        
        return k;
    }
}
