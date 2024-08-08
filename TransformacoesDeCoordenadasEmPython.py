import tkinter as tk

def calcular_ndcx(X, Xm, XM, coordenadaInicialX, coordenadaFinalX):
    NDCX = ((X - Xm) / (XM - Xm)) * (coordenadaFinalX - coordenadaInicialX) + coordenadaInicialX
    return NDCX

def calcular_ndcy(Y, Ym, YM, coordenadaInicialY, coordenadaFinalY):
    NDCY = ((Y - Ym) / (YM - Ym)) * (coordenadaFinalY - coordenadaInicialY) + coordenadaInicialY
    return NDCY

def inp_to_ndc(X, Xm, XM, Y, Ym, YM, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY):
    NDCX = calcular_ndcx(X, Xm, XM, coordenadaInicialX, coordenadaFinalX)
    NDCY = calcular_ndcy(Y, Ym, YM, coordenadaInicialY, coordenadaFinalY)
    return [NDCX, NDCY]

def calcular_dcx(NDCX, NDH, coordenadaInicialX, coordenadaFinalX):
    DCX = round(((NDCX - coordenadaInicialX) * (NDH - 1)) / (coordenadaFinalX - coordenadaInicialX))
    return DCX

def calcular_dcy(NDCY, NDV, coordenadaInicialY, coordenadaFinalY):
    DCY = round(((NDCY - coordenadaInicialY) * (NDV - 1)) / (coordenadaFinalY - coordenadaInicialY))
    return DCY

def ndc_to_dc(NDCX, NDCY, NDH, NDV, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY):
    DCX = calcular_dcx(NDCX, NDH, coordenadaInicialX, coordenadaFinalX)
    DCY = calcular_dcy(NDCY, NDV, coordenadaInicialY, coordenadaFinalY)
    return [DCX, DCY]

print("Sistema de Transformacoes de Coordenadas\n")
X = float(input("Digite o valor de X: "))
Xm = float(input("Digite o valor de Xm: "))
XM = float(input("Digite o valor de XM: "))
Y = float(input("Digite o valor de Y: "))
Ym = float(input("Digite o valor de Ym: "))
YM = float(input("Digite o valor de YM: "))

coordenadaInicialX = float(input("Digite o valor inicial NDC de X: "))
coordenadaFinalX = float(input("Digite o valor final NDC de X: "))
coordenadaInicialY = float(input("Digite o valor inicial NDC de Y: "))
coordenadaFinalY = float(input("Digite o valor final NDC de Y: "))

NDH = float(input("Digite a largura da tela: "))
NDV = float(input("Digite a altura da tela: "))

NDCX = inp_to_ndc(X, Xm, XM, Y, Ym, YM, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY)[0]
NDCY = inp_to_ndc(X, Xm, XM, Y, Ym, YM, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY)[1]

DCX = ndc_to_dc(NDCX, NDCY, NDH, NDV, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY)[0]
DCY = ndc_to_dc(NDCX, NDCY, NDH, NDV, coordenadaInicialX, coordenadaFinalX, coordenadaInicialY, coordenadaFinalY)[1]

print("\nValores calculados: \n")
print(f"NDCX: {NDCX:.3f}, NDCY: {NDCY:.3f}, DCX: {DCX}, DCY: {DCY}")

tela = tk.Tk()

canvas = tk.Canvas(tela, width=NDH, height=NDV, relief="solid", bd=2)
canvas.pack(padx=3, pady=3)

yDoPixel = NDV - DCY #Vertical (dado pelo usuário) - Altura da Tela (calculado)

canvas.create_rectangle(DCX, yDoPixel, DCX + 10, yDoPixel + 10, fill="black")

tela.mainloop()