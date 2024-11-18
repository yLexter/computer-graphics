import sys
import cv2
import numpy as np
import matplotlib.pyplot as plt
from pathlib import Path

def plot_histogram(image, title, output_path):
    hist, bins = np.histogram(image.flatten(), bins=256, range=[0, 256])
    plt.figure()
    plt.title(title)
    plt.bar(bins[:-1], hist, width=1, color='black')
    plt.xlabel('Intensidade')
    plt.ylabel('Frequência')
    plt.savefig(output_path, dpi=100)
    plt.close()

def main(input_image_path, output_dir):
    # Leitura da imagem PGM
    image = cv2.imread(input_image_path, cv2.IMREAD_GRAYSCALE)

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

    # Equalização do histograma
    equalized_image = cv2.equalizeHist(image)

    # Gera o histograma da imagem equalizada
    plot_histogram(equalized_image, "Histograma Equalizado", equalized_hist_path)

    # Salva as imagens
    cv2.imwrite(original_image_path, image)
    cv2.imwrite(equalized_image_path, equalized_image)

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
