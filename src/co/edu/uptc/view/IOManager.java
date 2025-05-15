package co.edu.uptc.view;

import javax.swing.JOptionPane;

/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */

public class IOManager {

    public void showMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public String inputData(String message){
        return JOptionPane.showInputDialog(message);
    }
}