import java.util.Arrays;
import java.util.Scanner;
import algoritmos.BubbleSort;
//import algortimos.*; para usar todas as classes dentro de /algoritmos

public class Main{

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int[] vetor = null;

        System.out.println("Escolha o vetor utilizado:\n1. Vetor ordenado\n2. vetor medio\n3. vetor inverso\nEscolha: ");

        int esc = scanner.nextInt();

        switch(esc){

            case 1:

                vetor = Vetores.getVetorOrdenado();

                System.out.println("Antes da ordenação:  " + Arrays.toString(vetor));

                BubbleSort.ordenador(vetor);

                System.out.println("Depois da ordenação:  " + Arrays.toString(vetor));

                break;

            case 2:

                vetor = Vetores.getVetorMedio();

                System.out.println("Antes da ordenação:  " + Arrays.toString(vetor));

                BubbleSort.ordenador(vetor);

                System.out.println("Depois da ordenação:  " + Arrays.toString(vetor));

                break;

            case 3:

                vetor = Vetores.getVetorInverso();

                System.out.println("Antes da ordenação:  " + Arrays.toString(vetor));

                BubbleSort.ordenador(vetor);

                System.out.println("Depois da ordenação:  " + Arrays.toString(vetor));

                break;

            default:

               System.out.println("Opção inválida!");

               scanner.close();

               return;

        }

    }

}
