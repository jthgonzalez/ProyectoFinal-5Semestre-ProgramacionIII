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
import org.interfaces.CrudMesa;
import org.models.ModelMesa;


public class DaoMesa implements  CrudMesa{
    
    //Se crea un objeto publico del Cliente
    ModelMesa mesa = new ModelMesa();
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
        ArrayList<ModelMesa>lstMesa = new ArrayList<>();
         try {            
            strSql = "SELECT * FROM MESA";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelMesa mes = new ModelMesa();
                mes.setIdMesa(rs.getInt("ID_MESA"));
                mes.setDescripcion(rs.getString("DESCRIPCION"));
                mes.setAsiento(rs.getInt("ASIENTOS"));
                mes.setEstado(rs.getString("ESTADO"));
                mes.setTipo(rs.getString("TIPO"));
                mes.setIdArea(rs.getInt("ID_AREA"));
                lstMesa.add(mes);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstMesa;
    }

        @Override
    public List listarMesas(int idArea) {
        ArrayList<ModelMesa>lstMesa = new ArrayList<>();
         try {            
            strSql = "SELECT * FROM MESA where ID_AREA="+idArea;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelMesa mes = new ModelMesa();
                mes.setIdMesa(rs.getInt("ID_MESA"));
                mes.setDescripcion(rs.getString("DESCRIPCION"));
                mes.setAsiento(rs.getInt("ASIENTOS"));
                mes.setEstado(rs.getString("ESTADO"));
                mes.setTipo(rs.getString("TIPO"));
                mes.setIdArea(rs.getInt("ID_AREA"));
                lstMesa.add(mes);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstMesa;
    }

    @Override
    public ModelMesa list(int id) {
        try {            
            strSql = "SELECT * FROM MESA WHERE ID_MESA = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
       
                
                  mesa.setIdMesa(rs.getInt("ID_MESA"));
                mesa.setDescripcion(rs.getString("DESCRIPCION"));
                mesa.setAsiento(rs.getInt("ASIENTOS"));
                mesa.setEstado(rs.getString("ESTADO"));
                mesa.setTipo(rs.getString("TIPO"));
                mesa.setIdArea(rs.getInt("ID_AREA"));
                
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return mesa;
    }

    @Override
    public String mesaNombre(int id) {
        try {            
            strSql = "SELECT * FROM MESA WHERE ID_MESA = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
       
                
                  mesa.setIdMesa(rs.getInt("ID_MESA"));
                mesa.setDescripcion(rs.getString("DESCRIPCION"));
                mesa.setAsiento(rs.getInt("ASIENTOS"));
                mesa.setEstado(rs.getString("ESTADO"));
                mesa.setTipo(rs.getString("TIPO"));
                mesa.setIdArea(rs.getInt("ID_AREA"));
                
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return mesa.getDescripcion();
    }
    @Override
    public boolean insertar(ModelMesa mesa) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "INSERT INTO MESA (ID_MESA,ID_AREA,DESCRIPCION,ASIENTOS,ESTADO,TIPO) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_MESA),0) + 1 FROM MESA), " +                   
                 "'" + mesa.getIdArea()+ "', " +                 
                "'" + mesa.getDescripcion()+ "', " +       
                 "'" + mesa.getAsiento()+ "', " +
                "'" + mesa.getEstado()+ "', " +
                "'" + mesa.getTipo()+ "' " +        
                  ")";
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelMesa mesa) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "UPDATE MESA " +
                 "SET " +
                 "ID_AREA = '" + mesa.getIdArea()+ "', " + 
                 "DESCRIPCION = '" + mesa.getDescripcion()+ "', " +
                 "ASIENTOS = '" + mesa.getAsiento()+ "', " + 
                 "ESTADO = '" + mesa.getEstado()+ "', " + 
                 "TIPO = '" + mesa.getTipo()+ "' " +
                 " WHERE ID_MESA =  " + mesa.getIdMesa();
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelMesa mesa) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "DELETE MESA WHERE ID_MESA = " + mesa.getIdMesa();
        
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoMesa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
    
}
