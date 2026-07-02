import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    static final Path DATA_DIR = Path.of("data");
    static final Path DATA_FILE = DATA_DIR.resolve("productos.csv");
    static final Scanner scanner = new Scanner(System.in);
    static final List<Producto> productos = new ArrayList<>();

    public static void main(String[] args) {
        cargarProductos();
        String opcion = "";
        while (!opcion.equals("9")) {
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
                ordenarProductos();
            } else if (opcion.equals("7")) {
                mostrarConsultas();
            } else if (opcion.equals("8")) {
                guardarProductos();
            } else if (opcion.equals("9")) {
                guardarProductos();
                System.out.println("Fin del programa.");
            } else {
                System.out.println("Opcion no valida.");
            }
        }
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("=== Market CLI U2 Java ===");
        System.out.println("1. Registrar");
        System.out.println("2. Listar");
        System.out.println("3. Buscar");
        System.out.println("4. Editar");
        System.out.println("5. Eliminar");
        System.out.println("6. Ordenar");
        System.out.println("7. Consultas");
        System.out.println("8. Guardar");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    static void registrarProducto() {
        System.out.println();
        System.out.println("Registrar producto");
        String codigo = leerTexto("Codigo: ");
        if (buscarIndicePorCodigo(codigo) != -1) {
            System.out.println("Ya existe un producto con ese codigo.");
            return;
        }

        Producto producto = new Producto(
                codigo,
                leerTexto("Nombre: "),
                leerTexto("Categoria: "),
                leerDecimal("Precio: ", 0.01),
                leerEntero("Stock: ", 0)
        );
        productos.add(producto);
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
        int indice = buscarIndicePorCodigo(codigo);
        if (indice == -1) {
            System.out.println("Producto no encontrado.");
        } else {
            listarProductos(List.of(productos.get(indice)));
        }
    }

    static void editarProducto() {
        String codigo = leerTexto("Codigo a editar: ");
        int indice = buscarIndicePorCodigo(codigo);
        if (indice == -1) {
            System.out.println("Producto no encontrado.");
            return;
        }

        Producto producto = productos.get(indice);
        System.out.println("Deje vacio para mantener el dato actual.");
        System.out.print("Nombre (" + producto.nombre + "): ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Categoria (" + producto.categoria + "): ");
        String categoria = scanner.nextLine().trim();
        System.out.print("Precio (" + producto.precio + "): ");
        String precio = scanner.nextLine().trim();
        System.out.print("Stock (" + producto.stock + "): ");
        String stock = scanner.nextLine().trim();

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
                }
            } catch (NumberFormatException e) {
                System.out.println("Precio no actualizado por ser invalido.");
            }
        }
        if (!stock.isEmpty()) {
            try {
                int nuevoStock = Integer.parseInt(stock);
                if (nuevoStock >= 0) {
                    producto.stock = nuevoStock;
                }
            } catch (NumberFormatException e) {
                System.out.println("Stock no actualizado por ser invalido.");
            }
        }
        System.out.println("Producto actualizado.");
    }

    static void eliminarProducto() {
        String codigo = leerTexto("Codigo a eliminar: ");
        int indice = buscarIndicePorCodigo(codigo);
        if (indice == -1) {
            System.out.println("Producto no encontrado.");
        } else {
            Producto eliminado = productos.remove(indice);
            System.out.println("Producto eliminado: " + eliminado.nombre);
        }
    }

    static void ordenarProductos() {
        System.out.println("1. Ordenar por nombre");
        System.out.println("2. Ordenar por stock");
        System.out.print("Seleccione: ");
        String opcion = scanner.nextLine().trim();
        if (opcion.equals("1")) {
            productos.sort(Comparator.comparing(producto -> producto.nombre.toLowerCase()));
            System.out.println("Productos ordenados por nombre.");
        } else if (opcion.equals("2")) {
            productos.sort(Comparator.comparingInt(producto -> producto.stock));
            System.out.println("Productos ordenados por stock.");
        } else {
            System.out.println("Opcion no valida.");
        }
    }

    static void mostrarConsultas() {
        if (productos.isEmpty()) {
            System.out.println("No hay datos para consultar.");
            return;
        }

        int totalProductos = productos.size();
        int totalStock = 0;
        double valorTotal = 0.0;
        int stockBajo = 0;
        for (Producto producto : productos) {
            totalStock += producto.stock;
            valorTotal += producto.valorInventario();
            if (producto.stock <= 5) {
                stockBajo++;
            }
        }

        System.out.println();
        System.out.println("Consultas");
        System.out.println("Total de productos: " + totalProductos);
        System.out.println("Stock total: " + totalStock);
        System.out.printf("Valor total de inventario: %.2f%n", valorTotal);
        System.out.println("Productos con stock bajo: " + stockBajo);
    }

    static int buscarIndicePorCodigo(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).codigo.equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    static void guardarProductos() {
        try {
            Files.createDirectories(DATA_DIR);
            try (BufferedWriter writer = Files.newBufferedWriter(DATA_FILE)) {
                writer.write("codigo,nombre,categoria,precio,stock");
                writer.newLine();
                for (Producto producto : productos) {
                    writer.write(producto.toCsv());
                    writer.newLine();
                }
            }
            System.out.println("Datos guardados en " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("No se pudo guardar: " + e.getMessage());
        }
    }

    static void cargarProductos() {
        productos.clear();
        if (!Files.exists(DATA_FILE)) {
            return;
        }
        try (BufferedReader reader = Files.newBufferedReader(DATA_FILE)) {
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
            System.out.println("No se pudo cargar datos: " + e.getMessage());
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
}
