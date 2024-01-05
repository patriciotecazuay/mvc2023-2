/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc2023.pkg2.controlador;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mvc2023.pkg2.modelo.ConexionPg;
import mvc2023.pkg2.modelo.ModeloPersona;
import mvc2023.pkg2.modelo.Persona;
import mvc2023.pkg2.vista.VistaPersona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

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
        vista.getBtnImprimir().addActionListener(l->imprimirPersonas());
    }
    
    private void imprimirPersonas(){
        ConexionPg conecction=new ConexionPg();
        try {
            JasperReport reporte=(JasperReport)JRLoader.loadObject(
                    getClass().getResource("/mvc2023/pkg2/vista/reportes/ReporteClientes.jasper")
            );
            
            JasperPrint jp =JasperFillManager.fillReport(
                    reporte,
                    null,
                    conecction.getCon());
            JasperViewer jv=new JasperViewer(jp,false);
            jv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorPersonas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
