# market-cli-u2

Producto Java de la Unidad 2 de Fundamentos de Programacion.

Esta version evoluciona la base de U1 hacia una aplicacion CLI con CRUD modular, listas, busqueda, ordenacion, persistencia en archivos y consultas basicas.

## Producto U2

Aplicacion CLI con CRUD modular, memoria, archivos basicos y consultas.

## Capacidades incluidas

- Registro de varios productos usando `ArrayList`.
- Menu interactivo con `while`.
- Metodos con parametros y retorno.
- CRUD: registrar, listar, buscar, editar y eliminar.
- Busqueda lineal por codigo.
- Ordenacion por nombre o stock.
- Guardado y carga desde archivo CSV.
- Consultas y agregaciones basicas.

## Requisito

Java 21 o superior.

## Ejecutar

```bash
javac App.java
java App
```

Los datos se guardan en:

```text
data/productos.csv
```

## Flujo sugerido de demo

1. Registrar tres productos.
2. Listar productos.
3. Buscar un producto por codigo.
4. Editar precio o stock.
5. Ordenar por nombre o stock.
6. Guardar, salir y volver a ejecutar para comprobar persistencia.
