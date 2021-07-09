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
import org.models.ModelSucursal;
import org.interfaces.CrudSucursal;    
public class DaoSucursal implements CrudSucursal{    
    //ModelUsuario usuario = new ModelSucursal();
    //Variable para crear las sentencias SQL
    String strSql =  "";
    //Se crea un obejto de tipo conexión para manejar la persistencia hacia la base de datos
    Conexion conexion = new Conexion();    
    ResultSet rs = null;    
    boolean respuesta = false;   
      
    
    
    @Override
    public List listar() {
        ArrayList<ModelSucursal> lstSucursal = new ArrayList<>();
         try {  
              strSql = "SELECT * FROM SUCURSAL";
              
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelSucursal sucursal = new ModelSucursal();
                sucursal.setIdSucursal(rs.getInt("ID_SUCURSAL"));
                sucursal.setIdEmpresa(rs.getInt("ID_EMPRESA"));
               sucursal.setNombre(rs.getString("NOMBRE"));
               sucursal.setDireccion(rs.getString("DIRECCION"));
                sucursal.setDescripcion(rs.getString("DESCRIPCION"));
                sucursal.setTelefono(rs.getString("TELEFONO"));
                sucursal.setEstado(rs.getInt("ESTADO"));
                lstSucursal.add(sucursal);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoSucursal.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoSucursal.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstSucursal;
    }

    @Override
    public ModelSucursal list(int id) {
        ModelSucursal sucursal = new ModelSucursal();
    try {            
            strSql = "SELECT * FROM SUCURSAL WHERE ID_SUCURSAL = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                
            sucursal.setIdSucursal(rs.getInt("ID_SUCURSAL"));
                sucursal.setIdEmpresa(rs.getInt("ID_EMPRESA"));
               sucursal.setNombre(rs.getString("NOMBRE"));
               sucursal.setDireccion(rs.getString("DIRECCION"));
                sucursal.setDescripcion(rs.getString("DESCRIPCION"));
                sucursal.setTelefono(rs.getString("TELEFONO"));
                sucursal.setEstado(rs.getInt("ESTADO"));             
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoSucursal.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoSucursal.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return sucursal;
    }

    @Override
    public boolean insertar(ModelSucursal sucursal) {
     //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "INSERT INTO SUCURSAL (ID_SUCURSAL,ID_EMPRESA, NOMBRE, DESCRIPCION,DIRECCION, TELEFONO, ESTADO) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_SUCURSAL),0) + 1 FROM SUCURSAL), " +                   
                  "" + sucursal.getIdEmpresa() + ", " +   
                "'" + sucursal.getNombre() + "', " +                 
                "'" + sucursal.getDescripcion()+ "', " +       
                 "'" + sucursal.getDireccion() + "', " +
                "'" + sucursal.getTelefono() + "', " +
                "'" + sucursal.getEstado()+ "' " +                
                  ")";

        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoSucursal.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoSucursal.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelSucursal sucursal) {
               //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "UPDATE SUCURSAL " +
                 "SET " +
                 "NOMBRE = '" + sucursal.getNombre() + "', " +
                 "DESCRIPCION = '" + sucursal.getDescripcion()+ "', " + 
                 "DIRECCION = '" + sucursal.getDireccion() + "', " + 
                "TELEFONO = '" + sucursal.getTelefono() + "', " + 
                "ID_EMPRESA = " + sucursal.getIdEmpresa() + ", " + 
                 "ESTADO = " + sucursal.getEstado() +
                 "WHERE ID_SUCURSAL =  " + sucursal.getIdSucursal();
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoSucursal.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoSucursal.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelSucursal usuario) {
           //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "DELETE SUCURSAL WHERE ID_SUCURSAL = " + usuario.getIdSucursal();
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoSucursal.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoSucursal.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
    
}
