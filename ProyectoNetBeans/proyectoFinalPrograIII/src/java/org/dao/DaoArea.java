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
import org.interfaces.CrudArea;
import org.models.ModelArea;

public class DaoArea implements  CrudArea{
    //Se crea un objeto publico del Cliente
    ModelArea area = new ModelArea();
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
        ArrayList<ModelArea>lstArea = new ArrayList<>();
         try {            
            strSql = "SELECT * FROM AREA";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelArea area = new ModelArea();
                area.setIdArea(rs.getInt("ID_AREA"));
                area.setDescripcion(rs.getString("DESCRIPCION"));
                area.setEstado(rs.getInt("ESTADO"));
                area.setTipo(rs.getString("TIPO"));
               
                lstArea.add(area);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstArea;
    }
    
    @Override
    public List areasMesas() {
        ArrayList<ModelArea>lstArea = new ArrayList<>();
         try {            
            strSql = "select * from AREA a\n" +
"where (select count(*) from MESA x where x.ID_AREA= a.ID_AREA)>0";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelArea area = new ModelArea();
                area.setIdArea(rs.getInt("ID_AREA"));
                area.setDescripcion(rs.getString("DESCRIPCION"));
                area.setEstado(rs.getInt("ESTADO"));
                area.setTipo(rs.getString("TIPO"));
               
                lstArea.add(area);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstArea;
    }

    @Override
    public ModelArea list(int id) {
        try {            
            strSql = "SELECT * FROM AREA WHERE ID_AREA = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                area.setIdArea(rs.getInt("ID_AREA"));
                area.setDescripcion(rs.getString("DESCRIPCION"));
                area.setEstado(rs.getInt("ESTADO"));
                area.setTipo(rs.getString("TIPO"));
                               
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return area;
    }
    @Override
    public String areaNombre(int id) {
        try {            
            strSql = "SELECT * FROM AREA WHERE ID_AREA = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                area.setIdArea(rs.getInt("ID_AREA"));
                area.setDescripcion(rs.getString("DESCRIPCION"));
                area.setEstado(rs.getInt("ESTADO"));
                area.setTipo(rs.getString("TIPO"));
                               
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return area.getDescripcion();
    }
    @Override
    public boolean insertar(ModelArea area) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "INSERT INTO AREA (ID_AREA, DESCRIPCION, ESTADO, TIPO) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_AREA),0) + 1 FROM AREA), " +                   
                 "'" + area.getDescripcion() + "', " +                 
                "" + area.getEstado() + ", " +       
                 "'" + area.getTipo() + "'" +
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
    public boolean modificar(ModelArea area) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "UPDATE AREA " +
                 "SET " +
                 "DESCRIPCION = '" + area.getDescripcion() + "', " +
                 "ESTADO = " + area.getEstado()+ ", " + 
                 "TIPO = '" + area.getTipo() + "'" + 
                 
                 "WHERE ID_AREA =  " + area.getIdArea();
                
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
    public boolean eliminar(ModelArea area) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "DELETE AREA WHERE ID_AREA = " + area.getIdArea();
        
        
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
