import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static boolean productoRegistrado = false;
    static String codigo = "";
    static String nombre = "";
    static String categoria = "";
    static double precio = 0.0;
    static int stock = 0;

    public static void main(String[] args) {
        String opcion = "";
        while (!opcion.equals("4")) {
            mostrarMenu();
            opcion = scanner.nextLine().trim();

            if (opcion.equals("1")) {
                registrarProducto();
            } else if (opcion.equals("2")) {
                mostrarProducto();
            } else if (opcion.equals("3")) {
                evaluarStock();
            } else if (opcion.equals("4")) {
                System.out.println("Fin del programa.");
            } else {
                System.out.println("Opcion no valida.");
            }
        }
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("=== Market CLI U1 Java ===");
        System.out.println("1. Registrar producto");
        System.out.println("2. Mostrar producto");
        System.out.println("3. Evaluar stock");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    static void registrarProducto() {
        System.out.println();
        System.out.println("Registro de producto");
        String nuevoCodigo = leerTexto("Codigo: ");
        String nuevoNombre = leerTexto("Nombre: ");
        String nuevaCategoria = leerTexto("Categoria: ");
        double nuevoPrecio = leerDecimal("Precio: ");
        int nuevoStock = leerEntero("Stock: ");

        if (nuevoCodigo.isEmpty() || nuevoNombre.isEmpty() || nuevaCategoria.isEmpty()) {
            System.out.println("No se registro el producto porque faltan datos.");
        } else if (nuevoPrecio <= 0) {
            System.out.println("No se registro el producto porque el precio debe ser mayor que cero.");
        } else if (nuevoStock < 0) {
            System.out.println("No se registro el producto porque el stock no puede ser negativo.");
        } else {
            codigo = nuevoCodigo;
            nombre = nuevoNombre;
            categoria = nuevaCategoria;
            precio = nuevoPrecio;
            stock = nuevoStock;
            productoRegistrado = true;
            System.out.println("Producto registrado correctamente.");
        }
    }

    static void mostrarProducto() {
        if (!productoRegistrado) {
            System.out.println("Todavia no hay un producto registrado.");
            return;
        }

        double valorInventario = precio * stock;
        System.out.println();
        System.out.println("Producto registrado");
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Categoria: " + categoria);
        System.out.printf("Precio: %.2f%n", precio);
        System.out.println("Stock: " + stock);
        System.out.printf("Valor de inventario: %.2f%n", valorInventario);
    }

    static void evaluarStock() {
        if (!productoRegistrado) {
            System.out.println("Registre un producto antes de evaluar stock.");
            return;
        }

        String estado;
        if (stock == 0) {
            estado = "SIN STOCK";
        } else if (stock <= 5) {
            estado = "STOCK BAJO";
        } else {
            estado = "STOCK DISPONIBLE";
        }
        System.out.println("Estado del producto: " + estado);
    }

    static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        String valor = scanner.nextLine().trim();
        if (valor.isEmpty()) {
            System.out.println("El dato no puede estar vacio.");
        }
        return valor;
    }

    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero entero valido.");
            return -1;
        }
    }

    static double leerDecimal(String mensaje) {
        System.out.print(mensaje);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero decimal valido.");
            return -1;
        }
    }
}
