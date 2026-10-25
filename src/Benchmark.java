import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import algoritmos.*;

public class Benchmark {
    public static void main(String[] args){
        final int repeticoes = 10;
        final int[] tamanhos = {1000, 10000, 100000, 1000000};
        final String[] vetorCasos = {"Ordenado", "Aleatorio", "Inverso", "Repetido"};
        
        final String dirBruto = "logs/bruto";
        final String dirMedias = "logs/medias";
        
        new File(dirBruto).mkdirs();
        new File(dirMedias).mkdirs();
        
        System.out.println("Iniciando benchmark...");

        for (final EnumAlgoritmos algoritmo : EnumAlgoritmos.values()) {
            for (int config = 1; config <= 4; config++) {
                
                String nomeArquivoMedias = String.format("%s/medias_java_%s_caso%d.csv", dirMedias, algoritmo.getNome(), config);
                File fileMedias = new File(nomeArquivoMedias);
                
                for (final int tamanho : tamanhos) {
                    
                    if (tamanho >= 1000000 && ehQuadratico(algoritmo)) {
                        System.out.println("[ PULADO ] " + algoritmo.getNome() + " | Tam: " + tamanho + " (Algoritmo Quadrático)");
                        continue; 
                    }

                    String nomeArquivoBruto = String.format("%s/log_java_%s_caso%d_%d.csv", dirBruto, algoritmo.getNome(), config, tamanho);
                    File fileBruto = new File(nomeArquivoBruto);
                    
                    boolean rodar = true;
                    
                    // Lógica de Checkpoint - conta as linhas e apaga se estiver incompleto
                    if (fileBruto.exists()) {
                        int linhasAtuais = contarLinhas(fileBruto);
                        int linhasEsperadas = repeticoes + 1; // +1 devido ao cabeçalho
                        
                        if (linhasAtuais >= linhasEsperadas) {
                            System.out.println("Execução " + algoritmo.getNome() + " caso " + config + " tam " + tamanho + " já realizada. (" + linhasAtuais + "/" + linhasEsperadas + ")");
                            System.out.println("Pulando...");
                            rodar = false;
                        } else {
                            System.out.println("Execução " + algoritmo.getNome() + " caso " + config + " tam " + tamanho + " incompleta. (" + linhasAtuais + "/" + linhasEsperadas + ")");
                            System.out.println("Apagando para refazer...");
                            fileBruto.delete();
                            rodar = true;
                        }
                    }

                    if (rodar) {
                        try (PrintWriter writerBruto = new PrintWriter(new FileWriter(fileBruto));
                             PrintWriter writerMedias = new PrintWriter(new FileWriter(fileMedias, true))) {
                            
                            writerBruto.println("Tamanho,Repeticao,Tempo_ns");
                            
                            if (fileMedias.length() == 0) {
                                writerMedias.println("Tamanho,Media_Tempo_ns");
                            }

                            long somaTempo = 0;
                            
                            for (int i=0; i<repeticoes; i++) {
                                System.out.print("\rExecutando: " + algoritmo.getNome() + " | Tam: " + tamanho + " | Config: " + vetorCasos[config-1] + " | Rep: " + (i+1) + "/" + repeticoes + "   ");
                                
                                final Integer[] vetor = gerarVetor(config, tamanho);
                                final Integer[] vetorCopia = vetor.clone();
                                
                                final long inicio = System.nanoTime();
                                executarAlgoritmo(algoritmo, vetorCopia);
                                final long fim = System.nanoTime();

                                final long tempoExecucao = fim - inicio;
                                somaTempo += tempoExecucao;
                                
                                writerBruto.printf("%d,%d,%d\n", tamanho, i+1, tempoExecucao);
                                writerBruto.flush();
                            }
                            
                            long media = somaTempo / repeticoes;
                            System.out.println("\n[ OK ] Média para a entrada " + tamanho + ": " + media + " ns");
                            
                            writerMedias.printf("%d,%d\n", tamanho, media);
                            writerMedias.flush();
                            
                        } catch (IOException e) {
                            System.err.println("Erro ao escrever nos arquivos de log: " + e.getMessage());
                        }
                    }
                }
            }
        }
        System.out.println("\nExecuções concluídas!!");
    }

    // Contador das linhas do arquivo para verificar se a execução foi completa
    private static int contarLinhas(File arquivo) {
        int linhas = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            while (reader.readLine() != null) {
                linhas++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + arquivo.getName());
        }
        return linhas;
    }

    private static Integer[] gerarVetor(int config, int tamanho) {
        switch(config){
            case 1: 
                return Vetores.getVetorOrdenado(tamanho);
            case 2: 
                return Vetores.getVetorAleatorio(tamanho);
            case 3: 
                return Vetores.getVetorInverso(tamanho);
            case 4: 
                return Vetores.getVetorRepetido(tamanho);
            default: 
                return new Integer[0];
        }
    }

    private static void executarAlgoritmo(final EnumAlgoritmos algoritmo, final Integer[] vetor) {
        switch(algoritmo){
            case INSERTION: 
                InsertionSort.ordenador(vetor); 
                break;
            case BUBBLE: 
                BubbleSort.ordenador(vetor); 
                break;
            case SELECT: 
                SelectionSort.ordenador(vetor);
                break;
            case SHELL: 
                ShellSort.ordenador(vetor, vetor.length);
                break;
            case MERGE: 
                MergeSort.ordenador(vetor); 
                break;
            case QUICK: 
                QuickSort.ordenador(vetor, 0, vetor.length-1); 
                break;
            case HEAP: 
                HeapSort.ordenador(vetor); 
                break;
            case RADIX: 
                RadixSort.ordenador(vetor);
                break;
            case COUNTING: 
                int k = CountingSort.getMaiorValor(vetor);
                CountingSort.ordenador(vetor, vetor.length, k);
                break;
            case BUCKET: 
                BucketSort.ordenador(vetor, vetor.length); 
                break;
            
            default: break;
        }
    }

    private static boolean ehQuadratico(final EnumAlgoritmos algoritmo) {
        return algoritmo == EnumAlgoritmos.INSERTION || 
               algoritmo == EnumAlgoritmos.BUBBLE || 
               algoritmo == EnumAlgoritmos.SELECT;
    }
}