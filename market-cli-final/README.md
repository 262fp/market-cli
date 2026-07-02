# market-cli-final

Producto final Java del curso de Fundamentos de Programacion.

Esta version consolida U1 y U2 e incorpora procesamiento algoritmico avanzado con mapas, matrices, reportes y exportacion simple a archivos.

## Producto final

Aplicacion CLI completa para la gestion de inventario basico con productos, movimientos, persistencia, consultas y reportes.

## Capacidades incluidas

- CRUD persistente de productos.
- Registro de movimientos de entrada y salida de stock.
- Validaciones de datos y reglas de negocio.
- Busqueda y ordenacion.
- `HashMap` para indices y agregaciones por categoria.
- Matriz para resumen tabular de categorias.
- Reporte por terminal.
- Exportacion CSV.
- Reporte de texto imprimible.

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
data/movimientos.csv
reportes/reporte_inventario.txt
reportes/productos_exportados.csv
```

## Flujo sugerido de demo

1. Registrar productos.
2. Registrar entradas y salidas de stock.
3. Buscar y ordenar productos.
4. Mostrar consultas por categoria.
5. Generar reporte por terminal.
6. Exportar CSV y reporte de texto.
7. Cerrar y volver a ejecutar para comprobar persistencia.
