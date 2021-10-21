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
public class Knapsackv3 {

    private int n;
    private int kapasitas;
    private int[][] barang;
    private int totalBenefit;
    private double[] bobot;
    private double[] nilai;
    private double temp[][] = new double[n][n];

    public Knapsackv3(int n, int kapasitas, double[] bobot, double[] nilai) {
        this.n = n;
        this.kapasitas = kapasitas;
        this.bobot = bobot;
        this.nilai = nilai;
        this.barang = new int[n + 1][kapasitas + 1];
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public void solve() {

        for (int i = 0; i < Math.pow(n, 2); i++) {
            for (int j = 0; j < n; j++) {
                for (int w = 0; w < 1; w++) {
                    temp[j][w] = max(barang[j][w], barang[j][w + 1]);
                }

            }
        }
    }

    public static void main(String[] args) {
        int kapasitas, n;

        Scanner in = new Scanner(System.in);

        System.out.print("Masukkan jumlah N : ");
        n = in.nextInt();
        double barang[][] = new double[n][n], bobot[] = new double[n], nilai[] = new double[n];

        System.out.print("Masukkan Kapasitas Muatan : ");
        kapasitas = in.nextInt();

        System.out.println("Masukkan Bobot : ");
        for (int i = 0; i < n; i++) {
            barang[i][0] = in.nextInt();
            bobot[i] = barang[i][0];
        }

        System.out.println("Masukkan Keuntungan : ");
        for (int i = 0; i < n; i++) {
            barang[i][1] = in.nextInt();
            nilai[i] = barang[i][1];
        }

        for (int i = 0; i < n; i++) {
            barang[i][2] = barang[i][0] / barang[i][1];
        }

        Knapsackv3 knp = new Knapsackv3(n, kapasitas, bobot, nilai);

    }

}
