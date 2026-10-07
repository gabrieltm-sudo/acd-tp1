import java.util.Arrays;
import java.util.Scanner;
import algoritmos.BubbleSort;
import algoritmos.SelectionSort;
//import algortimos.*; para usar todas as classes dentro de /algoritmos

public class Main{

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int[] vetor = null;

        System.out.println("Escolha o vetor utilizado:\n1. Vetor ordenado\n2. vetor medio\n3. vetor inverso\n4. Vetor aleatório\nEscolha: ");

        int esc = scanner.nextInt();

        switch(esc){
            case 1:
                vetor = Vetores.getVetorOrdenado();
                break;
            case 2:
                vetor = Vetores.getVetorMedio();
                break;
            case 3:
                vetor = Vetores.getVetorInverso();
                break;
            case 4:
                vetor = Vetores.getVetorRandomico();
        }

                System.out.println("VETOR A SER ORDENADO:  " + Arrays.toString(vetor));

                System.out.println("\n");

                System.out.println("SELECTION SORT");

                long inicio = System.nanoTime();

                SelectionSort.ordenador(vetor);

                long fim = System.nanoTime();

                System.out.println("Depois da ordenação SelectionSort:  " + Arrays.toString(vetor));

                long tempoTotalNano = fim - inicio;

                double tempoTotalMs = tempoTotalNano / 1_000_000.0;

                System.out.println("Tempo de execução SelectionSort: " + tempoTotalMs + " ms");

                System.out.println("\n");

                System.out.println("BUBBLE SORT");

                inicio = System.nanoTime();

                BubbleSort.ordenador(vetor);

                fim = System.nanoTime();

                System.out.println("Depois da ordenação BubbleSort:  " + Arrays.toString(vetor));

                tempoTotalNano = fim - inicio;

                tempoTotalMs = tempoTotalNano / 1_000_000.0;

                System.out.println("Tempo de execução BubbleSort: " + tempoTotalMs + " ms");

    }

}
