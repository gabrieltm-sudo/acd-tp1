import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import algoritmos.*;

public class Benchmark {
    private static final int REPETICOES = 10;
    private static final int AQUECIMENTOS = 3;
    private static final int[] TAMANHOS = {1000, 10000, 100000, 1000000};
    private static final String[] VETOR_CASOS = {"Ordenado", "Aleatorio", "Inverso", "Repetido"};

    public static void main(String[] args) {
        final String dirBruto = "logs/bruto";
        final String dirMedias = "logs/medias";

        new File(dirBruto).mkdirs();
        new File(dirMedias).mkdirs();

        System.out.println("Iniciando benchmark...");

        for (int config = 1; config <= 4; config++) {
            for (int tamanho : TAMANHOS) {
                Integer[] vetorOriginal = gerarVetor(config, tamanho);

                for (EnumAlgoritmos algoritmo : EnumAlgoritmos.values()) {
                    if (tamanho >= 1000000 && ehQuadratico(algoritmo)) {
                        System.out.println("[ PULADO ] " + algoritmo.getNome() + " | Tam: " + tamanho
                                + " (Algoritmo Quadrático)");
                        continue;
                    }

                    executarConfiguracao(algoritmo, config, tamanho, vetorOriginal, dirBruto);
                }
            }

            for (EnumAlgoritmos algoritmo : EnumAlgoritmos.values()) {
                reconstruirArquivoDeMedias(algoritmo, config, dirBruto, dirMedias);
            }
        }

        System.out.println("\nExecuções concluídas!!");
    }

    private static void executarConfiguracao(EnumAlgoritmos algoritmo, int config, int tamanho,
            Integer[] vetorOriginal, String dirBruto) {
        File fileBruto = new File(String.format("%s/log_java_%s_caso%d_%d.csv", dirBruto, algoritmo.getNome(), config, tamanho));

        if (execucaoCompleta(fileBruto)) {
            System.out.println("Execução " + algoritmo.getNome() + " caso " + config + " tam " + tamanho + " já realizada. Pulando...");
            return;
        }

        if (fileBruto.exists() && !fileBruto.delete()) {
            throw new IllegalStateException("Falha crítica de I/O: Não foi possível remover o arquivo corrompido. Feche outros programas que possam estar usando " + fileBruto.getPath());
        }

        aquecer(algoritmo, vetorOriginal);

        try (PrintWriter writerBruto = new PrintWriter(new FileWriter(fileBruto))) {
            writerBruto.println("Tamanho,Repeticao,Tempo_ns");
            long somaTempo = 0;

            for (int i = 0; i < REPETICOES; i++) {
                System.out.print("\rExecutando: " + algoritmo.getNome() + " | Tam: " + tamanho + " | Config: " + VETOR_CASOS[config - 1] + " | Rep: " + (i + 1) + "/" + REPETICOES + "   ");

                Integer[] vetorCopia = vetorOriginal.clone();
                long inicio = System.nanoTime();
                executarAlgoritmo(algoritmo, vetorCopia);
                long tempoExecucao = System.nanoTime() - inicio;

                somaTempo += tempoExecucao;
                writerBruto.printf("%d,%d,%d%n", tamanho, i + 1, tempoExecucao);
                writerBruto.flush();
            }

            System.out.println("\n[ OK ] Média para a entrada " + tamanho + ": " + (somaTempo/REPETICOES) + " ns");
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo de log: " + e.getMessage());
        }
    }

    private static void aquecer(EnumAlgoritmos algoritmo, Integer[] vetorOriginal) {
        int tamanhoAquecimento = Math.min(vetorOriginal.length, 10000);
        Integer[] vetorAquecimento = Arrays.copyOf(vetorOriginal, tamanhoAquecimento);

        for (int i = 0; i < AQUECIMENTOS; i++) {
            Integer[] copia = vetorAquecimento.clone();
            executarAlgoritmo(algoritmo, copia);
        }
    }

    private static boolean execucaoCompleta(File arquivo) {
        if (!arquivo.exists()) {
            return false;
        }

        int linhas = contarLinhas(arquivo);
        return linhas == REPETICOES + 1;
    }

    private static void reconstruirArquivoDeMedias(EnumAlgoritmos algoritmo, int config, String dirBruto, String dirMedias) {
        File arquivoMedias = new File(String.format("%s/medias_java_%s_caso%d.csv", dirMedias, algoritmo.getNome(), config));

        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivoMedias))) {
            writer.println("Tamanho,Media_Tempo_ns");
            for (int tamanho: TAMANHOS) {
                File arquivoBruto = new File(String.format("%s/log_java_%s_caso%d_%d.csv",
                        dirBruto, algoritmo.getNome(), config, tamanho));
                long soma = 0;
                int quantidade = 0;

                if (!arquivoBruto.exists()) {
                    continue;
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(arquivoBruto))) {
                    String linha;
                    reader.readLine();
                    while((linha = reader.readLine()) != null) {
                        String[] colunas = linha.split(",");
                        if (colunas.length != 3) {
                            continue;
                        }
                        soma += Long.parseLong(colunas[2]);
                        quantidade++;
                    }
                }

                if (quantidade == REPETICOES) {
                    writer.printf("%d,%d\n", tamanho, soma/REPETICOES);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao reconstruir médias de " + algoritmo.getNome()
                    + ": " + e.getMessage());
        }
    }

    private static int contarLinhas(File arquivo) {
        int linhas = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            while (reader.readLine() != null) {
                linhas++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
        }
        return linhas;
    }

    private static Integer[] gerarVetor(int config, int tamanho) {
        switch (config) {
            case 1:
                return Vetores.getVetorOrdenado(tamanho);
            case 2:
                return Vetores.getVetorAleatorio(tamanho);
            case 3:
                return Vetores.getVetorInverso(tamanho);
            case 4:
                return Vetores.getVetorRepetido(tamanho);
            default:
                throw new IllegalArgumentException("Configuração de vetor inválida: " + config);
        }
    }

    private static void executarAlgoritmo(EnumAlgoritmos algoritmo, Integer[] vetor) {
        switch (algoritmo) {
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
                QuickSort.ordenador(vetor, 0, vetor.length - 1);
                break;
            case HEAP:
                HeapSort.ordenador(vetor);
                break;
            case RADIX:
                RadixSort.ordenador(vetor);
                break;
            case COUNTING:
                int maiorValor = CountingSort.getMaiorValor(vetor);
                CountingSort.ordenador(vetor, vetor.length, maiorValor);
                break;
            case BUCKET:
                BucketSort.ordenador(vetor, vetor.length);
                break;
            default:
                throw new IllegalArgumentException("Algoritmo não suportado: " + algoritmo);
        }
    }

    private static boolean ehQuadratico(EnumAlgoritmos algoritmo) {
        return algoritmo == EnumAlgoritmos.INSERTION
                || algoritmo == EnumAlgoritmos.BUBBLE
                || algoritmo == EnumAlgoritmos.SELECT;
    }
}
