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
import org.interfaces.CrudTipoProducto;
import org.models.ModelTipoProducto;




    
public class DaoTipoProducto implements CrudTipoProducto{    
    //ModelUsuario usuario = new ModelUsuario();
    //Variable para crear las sentencias SQL
    String strSql =  "";
    //Se crea un obejto de tipo conexión para manejar la persistencia hacia la base de datos
    Conexion conexion = new Conexion();    
    ResultSet rs = null;    
    boolean respuesta = false;   
      
    @Override
    public List listar() {
        ArrayList<ModelTipoProducto> lstTipoProducto = new ArrayList<>();
         try {  
          
             strSql = "SELECT * FROM TIPO_PRODUCTO";
                     
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelTipoProducto pr = new ModelTipoProducto();
                pr.setIdTipoProducto(rs.getInt("ID_TIPO_PRODUCTO"));
                pr.setDescripcion(rs.getString("DESCRIPCION"));
                pr.setEstado(rs.getInt("ESTADO"));
                pr.setIdEmpresa(rs.getInt("ID_EMPRESA"));
               
                lstTipoProducto.add(pr);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoTipoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoTipoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstTipoProducto;
    }

    @Override
    public ModelTipoProducto list(int id) {
        ModelTipoProducto pr = new ModelTipoProducto();
    try {            
            strSql = "SELECT * FROM TIPO_PRODUCTO WHERE ID_TIPO_PRODUCTO = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                
                pr.setIdTipoProducto(rs.getInt("ID_TIPO_PRODUCTO"));
                pr.setDescripcion(rs.getString("DESCRIPCION"));
                pr.setIdEmpresa(rs.getInt("ID_EMPRESA"));
                
                pr.setEstado(rs.getInt("ESTADO"));               
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoTipoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoTipoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return pr;
    }

    @Override
    public boolean insertar(ModelTipoProducto pr) {
     //Se prepara la sentencia SQL a ejecutar en la BD
    String    strSql2 = "INSERT INTO TIPO_PRODUCTO (ID_TIPO_PRODUCTO, DESCRIPCION, ESTADO, ID_EMPRESA) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_TIPO_PRODUCTO),0) + 1 FROM TIPO_PRODUCTO), " +                   
               //  "'" + pr.getIdTipoProducto() + "', " +                 
                "'" + pr.getDescripcion()+ "', " +       
                 "'" + pr.getEstado() + "', " +
                "'" + pr.getIdEmpresa()+ "' " +                
                  ")";
       
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql2);
            //respuesta = conexion.executeSql(sql);
         //  respuesta = conexion.executeSql(sql2);
            
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoTipoProducto.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoTipoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelTipoProducto pr) {
               //Se prepara la sentencia SQL a ejecutar en la BD
      String  strSql2 = "UPDATE TIPO_PRODUCTO " +
                 "SET " +
                 "ID_EMPRESA = '" + pr.getIdEmpresa() + "', " +
                 "DESCRIPCION = '" + pr.getDescripcion() + "', " + 
                 "ESTADO = " + pr.getEstado() +
                 " WHERE ID_TIPO_PRODUCTO =  " + pr.getIdTipoProducto();
        
             
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            
                respuesta = conexion.executeSql(strSql2);
            
            //respuesta = conexion.executeSql(sql);
            //respuesta = conexion.executeSql(sql2);
          
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
           
            Logger.getLogger(DaoTipoProducto.class.getName()).log(Level.SEVERE, null, ex);     
            respuesta= false;
        } catch(Exception ex){
             
             respuesta= false;
            Logger.getLogger(DaoTipoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelTipoProducto pr) {
           //Se prepara la sentencia SQL a ejecutar en la BD
        
      //ModelTipoProducto user=  list(usuario.getIdUsuario());
        strSql = "DELETE TIPO_PRODUCTO WHERE  ID_TIPO_PRODUCTO = " + pr.getIdTipoProducto();
        //String sql=" delete ROL_USUARIO where upper(usuario) = upper('"+user.getUsuario()+"')";
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
           
          //respuesta = conexion.executeSql(sql);
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoTipoProducto.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoTipoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
@Override
    public String getNombre(int id) {
        ModelTipoProducto pr = new ModelTipoProducto();
  try {            
            strSql = "SELECT * FROM TIPO_PRODUCTO WHERE ID_TIPO_PRODUCTO = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                pr.setIdEmpresa(rs.getInt("ID_EMPRESA"));
                pr.setIdTipoProducto(rs.getInt("ID_TIPO_PRODUCTO"));
                pr.setDescripcion(rs.getString("DESCRIPCION"));
                pr.setEstado(rs.getInt("ESTADO"));                
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return pr.getDescripcion();
    }
   
}
