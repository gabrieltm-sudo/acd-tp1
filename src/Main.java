import java.util.Arrays;
import java.util.Scanner;
// import algoritmos.BubbleSort;
import algoritmos.InsertionSort;

public class Main{

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int[] vetor = null;
        long inicio, fim;
        
        Menu();
        System.out.print("Selecione uma das opções acima: ");        
        int esc = scanner.nextInt();
        
        System.out.println("========================================");
        switch(esc){
            case 1:
                vetor = Vetores.getVetorOrdenado();    
                System.out.println("Elementos antes da ordenação: " + Arrays.toString(vetor));
            
                // BubbleSort.ordenador(vetor);
                inicio = System.nanoTime();
                InsertionSort.ordenador(vetor);
                fim = System.nanoTime();

                System.out.println("Elementos depois da ordenação: " + Arrays.toString(vetor) + " | Execução em  " + (fim-inicio) + " nanosegundos");
                break;
            case 2:
                vetor = Vetores.getVetorMedio();
                System.out.println("Elementos antes da ordenação: " + Arrays.toString(vetor));

                // BubbleSort.ordenador(vetor);
                inicio = System.nanoTime();
                InsertionSort.ordenador(vetor);
                fim = System.nanoTime();

                System.out.println("Elementos depois da ordenação: " + Arrays.toString(vetor) + " | Execução em  " + (fim-inicio) + " nanosegundos");
                break;
            case 3:
                vetor = Vetores.getVetorInverso();
                System.out.println("Elementos antes da ordenação: " + Arrays.toString(vetor));

                // BubbleSort.ordenador(vetor);
                inicio = System.nanoTime();
                InsertionSort.ordenador(vetor);
                fim = System.nanoTime();
                
                System.out.println("Elementos depois da ordenação: " + Arrays.toString(vetor) + " | Execução em  " + (fim-inicio) + " nanosegundos");
                break;
            case 0:
                System.out.println("Encerrando o programa...");
                scanner.close();
                return;
            default:
               System.out.println("[ ERRO ] Opção inválida! Por favor, selecione uma das opções disponíveis.");
               scanner.close();
               return;
        }
        scanner.close();
    }

    public static void Menu(){
        System.out.println("================= MENU =================");
        System.out.println("\n1. Ordenar elementos em ordem crescente\n2. Ordenar elementos em ordem aleatória\n3. Ordenar elementos em ordem decrescente\n4. Ordenar elementos com várias ocorrências repetidas (Não implementado ainda)\n0. Encerrar programa");
    }
}
