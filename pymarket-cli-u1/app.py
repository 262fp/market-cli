producto_registrado = False
codigo = ""
nombre = ""
categoria = ""
precio = 0.0
stock = 0


def leer_texto(mensaje):
    valor = input(mensaje).strip()
    if valor == "":
        print("El dato no puede estar vacio.")
    return valor


def leer_entero(mensaje):
    texto = input(mensaje).strip()
    if texto.isdigit():
        return int(texto)
    print("Debe ingresar un numero entero valido.")
    return -1


def leer_decimal(mensaje):
    texto = input(mensaje).strip()
    try:
        valor = float(texto)
        return valor
    except ValueError:
        print("Debe ingresar un numero decimal valido.")
        return -1


def registrar_producto():
    global producto_registrado, codigo, nombre, categoria, precio, stock

    print("\nRegistro de producto")
    nuevo_codigo = leer_texto("Codigo: ")
    nuevo_nombre = leer_texto("Nombre: ")
    nueva_categoria = leer_texto("Categoria: ")
    nuevo_precio = leer_decimal("Precio: ")
    nuevo_stock = leer_entero("Stock: ")

    if nuevo_codigo == "" or nuevo_nombre == "" or nueva_categoria == "":
        print("No se registro el producto porque faltan datos.")
    elif nuevo_precio <= 0:
        print("No se registro el producto porque el precio debe ser mayor que cero.")
    elif nuevo_stock < 0:
        print("No se registro el producto porque el stock no puede ser negativo.")
    else:
        codigo = nuevo_codigo
        nombre = nuevo_nombre
        categoria = nueva_categoria
        precio = nuevo_precio
        stock = nuevo_stock
        producto_registrado = True
        print("Producto registrado correctamente.")


def mostrar_producto():
    if producto_registrado:
        valor_inventario = precio * stock
        print("\nProducto registrado")
        print("Codigo:", codigo)
        print("Nombre:", nombre)
        print("Categoria:", categoria)
        print("Precio:", precio)
        print("Stock:", stock)
        print("Valor de inventario:", valor_inventario)
    else:
        print("Todavia no hay un producto registrado.")


def evaluar_stock():
    if producto_registrado:
        if stock == 0:
            estado = "SIN STOCK"
        elif stock <= 5:
            estado = "STOCK BAJO"
        else:
            estado = "STOCK DISPONIBLE"
        print("Estado del producto:", estado)
    else:
        print("Registre un producto antes de evaluar stock.")


def mostrar_menu():
    print("\n=== Market CLI U1 ===")
    print("1. Registrar producto")
    print("2. Mostrar producto")
    print("3. Evaluar stock")
    print("4. Salir")


opcion = ""
while opcion != "4":
    mostrar_menu()
    opcion = input("Seleccione una opcion: ").strip()

    if opcion == "1":
        registrar_producto()
    elif opcion == "2":
        mostrar_producto()
    elif opcion == "3":
        evaluar_stock()
    elif opcion == "4":
        print("Fin del programa.")
    else:
        print("Opcion no valida.")
