import csv
from datetime import datetime
from pathlib import Path


DATA_DIR = Path("data")
REPORT_DIR = Path("reportes")
PRODUCTOS_FILE = DATA_DIR / "productos.csv"
MOVIMIENTOS_FILE = DATA_DIR / "movimientos.csv"

productos = []
movimientos = []


def leer_texto(mensaje):
    while True:
        valor = input(mensaje).strip()
        if valor:
            return valor
        print("El dato no puede estar vacio.")


def leer_entero(mensaje, minimo=0):
    while True:
        try:
            valor = int(input(mensaje).strip())
            if valor >= minimo:
                return valor
            print("El valor minimo es", minimo)
        except ValueError:
            print("Ingrese un numero entero valido.")


def leer_decimal(mensaje, minimo=0.01):
    while True:
        try:
            valor = float(input(mensaje).strip())
            if valor >= minimo:
                return valor
            print("El valor minimo es", minimo)
        except ValueError:
            print("Ingrese un numero decimal valido.")


def construir_indice_productos():
    indice = {}
    for i, producto in enumerate(productos):
        indice[producto["codigo"]] = i
    return indice


def buscar_producto_por_codigo(codigo):
    indice = construir_indice_productos()
    if codigo in indice:
        return productos[indice[codigo]]
    return None


def registrar_producto():
    print("\nRegistrar producto")
    codigo = leer_texto("Codigo: ")
    if buscar_producto_por_codigo(codigo) is not None:
        print("Ya existe un producto con ese codigo.")
        return

    productos.append(
        {
            "codigo": codigo,
            "nombre": leer_texto("Nombre: "),
            "categoria": leer_texto("Categoria: "),
            "precio": leer_decimal("Precio: "),
            "stock": leer_entero("Stock inicial: "),
        }
    )
    print("Producto registrado.")


def listar_productos(lista=None):
    datos = productos if lista is None else lista
    if not datos:
        print("No hay productos registrados.")
        return

    print("\nCodigo | Nombre | Categoria | Precio | Stock | Valor")
    print("-" * 70)
    for producto in datos:
        valor = producto["precio"] * producto["stock"]
        print(
            producto["codigo"],
            "|",
            producto["nombre"],
            "|",
            producto["categoria"],
            "|",
            f"{producto['precio']:.2f}",
            "|",
            producto["stock"],
            "|",
            f"{valor:.2f}",
        )


def buscar_producto():
    codigo = leer_texto("Codigo a buscar: ")
    producto = buscar_producto_por_codigo(codigo)
    if producto is None:
        print("Producto no encontrado.")
    else:
        listar_productos([producto])


def editar_producto():
    codigo = leer_texto("Codigo a editar: ")
    producto = buscar_producto_por_codigo(codigo)
    if producto is None:
        print("Producto no encontrado.")
        return

    print("Deje vacio para mantener el dato actual.")
    nombre = input(f"Nombre ({producto['nombre']}): ").strip()
    categoria = input(f"Categoria ({producto['categoria']}): ").strip()
    precio = input(f"Precio ({producto['precio']}): ").strip()

    if nombre:
        producto["nombre"] = nombre
    if categoria:
        producto["categoria"] = categoria
    if precio:
        try:
            nuevo_precio = float(precio)
            if nuevo_precio > 0:
                producto["precio"] = nuevo_precio
            else:
                print("Precio no actualizado por ser menor o igual que cero.")
        except ValueError:
            print("Precio no actualizado por ser invalido.")
    print("Producto actualizado.")


def eliminar_producto():
    codigo = leer_texto("Codigo a eliminar: ")
    producto = buscar_producto_por_codigo(codigo)
    if producto is None:
        print("Producto no encontrado.")
        return
    if producto["stock"] > 0:
        print("No se elimina un producto con stock. Registre salidas primero.")
        return
    productos.remove(producto)
    print("Producto eliminado.")


