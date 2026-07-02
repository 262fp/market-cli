import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    static final Path DATA_DIR = Path.of("data");
    static final Path REPORT_DIR = Path.of("reportes");
    static final Path PRODUCTOS_FILE = DATA_DIR.resolve("productos.csv");
    static final Path MOVIMIENTOS_FILE = DATA_DIR.resolve("movimientos.csv");
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static final Scanner scanner = new Scanner(System.in);
    static final List<Producto> productos = new ArrayList<>();
    static final List<Movimiento> movimientos = new ArrayList<>();

    public static void main(String[] args) {
        cargarDatos();
        String opcion = "";
        while (!opcion.equals("13")) {
            mostrarMenu();
            opcion = scanner.nextLine().trim();
            if (opcion.equals("1")) {
                registrarProducto();
            } else if (opcion.equals("2")) {
                listarProductos(productos);
            } else if (opcion.equals("3")) {
                buscarProducto();
            } else if (opcion.equals("4")) {
                editarProducto();
            } else if (opcion.equals("5")) {
                eliminarProducto();
            } else if (opcion.equals("6")) {
                registrarMovimiento();
            } else if (opcion.equals("7")) {
                listarMovimientos();
            } else if (opcion.equals("8")) {
                ordenarProductos();
            } else if (opcion.equals("9")) {
                mostrarConsultas();
            } else if (opcion.equals("10")) {
                exportarProductosCsv();
            } else if (opcion.equals("11")) {
                generarReporteTexto();
            } else if (opcion.equals("12")) {
                guardarDatos();
            } else if (opcion.equals("13")) {
                guardarDatos();
                System.out.println("Fin del programa.");
            } else {
                System.out.println("Opcion no valida.");
            }
        }
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("=== Market CLI Final Java ===");
        System.out.println("1. Registrar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar producto");
        System.out.println("4. Editar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Registrar movimiento");
        System.out.println("7. Listar movimientos");
        System.out.println("8. Ordenar productos");
        System.out.println("9. Consultas y resumen");
        System.out.println("10. Exportar CSV");
        System.out.println("11. Generar reporte de texto");
        System.out.println("12. Guardar");
        System.out.println("13. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    static void registrarProducto() {
        System.out.println();
        System.out.println("Registrar producto");
        String codigo = leerTexto("Codigo: ");
        if (buscarProductoPorCodigo(codigo) != null) {
            System.out.println("Ya existe un producto con ese codigo.");
            return;
        }

        productos.add(new Producto(
                codigo,
                leerTexto("Nombre: "),
                leerTexto("Categoria: "),
                leerDecimal("Precio: ", 0.01),
                leerEntero("Stock inicial: ", 0)
        ));
        System.out.println("Producto registrado.");
    }

    static void listarProductos(List<Producto> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println();
        System.out.println("Codigo | Nombre | Categoria | Precio | Stock | Valor");
        System.out.println("-".repeat(70));
        for (Producto producto : lista) {
            System.out.printf(
                    "%s | %s | %s | %.2f | %d | %.2f%n",
                    producto.codigo,
                    producto.nombre,
                    producto.categoria,
                    producto.precio,
                    producto.stock,
                    producto.valorInventario()
            );
        }
    }

    static void buscarProducto() {
        String codigo = leerTexto("Codigo a buscar: ");
        Producto producto = buscarProductoPorCodigo(codigo);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
        } else {
            listarProductos(List.of(producto));
        }
    }

    static void editarProducto() {
        String codigo = leerTexto("Codigo a editar: ");
        Producto producto = buscarProductoPorCodigo(codigo);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Deje vacio para mantener el dato actual.");
        System.out.print("Nombre (" + producto.nombre + "): ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Categoria (" + producto.categoria + "): ");
        String categoria = scanner.nextLine().trim();
        System.out.print("Precio (" + producto.precio + "): ");
        String precio = scanner.nextLine().trim();

        if (!nombre.isEmpty()) {
            producto.nombre = nombre;
        }
        if (!categoria.isEmpty()) {
            producto.categoria = categoria;
        }
        if (!precio.isEmpty()) {
            try {
                double nuevoPrecio = Double.parseDouble(precio);
                if (nuevoPrecio > 0) {
                    producto.precio = nuevoPrecio;
                } else {
                    System.out.println("Precio no actualizado por ser menor o igual que cero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Precio no actualizado por ser invalido.");
            }
        }
        System.out.println("Producto actualizado.");
    }

    static void eliminarProducto() {
        String codigo = leerTexto("Codigo a eliminar: ");
        Producto producto = buscarProductoPorCodigo(codigo);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        if (producto.stock > 0) {
            System.out.println("No se elimina un producto con stock. Registre salidas primero.");
            return;
        }
        productos.remove(producto);
        System.out.println("Producto eliminado.");
    }

    static void registrarMovimiento() {
        String codigo = leerTexto("Codigo del producto: ");
        Producto producto = buscarProductoPorCodigo(codigo);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("1. Entrada");
        System.out.println("2. Salida");
        System.out.print("Tipo de movimiento: ");
        String opcion = scanner.nextLine().trim();
        String tipo;
        if (opcion.equals("1")) {
            tipo = "ENTRADA";
        } else if (opcion.equals("2")) {
            tipo = "SALIDA";
        } else {
            System.out.println("Tipo no valido.");
            return;
        }

        int cantidad = leerEntero("Cantidad: ", 1);
        if (tipo.equals("SALIDA") && cantidad > producto.stock) {
            System.out.println("No hay stock suficiente para la salida.");
            return;
        }

        if (tipo.equals("ENTRADA")) {
            producto.stock += cantidad;
        } else {
            producto.stock -= cantidad;
        }

        movimientos.add(new Movimiento(
                LocalDateTime.now().format(FORMATO_FECHA),
                codigo,
                tipo,
                cantidad
        ));
        System.out.println("Movimiento registrado.");
    }

    static void listarMovimientos() {
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados.");
            return;
        }

        System.out.println();
        System.out.println("Fecha | Codigo | Tipo | Cantidad");
        System.out.println("-".repeat(60));
        for (Movimiento movimiento : movimientos) {
            System.out.printf(
                    "%s | %s | %s | %d%n",
                    movimiento.fecha,
                    movimiento.codigo,
                    movimiento.tipo,
                    movimiento.cantidad
            );
        }
    }

    static void ordenarProductos() {
        System.out.println("1. Nombre");
        System.out.println("2. Categoria");
        System.out.println("3. Stock");
        System.out.print("Ordenar por: ");
        String opcion = scanner.nextLine().trim();
        if (opcion.equals("1")) {
            productos.sort(Comparator.comparing(producto -> producto.nombre.toLowerCase()));
        } else if (opcion.equals("2")) {
            productos.sort(Comparator.comparing(producto -> producto.categoria.toLowerCase()));
        } else if (opcion.equals("3")) {
            productos.sort(Comparator.comparingInt(producto -> producto.stock));
        } else {
            System.out.println("Opcion no valida.");
            return;
        }
        System.out.println("Productos ordenados.");
    }

    static void mostrarConsultas() {
        if (productos.isEmpty()) {
            System.out.println("No hay datos para consultar.");
            return;
        }

        Object[][] matriz = matrizResumenCategorias();
        int stockTotal = 0;
        double valorTotal = 0.0;
        int stockBajo = 0;
        for (Producto producto : productos) {
            stockTotal += producto.stock;
            valorTotal += producto.valorInventario();
            if (producto.stock <= 5) {
                stockBajo++;
            }
        }

        System.out.println();
        System.out.println("Resumen general");
        System.out.println("Productos registrados: " + productos.size());
        System.out.println("Stock total: " + stockTotal);
        System.out.printf("Valor total: %.2f%n", valorTotal);
        System.out.println("Productos con stock bajo: " + stockBajo);

        System.out.println();
        System.out.println("Resumen por categoria");
        System.out.println("Categoria | Productos | Stock | Valor");
        System.out.println("-".repeat(55));
        for (Object[] fila : matriz) {
            System.out.printf("%s | %d | %d | %.2f%n", fila[0], fila[1], fila[2], fila[3]);
        }
    }

    static Map<String, Integer> construirIndiceProductos() {
        Map<String, Integer> indice = new HashMap<>();
        for (int i = 0; i < productos.size(); i++) {
            indice.put(productos.get(i).codigo, i);
        }
        return indice;
    }

    static Producto buscarProductoPorCodigo(String codigo) {
        Map<String, Integer> indice = construirIndiceProductos();
        Integer posicion = indice.get(codigo);
        if (posicion == null) {
            return null;
        }
        return productos.get(posicion);
    }

    static Object[][] matrizResumenCategorias() {
        Map<String, ResumenCategoria> resumen = new HashMap<>();
        for (Producto producto : productos) {
            ResumenCategoria datos = resumen.getOrDefault(producto.categoria, new ResumenCategoria());
            datos.cantidadProductos++;
            datos.stockTotal += producto.stock;
            datos.valorTotal += producto.valorInventario();
            resumen.put(producto.categoria, datos);
        }

        Object[][] matriz = new Object[resumen.size()][4];
        int fila = 0;
        for (Map.Entry<String, ResumenCategoria> entry : resumen.entrySet()) {
            matriz[fila][0] = entry.getKey();
            matriz[fila][1] = entry.getValue().cantidadProductos;
            matriz[fila][2] = entry.getValue().stockTotal;
            matriz[fila][3] = entry.getValue().valorTotal;
            fila++;
        }
        return matriz;
    }

    static void guardarDatos() {
        try {
            Files.createDirectories(DATA_DIR);
            try (BufferedWriter writer = Files.newBufferedWriter(PRODUCTOS_FILE)) {
                writer.write("codigo,nombre,categoria,precio,stock");
                writer.newLine();
                for (Producto producto : productos) {
                    writer.write(producto.toCsv());
                    writer.newLine();
                }
            }

            try (BufferedWriter writer = Files.newBufferedWriter(MOVIMIENTOS_FILE)) {
                writer.write("fecha,codigo,tipo,cantidad");
                writer.newLine();
                for (Movimiento movimiento : movimientos) {
                    writer.write(movimiento.toCsv());
                    writer.newLine();
                }
            }
            System.out.println("Datos guardados.");
        } catch (IOException e) {
            System.out.println("No se pudo guardar: " + e.getMessage());
        }
    }

    static void cargarDatos() {
        productos.clear();
        movimientos.clear();
        cargarProductos();
        cargarMovimientos();
    }

    static void cargarProductos() {
        if (!Files.exists(PRODUCTOS_FILE)) {
            return;
        }
        try (BufferedReader reader = Files.newBufferedReader(PRODUCTOS_FILE)) {
            reader.readLine();
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",", -1);
                if (partes.length == 5) {
                    productos.add(new Producto(
                            partes[0],
                            partes[1],
                            partes[2],
                            Double.parseDouble(partes[3]),
                            Integer.parseInt(partes[4])
                    ));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("No se pudo cargar productos: " + e.getMessage());
        }
    }

    static void cargarMovimientos() {
        if (!Files.exists(MOVIMIENTOS_FILE)) {
            return;
        }
        try (BufferedReader reader = Files.newBufferedReader(MOVIMIENTOS_FILE)) {
            reader.readLine();
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",", -1);
                if (partes.length == 4) {
                    movimientos.add(new Movimiento(
                            partes[0],
                            partes[1],
                            partes[2],
                            Integer.parseInt(partes[3])
                    ));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("No se pudo cargar movimientos: " + e.getMessage());
        }
    }

    static void exportarProductosCsv() {
        try {
            Files.createDirectories(REPORT_DIR);
            Path destino = REPORT_DIR.resolve("productos_exportados.csv");
            try (BufferedWriter writer = Files.newBufferedWriter(destino)) {
                writer.write("codigo,nombre,categoria,precio,stock,valor");
                writer.newLine();
                for (Producto producto : productos) {
                    writer.write(producto.codigo + "," + producto.nombre + "," + producto.categoria + ","
                            + producto.precio + "," + producto.stock + "," + producto.valorInventario());
                    writer.newLine();
                }
            }
            System.out.println("CSV exportado en " + destino);
        } catch (IOException e) {
            System.out.println("No se pudo exportar: " + e.getMessage());
        }
    }

    static void generarReporteTexto() {
        try {
            Files.createDirectories(REPORT_DIR);
            Path destino = REPORT_DIR.resolve("reporte_inventario.txt");
            Object[][] matriz = matrizResumenCategorias();
            try (BufferedWriter writer = Files.newBufferedWriter(destino)) {
                writer.write("REPORTE DE INVENTARIO");
                writer.newLine();
                writer.write("=====================");
                writer.newLine();
                writer.newLine();
                writer.write("Productos registrados: " + productos.size());
                writer.newLine();
                writer.write("Movimientos registrados: " + movimientos.size());
                writer.newLine();
                writer.newLine();
                writer.write("Resumen por categoria");
                writer.newLine();
                for (Object[] fila : matriz) {
                    writer.write("- " + fila[0] + ": productos=" + fila[1] + ", stock=" + fila[2]
                            + ", valor=" + String.format("%.2f", fila[3]));
                    writer.newLine();
                }
            }
            System.out.println("Reporte generado en " + destino);
        } catch (IOException e) {
            System.out.println("No se pudo generar reporte: " + e.getMessage());
        }
    }

    static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("El dato no puede estar vacio.");
        }
    }

    static int leerEntero(String mensaje, int minimo) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= minimo) {
                    return valor;
                }
                System.out.println("El valor minimo es " + minimo);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    static double leerDecimal(String mensaje, double minimo) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim());
                if (valor >= minimo) {
                    return valor;
                }
                System.out.println("El valor minimo es " + minimo);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero decimal valido.");
            }
        }
    }

    static class Producto {
        String codigo;
        String nombre;
        String categoria;
        double precio;
        int stock;

        Producto(String codigo, String nombre, String categoria, double precio, int stock) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.categoria = categoria;
            this.precio = precio;
            this.stock = stock;
        }

        double valorInventario() {
            return precio * stock;
        }

        String toCsv() {
            return codigo + "," + nombre + "," + categoria + "," + precio + "," + stock;
        }
    }

    static class Movimiento {
        String fecha;
        String codigo;
        String tipo;
        int cantidad;

        Movimiento(String fecha, String codigo, String tipo, int cantidad) {
            this.fecha = fecha;
            this.codigo = codigo;
            this.tipo = tipo;
            this.cantidad = cantidad;
        }

        String toCsv() {
            return fecha + "," + codigo + "," + tipo + "," + cantidad;
        }
    }

    static class ResumenCategoria {
        int cantidadProductos;
        int stockTotal;
        double valorTotal;
    }
}
