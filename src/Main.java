import java.util.Arrays;
import java.util.Scanner;

import algoritmos.CountingSort;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Integer[] vetor = null; 
        long inicio, fim;
        
        Menu();
        System.out.print("Selecione uma das opções acima: ");        
        int esc = scanner.nextInt();
        
        if (esc == 0) {
            System.out.println("Encerrando o programa...");
            scanner.close();
            return;
        } else if (esc < 1 || esc > 4) {
            System.out.println("[ ERRO ] Opção inválida! Por favor, selecione uma das opções disponíveis.");
            scanner.close();
            return;
        }

        System.out.print("Digite o tamanho do vetor para o teste (ex: 10, 1000, 100000): ");
        int tamanho = scanner.nextInt();
        
        System.out.println("========================================");
        
        switch(esc) {
            case 1:
                vetor = Vetores.getVetorOrdenado(tamanho);    
                break;
            case 2:
                vetor = Vetores.getVetorAleatorio(tamanho);
                break;
            case 3:
                vetor = Vetores.getVetorInverso(tamanho);
                break;
            case 4:
                // Opção 4 ativada
                vetor = Vetores.getVetorRepetido(tamanho);
                break;
        }

        System.out.println("Elementos antes da ordenação: " + Arrays.toString(vetor));
    
        inicio = System.nanoTime();
        int k = CountingSort.getMaiorValor(vetor);
        CountingSort.ordenador(vetor, vetor.length, k);
        fim = System.nanoTime();

        System.out.println("Elementos depois da ordenação: " + Arrays.toString(vetor) + " | Execução em  " + (fim - inicio) + " nanosegundos");
        
        scanner.close();
    }

    public static void Menu() {
        System.out.println("================= MENU =================");
        System.out.println("1. Ordenar elementos em ordem crescente");
        System.out.println("2. Ordenar elementos em ordem aleatória");
        System.out.println("3. Ordenar elementos em ordem decrescente");
        System.out.println("4. Ordenar elementos com várias ocorrências repetidas");
        System.out.println("0. Encerrar programa");
    }
}