import os
import glob
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

sns.set_theme(style="whitegrid")
dir_bruto = "logs/bruto"
dir_saida = "graficos_barras"
os.makedirs(dir_saida, exist_ok=True)

MAPA_CASOS = {
    1: "Ordenado",
    2: "Aleatorio",
    3: "Inverso",
    4: "Repetido"
}

ORDEM_BASE = ["bubble", "insertion", "select", "shell", "heap", "merge", "quick", "counting", "radix", "bucket"]

arquivos = glob.glob(os.path.join(dir_bruto, "log_java_*.csv"))

if not arquivos:
    print(f"Nenhum arquivo encontrado em '{dir_bruto}'.")
    exit()

dados_totais = []

for arquivo in arquivos:
    nome_base = os.path.basename(arquivo)
    partes = nome_base.replace(".csv", "").split("_")

    try:
        algoritmo = partes[2]
        caso_num = int(partes[3].replace("caso", ""))
        configuracao = MAPA_CASOS.get(caso_num, f"Caso {caso_num}")

        df_temp = pd.read_csv(arquivo)
        df_temp['Algoritmo'] = algoritmo
        df_temp['Configuracao'] = configuracao

        dados_totais.append(df_temp)
    except Exception as e:
        print(f"Erro ao processar {nome_base}: {e}")

df = pd.concat(dados_totais, ignore_index=True)

tamanhos = df['Tamanho'].unique()
configuracoes = df['Configuracao'].unique()

print("Gerando gráficos de barras individuais por Tamanho e Cenário...")

for tamanho in sorted(tamanhos):
    for config in configuracoes:
        df_filtro = df[(df['Tamanho'] == tamanho) & (df['Configuracao'] == config)]

        if df_filtro.empty:
            continue

        algoritmos_presentes = df_filtro['Algoritmo'].unique()

        ordem_filtrada = []
        for nome_ref in ORDEM_BASE:
            for algo in algoritmos_presentes:
                if nome_ref in algo.lower() and algo not in ordem_filtrada:
                    ordem_filtrada.append(algo)

        for algo in algoritmos_presentes:
            if algo not in ordem_filtrada:
                ordem_filtrada.append(algo)

        plt.figure(figsize=(14, 7))

        ax = sns.barplot(
            data=df_filtro,
            x='Algoritmo',
            y='Tempo_ns',
            hue='Algoritmo',
            order=ordem_filtrada,
            hue_order=ordem_filtrada,
            legend=False,
            estimator=np.median,
            errorbar='sd',
            palette='Set3',
            capsize=0.1,
            err_kws={'linewidth': 1.5},
            edgecolor='black'
        )

        plt.xticks(rotation=45, ha='right')

        algoritmos_grafico = [item.get_text() for item in ax.get_xticklabels()]

        for i, algo in enumerate(algoritmos_grafico):
            dados_algo = df_filtro[df_filtro['Algoritmo'] == algo]['Tempo_ns']
            media = dados_algo.mean()
            desvio = dados_algo.std()
            mediana = dados_algo.median()

            cv = (desvio / media) * 100 if media > 0 else 0

            limite_erro = mediana + desvio if not pd.isna(desvio) else mediana

            ax.text(i, limite_erro * 1.2, f'CV: {cv:.1f}%\nMed: {mediana:.1e}',
                    ha='center', va='bottom', fontsize=9, color='black')

        plt.title(f"Comparação de Algoritmos - Tamanho: {tamanho} | Cenário: {config}", fontsize=14, weight='bold')
        plt.xlabel("Algoritmo", fontsize=12)
        plt.ylabel("Tempo de Execução (ns) [Escala Log]", fontsize=12)

        plt.yscale('log')

        ymin, ymax = ax.get_ylim()
        ax.set_ylim(ymin, ymax * 10)

        plt.tight_layout()

        nome_arquivo = f"barras_tam{tamanho}_{config.lower()}.png"
        caminho = os.path.join(dir_saida, nome_arquivo)
        plt.savefig(caminho, dpi=300, bbox_inches='tight')
        plt.close()

        print(f"-> Salvo: '{caminho}'")

print("\nProcesso concluído! Verifique a pasta:", dir_saida)
