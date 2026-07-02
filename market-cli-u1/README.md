# market-cli-u1

Producto Java de la Unidad 1 de Fundamentos de Programacion.

Esta version muestra la base de una aplicacion CLI para gestionar un producto de inventario. Trabaja con entrada y salida de datos, operadores, decisiones, validaciones iniciales y un menu basico.

## Producto U1

Menu basico de gestion CLI con registro, calculos y validaciones iniciales.

## Capacidades incluidas

- Captura de datos de un producto.
- Calculo del valor de inventario.
- Validaciones con `if`, `if-else` e `if-else-if`.
- Menu basico con opciones.
- Consulta simple del producto registrado.

## Requisito

Java 21 o superior.

## Ejecutar

```bash
javac App.java
java App
```

## Generar ejecutable nativo opcional

Este paso es opcional y requiere **GraalVM con Native Image** instalado. En Windows, el comando suele estar disponible como `native-image.cmd`.

Compilar primero:

```powershell
javac App.java
```

Generar el ejecutable:

```powershell
native-image.cmd -cp . App market-cli-u1
```

Ejecutar:

```powershell
.\market-cli-u1.exe
```

Si `native-image.cmd` no se reconoce, verifica:

```powershell
java -version
native-image.cmd --version
```

Debe estar activo GraalVM y su carpeta `bin` debe estar en el `Path`.

## Flujo sugerido de demo

1. Registrar un producto.
2. Intentar registrar datos invalidos.
3. Consultar el producto registrado.
4. Ver el estado del stock.
5. Salir del programa.
