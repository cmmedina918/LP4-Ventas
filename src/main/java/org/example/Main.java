package org.example;

import org.example.view.frminterfazprincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        frmLogin view = new frmLogin();
//        view.setVisible(true);
        frminterfazprincipal form = new frminterfazprincipal();
        form.setVisible(true);
        form.toFront();
    }
}