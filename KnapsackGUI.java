/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program2;

/**
 *
 * @author Mayor Kucing
 */
public class KnapsackGUI extends javax.swing.JFrame {

    /**
     * Creates new form KnapsackGUI
     */
    private int wt[];
    private int p[];
    private int objek[][];
    private int W;
    private int n;
    Integer obj1 = 0;
    Integer obj2 = 0;

    public KnapsackGUI() {
        initComponents();

    }

    public void knapsack() {
        objek = new int[n][4];
        int jum = 0, temp;

        for (int i = 0; i < n; i++) {
            objek[i][0] = p[i] / wt[i];
        }

        for (int i = 0; i < n; i++) {
            objek[i][1] = wt[i];
        }

        for (int i = 0; i < n; i++) {
            objek[i][2] = p[i];
        }

        for (int i = 1; i < n; i++) {
            if (max(objek[i][0], objek[i - 1][0])) {
                temp = objek[i][0];
                objek[i][0] = objek[i - 1][0];
                objek[i - 1][0] = temp;
            }
        }

        for (int i = 1; i < n; i++) {
            if (max(objek[i][0], objek[i - 1][0])) {
                temp = objek[i][1];
                objek[i][1] = objek[i - 1][1];
                objek[i - 1][1] = temp;
            }
        }

        for (int i = 1; i < n; i++) {
            if (max(objek[i][0], objek[i - 1][0])) {
                temp = objek[i][2];
                objek[i][2] = objek[i - 1][2];
                objek[i - 1][2] = temp;
            }
        }

        for (int i = 1; i < n; i++) {
            objek[i - 1][3] = i - 1;

            if (jum > W) {
                break;
            } else if (jum == W) {
                jum = objek[i][1] + objek[i - 1][1];
                if (jum > W) {
                    objek[i - 1][3] = i - 1;
                    break;
                }
            }
            jum = objek[i][1] + objek[i - 1][1];

        }

        for (int i = 0; i < n; i++) {

        }

        textArea.append("{");
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                continue;
            } else if (i == n - 1) {
                break;
            }
            textArea.append((objek[i][3] + 1) + ",");
        }
        textArea.append("}\n");

        textArea.append("Total Bobot : " + jum);
    }

    public boolean max(int a, int b) {
        return a > b ? true : false;
    }

    public void setup() {
        input();
        textArea.append("\nKeuntungan : " + KNP(W, wt, p, n));
    }

    public void input() {
        for (int i = 0; i < n; i++) {
            for (int column = 0; column < 1; column++) {
                obj1 = Integer.parseInt(model.getValueAt(i, column).toString());
            }
            wt[i] = obj1;
        }
        for (int i = 0; i < n; i++) {
            if (model.getValueAt(i, 1) != null && model.getValueAt(i, 1).toString().trim().length() != 0) {
                obj2 = Integer.parseInt(model.getValueAt(i, 1).toString());
            }
            p[i] = obj2;
        }
    }

    public int KNP(int W, int wt[], int p[], int n) {
        int i, j;
        int jum = 0;
        int K[][] = new int[n + 1][W + 1], data[] = new int[n], himpunan[] = new int[n];

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

//        for (i = 0; i < n; i++) {
//            for (j = 1; j <= n; j++) {
//                himpunan[i] = j;
//            }
//
//            if (p[0] != K[n][W]) {
//                data[0] = 0;
//            } else if (p[0] + p[1] != K[n][W]) {
//                data[0] = 0;
//                data[1] = 1;
//            } else if (p[0] + p[1] + p[2] != K[n][W]) {
//                data[0] = 0;
//                data[1] = 1;
//                data[2] = 2;
//            } else {
//                data[0] = 0;
//                data[1] = 1;
//                data[2] = 2;
//                data[3] = 3;
//            }
//        }
//
//        for (i = 0; i < n; i++) {
//            for (j = 0; j < n; j++) {
//                if (p[j] == K[n][W]) {
//                    data[0] = j;
//                    jum = bobot(data);
//                } else if (n == 2 || p[i] + p[j] == K[n][W]) {
//                    data[0] = j;
//                    data[1] = i;
//                    jum = bobot(data);
//                } else if (n == 3 || p[j] + p[1] + p[2] == K[n][W]) {
//                    data[0] = j;
//                    data[1] = 1;
//                    data[2] = 2;
//                    jum = bobot(data);
//                } else if (n == 4 && p[0] + p[1] + p[2] + p[3] == K[n][W]) {
//                    data[0] = 0;
//                    data[1] = 1;
//                    data[2] = 2;
//                    data[3] = 3;
//                    jum = bobot(data);
//                }
//            }
//        }
        return K[n][W];
    }

//    int bobot(int data[]) {
//        int n1 = 0, n2 = 0;
//
//        for (int i = 0; i < n; i++) {
//            if (n == 2) {
//                n1 = Math.max(wt[data[0]] + wt[data[1]], wt[data[1]] + wt[data[0]]);
//            } else if (n == 3) {
//                n1 = Math.max(wt[data[0]] + wt[data[1]], wt[data[1]] + wt[data[2]]);
//                n2 = Math.max(wt[data[2]] + wt[data[0]], wt[data[0]]);
//            } else if (n == 4) {
//                n1 = Math.max(wt[data[0]] + wt[data[1]], wt[data[1]] + wt[data[2]]);
//                n2 = Math.max(wt[data[2]] + wt[data[3]], wt[data[3]] + wt[data[1]]);
//            } else if (n == 5) {
//                n1 = Math.max(wt[data[0]] + wt[data[1]], wt[data[1]] + wt[data[2]]);
//                n2 = Math.max(wt[data[2]] + wt[data[3]], wt[data[3]] + wt[data[4]]);
//                n1 = Math.max(n1, wt[data[4]] + wt[data[0]]);
//            }
//        }
//
//        if (n1 <= W && n2 <= W) {
//            if (n1 > n2) {
//                return n1;
//            }
//        }
//        return n2;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        model = new javax.swing.JTable();
        nilaiN = new javax.swing.JTextField();
        kapasitas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        model.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Bobot", "Keuntungan"
            }
        ));
        jScrollPane1.setViewportView(model);

        jLabel1.setText("Jumlah Data");

        jLabel2.setText("Kapasitas");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Knapsack");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("APLIKASI KNAPSACK");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nilaiN)
                                    .addComponent(kapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1))
                        .addGap(121, 121, 121)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(67, 67, 67))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nilaiN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kapasitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        n = Integer.parseInt(nilaiN.getText());
        wt = new int[n];
        p = new int[n];
        W = Integer.parseInt(kapasitas.getText());
        setup();
        input();
        knapsack();
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KnapsackGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KnapsackGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KnapsackGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KnapsackGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KnapsackGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kapasitas;
    private javax.swing.JTable model;
    private javax.swing.JTextField nilaiN;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
