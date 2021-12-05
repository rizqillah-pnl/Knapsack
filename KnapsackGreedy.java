/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program5;

/*
 * @author Mayor Kucing
 */
import java.text.DecimalFormat;
import java.util.Scanner;

public class KnapsackGreedy {

    // format nilai
    // Object adalah variabel menyimpan jumlah himpunan yang diambil
    DecimalFormat df = new DecimalFormat("#.##");
    int Object;

    private void show(int n, int himpunan[], int profit[], int weight[], double pw[]) {
        // mencetak urutan himpunan data
        System.out.print("Himpunan ");
        for (int i = 0; i < n; i++) {
            System.out.print(himpunan[i] + "    ");
        }

        // mencetak seluruh nilai profit
        System.out.println("");
        System.out.print("Profit   ");
        for (int i = 0; i < n; i++) {
            System.out.print(profit[i] + "    ");
        }

        // mencetak seluruh nilai weight
        System.out.println("");
        System.out.print("Weight   ");
        for (int i = 0; i < n; i++) {
            System.out.print(weight[i] + "    ");
        }

        // mencetak nilai hasil bagi profit dengan weight
        System.out.println("");
        System.out.print("P/W      ");
        for (int i = 0; i < n; i++) {
            System.out.print(df.format(pw[i]) + "   ");
        }
    }

    private void Knapsack(int m, int n, int profit[], int weight[]) {
        int himpunan[] = new int[n];
        for (int i = 0; i < n; i++) {
            himpunan[i] = i + 1;
        }

        // pw adalah variabel hasil bagi profit dengan weight
        double pw[] = new double[n];
        for (int i = 0; i < n; i++) {
            pw[i] = (double) profit[i] / (double) weight[i];
        }

        // mencetak data sebenarnya
        System.out.println("");
        System.out.println("--------------- Data Awal ---------------");
        show(n, himpunan, profit, weight, pw);

        // melakukan perulangan untuk membandingkan nilai pw
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // membandingkan nilai, jika lebih kecil maka melakukan swap
                if (pw[i] < pw[j]) {
                    // Swap nilai pw
                    double temp = pw[j];
                    pw[j] = pw[i];
                    pw[i] = temp;

                    // swap nilai profit
                    int temp1 = profit[j];
                    profit[j] = profit[i];
                    profit[i] = temp1;

                    // swap nilai weight
                    int temp2 = weight[j];
                    weight[j] = weight[i];
                    weight[i] = temp2;

                    // swap nilai himpunan
                    int temp3 = himpunan[j];
                    himpunan[j] = himpunan[i];
                    himpunan[i] = temp3;
                }
            }
        }

        // mencetak data setelah di swap
        System.out.println("\n");
        System.out.println("------------ Setelah di Swap ------------");
        show(n, himpunan, profit, weight, pw);

        // cetak hasil
        // himpunan dipilih beserta jumlah max profit
        System.out.println("\n");
        double maxProfit = hitung(weight, profit, m);
        System.out.print("Himpunan dipilih : {");
        for (int i = 0; i < Object; i++) {
            System.out.print(" " + himpunan[i] + " ");
        }
        System.out.println("}");
        System.out.println("Profit Maximum = " + df.format(maxProfit));
    }

    double hitung(int weight[], int profit[], int m) {
        // i untuk nilai increment
        int i = 0;

        // hasil akan menyimpan hasil penjumlahan profit
        double hasil = 0;

        // melakukan perulangan selama nilai m lebih besar dari 0
        while (m > 0) {
            // pengecekan jika nilai weight lebih kecil dari m/kapasitas
            if (weight[i] < m) {
                // menghitung profit
                hasil += 1 * profit[i];
                // mengurangi nilai m dengan weight dari himpunan profit yang diambil
                m -= weight[i];
                Object += 1;
            } else {
                // mengalikan nilai profit setelah dikali dengan m
                double x4 = m * profit[i];

                // menyimpan nilai weight yang lebih besar dari m
                double x5 = weight[i];

                // membagikan hasil kali dengan weight yang lebih besar dari m
                double x6 = x4 / x5;

                // menyimpan hasil bagi x6
                // mengatur m = 0 agar perulangan berakhir
                hasil += x6;
                m = 0;
                Object += 1;
            }
            i++;
        }
        return hasil;
    }

    public static void main(String[] args) {
        KnapsackGreedy knpG = new KnapsackGreedy();
        Scanner sc = new Scanner(System.in);
        // n variabel jumlah himpunan
        // m variabel kapasitas tas
        int n, m;

        System.out.print("Input Total Himpunan : ");
        n = sc.nextInt();

        // weight adalah array untuk bobot
        // profit adalah array untuk keuntungan
        int weight[] = new int[n];
        int profit[] = new int[n];

        // melakukan perulangan untuk menginput nilai profit dan weight
        // perulangan dilakukan sebanyak nilai jumlah himpunan
        for (int i = 0; i < n; i++) {
            System.out.print("Masukkan Profit[" + (i + 1) + "] : ");
            profit[i] = sc.nextInt();
            System.out.print("Masukkan Weight[" + (i + 1) + "] : ");
            weight[i] = sc.nextInt();
            System.out.println("");
        }

        // variabel m adalah beban yang dapat ditampung
        System.out.print("Masukkan Kapasitas Tas : ");
        m = sc.nextInt();

        // memanggil method Knapsack melalui objek knpG
        knpG.Knapsack(m, n, profit, weight);

    }
}
