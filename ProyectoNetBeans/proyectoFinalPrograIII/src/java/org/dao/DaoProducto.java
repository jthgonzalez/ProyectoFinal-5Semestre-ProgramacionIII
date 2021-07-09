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
import org.interfaces.CrudProducto;
import org.models.ModelProducto;




    
public class DaoProducto implements CrudProducto{    
    //ModelUsuario usuario = new ModelUsuario();
    //Variable para crear las sentencias SQL
    String strSql =  "";
    //Se crea un obejto de tipo conexión para manejar la persistencia hacia la base de datos
    Conexion conexion = new Conexion();    
    ResultSet rs = null;    
    boolean respuesta = false;   
      
    @Override
    public List listar() {
        ArrayList<ModelProducto> lstProducto = new ArrayList<>();
         try {  
          
             strSql = "SELECT * FROM PRODUCTO";
                     
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelProducto pr = new ModelProducto();
                pr.setIdproducto(rs.getInt("ID_PRODUCTO"));
                pr.setIdtipoproducto(rs.getInt("ID_TIPO_PRODUCTO"));
                pr.setDescripcion(rs.getString("DESCRIPCION"));
                pr.setPrecio(rs.getInt("PRECIO"));
                pr.setExistencia(rs.getInt("EXISTENCIA"));
                pr.setEstado(rs.getInt("ESTADO"));
                pr.setIdempresa(rs.getInt("ID_EMPRESA"));
               
                lstProducto.add(pr);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstProducto;
    }

     @Override
    public List listartmp(String q,int t) {
        ArrayList<ModelProducto> lstProducto = new ArrayList<>();
         try {  
          boolean paso=false;
             strSql = "SELECT * FROM PRODUCTO ";
             if(!q.trim().equals("")){
             strSql= strSql + " WHERE upper(DESCRIPCION) LIKE upper('%"+q+"%') "; 
             paso=true;
             }
                if(t!=0 && paso){
             strSql= strSql + " and ID_TIPO_PRODUCTO="+t;    
             }
                    if(t!=0 && !paso){
             strSql= strSql + " WHERE ID_TIPO_PRODUCTO="+t;    
             }
             
            
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelProducto pr = new ModelProducto();
                pr.setIdproducto(rs.getInt("ID_PRODUCTO"));
                pr.setIdtipoproducto(rs.getInt("ID_TIPO_PRODUCTO"));
                pr.setDescripcion(rs.getString("DESCRIPCION"));
                pr.setPrecio(rs.getInt("PRECIO"));
                pr.setExistencia(rs.getInt("EXISTENCIA"));
                pr.setEstado(rs.getInt("ESTADO"));
                pr.setIdempresa(rs.getInt("ID_EMPRESA"));
               
                lstProducto.add(pr);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstProducto;
    }
    @Override
    public ModelProducto list(int id) {
        ModelProducto pr = new ModelProducto();
    try {            
            strSql = "SELECT * FROM PRODUCTO WHERE ID_PRODUCTO = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                pr.setIdproducto(rs.getInt("ID_PRODUCTO"));
                pr.setIdtipoproducto(rs.getInt("ID_TIPO_PRODUCTO"));
                pr.setDescripcion(rs.getString("DESCRIPCION"));
                pr.setPrecio(rs.getInt("PRECIO"));
                pr.setExistencia(rs.getInt("EXISTENCIA"));
                pr.setEstado(rs.getInt("ESTADO"));
                pr.setIdempresa(rs.getInt("ID_EMPRESA"));
                
                   
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return pr;
    }

    @Override
    public boolean insertar(ModelProducto pr) {
     //Se prepara la sentencia SQL a ejecutar en la BD
    String    strSql2 = "INSERT INTO PRODUCTO (ID_PRODUCTO, ID_TIPO_PRODUCTO, DESCRIPCION, PRECIO, EXISTENCIA, ESTADO, ID_EMPRESA) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_PRODUCTO),0) + 1 FROM PRODUCTO), " +                   
                 "'" + pr.getIdtipoproducto() + "', " +
                "'" + pr.getDescripcion()+ "', " + 
                 "'" + pr.getPrecio()+ "', " +
                "'" + pr.getExistencia()+ "', " +  
                 "'" + pr.getEstado() + "', " +
                "'" + pr.getIdempresa()+ "' " +                
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
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelProducto pr) {
               //Se prepara la sentencia SQL a ejecutar en la BD
      String  strSql2 = "UPDATE PRODUCTO " +
                 "SET " +
                 "ID_EMPRESA = '" + pr.getIdempresa() + "', " +
                 "DESCRIPCION = '" + pr.getDescripcion() + "', " + 
                 "EXISTENCIA = '" + pr.getExistencia() + "', " + 
                 "PRECIO = '" + pr.getPrecio() + "', " + 
                 "ID_TIPO_PRODUCTO = '" + pr.getIdtipoproducto() + "', " + 
                 "ESTADO = " + pr.getEstado() +
                 " WHERE ID_PRODUCTO =  " + pr.getIdproducto();
        
             
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
           
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);     
            respuesta= false;
        } catch(Exception ex){
             
             respuesta= false;
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelProducto pr) {
           //Se prepara la sentencia SQL a ejecutar en la BD
        
      //ModelTipoProducto user=  list(usuario.getIdUsuario());
        strSql = "DELETE PRODUCTO WHERE  ID_PRODUCTO = " + pr.getIdproducto();
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
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

   
}
