import sys
import numpy as np
import matplotlib.pyplot as plt
from pathlib import Path
from PIL import Image


def plot_histogram(image, title, output_path):
    hist, bins = np.histogram(image.flatten(), bins=256, range=[0, 256])
    plt.figure()
    plt.title(title)
    plt.bar(bins[:-1], hist, width=1, color='black')
    plt.xlabel('K (Níveis de cinza)')
    plt.ylabel('Ps(sk)')
    plt.savefig(output_path, dpi=100)
    plt.close()


def calcule_histogram(image):
    # Obtém o histograma da imagem
    hist, bins = np.histogram(image.flatten(), bins=256, range=[0, 256])

    # Calcula a distribuição acumulada de probabilidades
    cdf = hist.cumsum()
    cdf_normalized = (cdf - cdf.min()) * 255 / (cdf.max() - cdf.min())

    # Aplica a transformação de mapeamento para equalização
    equalized_image = cdf_normalized[image].astype('uint8')

    return equalized_image


def main(input_image_path, output_dir):
    # Leitura da imagem PGM
    image = np.array(Image.open(input_image_path).convert('L'))

    if image is None:
        print(f"Erro ao carregar a imagem: {input_image_path}")
        sys.exit(1)

    # Cria o diretório de saída, se não existir
    Path(output_dir).mkdir(parents=True, exist_ok=True)

    # Caminhos de saída
    original_hist_path = str(Path(output_dir) / "original_histogram.png")
    equalized_hist_path = str(Path(output_dir) / "equalized_histogram.png")
    original_image_path = str(Path(output_dir) / "original_image.png")
    equalized_image_path = str(Path(output_dir) / "equalized_image.png")

    # Gera o histograma da imagem original
    plot_histogram(image, "Histograma Original", original_hist_path)

    # Equalização do histograma manualmente
    equalized_image = calcule_histogram(image)

    # Gera o histograma da imagem equalizada
    plot_histogram(equalized_image, "Histograma Equalizado", equalized_hist_path)

    # Salva as imagens
    Image.fromarray(image).save(original_image_path)
    Image.fromarray(equalized_image).save(equalized_image_path)

    # Retorna os caminhos das saídas
    print(original_hist_path)
    print(equalized_hist_path)
    print(original_image_path)
    print(equalized_image_path)


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Uso: python equalize_histogram.py <caminho_imagem_entrada> <diretorio_saida>")
        sys.exit(1)

    input_image_path = sys.argv[1]
    output_dir = sys.argv[2]
    main(input_image_path, output_dir)
