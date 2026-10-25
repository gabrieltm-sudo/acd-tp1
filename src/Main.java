import java.util.Scanner;

import algoritmos.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Integer[] vetor = null; 
        long inicio, fim;
        
        MenuVetores();
        System.out.print("Selecione uma das opções acima: ");        
        int escVetor = scanner.nextInt();
        
        if (escVetor == 0) {
            System.out.println("Encerrando o programa...");
            scanner.close();
            return;
        } else if (escVetor < 1 || escVetor > 4) {
            System.out.println("[ ERRO ] Opção inválida! Por favor, selecione uma das opções disponíveis.");
            scanner.close();
            return;
        }

        System.out.print("Digite o tamanho do vetor para o teste (ex: 10, 1000, 100000): ");
        int tamanho = scanner.nextInt();
        
        System.out.println("========================================");
        
        switch(escVetor) {
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
                vetor = Vetores.getVetorRepetido(tamanho);
                break;
        }

        System.out.println("========================================");
        MenuAlgoritmos();
        System.out.print("Selecione uma das opções acima: ");
        int escAlgoritmo = scanner.nextInt();

        EnumAlgoritmos[] algoritmos = EnumAlgoritmos.values();
        
        if(escAlgoritmo<1 || escAlgoritmo>algoritmos.length){
            System.out.println("[ ERRO ] Opção inválida! Por favor, selecione uma das opções disponíveis.");
            scanner.close();
            return;
        }

        EnumAlgoritmos algoritmoSelecionado = algoritmos[escAlgoritmo-1];

        System.out.println("========================================");
        System.out.println("Iniciando ordenação com " + algoritmoSelecionado.getNome() + "...\n");
        
        Integer[] vetorCopia = vetor.clone();
        
        // Inicio da contagem de tempo
        inicio = System.nanoTime();
        switch(algoritmoSelecionado){
            case INSERTION:
                InsertionSort.ordenador(vetorCopia);
                break;
            case BUBBLE:

                break;
            case SELECT:

                break;
            case SHELL:

                break;

            case MERGE:
                MergeSort.ordenador(vetorCopia);
                break;
            case QUICK:

                break;
            case HEAP:

                break;
            case RADIX:

                break;
            case COUNTING:
                int k = CountingSort.getMaiorValor(vetor);
                CountingSort.ordenador(vetorCopia, vetorCopia.length, k);
                break;
            case BUCKET:
                BucketSort.ordenador(vetorCopia, vetorCopia.length);
                break;
        }
        
        // Fim da contagem de tempo
        fim = System.nanoTime();
        long totalTimeMS = (fim - inicio)/1000000;
        
        System.out.println(algoritmoSelecionado.getNome() + " com " + tamanho + " entradas: Execução concluída em " + totalTimeMS + " ms");
        scanner.close();
    }

    public static void MenuVetores() {
        System.out.println("================= TIPO DE ENTRADA =================");
        System.out.println("1. Ordenar elementos em ordem crescente");
        System.out.println("2. Ordenar elementos em ordem aleatória");
        System.out.println("3. Ordenar elementos em ordem decrescente");
        System.out.println("4. Ordenar elementos com várias ocorrências repetidas");
        System.out.println("0. Encerrar programa");
    }

    public static void MenuAlgoritmos(){
        System.out.println("================= ALGORITMOS DE ORDENAÇÃO =================");
        for (EnumAlgoritmos algoritmo : EnumAlgoritmos.values()) {
            System.out.println(algoritmo.ordinal() + 1 + ". " + algoritmo.getNome());
        }
        System.out.println("0. Encerrar programa");
    }
}
