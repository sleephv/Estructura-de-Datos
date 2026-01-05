import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Dataset {
    ArrayList<Game> data;
    String sortedByAttribute;
    public Dataset(ArrayList<Game> data) {
        this.data = data;
        this.sortedByAttribute = "nada";
    }
    public ArrayList<Game> getGamesByPrice(int precio) {
        ArrayList<Game> result = new ArrayList<>();
        if (sortedByAttribute.equals("precio")) {
            long startBinaryTime = System.nanoTime();
            int left = 0, right = data.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midPrice = data.get(mid).getPrecio();
                if (midPrice == precio) {
                    int index = mid;
                    while (index >= 0 && data.get(index).getPrecio() == precio) {
                        result.add(data.get(index));
                        index--;
                    }
                    index = mid + 1;
                    while (index < data.size() && data.get(index).getPrecio() == precio) {
                        result.add(data.get(index));
                        index++;
                    }
                    break;
                }
                else if (midPrice < precio) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
            long endBinaryTime = System.nanoTime();
            long binaryDuration = (endBinaryTime - startBinaryTime) / 1_000_000;
            System.out.println("Busqueda binaria tomo " + binaryDuration + " ms.");
        } else {
            long startLinearTime = System.nanoTime();
            for (Game game : data) {
                if (game.getPrecio() == precio) {
                    result.add(game);
                }
            }
            long endLinearTime = System.nanoTime();
            long linearDuration = (endLinearTime - startLinearTime) / 1_000_000;
            System.out.println("Busqueda lineal tomo " + linearDuration + " ms.");
        }
        if(result.isEmpty()) {
            System.out.println("No se encontraron juegos con ese precio");
        }
        else {
            System.out.println("Juegos encontrados:");
            for (Game game : result) {
                System.out.println("Nombre: " + game.getNombre() + ", Categoria: " + game.getCategoria() +
                        ", Precio: " + game.getPrecio() + ", Calidad: " + game.getCalidad());
            }
        }
        return result;
    }
    public void getGameByPriceRange(int MinPrecio, int MaxPrecio) {
        if (sortedByAttribute.equals("precio")) {
            long startBinaryTime = System.nanoTime();
            int left = 0, right = data.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midPrice = data.get(mid).getPrecio();
                if (midPrice < MinPrecio) {
                    left = mid + 1;
                } else if (midPrice > MaxPrecio) {
                    right = mid - 1;
                } else {
                    right = mid - 1;
                }
            }
            while (left < data.size() && data.get(left).getPrecio() < MinPrecio) {
                left++;
            }
            long endBinaryTime = System.nanoTime();
            long binaryDuration = (endBinaryTime - startBinaryTime) / 1_000_000;
            System.out.println("Busqueda binaria tomo " + binaryDuration + " ms.");
            if (left >= data.size() || data.get(left).getPrecio() > MaxPrecio) {
                System.out.println("No se encontraron juegos en ese rango de precio.");
                return;
            }
            boolean found = false;
            for (int i = left; i < data.size() && data.get(i).getPrecio() <= MaxPrecio; i++) {
                System.out.println("Nombre: " + data.get(i).getNombre() + ", Categoria: " + data.get(i).getCategoria() +
                        ", Precio: " + data.get(i).getPrecio() + ", Calidad: " + data.get(i).getCalidad());
                found = true;
            }
            if (!found) {
                System.out.println("No se encontraron juegos en ese rango de precio.");
            }
        }
        else {
            long startLinearTime = System.nanoTime();
            boolean found = false;
            for (Game game : data) {
                int price = game.getPrecio();
                if (price >= MinPrecio && price <= MaxPrecio) {
                    System.out.println("Nombre: " + game.getNombre() + ", Categoria: " + game.getCategoria() +
                            ", Precio: " + game.getPrecio() + ", Calidad: " + game.getCalidad());
                    found = true;
                }
            }
            long endLinearTime = System.nanoTime();
            long linearDuration = (endLinearTime - startLinearTime) / 1_000_000;
            System.out.println("Busqueda lineal tomo " + linearDuration + " ms.");
            if (!found) {
                System.out.println("No se encontraron juegos en ese rango de precio.");
            }
        }
    }
    public ArrayList<Game> getGameByCategory(String categoria) {
        ArrayList<Game> result = new ArrayList<>();
        if (sortedByAttribute.equals("categoria")) {
            long startBinaryTime = System.nanoTime();
            int left = 0, right = data.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                String midCategory = data.get(mid).getCategoria();
                if (midCategory.equals(categoria)) {
                    int index = mid;
                    while (index >= 0 && data.get(index).getCategoria().equals(categoria)) {
                        result.add(data.get(index));
                        index--;
                    }
                    index = mid + 1;
                    while (index < data.size() && data.get(index).getCategoria().equals(categoria)) {
                        result.add(data.get(index));
                        index++;
                    }
                    break;
                } else if (midCategory.compareTo(categoria) < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            long endBinaryTime = System.nanoTime();
            long binaryDuration = (endBinaryTime - startBinaryTime) / 1_000_000;
            System.out.println("Busqueda binaria tomo " + binaryDuration + " ms.");
        } else {
            long startLinearTime = System.nanoTime();
            for (Game game : data) {
                if (game.getCategoria().equals(categoria)) {
                    result.add(game);
                }
            }
            long endLinearTime = System.nanoTime();
            long linearDuration = (endLinearTime - startLinearTime) / 1_000_000;
            System.out.println("Busqueda lineal tomo " + linearDuration + " ms.");
        }
        if(result.isEmpty()) {
            System.out.println("No se encontraron juegos con esa categoria");
        }
        else {
            System.out.println("Juegos encontrados:");
            for (Game game : result) {
                System.out.println("Nombre: " + game.getNombre() + ", Categoria: " + game.getCategoria() +
                        ", Precio: " + game.getPrecio() + ", Calidad: " + game.getCalidad());
            }
        }
        return result;
    }
    public ArrayList<Game> getGameByQuality(int calidad) {
        ArrayList<Game> result = new ArrayList<>();
        if (sortedByAttribute.equals("calidad")) {
            long startBinaryTime = System.nanoTime();
            int left = 0, right = data.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midQuality = data.get(mid).getCalidad();
                if (midQuality == calidad) {
                    int index = mid;
                    while (index >= 0 && data.get(index).getCalidad() == calidad) {
                        result.add(data.get(index));
                        index--;
                    }
                    index = mid + 1;
                    while (index < data.size() && data.get(index).getCalidad() == calidad) {
                        result.add(data.get(index));
                        index++;
                    }
                    break;
                }
                else if (midQuality < calidad) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
            long endBinaryTime = System.nanoTime();
            long binaryDuration = (endBinaryTime - startBinaryTime) / 1_000_000;
            System.out.println("Busqueda binaria tomo " + binaryDuration + " ms.");
        }
        else {
            long startLinearTime = System.nanoTime();
            for (Game game : data) {
                if (game.getCalidad() == calidad) {
                    result.add(game);
                }
            }
            long endLinearTime = System.nanoTime();
            long linearDuration = (endLinearTime - startLinearTime) / 1_000_000;
            System.out.println("Busqueda lineal tomo " + linearDuration + " ms.");
        }
        if(result.isEmpty()) {
            System.out.println("No se encontraron juegos con esa calidad");
        }
        else {
            System.out.println("Juegos encontrados:");
            for (Game game : result) {
                System.out.println("Nombre: " + game.getNombre() + ", Categoria: " + game.getCategoria() +
                        ", Precio: " + game.getPrecio() + ", Calidad: " + game.getCalidad());
            }
        }
        return result;
    }
    void sortByAlgorithm(String algoritmo, String atributo) {
        Comparator<Game> comparator;
        switch (atributo) {
            case "precio":
                comparator = Comparator.comparingInt(Game::getPrecio);
                break;
            case "categoria":
                comparator = Comparator.comparing(Game::getCategoria);
                break;
            case "calidad":
                comparator = Comparator.comparingInt(Game::getCalidad);
                break;
            default:
                System.out.println("Algoritmo no encontrado, se ocupara 'precio' como default ");
                comparator = Comparator.comparingInt(Game::getPrecio);
                atributo = "precio";
        }
        sortedByAttribute = atributo;
        switch (algoritmo) {
            case "bubbleSort":
                long startTimeBubble = System.nanoTime();
                bubbleSort(comparator);
                System.out.println("Bubble Sort tomo " + measureExecutionTime(startTimeBubble) + " ms.");
                break;
            case "insertionSort":
                long startTimeInsertion = System.nanoTime();
                insertionSort(comparator);
                System.out.println("Insertion Sort tomo " + measureExecutionTime(startTimeInsertion) + " ms.");
                break;
            case "selectionSort":
                long startTimeSelection = System.nanoTime();
                selectionSort(comparator);
                System.out.println("Selection Sort tomo " + measureExecutionTime(startTimeSelection) + " ms.");
                break;
            case "mergeSort":
                long startTimeMerge = System.nanoTime();
                mergeSort(comparator, 0, data.size() - 1);
                System.out.println("Merge Sort tomo " + measureExecutionTime(startTimeMerge) + " ms.");
                break;
            case "quickSort":
                long startTimeQuick = System.nanoTime();
                quickSort(comparator, 0, data.size() - 1);
                System.out.println("Quick Sort tomo " + measureExecutionTime(startTimeQuick) + " ms.");
                break;
            case "countingSort":
                long startTimeCounting = System.nanoTime();
                countingSort(comparator);
                System.out.println("Counting Sort tomo " + measureExecutionTime(startTimeCounting) + " ms.");
                break;
            default:
                System.out.println("Algoritmo no reconocido. Se usara Collections.sort().");
                long startTimeCollections = System.nanoTime();
                Collections.sort(data, comparator);
                System.out.println("Collections Sort tomo " + measureExecutionTime(startTimeCollections) + " ms.");
        }
    }
    private long measureExecutionTime(long startTime) {
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }
    private void bubbleSort(Comparator<Game> comparator) {
        int n = data.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(data.get(j), data.get(j + 1)) > 0) {
                    Game temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
    private void selectionSort(Comparator<Game> comparator) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(data.get(j), data.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            Game temp = data.get(i);
            data.set(i, data.get(minIndex));
            data.set(minIndex, temp);
        }
    }
    private void insertionSort(Comparator<Game> comparator) {
        for (int i = 1; i < data.size(); i++) {
            Game key = data.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(data.get(j), key) > 0) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
    }
    private void mergeSort(Comparator<Game> comparator, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(comparator, left, mid);
            mergeSort(comparator, mid + 1, right);
            merge(comparator, left, mid, right);
        }
    }
    private void merge(Comparator<Game> comparator, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        ArrayList<Game> leftList = new ArrayList<>(n1);
        ArrayList<Game> rightList = new ArrayList<>(n2);
        for (int i = 0; i < n1; i++)
            leftList.add(data.get(left + i));
        for (int j = 0; j < n2; j++)
            rightList.add(data.get(mid + 1 + j));
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0) {
                data.set(k, leftList.get(i));
                i++;
            } else {
                data.set(k, rightList.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            data.set(k, leftList.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            data.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
    private void quickSort(Comparator<Game> comparator, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(comparator, left, right);
            quickSort(comparator, left, pivotIndex - 1);
            quickSort(comparator, pivotIndex + 1, right);
        }
    }
    private int partition(Comparator<Game> comparator, int left, int right) {
        Game pivot = data.get(right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare(data.get(j), pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, right);
        return i + 1;
    }
    private void swap(int i, int j) {
        Game temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }
    private void countingSort(Comparator<Game> comparator) {
        if (data.isEmpty()) return;
        int minCalidad = data.stream().mapToInt(Game::getCalidad).min().orElse(0);
        int maxCalidad = data.stream().mapToInt(Game::getCalidad).max().orElse(0);
        int rango = maxCalidad - minCalidad + 1;
        int[] contador = new int[rango];
        for (Game game : data) {
            contador[game.getCalidad() - minCalidad]++;
        }
        ArrayList<Game> sortedList = new ArrayList<>();
        for (int i = 0; i < rango; i++) {
            while (contador[i] > 0) {
                for (Game game : data) {
                    if (game.getCalidad() == i + minCalidad) {
                        sortedList.add(game);
                        contador[i]--;
                    }
                }
            }
        }
        data.clear();
        data.addAll(sortedList);
    }
    static class  LoadData {
        public static ArrayList<Game> loadGamesFromFile(String filename) {
            ArrayList<Game> games = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 4) {
                        String name = values[0];
                        String category = values[1];
                        int price = Integer.parseInt(values[2]);
                        int quality = Integer.parseInt(values[3]);
                        games.add(new Game(name, category, price, quality));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
            return games;
        }
        public static void main(String[] args) {
            ArrayList<Game> games = loadGamesFromFile("prueba 100.csv");
            Dataset dataset = new Dataset(games);
            System.out.println("Dataset cargado con " + games.size() + " juegos.");
            dataset.sortByAlgorithm("countingSort","calidad");
            dataset.getGamesByPrice(13631);
        }
    }
}