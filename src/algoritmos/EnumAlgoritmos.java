package algoritmos;

public enum EnumAlgoritmos {
    BUBBLE("BubbleSort"), INSERTION("InsertionSort"), SELECT("SelectionSort"), SHELL("ShellSort"), HEAP("HeapSort"), MERGE("MergeSort"), QUICK("QuickSort"), COUNTING("CountingSort"), RADIX("RadixSort"), BUCKET("BucketSort");
    private String nome;

    EnumAlgoritmos(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
}
