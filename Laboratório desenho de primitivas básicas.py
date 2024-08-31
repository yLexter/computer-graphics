import matplotlib.pyplot as plt

def DDA(x1, y1, x2, y2):
    dx = abs(x1 - x2)
    dy = abs(y1 - y2)

    # find maximum difference
    length = max(dx, dy)

    #  incremento de x e y
    xinc = dx / length
    yinc = dy / length

    x = float(x1)
    y = float(y1)

    # lista dos pontos
    x_coords = []
    y_coords = []

    for i in range(length):

        x_coords.append(x)
        y_coords.append(y)

        # incremento
        x = x + xinc
        y = y + yinc

    #marker do tipo pixel
    plt.plot(x_coords, y_coords, marker=",", markersize=3, linestyle="None", color="black")

    #  limites para o gráfico
    plt.xlim(-90, 90)
    plt.ylim(-90, 90)
    plt.axhline(0, color='black', linewidth=0.5, clip_on=False)
    plt.axvline(0, color='black', linewidth=0.5, clip_on=False)


    plt.grid(True)
    plt.show()

# Driver code
if __name__ == "__main__":
    #cordenadas do primero ponto
    x1, y1 = -20,15

    #cordenadas do segundo ponto
    x2, y2 = 80, 50
    DDA(x1, y1, x2, y2)