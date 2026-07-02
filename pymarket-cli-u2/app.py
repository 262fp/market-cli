import csv
from pathlib import Path


DATA_DIR = Path("data")
DATA_FILE = DATA_DIR / "productos.csv"
productos = []


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


def buscar_indice_por_codigo(codigo):
    for i in range(len(productos)):
        if productos[i]["codigo"] == codigo:
            return i
    return -1


def registrar_producto():
    print("\nRegistrar producto")
    codigo = leer_texto("Codigo: ")
    if buscar_indice_por_codigo(codigo) != -1:
        print("Ya existe un producto con ese codigo.")
        return

    producto = {
        "codigo": codigo,
        "nombre": leer_texto("Nombre: "),
        "categoria": leer_texto("Categoria: "),
        "precio": leer_decimal("Precio: "),
        "stock": leer_entero("Stock: "),
    }
    productos.append(producto)
    print("Producto registrado.")


def listar_productos(lista=None):
    datos = productos if lista is None else lista
    if not datos:
        print("No hay productos registrados.")
        return

    print("\nCodigo | Nombre | Categoria | Precio | Stock | Valor")
    print("-" * 65)
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
    indice = buscar_indice_por_codigo(codigo)
    if indice == -1:
        print("Producto no encontrado.")
    else:
        listar_productos([productos[indice]])


def editar_producto():
    codigo = leer_texto("Codigo a editar: ")
    indice = buscar_indice_por_codigo(codigo)
    if indice == -1:
        print("Producto no encontrado.")
        return

    producto = productos[indice]
    print("Deje vacio para mantener el dato actual.")
    nombre = input(f"Nombre ({producto['nombre']}): ").strip()
    categoria = input(f"Categoria ({producto['categoria']}): ").strip()
    precio = input(f"Precio ({producto['precio']}): ").strip()
    stock = input(f"Stock ({producto['stock']}): ").strip()

    if nombre:
        producto["nombre"] = nombre
    if categoria:
        producto["categoria"] = categoria
    if precio:
        try:
            nuevo_precio = float(precio)
            if nuevo_precio > 0:
                producto["precio"] = nuevo_precio
        except ValueError:
            print("Precio no actualizado por ser invalido.")
    if stock:
        try:
            nuevo_stock = int(stock)
            if nuevo_stock >= 0:
                producto["stock"] = nuevo_stock
        except ValueError:
            print("Stock no actualizado por ser invalido.")

    print("Producto actualizado.")


def eliminar_producto():
    codigo = leer_texto("Codigo a eliminar: ")
    indice = buscar_indice_por_codigo(codigo)
    if indice == -1:
        print("Producto no encontrado.")
    else:
        eliminado = productos.pop(indice)
        print("Producto eliminado:", eliminado["nombre"])


def ordenar_productos():
    print("1. Ordenar por nombre")
    print("2. Ordenar por stock")
    opcion = input("Seleccione: ").strip()
    if opcion == "1":
        productos.sort(key=lambda producto: producto["nombre"].lower())
        print("Productos ordenados por nombre.")
    elif opcion == "2":
        productos.sort(key=lambda producto: producto["stock"])
        print("Productos ordenados por stock.")
    else:
        print("Opcion no valida.")


def mostrar_consultas():
    if not productos:
        print("No hay datos para consultar.")
        return

    total_productos = len(productos)
    total_stock = sum(producto["stock"] for producto in productos)
    valor_total = sum(producto["precio"] * producto["stock"] for producto in productos)
    stock_bajo = [producto for producto in productos if producto["stock"] <= 5]

    print("\nConsultas")
    print("Total de productos:", total_productos)
    print("Stock total:", total_stock)
    print("Valor total de inventario:", f"{valor_total:.2f}")
    print("Productos con stock bajo:", len(stock_bajo))


def guardar_productos():
    DATA_DIR.mkdir(exist_ok=True)
    with DATA_FILE.open("w", newline="", encoding="utf-8") as archivo:
        campos = ["codigo", "nombre", "categoria", "precio", "stock"]
        writer = csv.DictWriter(archivo, fieldnames=campos)
        writer.writeheader()
        for producto in productos:
            writer.writerow(producto)
    print("Datos guardados en", DATA_FILE)


def cargar_productos():
    productos.clear()
    if not DATA_FILE.exists():
        return
    with DATA_FILE.open("r", newline="", encoding="utf-8") as archivo:
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


def mostrar_menu():
    print("\n=== Market CLI U2 ===")
    print("1. Registrar")
    print("2. Listar")
    print("3. Buscar")
    print("4. Editar")
    print("5. Eliminar")
    print("6. Ordenar")
    print("7. Consultas")
    print("8. Guardar")
    print("9. Salir")


def main():
    cargar_productos()
    opcion = ""
    while opcion != "9":
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
            ordenar_productos()
        elif opcion == "7":
            mostrar_consultas()
        elif opcion == "8":
            guardar_productos()
        elif opcion == "9":
            guardar_productos()
            print("Fin del programa.")
        else:
            print("Opcion no valida.")


main()