def registrar_movimiento():
    codigo = leer_texto("Codigo del producto: ")
    producto = buscar_producto_por_codigo(codigo)
    if producto is None:
        print("Producto no encontrado.")
        return

    print("1. Entrada")
    print("2. Salida")
    opcion = input("Tipo de movimiento: ").strip()
    if opcion == "1":
        tipo = "ENTRADA"
    elif opcion == "2":
        tipo = "SALIDA"
    else:
        print("Tipo no valido.")
        return

    cantidad = leer_entero("Cantidad: ", 1)
    if tipo == "SALIDA" and cantidad > producto["stock"]:
        print("No hay stock suficiente para la salida.")
        return

    if tipo == "ENTRADA":
        producto["stock"] += cantidad
    else:
        producto["stock"] -= cantidad

    movimientos.append(
        {
            "fecha": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
            "codigo": codigo,
            "tipo": tipo,
            "cantidad": cantidad,
        }
    )
    print("Movimiento registrado.")


def ordenar_productos():
    print("1. Nombre")
    print("2. Categoria")
    print("3. Stock")
    opcion = input("Ordenar por: ").strip()
    if opcion == "1":
        productos.sort(key=lambda producto: producto["nombre"].lower())
    elif opcion == "2":
        productos.sort(key=lambda producto: producto["categoria"].lower())
    elif opcion == "3":
        productos.sort(key=lambda producto: producto["stock"])
    else:
        print("Opcion no valida.")
        return
    print("Productos ordenados.")


def matriz_resumen_categorias():
    resumen = {}
    for producto in productos:
        categoria = producto["categoria"]
        if categoria not in resumen:
            resumen[categoria] = {"cantidad": 0, "stock": 0, "valor": 0.0}
        resumen[categoria]["cantidad"] += 1
        resumen[categoria]["stock"] += producto["stock"]
        resumen[categoria]["valor"] += producto["precio"] * producto["stock"]

    matriz = []
    for categoria, datos in resumen.items():
        matriz.append([categoria, datos["cantidad"], datos["stock"], datos["valor"]])
    return matriz


def mostrar_consultas():
    if not productos:
        print("No hay datos para consultar.")
        return

    matriz = matriz_resumen_categorias()
    valor_total = sum(fila[3] for fila in matriz)
    stock_total = sum(fila[2] for fila in matriz)
    stock_bajo = [producto for producto in productos if producto["stock"] <= 5]

    print("\nResumen general")
    print("Productos registrados:", len(productos))
    print("Stock total:", stock_total)
    print("Valor total:", f"{valor_total:.2f}")
    print("Productos con stock bajo:", len(stock_bajo))

    print("\nResumen por categoria")
    print("Categoria | Productos | Stock | Valor")
    print("-" * 50)
    for fila in matriz:
        print(fila[0], "|", fila[1], "|", fila[2], "|", f"{fila[3]:.2f}")


def listar_movimientos():
    if not movimientos:
        print("No hay movimientos registrados.")
        return
    print("\nFecha | Codigo | Tipo | Cantidad")
    print("-" * 60)
    for movimiento in movimientos:
        print(
            movimiento["fecha"],
            "|",
            movimiento["codigo"],
            "|",
            movimiento["tipo"],
            "|",
            movimiento["cantidad"],
        )


def guardar_datos():
    DATA_DIR.mkdir(exist_ok=True)
    with PRODUCTOS_FILE.open("w", newline="", encoding="utf-8") as archivo:
        campos = ["codigo", "nombre", "categoria", "precio", "stock"]
        writer = csv.DictWriter(archivo, fieldnames=campos)
        writer.writeheader()
        writer.writerows(productos)

    with MOVIMIENTOS_FILE.open("w", newline="", encoding="utf-8") as archivo:
        campos = ["fecha", "codigo", "tipo", "cantidad"]
        writer = csv.DictWriter(archivo, fieldnames=campos)
        writer.writeheader()
        writer.writerows(movimientos)
    print("Datos guardados.")


