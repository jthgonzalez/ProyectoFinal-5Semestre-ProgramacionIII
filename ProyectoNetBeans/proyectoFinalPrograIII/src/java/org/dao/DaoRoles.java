/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.clases.funciones;
import org.config.Conexion;
import org.interfaces.CrudRoles;
import org.models.ModelRoles;


public class DaoRoles implements  CrudRoles{
    
    ModelRoles roles = new ModelRoles();
    String strSql =  "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;
  
    
    
    @Override
    public List listar() {
        ArrayList<ModelRoles>lstRoles = new ArrayList<>();
         try {            
            strSql = "SELECT * FROM ROLES";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelRoles rol = new ModelRoles();
                rol.setIdRol(rs.getInt("ID_ROL"));
                rol.setNombre(rs.getString("NOMBRE"));
                rol.setIdEmpresa(rs.getInt("ID_EMPRESA"));     
                lstRoles.add(rol);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstRoles;
    }

    @Override
    public ModelRoles list(int id) {
        try {            
            strSql = "SELECT * FROM ROLES WHERE ID_ROL = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                roles.setIdRol(rs.getInt("ID_ROL"));               
                roles.setNombre(rs.getString("NOMBRE"));
                roles.setIdEmpresa(rs.getInt("ID_EMPRESA"));    
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return roles;
    }

    @Override
    public boolean insertar(ModelRoles roles) {
        strSql = "INSERT INTO ROLES (ID_ROL,NOMBRE, ID_EMPRESA) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_ROL),0) + 1 FROM ROLES), " +                      
                 "'" + roles.getNombre() + "', " +
                 "" + roles.getIdEmpresa()+ "" +                 
                  ")";
        
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelRoles roles) {
        strSql = "UPDATE ROLES " +
                 "SET " +
                  
                 "NOMBRE = '" + roles.getNombre() + "', " + 
                
                 "ID_EMPRESA = " + roles.getIdEmpresa()+ " " +
                 "WHERE ID_ROL=  " + roles.getIdRol();
        try {

            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelRoles roles) {
      
        strSql = "DELETE ROLES WHERE ID_ROL = " + roles.getIdRol();
        
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    

   
}
