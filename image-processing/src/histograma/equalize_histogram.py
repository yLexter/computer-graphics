import sys
import numpy as np
import matplotlib.pyplot as plt
from PIL import Image
from io import BytesIO

def plot_histogram(image, title):
    hist, bins = np.histogram(image.flatten(), bins=256, range=[0, 256])
    plt.figure()
    plt.title(title)
    plt.bar(bins[:-1], hist, width=1, color='black')
    plt.xlabel('K (Níveis de cinza)')
    plt.ylabel('Ps(sk)')
    plt.show()

def equalize_histogram(image):
    hist, bins = np.histogram(image.flatten(), bins=256, range=[0, 256])
    cdf = hist.cumsum()
    cdf_normalized = (cdf - cdf.min()) * 255 / (cdf.max() - cdf.min())
    equalized_image = cdf_normalized[image].astype('uint8')
    return equalized_image

def main():
    # Recebe a imagem do stdin
    raw_data = sys.stdin.buffer.read()
    image = np.array(Image.open(BytesIO(raw_data)).convert('L'))

    # Processa a imagem
    plot_histogram(image, "Histograma Original")
    equalized_image = equalize_histogram(image)
    plot_histogram(equalized_image, "Histograma Equalizado")

if __name__ == "__main__":
    main()
