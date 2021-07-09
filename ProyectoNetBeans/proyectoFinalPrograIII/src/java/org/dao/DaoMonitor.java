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
import org.interfaces.CrudMonitor;
import org.models.ModelMonitor;

import org.models.ModelProducto;
import org.models.ModelTmpPedido;

public class DaoMonitor implements  CrudMonitor{
    //Se crea un objeto publico del Cliente
    ModelMonitor moni = new ModelMonitor();
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
    public List listar(int tipoproducto) {
        ArrayList<ModelMonitor> lstMonitor = new ArrayList<>();
         try {            
                 strSql = "SELECT* FROM  VW_MONITOR_PEDIDO WHERE ID_TIPO_PRODUCTO =" +tipoproducto;

            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelMonitor vmonitor = new ModelMonitor();
                 vmonitor.setIdpedido(rs.getInt("ID_PEDIDO"));
                 vmonitor.setIddetelle(rs.getInt("ID_DETALLE"));
                 vmonitor.setIdarea(rs.getInt("ID_AREA"));
                 vmonitor.setIdmesa(rs.getInt("ID_MESA"));
                vmonitor.setIdproducto(rs.getString("ID_PRODUCTO"));
                vmonitor.setDescripcion(rs.getString("DESCRIPCION"));
                vmonitor.setCantidad(rs.getInt("CANTIDAD"));
                vmonitor.setTipoproducto(rs.getInt("ID_TIPO_PRODUCTO"));
                vmonitor.setIdcliente(rs.getInt("ID_CLIENTE"));
                vmonitor.setNomcliente(rs.getString("CLIENTE"));
                
                

              
               
                lstMonitor.add(vmonitor);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstMonitor;
    }

    @Override
    public ModelMonitor list(int id) {
      ModelMonitor vmonitor = new ModelMonitor();
    try {            
                    strSql = "SELECT* FROM  VW_MONITOR_PEDIDO WHERE ID_PEDIDO =" +id;

            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                
                 vmonitor.setIdpedido(rs.getInt("ID_PEDIDO"));
                 vmonitor.setIddetelle(rs.getInt("ID_DETALLE"));
                 vmonitor.setIdarea(rs.getInt("ID_AREA"));
                 vmonitor.setIdmesa(rs.getInt("ID_MESA"));
                vmonitor.setIdproducto(rs.getString("ID_PRODUCTO"));
                vmonitor.setDescripcion(rs.getString("DESCRIPCION"));
                vmonitor.setCantidad(rs.getInt("CANTIDAD"));
                vmonitor.setTipoproducto(rs.getInt("ID_TIPO_PRODUCTO"));
                vmonitor.setIdcliente(rs.getInt("ID_CLIENTE"));
                vmonitor.setNomcliente(rs.getString("CLIENTE"));
                
                

              
               
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoProducto.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return vmonitor;
    }
    
    @Override
    public boolean insertar(ModelMonitor monitor) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "INSERT INTO TMP_PEDIDO (ID_TMP, ID_PRODUCTO, CANTIDAD, ID_CLIENTE,USUARIO) ";
              
        
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
    public boolean modificar(ModelMonitor monitor) {
         //Se prepara la sentencia SQL a ejecutar en la BD
     String strSql1 = "update DETALLE_PEDIDO set ESTADO='FINALIZADO'\n" +
"WHERE ID_PEDIDO="+ monitor.getIdpedido() +
"AND ID_DETALLE=" + monitor.getIddetelle();
                 
  String sql ="select count(*) from DETALLE_PEDIDO \n" +
"WHERE ID_PEDIDO="+ monitor.getIdpedido() +
"and ESTADO='PENDIENTE'";  
  
  ModelMonitor vmonitor = new ModelMonitor();
           vmonitor=list(monitor.getIdpedido());     
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql1);
            
           int existe =Integer.parseInt(conexion.onedato(sql));
            if(existe==0){
              String sql2="update PEDIDO SET ESTADO='FINALIZADO',FECHA_MODIFICA=getdate(),USUARIO_MODIFICA='"+monitor.getUsuario() +"' WHERE ID_PEDIDO="+ monitor.getIdpedido();
              respuesta = conexion.executeSql(sql2);
             String sql3="update MESA SET ESTADO='LIBRE' WHERE ID_MESA="+ vmonitor.getIdmesa() + " and ID_AREA="+ vmonitor.getIdarea();
              respuesta = conexion.executeSql(sql3);
              String sql4="exec genera_factura "+vmonitor.getIdcliente() +","+vmonitor.getIdpedido();
               respuesta = conexion.executeSql(sql4);
            }
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
    public boolean eliminar(ModelMonitor monitor) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = " ";    
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