def cargar_datos():
    productos.clear()
    movimientos.clear()
    if PRODUCTOS_FILE.exists():
        with PRODUCTOS_FILE.open("r", newline="", encoding="utf-8") as archivo:
            reader = csv.DictReader(archivo)
            for fila in reader:
                productos.append(
                    {
                        "codigo": fila["codigo"],
                        "nombre": fila["nombre"],
                        "categoria": fila["categoria"],
                        "precio": float(fila["precio"]),
                        "stock": int(fila["stock"]),
                    }
                )

    if MOVIMIENTOS_FILE.exists():
        with MOVIMIENTOS_FILE.open("r", newline="", encoding="utf-8") as archivo:
            reader = csv.DictReader(archivo)
            for fila in reader:
                movimientos.append(
                    {
                        "fecha": fila["fecha"],
                        "codigo": fila["codigo"],
                        "tipo": fila["tipo"],
                        "cantidad": int(fila["cantidad"]),
                    }
                )


def exportar_productos_csv():
    REPORT_DIR.mkdir(exist_ok=True)
    destino = REPORT_DIR / "productos_exportados.csv"
    with destino.open("w", newline="", encoding="utf-8") as archivo:
        campos = ["codigo", "nombre", "categoria", "precio", "stock", "valor"]
        writer = csv.DictWriter(archivo, fieldnames=campos)
        writer.writeheader()
        for producto in productos:
            writer.writerow(
                {
                    "codigo": producto["codigo"],
                    "nombre": producto["nombre"],
                    "categoria": producto["categoria"],
                    "precio": producto["precio"],
                    "stock": producto["stock"],
                    "valor": producto["precio"] * producto["stock"],
                }
            )
    print("CSV exportado en", destino)


def generar_reporte_texto():
    REPORT_DIR.mkdir(exist_ok=True)
    destino = REPORT_DIR / "reporte_inventario.txt"
    matriz = matriz_resumen_categorias()
    with destino.open("w", encoding="utf-8") as archivo:
        archivo.write("REPORTE DE INVENTARIO\n")
        archivo.write("=====================\n\n")
        archivo.write("Productos registrados: " + str(len(productos)) + "\n")
        archivo.write("Movimientos registrados: " + str(len(movimientos)) + "\n\n")
        archivo.write("Resumen por categoria\n")
        for fila in matriz:
            archivo.write(
                f"- {fila[0]}: productos={fila[1]}, stock={fila[2]}, valor={fila[3]:.2f}\n"
            )
    print("Reporte generado en", destino)


def mostrar_menu():
    print("\n=== Market CLI Final ===")
    print("1. Registrar producto")
    print("2. Listar productos")
    print("3. Buscar producto")
    print("4. Editar producto")
    print("5. Eliminar producto")
    print("6. Registrar movimiento")
    print("7. Listar movimientos")
    print("8. Ordenar productos")
    print("9. Consultas y resumen")
    print("10. Exportar CSV")
    print("11. Generar reporte de texto")
    print("12. Guardar")
    print("13. Salir")


def main():
    cargar_datos()
    opcion = ""
    while opcion != "13":
        mostrar_menu()
        opcion = input("Seleccione una opcion: ").strip()
        if opcion == "1":
            registrar_producto()
        elif opcion == "2":
            listar_productos()
        elif opcion == "3":
            buscar_producto()
        elif opcion == "4":
            editar_producto()
        elif opcion == "5":
            eliminar_producto()
        elif opcion == "6":
            registrar_movimiento()
        elif opcion == "7":
            listar_movimientos()
        elif opcion == "8":
            ordenar_productos()
        elif opcion == "9":
            mostrar_consultas()
        elif opcion == "10":
            exportar_productos_csv()
        elif opcion == "11":
            generar_reporte_texto()
        elif opcion == "12":
            guardar_datos()
        elif opcion == "13":
            guardar_datos()
            print("Fin del programa.")
        else:
            print("Opcion no valida.")


main()
