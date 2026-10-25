
package algoritmos;

import java.util.Arrays;

public class RadixSort {

    private static int getMax(Integer[] vetor) {
        int max = vetor[0];

        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i] > max) {
                max = vetor[i];
            }
        }

        return max;
    }

    private static void countSort(Integer[] vetor, int exp) {
        int n = vetor.length;
        Integer[] out = new Integer[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(vetor[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (vetor[i] / exp) % 10;
            out[count[digit] - 1] = vetor[i];
            count[digit]--;
        }

        for (int i = 0; i < n; i++) {
            vetor[i] = out[i];
        }
    }

    public static void ordenador(Integer[] vetor) {
        if (vetor.length == 0) {
            return;
        }

        int max = getMax(vetor);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(vetor, exp);
        }
    }
}
