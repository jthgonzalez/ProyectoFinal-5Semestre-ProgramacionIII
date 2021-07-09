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
import org.interfaces.CrudModulo;
import org.models.ModelModulo;


public class DaoModulos implements  CrudModulo{
    
    ModelModulo modulos = new ModelModulo();
    String strSql =  "";
    String strSql2 =  "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;
      
    
    
    @Override
    public List listar() {
        ArrayList<ModelModulo> lstModulos = new ArrayList<>();
         try {            
            strSql = "SELECT * FROM MODULO order by 1";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelModulo modulo = new ModelModulo();
                modulo.setId_modulo(rs.getInt("id_modulo"));
                modulo.setModulo(rs.getString("modulo"));
                modulo.setDescripcion(rs.getString("descripcion"));
                 modulo.setAyuda(rs.getString("ayuda"));  
                lstModulos.add(modulo);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstModulos;
    }

    @Override
    public ModelModulo list(int id) {
        try {            
            strSql = "SELECT * FROM MODULO WHERE id_modulo = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                modulos.setId_modulo(rs.getInt("id_modulo")); 
                modulos.setModulo(rs.getString("modulo"));
                modulos.setDescripcion(rs.getString("descripcion"));
                modulos.setAyuda(rs.getString("ayuda"));
              
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return modulos;
    }

    @Override
    public boolean insertar(ModelModulo modulo) {
    String    strSql1 = "INSERT INTO MODULO (id_modulo, modulo, descripcion, ayuda) "
                + "VALUES ( (SELECT ISNULL(MAX(id_modulo),0) + 1 FROM MODULO), " +                   
               "'" + modulo.getModulo()+ "', " + 
                "'" + modulo.getDescripcion()+ "', " +       
                 "'" + modulo.getAyuda() + "'" +//aqui
                               
                  ")";
     String   strSql3="INSERT INTO OPCION_MENU (tipo,opcion, descripcion, modulo,visible,link) \n" +
           "values('M','"+modulo.getModulo()+"M1',"
                + "'"+modulo.getDescripcion()+"','"+modulo.getModulo()+"','S','#')";
        
        String sql="insert into ROL_OPCION(id_empresa,id_rol,opcion,modulo)"
                + "values(1,1,"
                + "'"+modulo.getModulo()+"M1',"
                + "'"+modulo.getModulo()+"')";
        
        
         String sq = "INSERT INTO MODULO_EMPRESA(MODULO,ID_EMPRESA)" 
          +  "values('"+modulo.getModulo()+ "',1)";

         
        try {
            conexion.open();
            conexion.begin();
            respuesta = conexion.executeSql(strSql1);
            respuesta = conexion.executeSql(strSql3);
            respuesta = conexion.executeSql(sql);
            respuesta = conexion.executeSql(sq);
            conexion.commit();
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conexion.rollbak();
                 conexion.close();
            } catch (Exception ex1) {
                Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex1);
            }
           
            return false;
        } catch(Exception ex){
                  try {
                conexion.rollbak();
                 conexion.close();
            } catch (Exception ex1) {
                Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex1);
            }
           
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelModulo modulo) {
        strSql = "UPDATE MODULO " +
                 "SET " +
                 "modulo = '" + modulo.getModulo()+ "', " + 
                 "descripcion = '" + modulo.getDescripcion()+ "', " + 
                 "ayuda = '" + modulo.getAyuda() + "' " + 
                
              
                 "WHERE id_modulo=  " + modulo.getId_modulo();
        try {

            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelModulo modulo) {
      
        strSql = "DELETE MODULO WHERE id_modulo = " + modulo.getId_modulo();
        
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    
        @Override
    public String ModuloId(String id) {
        try {            
            strSql = "SELECT * FROM MODULO WHERE modulo = '" + id +"'";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                modulos.setId_modulo(rs.getInt("id_modulo")); 
                modulos.setModulo(rs.getString("modulo"));
                modulos.setDescripcion(rs.getString("descripcion"));
                modulos.setAyuda(rs.getString("ayuda"));
              
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoModulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return modulos.getModulo();
    }

    

   
}
