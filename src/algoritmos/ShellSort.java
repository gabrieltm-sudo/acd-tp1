package algoritmos;

public class ShellSort {
    public static <T extends Comparable<T>> void ordenador(T[] vetor, int n){
        int h = 1;
        do{
            h=3*h+1;
        }while(h<n);

        do{
            h/=3;
            for(int i=h; i<n; i++){
                T x = vetor[i];
                int j = i;

                while(j>=h && vetor[j-h].compareTo(x)>0){
                    vetor[j] = vetor[j-h];
                    j-=h;
                }
                vetor[j] = x;
            }
        }while(h!=1);
    }
}