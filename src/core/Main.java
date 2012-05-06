package core;


import view.PrincipalView;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiago
 */
public class Main {
    
    public static void main(String[] args) {
//        PrincipalView p = PrincipalView.getInstance();
//        p.setVisible(true);
        Principal p = Principal.getInstance();
        p.iniciar();
    }
    
}
