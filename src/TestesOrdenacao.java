import java.util.Arrays;
import algoritmos.*;

public class TestesOrdenacao {
    public static void main(String[] args) {
        Integer[][] casos = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
            {7, 2, 9, 2, 5, 1, 7, 3, 8, 4, 6},
            {4, 4, 4, 1, 2, 1, 3, 2, 4, 1},
            {5, 2, 8, 1, 9, 3, 7, 4, 6}
        };
        String[] nomes = {"BubbleSort", "InsertionSort", "SelectionSort", "ShellSort",
                "HeapSort", "MergeSort", "QuickSort", "CountingSort", "RadixSort", "BucketSort"};

        for (Integer[] caso : casos) {
            Integer[] esperado = caso.clone();
            Arrays.sort(esperado);
            for (String nome : nomes) {
                Integer[] vetor = caso.clone();
                ordenar(nome, vetor);
                if (!Arrays.equals(vetor, esperado)) {
                    throw new AssertionError(nome + " falhou para " + Arrays.toString(caso));
                }
            }
        }
        System.out.println("Todos os testes de corretude passaram.");
    }

    private static void ordenar(String nome, Integer[] vetor) {
        switch (nome) {
            case "BubbleSort": 
                BubbleSort.ordenador(vetor); 
                break;
            case "InsertionSort": 
                InsertionSort.ordenador(vetor); 
                break;
            case "SelectionSort": 
                SelectionSort.ordenador(vetor); 
                break;
            case "ShellSort": 
                ShellSort.ordenador(vetor, vetor.length); 
                break;
            case "HeapSort": 
                HeapSort.ordenador(vetor); 
                break;
            case "MergeSort": 
                MergeSort.ordenador(vetor); 
                break;
            case "QuickSort": 
                QuickSort.ordenador(vetor, 0, vetor.length - 1); 
                break;
            case "CountingSort": 
                CountingSort.ordenador(vetor, vetor.length, CountingSort.getMaiorValor(vetor)); 
                break;
            case "RadixSort": 
                RadixSort.ordenador(vetor); 
                break;
            case "BucketSort": 
                BucketSort.ordenador(vetor, vetor.length); 
                break;
            default: 
                throw new IllegalArgumentException("Algoritmo inválido: " + nome);
        }
    }
}
