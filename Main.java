import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {
    /*
     * Pole má 100 prvků - -100 až 100
     * vypište nejprve záporná pak nuly a pak kladná čísla
     * 
     * zjistěte, zda se v posloupnosti nějaké číslo opakuje
     * která čísla se opakují
     * Kolikrát se daná čísla opakují
     */
    public static void main(String[] args) {
        Random r = new Random();
        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(-100, 100);
        }

        while(true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            konzole(arr);
        }
    }

    private static void konzole(int[] arr) {
        System.out.println("Vyberte si, co požadujete: \nVypsat čísla od záporných, po nulu, až po kladná: 1\nVypsat čísla od záporných, po nulu až po kladná, ale s použitím sort: 2\nZjistit, zda se v tomto poli opakují čísla (jednodušeji): 3\nZjistit, zda se v tomto poli opakují čísla (HashSet): 4\nVypiš, jaká čísla se v tomto poli opakují (jednodušeji): 5\nVypiš, jaká čísla se v tomto poli opakují (HashSet): 6\nVypiš, jaká čísla se v tomto poli opakují a kolikrát (jednodušeji): 7\nVypiš, jaká čísla se v tomto poli opakují a kolikrát (HashMap): 8\nVypiš autory: 9\nUkončit program: 10");
        Scanner in = new Scanner(System.in);
        byte vstup = in.nextByte();
        delej(vstup, arr);
    }

    private static void delej(byte in, int[] arr) {
        switch (in){
            case 1: vypsat(arr); break;
            case 2: vypsatmeth2(arr); break;
            case 3: System.out.println("\n" + opak(arr) + "\n"); break;
            case 4: System.out.println("\n" + opak_tezsi(arr) + "\n"); break;
            case 5: opak2(arr); break;
            case 6: opak2_tezsi(arr); break;
            case 7: opak3(arr); break;
            case 8: opak3_tezsi(arr); break;
            case 9: cisticKonzole(); System.out.println("\nAutoři tohoto kódu jsou: Radim Kalvoda, Radek Kawik a Adam Řezníček\n"); break;
            case 10: System.exit(0); break;
            default: System.out.println("Špatná hodnota kápo"); break;
        }
    }

    private static void cisticKonzole() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        }  

    private static void vypsat(int[] arr) {
        System.out.print("\n");

        for (int i : arr) {
            if (i < 0)
                System.out.println(i);
        }

        for (int i : arr) {
            if (i == 0)
                System.out.println(i);
        }

        for (int i : arr) {
            if (i > 0)
                System.out.println(i);
        }
        System.out.print("\n");
    }

    private static void vypsatmeth2(int[] arr) {
        System.out.print("\n");
        Arrays.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
        System.out.print("\n");
    }

    private static boolean opak_tezsi(int[] arr) {
        HashSet<Integer> num = new HashSet<>();
        for (int i : arr) {
            if (num.contains(i)) {
                return true;
            }
            num.add(i);
        }
        return false;
    }

    private static boolean opak(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void opak2_tezsi(int[] arr) {
        System.out.print("\n");
        boolean rep = false;
        HashSet<Integer> num = new HashSet<>();
        for (int i : arr) {
            if (num.contains(i)) {
                System.out.println(i);
                if (!rep)
                    rep = true;
            }
            num.add(i);
        }

        if (!rep) {
            System.out.println("Nebyly nalezeny žádné opakující se čísla");
        }
        System.out.print("\n");
    }

    private static void opak2(int[] arr) {
        System.out.print("\n");
        boolean rep = false;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    if (!rep) {
                        rep = true;
                    }
                    System.out.println(arr[i]);

                }
            }
        }

        if (!rep) {
            System.out.println("Nebyly nalezeny žádné opakující se čísla");
        }
        System.out.print("\n");
    }

    private static void opak3_tezsi(int[] arr) {
        // Tady jsem musel použít StackOverflow - Radim
        System.out.print("\n");
        HashMap<Integer, Integer> num = new HashMap<>();
        for (int i : arr) {
            num.put(i, num.getOrDefault(i, 0) + 1);
        }

        boolean rep = false;
        for (int i : num.keySet()) {
            int count = num.get(i);

            if (count > 1) {
                System.out.println(i + " : " + count);
                rep = true;
            }
        }

        if (!rep) {
            System.out.println("Nebyly nalezeny žádné opakující se čísla");
        }
        System.out.print("\n");
    }

    private static void opak3(int[] arr) {
        System.out.print("\n");
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }

        int[] occ = new int[max - min + 1];

        for (int num : arr) {
            occ[num - min]++;
        }
    
        boolean rep = false;

        for (int i = 0; i < occ.length; i++) {
            if (occ[i] > 1) {
                System.out.println((i + min) + " : " + occ[i]);
                rep = true;
            }
        }
    
        if (!rep) {
            System.out.println("Nebyly nalezeny žádné opakující se čísla");
        }
        System.out.print("\n");
    }
}