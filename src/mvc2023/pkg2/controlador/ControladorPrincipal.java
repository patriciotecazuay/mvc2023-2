/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc2023.pkg2.controlador;

import mvc2023.pkg2.modelo.ModeloPersona;
import mvc2023.pkg2.vista.VistaPersona;
import mvc2023.pkg2.vista.VistaPrincipal;

/**
 *
 * @author Patricio
 */
public class ControladorPrincipal {
    
    VistaPrincipal vistaPrincipal;
    //Modelo en cas de necesitarlo

    public ControladorPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        vistaPrincipal.setVisible(true);
    }
    public void iniciarControl(){
    vistaPrincipal.getTlbPersonas().addActionListener(l->crudPersonas());
    vistaPrincipal.getMnuCrearPersona().addActionListener(l->crudPersonas());
    
    }
    
    private void crudPersonas(){
    
        ModeloPersona modeloPersonas=new ModeloPersona();
        VistaPersona vistaPersonas= new VistaPersona();
        vistaPrincipal.getDskPrincipal().add(vistaPersonas);
        ControladorPersonas control= new ControladorPersonas(modeloPersonas, vistaPersonas);
        control.iniciaControl();
    }
    
    
}
