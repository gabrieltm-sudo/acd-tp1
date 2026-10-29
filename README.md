# TP1 - Análise de Algoritmos de Ordenação

Este repositório contém a implementação em Java dos algoritmos de ordenação estudados no trabalho prático da disciplina **Algoritmos e Classificação de Dados** (UNIPAMPA). O objetivo é validar a corretude dos algoritmos mediante testes unitários e analisar seu desempenho em diferentes cenários de entrada.

## Autores

- *Adrian do Couto Martins* - [GitHub](https://github.com/AdrianCouto)
- *Gabriel Torres Machado* - [GitHub](https://github.com/gabrieltm-sudo)


## Algoritmos Implementados
- **Bubble Sort**
- **Insertion Sort**
- **Selection Sort**
- **Merge Sort**
- **Heap Sort**
- **Shell Sort**
- **Quick Sort**
- **Counting Sort**
- **Radix Sort**
- **Bucket Sort**

## Estrutura do Projeto
```
src/
├─ algoritmos/                          # Implementações dos algoritmos
│   ├─ BubbleSort.java
│   ├─ InsertionSort.java
│   └─ ...
├─ Main.java                             # Execução dos exemplos principais
├─ TestesOrdenacao.java                  # Testes de corretude
├─ Benchmark.java                        # Execução automatizada dos benchmarks
├─ Vetores.java                          # Geração dos vetores de entrada
├─ ComparaTodos.py                       # Geração dos gráficos
├─ Makefile                              # Comandos de compilação e execução
├─ logs/
│   ├─ bruto/                            # Tempos de cada repetição
│   └─ medias/                           # Estatísticas dos benchmarks
└─ graficos/                             # Gráficos gerados a partir dos resultados
```

## Metodologia
1. **Validação da corretude** – Cada algoritmo é comparado ao `Arrays.sort()` do Java usando vetores de teste com diferentes padrões (ordenado, inverso, aleatório, repetido).
2. **Benchmark** – Para cada combinação de algoritmo, cenário e tamanho de vetor (1 000, 10 000, 100 000 e 1 000 000) são realizadas 10 repetições. O tempo é medido com `System.nanoTime()`.
3. **Análise** – A partir dos logs são calculados média, desvio‑padrão, mediana e coeficiente de variação (CV), que são usados para gerar os gráficos apresentados no repositório.

## Execução
```bash
# Ir para repositório source - Etapa obrigatória para todas as execuções a seguir
cd src/

# Compilar
make compile

# Executar main
make run

# Executar testes de corretude
make test

# Executar benchmark (gera logs em logs/bruto/)
make benchmark

# Remover bytecodes (.class)
make rm


# Remover arquivos CSVs
make rmcsv
```