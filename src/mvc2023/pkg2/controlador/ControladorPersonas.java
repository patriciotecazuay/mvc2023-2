/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc2023.pkg2.controlador;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mvc2023.pkg2.modelo.ModeloPersona;
import mvc2023.pkg2.modelo.Persona;
import mvc2023.pkg2.vista.VistaPersona;

/**
 *
 * @author Patricio
 */
public class ControladorPersonas {
    
    private ModeloPersona modelo;
    private VistaPersona vista;

    public ControladorPersonas(ModeloPersona modelo, VistaPersona vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setVisible(true);
    }
    
    public void iniciaControl(){
        
        vista.getBtnActualizar().addActionListener(l->listarPersonas());
        vista.getBntEliminar().addActionListener(l->eliminarPersonas());
        vista.getBtnNuevo().addActionListener(l->abrirDialogo(true));
        vista.getBtnEditar().addActionListener(l->abrirDialogo(false));
        vista.getBtnAceptar().addActionListener(l->grabarEditarPersona());
    }
    
    private void grabarEditarPersona(){
            if(vista.getDlgPersona().getTitle().contentEquals("CREAR NUEVA PERSONA")){
            //LOGICA PARA GRABAR
            //VALIDAR ANTES
            String cedula= vista.getTxtCedula().getText();
            String nombre= vista.getTxtNombres().getText();
            String apellidos= vista.getTxtApellido().getText();
            String correo= vista.getTxtCorreo().getText();
            ModeloPersona persona= new ModeloPersona();
            persona.setCedula(cedula);
            persona.setNombres(nombre);
            persona.setApellidos(apellidos);
            persona.setCorreo(correo);
            
            if (persona.grabarPersona()==null){
                vista.getDlgPersona().setVisible(false);
                JOptionPane.showMessageDialog(vista,"Persona Creada Satisfacoriamente");
                listarPersonas();
            }else{
                JOptionPane.showMessageDialog(vista, "ERROR");
            }
            
            
            }else{
            //logica para Editar
            }
    }
    private void abrirDialogo(boolean nuevo){
        if (nuevo){
            vista.getDlgPersona().setTitle("CREAR NUEVA PERSONA");
            
        }else{
            vista.getDlgPersona().setTitle("EDITAR PERSONA");
        }
        
        vista.getDlgPersona().setLocationRelativeTo(vista);
        vista.getDlgPersona().setSize(500,320);
        vista.getDlgPersona().setVisible(true);
    
    }
    private void listarPersonas(){
        ///Logica cargar personas
         List<Persona> listap=ModeloPersona.listaTodasPersonas();
         DefaultTableModel mTabla;
         mTabla=(DefaultTableModel)vista.getTblPersonas().getModel();
         mTabla.setNumRows(0);//limpio la tabla
         listap.stream().forEach(per->{
             String[] rowData={per.getCedula(),per.getNombres(),per.getApellidos(),per.getCorreo()};
             mTabla.addRow(rowData);
         });
         
    }
    private void eliminarPersonas(){
    // logica editar pernas
    }
    
}
