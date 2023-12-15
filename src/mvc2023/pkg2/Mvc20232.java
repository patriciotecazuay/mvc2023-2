/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc2023.pkg2;

import mvc2023.pkg2.controlador.ControladorPersonas;
import mvc2023.pkg2.controlador.ControladorPrincipal;
import mvc2023.pkg2.modelo.ModeloPersona;
import mvc2023.pkg2.vista.VistaPersona;
import mvc2023.pkg2.vista.VistaPrincipal;

/**
 *
 * @author Patricio
 */
public class Mvc20232 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        VistaPrincipal vistaPrincipal=new VistaPrincipal();
        
        ControladorPrincipal controlPrincipal=new ControladorPrincipal(vistaPrincipal);
       
        controlPrincipal.iniciarControl();
    }
    
}
