
public class OrdenamientosYBusquedas {

    private static int cmp(String a, String b) { return a.compareTo(b); }

    private static void swap(String[] arr, int i, int j) {
        if (i != j) { String tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp; }
    }

    public static void seleccion(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (cmp(arr[j], arr[minIdx]) < 0) minIdx = j;
            }
            swap(arr, i, minIdx);
        }
    }

    public static void insercion(String[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            String clave = arr[i];
            int j = i - 1;
            while (j >= 0 && cmp(arr[j], clave) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = clave;
        }
    }

    public static void burbuja(String[] arr) {
        int n = arr.length;
        boolean huboIntercambio;
        for (int i = 0; i < n - 1; i++) {
            huboIntercambio = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (cmp(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    huboIntercambio = true;
                }
            }
            if (!huboIntercambio) break;
        }
    }

    public static void mergeSort(String[] arr) {
        if (arr.length <= 1) return;
        String[] aux = new String[arr.length];
        mergeSortRec(arr, aux, 0, arr.length - 1);
    }

    private static void mergeSortRec(String[] arr, String[] aux, int izq, int der) {
        if (izq >= der) return;
        int mid = (izq + der) / 2;
        mergeSortRec(arr, aux, izq, mid);
        mergeSortRec(arr, aux, mid + 1, der);
        int i = izq, j = mid + 1, k = izq;
        while (i <= mid && j <= der) {
            if (cmp(arr[i], arr[j]) <= 0) aux[k++] = arr[i++];
            else aux[k++] = arr[j++];
        }
        while (i <= mid) aux[k++] = arr[i++];
        while (j <= der) aux[k++] = arr[j++];
        for (k = izq; k <= der; k++) arr[k] = aux[k];
    }

    public static void quickSort(String[] arr) { quickSortRec(arr, 0, arr.length - 1); }

    private static void quickSortRec(String[] arr, int izq, int der) {
        if (izq >= der) return;
        int i = izq, j = der;
        String pivote = arr[(izq + der) / 2]; 
        while (i <= j) {
            while (cmp(arr[i], pivote) < 0) i++;
            while (cmp(arr[j], pivote) > 0) j--;
            if (i <= j) { swap(arr, i, j); i++; j--; }
        }
        if (izq < j) quickSortRec(arr, izq, j);
        if (i < der) quickSortRec(arr, i, der);
    }

    public static int busquedaBinaria(String[] arr, String objetivo) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1; 
            int c = cmp(arr[mid], objetivo);
            if (c == 0) return mid;
            else if (c < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
