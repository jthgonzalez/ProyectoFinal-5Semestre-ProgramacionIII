/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.clases.funciones;
import org.config.Conexion;
import org.models.ModelEmpresa;
import org.interfaces.CrudEmpresa;


public class DaoEmpresa implements  CrudEmpresa{
    
    //Se crea un objeto publico de la Empresa
    ModelEmpresa empresa = new ModelEmpresa();
    //Variable para crear las sentencias SQL
    String strSql =  "";
    //Se crea un obejto de tipo conexión para manejar la persistencia hacia la base de datos
    Conexion conexion = new Conexion();
    //Obtiene el resultado de las consultas SQL
    ResultSet rs = null;
    //flag para retornar si la sentencia SQL fue satisfactorio o no
    boolean respuesta = false;
    
      funciones fn = new funciones();
    
    @Override
    public List listar() {
        ArrayList<ModelEmpresa>lstEmpresa = new ArrayList<>();
         try { 
          
                
                        strSql = "SELECT * FROM EMPRESA";
                    
             conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelEmpresa empre = new ModelEmpresa();
                empre.setIdEmpresa(rs.getInt("ID_EMPRESA"));
                empre.setNombre(rs.getString("NOMBRE"));
                empre.setDescripcion(rs.getString("DESCRIPCION"));
                empre.setTelefono(rs.getString("TELEFONO"));
                empre.setDireccion(rs.getString("DIRECCION"));
                empre.setEstado(rs.getInt("ESTADO"));
                lstEmpresa.add(empre);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstEmpresa;
    }

    @Override
    public ModelEmpresa list(int id) {
        try {            
            strSql = "SELECT * FROM EMPRESA WHERE ID_EMPRESA = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                empresa.setIdEmpresa(rs.getInt("ID_EMPRESA"));
                empresa.setNombre(rs.getString("NOMBRE"));
                empresa.setDescripcion(rs.getString("DESCRIPCION"));
                empresa.setTelefono(rs.getString("TELEFONO"));
                empresa.setDireccion(rs.getString("DIRECCION"));
                empresa.setEstado(rs.getInt("ESTADO"));                
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return empresa;
    }

    @Override
    public boolean insertar(ModelEmpresa empresa) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "INSERT INTO EMPRESA (ID_EMPRESA, NOMBRE, DESCRIPCION, ESTADO, TELEFONO, DIRECCION) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_EMPRESA),0) + 1 FROM EMPRESA), " +                   
                 "'" + empresa.getNombre() + "', " +                 
                "'" + empresa.getDescripcion()+ "', " +       
                 "" + empresa.getEstado() + ", " +
                "'" + empresa.getTelefono()+ "', " +
                 "'" + empresa.getDireccion()+ "'" +                 
                  ")";
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelEmpresa empresa) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "UPDATE EMPRESA " +
                 "SET " +
                 "NOMBRE = '" + empresa.getNombre() + "', " +
                 "DESCRIPCION = '" + empresa.getDescripcion()+ "', " + 
                 "ESTADO = " + empresa.getEstado() + ", " + 
                 "TELEFONO = '" + empresa.getTelefono() + "', " +
                 "DIRECCION = '" + empresa.getDireccion()+ "' " +
                 "WHERE ID_EMPRESA =  " + empresa.getIdEmpresa();
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelEmpresa empresa) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "DELETE EMPRESA WHERE ID_EMPRESA = " + empresa.getIdEmpresa();
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public String getNombre(int id) {
  try {            
            strSql = "SELECT * FROM EMPRESA WHERE ID_EMPRESA = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                empresa.setIdEmpresa(rs.getInt("ID_EMPRESA"));
                empresa.setNombre(rs.getString("NOMBRE"));
                empresa.setDescripcion(rs.getString("DESCRIPCION"));
                empresa.setTelefono(rs.getString("TELEFONO"));
                empresa.setDireccion(rs.getString("DIRECCION"));
                empresa.setEstado(rs.getInt("ESTADO"));                
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return empresa.getNombre();
    }
    
}
