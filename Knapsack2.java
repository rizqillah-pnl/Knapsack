/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program2;

import java.util.Scanner;

/**
 *
 * @author Mayor Kucing
 */
public class Knapsack2 {

    // A utility function that returns maximum of two integers
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int KNP(int kapasitas, int bobot[], int keuntungan[], int n) {
        // Base Case
        if (n == 0 || kapasitas == 0) {
            return 0;
        }

        // If weight of the nth item is more
        // than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (bobot[n - 1] > kapasitas) {
            return KNP(kapasitas, bobot, keuntungan, n - 1);
        } else {
            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
            return max(keuntungan[n - 1] + KNP(kapasitas - bobot[n - 1], bobot, keuntungan, n - 1),
                    KNP(kapasitas, bobot, keuntungan, n - 1));
        }
    }

    public static void main(String[] args) {
        int kapasitas, n, hasil;
        int bobot[] = new int[10], keuntungan[] = new int[10];
        Scanner in = new Scanner(System.in);

        System.out.print("Masukkan Kapasitas : ");
        kapasitas = in.nextInt();

        System.out.print("Masukkan data N : ");
        n = in.nextInt();

        System.out.println("Masukkan Bobot : ");
        for (int i = 0; i < n; i++) {
            bobot[i] = in.nextInt();
        }

        System.out.println("Masukkan Keuntungan : ");
        for (int i = 0; i < n; i++) {
            keuntungan[i] = in.nextInt();
        }

        hasil = KNP(kapasitas, bobot, keuntungan, n);
        System.out.println("Hasil Knapsack : " + hasil);
    }
}
