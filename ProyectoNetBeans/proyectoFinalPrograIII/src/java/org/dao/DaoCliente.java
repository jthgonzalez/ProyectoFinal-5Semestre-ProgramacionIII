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
import org.interfaces.CrudCliente;
import org.models.ModelCliente;


public class DaoCliente implements  CrudCliente{
    
    //Se crea un objeto publico del Cliente
    ModelCliente cliente = new ModelCliente();
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
        ArrayList<ModelCliente>lstCliente = new ArrayList<>();
         try {            
            strSql = "SELECT * FROM CLIENTE";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelCliente clie = new ModelCliente();
                clie.setIdCliente(rs.getInt("ID_CLIENTE"));
                clie.setNombre(rs.getString("NOMBRE"));
                clie.setApellido(rs.getString("APELLIDO"));
                clie.setTelefono(rs.getString("TELEFONO"));
                clie.setDireccion(rs.getString("DIRECCION"));
                clie.setIdEmpresa(rs.getInt("ID_EMPRESA"));
                clie.setNit(rs.getString("NIT"));
                lstCliente.add(clie);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstCliente;
    }

    @Override
    public ModelCliente list(int id) {
        try {            
            strSql = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setApellido(rs.getString("APELLIDO"));
                cliente.setTelefono(rs.getString("TELEFONO"));
                cliente.setDireccion(rs.getString("DIRECCION"));
                cliente.setIdEmpresa(rs.getInt("ID_EMPRESA"));
                cliente.setNit(rs.getString("NIT"));                
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return cliente;
    }

    @Override
    public boolean insertar(ModelCliente cliente) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "INSERT INTO CLIENTE (ID_CLIENTE, NOMBRE, APELLIDO, NIT, TELEFONO, DIRECCION,ID_EMPRESA) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_CLIENTE),0) + 1 FROM CLIENTE), " +                   
                 "'" + cliente.getNombre() + "', " +                 
                "'" + cliente.getApellido()+ "', " +       
                 "'" + cliente.getNit() + "', " +
                "'" + cliente.getTelefono()+ "', " +
                 "'" + cliente.getDireccion()+ "'," +
                "" + cliente.getIdEmpresa()+ "" + 
                  ")";
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelCliente cliente) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "UPDATE CLIENTE " +
                 "SET " +
                 "NOMBRE = '" + cliente.getNombre() + "', " +
                 "APELLIDO = '" + cliente.getApellido()+ "', " + 
                 "NIT = '" + cliente.getNit() + "', " + 
                 "TELEFONO = '" + cliente.getTelefono() + "', " +
                 "DIRECCION = '" + cliente.getDireccion()+ "' " +
                 "WHERE ID_CLIENTE =  " + cliente.getIdCliente()+
                 " AND ID_EMPRESA =  " + cliente.getIdEmpresa();
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelCliente cliente) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "DELETE CLIENTE WHERE ID_CLIENTE = " + cliente.getIdCliente();
        
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
    
}
