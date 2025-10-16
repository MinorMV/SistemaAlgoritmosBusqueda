import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Algoritmos de ordenamiento y búsqueda (Strings) ===");
        int n = leerEntero("Tamaño del arreglo (1..2000): ", 1, 2000);

        String[] datos = new String[n];
        System.out.println("\nIngresa " + n + " palabras (una por línea):");
        for (int i = 0; i < n; i++) datos[i] = leerNoVacio("[" + i + "] = ");

        boolean ordenado = false;

        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1) Ordenamiento por Selección   (O(n^2))");
            System.out.println("2) Ordenamiento por Inserción   (O(n^2), mejor caso O(n))");
            System.out.println("3) Ordenamiento por Burbuja     (O(n^2), mejor caso O(n))");
            System.out.println("4) Ordenamiento por Mezcla      (Merge Sort, O(n log n))");
            System.out.println("5) Ordenamiento Rápido          (Quick Sort, prom. O(n log n), peor O(n^2))");
            System.out.println("6) Búsqueda Binaria (requiere arreglo ordenado)");
            System.out.println("7) Ver arreglo actual");
            System.out.println("8) Volver a llenar arreglo");
            System.out.println("9) Salir");

            int op = leerEntero("Elige una opción: ", 1, 9);
            if (op == 9) { System.out.println("¡Listo!"); break; }

            switch (op) {
                case 1 -> { OrdenamientosYBusquedas.seleccion(datos); ordenado = true; mostrar("Selección", datos); }
                case 2 -> { OrdenamientosYBusquedas.insercion(datos); ordenado = true; mostrar("Inserción", datos); }
                case 3 -> { OrdenamientosYBusquedas.burbuja(datos);   ordenado = true; mostrar("Burbuja", datos); }
                case 4 -> { OrdenamientosYBusquedas.mergeSort(datos); ordenado = true; mostrar("Mezcla (Merge Sort)", datos); }
                case 5 -> { OrdenamientosYBusquedas.quickSort(datos); ordenado = true; mostrar("Rápido (Quick Sort)", datos); }
                case 6 -> {
                    if (!ordenado) { System.out.println("Primero ordena el arreglo para usar búsqueda binaria."); break; }
                    String objetivo = leerNoVacio("Palabra a buscar: ");
                    int idx = OrdenamientosYBusquedas.busquedaBinaria(datos, objetivo);
                    System.out.println(idx >= 0 ? "Encontrada en índice: " + idx : "No se encontró.");
                }
                case 7 -> { System.out.println("Arreglo actual:"); System.out.println(Arrays.toString(datos)); }
                case 8 -> {
                    System.out.println("Vuelve a ingresar " + n + " palabras:");
                    for (int i = 0; i < n; i++) datos[i] = leerNoVacio("[" + i + "] = ");
                    ordenado = false;
                }
            }
        }
    }

    private static int leerEntero(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v < min || v > max) {
                    System.out.println("Valor fuera de rango [" + min + ", " + max + "]. Intenta de nuevo.");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número entero.");
            }
        }
    }

    private static String leerNoVacio(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (s != null && !s.trim().isEmpty()) return s.trim();
            System.out.println("La cadena no puede estar vacía. Intenta de nuevo.");
        }
    }

    private static void mostrar(String nombre, String[] arr) {
        System.out.println("Ordenado por " + nombre + ":");
        System.out.println(Arrays.toString(arr));
    }
}
