/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc2023.pkg2.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patricio
 */
public class ModeloPersona extends Persona{

    ConexionPg cpg=new ConexionPg();//Conectamos a la base
    
    public ModeloPersona() {
    }
    
    public SQLException grabarPersona(){
    
        String sql;//"INSERT INTO TABLA () VALUES()"
        sql="INSERT INTO personas (cedula,nombre,apellidos,correo) "
                + "VALUES('"+getCedula()+"','"+getNombres()+"','"+getApellidos()+"','"+getCorreo()+"')";
        return cpg.accionBD(sql);//DEVUELVO NULL SI ES CORRECTO.
        
    }
    public static  List<Persona> listaTodasPersonas(){
        ConexionPg cpg=new ConexionPg();//Conectamos a la base
        List<Persona> listaPersonas=new ArrayList<Persona>();
        
        String sql;//SELECT * FROM TABLA
        sql="SELECT cedula,nombre,apellidos,correo FROM personas";//
        ResultSet rs= cpg.consultaDB(sql);
        try {
            while(rs.next()){
                Persona persona=new Persona();
                persona.setCedula(rs.getString("cedula"));
                persona.setNombres(rs.getString("nombre"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setCorreo(rs.getString("correo"));
                listaPersonas.add(persona);
            }
        rs.close();//CIERRO CONEXION CON LA BASE DE DATOS.
        return listaPersonas;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            return null;//CUANDO REGRESA NULL, HUBO ERROR EN EL QUERY
        }
            
    }
}
