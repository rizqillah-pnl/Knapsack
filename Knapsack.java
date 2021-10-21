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
public class Knapsack {

    public static int KNP(int W, int wt[], int p[], int n) {
        int i, j;
        int K[][] = new int[n + 1][W + 1], himpunan[] = new int[n + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++) { // n = 4
            for (j = 0; j <= W; j++) { // W = 16
                if (i == 0 || j == 0) {
                    K[i][j] = 0;
                } else if (wt[i - 1] <= j) {
                    K[i][j] = Math.max(p[i - 1] + K[i - 1][j - wt[i - 1]], K[i - 1][j]);
                } else {
                    K[i][j] = K[i - 1][j];
                }
            }
        }

        for (i = 1; i < n; i++) {
            if (p[i] + p[i - 1] == K[n][W]) {
                himpunan[0] = i;
                himpunan[1] = i + 1;
            } else if (p[i] + p[i + 1] + p[i + 2] == K[n][W]) {
                himpunan[0] = i;
                himpunan[1] = i + 1;
            } else if (p[i] + p[i + 1] + p[i + 2] + p[i + 3] == K[n][W]) {
                himpunan[0] = i;
                himpunan[1] = i + 1;
            }
        }

        System.out.println("Himpunan {" + himpunan[0] + "," + himpunan[1] + "}");
        System.out.println("Jumlah Bobot : " + (wt[himpunan[0]] + wt[himpunan[1]]));

        return K[n][W];
    }

    static void func(int W, int w[], int p[], int n) {
        int objek[][] = new int[n][2];
        int bobot;
        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                bobot =
//            }
        }
    }

    public static void main(String[] args) {
        int W, n, hasil;
        int wt[] = new int[10], p[] = new int[10], objek[][] = new int[10][10];
        Scanner in = new Scanner(System.in);

        System.out.print("Masukkan jumlah N : ");
        n = in.nextInt();

        System.out.print("Masukkan Kapasitas Muatan : ");
        W = in.nextInt();

        System.out.println("Masukkan Bobot : ");
        for (int i = 0; i < n; i++) {
            wt[i] = in.nextInt();
        }

        System.out.println("\nMasukkan Keuntungan : ");
        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt();
        }

        System.out.println("Keutungan : " + KNP(W, wt, p, n));

    }
}
