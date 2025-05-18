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

    public int bouttonQuestion(String message, String titule, Object[] options) {
        return JOptionPane.showOptionDialog(null, message, titule, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
    }

    public String inputList(String message, String title, String[] options) {
        String action = String.valueOf(JOptionPane.showInputDialog(null, message, title, -1, null, options, null));
        return action;
    }
}