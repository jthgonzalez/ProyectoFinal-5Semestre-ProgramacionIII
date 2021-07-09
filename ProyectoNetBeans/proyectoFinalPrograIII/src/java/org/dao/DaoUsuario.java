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
import org.interfaces.CrudUsuario;
import org.models.ModelUsuario;
public class DaoUsuario implements CrudUsuario{    
    //ModelUsuario usuario = new ModelUsuario();
    //Variable para crear las sentencias SQL
    String strSql =  "";
    //Se crea un obejto de tipo conexión para manejar la persistencia hacia la base de datos
    Conexion conexion = new Conexion();    
    ResultSet rs = null;    
    boolean respuesta = false;   
      
    @Override
    public List listar(String buscar) {
        ArrayList<ModelUsuario> lstUsuario = new ArrayList<>();
         try {  
          
             strSql = "SELECT * FROM USUARIO";
                     
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelUsuario usu = new ModelUsuario();
                usu.setIdUsuario(rs.getInt("ID_USUARIO"));
                usu.setNombre(rs.getString("NOMBRE"));
                usu.setUsuario(rs.getString("USUARIO"));
                usu.setClave(rs.getString("CLAVE"));
                usu.setEstado(rs.getInt("ESTADO"));
                lstUsuario.add(usu);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstUsuario;
    }

    @Override
    public ModelUsuario list(int id) {
        ModelUsuario usuario = new ModelUsuario();
    try {            
            strSql = "SELECT * FROM USUARIO WHERE ID_USUARIO = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                
                usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setUsuario(rs.getString("USUARIO"));
                usuario.setClave(rs.getString("CLAVE"));
                usuario.setEstado(rs.getInt("ESTADO"));               
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return usuario;
    }

    @Override
    public boolean insertar(ModelUsuario usuario) {
     //Se prepara la sentencia SQL a ejecutar en la BD
    String    strSql2 = "INSERT INTO USUARIO (ID_USUARIO, NOMBRE, USUARIO, CLAVE, ESTADO) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_USUARIO),0) + 1 FROM USUARIO), " +                   
                 "'" + usuario.getNombre() + "', " +                 
                "'" + usuario.getUsuario()+ "', " +       
                 "'" + usuario.getClave() + "', " +
                "'" + usuario.getEstado()+ "' " +                
                  ")";
        String sql="delete ROL_USUARIO where upper(usuario) = upper('"+usuario.getUsuario()+"')";
        
        String sql2="insert into ROL_USUARIO(id_rol,id_empresa,usuario)"
                + "values("+ usuario.getRol()
                + ","+ getRolEmpresa(usuario.getRol()) +",'"+usuario.getUsuario()+"')";

        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql2);
            respuesta = conexion.executeSql(sql);
           respuesta = conexion.executeSql(sql2);
            
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelUsuario usuario) {
               //Se prepara la sentencia SQL a ejecutar en la BD
      String  strSql2 = "UPDATE USUARIO " +
                 "SET " +
                 "NOMBRE = '" + usuario.getNombre() + "', " +
                 "CLAVE = '" + usuario.getClave() + "', " + 
                 "ESTADO = " + usuario.getEstado() +
                 " WHERE ID_USUARIO =  " + usuario.getIdUsuario();
        
              String sql="delete ROL_USUARIO where upper(usuario) = upper('"+usuario.getUsuario()+"')";
        
        String sql2="insert into ROL_USUARIO(id_rol,id_empresa,usuario)"
                + "values("+ usuario.getRol()
                + ","+ getRolEmpresa(usuario.getRol()) +",'"+usuario.getUsuario()+"')";
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            
                respuesta = conexion.executeSql(strSql2);
            
            respuesta = conexion.executeSql(sql);
            respuesta = conexion.executeSql(sql2);
          
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
           
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);     
            respuesta= false;
        } catch(Exception ex){
             
             respuesta= false;
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelUsuario usuario) {
           //Se prepara la sentencia SQL a ejecutar en la BD
        
      ModelUsuario user=  list(usuario.getIdUsuario());
        strSql = "DELETE USUARIO WHERE  ID_USUARIO<>1 and ID_USUARIO = " + usuario.getIdUsuario();
        String sql=" delete ROL_USUARIO where upper(usuario) = upper('"+user.getUsuario()+"')";
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
           
          respuesta = conexion.executeSql(sql);
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean validarusuario(ModelUsuario usuario) {
         boolean existe = false;  
         
    try {            
            strSql = "SELECT count(*) FROM USUARIO "
                    + " WHERE upper(USUARIO) = upper('"+ usuario.getUsuario() +"') "
                    + " and clave = '"+usuario.getClave()+"'";
           conexion.open();
           if(Integer.parseInt(conexion.onedato(strSql)) >0) existe = true;
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return existe;
    }

    @Override
    public int getIdRol(ModelUsuario usuario) {
        int idrol=0;
        
    try {            
            strSql = "select isnull(id_rol,0) from rol_usuario "
                    + " WHERE upper(USUARIO) = upper('"+ usuario.getUsuario() +"') ";

           conexion.open();
           idrol=Integer.parseInt(conexion.onedato(strSql));
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        
  return idrol;
    }
    @Override
    public int getEmpresa(String pusuario) {
        int idempresa=0;
        
    try {            
            strSql = "select isnull(id_empresa,0) from rol_usuario "
                    + " WHERE upper(USUARIO) = upper('"+ pusuario +"') ";
            funciones fn = new funciones();
           conexion.open();
           idempresa=Integer.parseInt(conexion.onedato(strSql));
         
           fn.setEmpresa(idempresa);
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        
  return idempresa;
    }
      @Override
    public int getRolEmpresa(int idrol) {
        int idempresa=0;
        
    try {            
            strSql = "select id_empresa from ROLES\n" +
"where id_rol="+idrol;

           conexion.open();
           idempresa=Integer.parseInt(conexion.onedato(strSql));
           funciones fn = new funciones();
           fn.setEmpresa(idempresa);
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        
  return idempresa;
    }
}
