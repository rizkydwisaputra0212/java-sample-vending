/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vendingmachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendingMachine extends JFrame {

    private JLabel label1;
    private JLabel label2;
    private JTextField inputUang;
    private JButton[] drinkButtons;
    private String[] drinkNames = {"Teh", "Kopi", "Soda"};
    private int[] drinkPrices = {1500, 2000, 1000}; // Harga minuman dalam rupiah
    private int change;
    private int moneyUserBalance;

    public VendingMachine() {
        setTitle("Vending Machine");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel utama dengan layout BorderLayout
        JPanel mainPanel = new JPanel(null); // Layout null untuk menggunakan setBounds
        setContentPane(mainPanel);

        // Panel bagian atas
        label1 = new JLabel("Uang terpotong : " + moneyUserBalance + " Rupiah");
        label1.setBounds(20, 100, 200, 30); // SetBounds(x, y, width, height)
        mainPanel.add(label1);

        // Panel bagian kiri
        label2 = new JLabel("Uang kembalian : " + change + " Rupiah");
        label2.setBounds(20, 60, 200, 30); // SetBounds(x, y, width, height)
        mainPanel.add(label2);

        // Panel tengah
        JPanel centerPanel = new JPanel(new FlowLayout());
        JLabel inputLabel = new JLabel("Masukan Uang Anda: ");
        inputUang = new JTextField(10);
        centerPanel.add(inputLabel);
        centerPanel.add(inputUang);
        centerPanel.setBounds(0, 20, 200, 50);
        mainPanel.add(centerPanel);

        // Panel bawah
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        drinkButtons = new JButton[3];
        for (int i = 0; i < 3; i++) {
            drinkButtons[i] = new JButton(drinkNames[i] + " (" + drinkPrices[i] + " Rupiah)");
            final int drinkIndex = i;
            drinkButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    processPurchase(drinkIndex);
                }
            });
            bottomPanel.add(drinkButtons[i]);
        }
        bottomPanel.setBounds(20, 140, 450, 30); // SetBounds(x, y, width, height)
        mainPanel.add(bottomPanel);

        setVisible(true);
    }

    private void processPurchase(int drinkIndex) {
        try {
            int insertedAmount = Integer.parseInt(inputUang.getText());
            if (insertedAmount >= drinkPrices[drinkIndex]) {
                change = insertedAmount - drinkPrices[drinkIndex];
                JOptionPane.showMessageDialog(this, "Terima kasih! Anda telah membeli " + drinkNames[drinkIndex] +
                        ". Kembalian Anda: " + change + " Rupiah");
                moneyUserBalance += drinkPrices[drinkIndex];
                label1.setText("Uang Terpotong : " + moneyUserBalance + " Rupiah");
                label2.setText("Uang kembalian : " + change + " Rupiah");
            } else {
                JOptionPane.showMessageDialog(this, "Saldo tidak mencukupi. Silakan masukkan jumlah yang mencukupi.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan jumlah uang yang valid.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VendingMachine();
            }
        });
    }
}
