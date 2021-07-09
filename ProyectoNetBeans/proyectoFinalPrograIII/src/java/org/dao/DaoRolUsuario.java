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
import org.config.Conexion;
import org.interfaces.CrudRolUsuario;
import org.models.ModelRolUsuario;


public class DaoRolUsuario implements  CrudRolUsuario{
    
    ModelRolUsuario roles = new ModelRolUsuario();
    String strSql =  "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;
    
    
    
    @Override
    public List listar() {
        ArrayList<ModelRolUsuario>lstRoles = new ArrayList<>();
         try {            
            strSql = "SELECT * FROM ROL_USUARIO";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelRolUsuario rol = new ModelRolUsuario();
                rol.setIdRol(rs.getInt("ID_ROL"));
                rol.setIdEmpresa(rs.getInt("ID_EMPRESA"));
                rol.setUsuario(rs.getString("USUARIO"));     
                lstRoles.add(rol);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRolUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoRolUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstRoles;
    }

    @Override
    public ModelRolUsuario list(int id) {
        try {            
            strSql = "SELECT * FROM ROL_USUARIO WHERE ID_ROL = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                roles.setIdRol(rs.getInt("ID_ROL"));               
                roles.setUsuario(rs.getString("USUARIO"));
                roles.setIdEmpresa(rs.getInt("ID_EMPRESA"));    
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRolUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoRolUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return roles;
    }

    @Override
    public boolean insertar(ModelRolUsuario roles) {
        strSql = "INSERT INTO ROL_USUARIO (ID_ROL,USUARIO, ID_EMPRESA) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_ROL),0) + 1 FROM ROL_USUARIO), " +                      
                 "'" + roles.getUsuario() + "', " +
                 "" + roles.getIdEmpresa()+ "" +                 
                  ")";
        
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRolUsuario.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoRolUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelRolUsuario roles) {
        strSql = "UPDATE ROL_USUARIO " +
                 "SET " +
                  
                 "USUARIO = '" + roles.getUsuario() + "', " + 
                
                 "ID_EMPRESA = " + roles.getIdEmpresa()+ " " +
                 "WHERE ID_ROL=  " + roles.getIdRol();
        try {

            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRolUsuario.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoRolUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelRolUsuario roles) {
      
        strSql = "DELETE ROL_USUARIO WHERE ID_ROL = " + roles.getIdRol();
        
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRolUsuario.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoRolUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    

   
}
