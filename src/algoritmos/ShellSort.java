package algoritmos;

public class ShellSort {
    public static void ordenador(int[] vetor, int n){
        int h = 1;
        do{
            h=3*h+1;
        }while(h<n);

        do{
            h/=3;
            for(int i=h; i<n; i++){
                int x = vetor[i], j = i;

                while(j>=h && vetor[j-h]>x){
                    vetor[j] = vetor[j-h];
                    j-=h;
                }
                vetor[j] = x;
            }
        }while(h!=1);
    }
}