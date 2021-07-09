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
import org.interfaces.CrudTmpPedido;
import org.models.ModelArea;
import org.models.ModelProducto;
import org.models.ModelTmpPedido;

public class DaoTmpPedido implements  CrudTmpPedido{
    //Se crea un objeto publico del Cliente
    ModelTmpPedido pedido = new ModelTmpPedido();
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
    public List listar(int cliente,String usuario) {
        ArrayList<ModelTmpPedido> lstPedido = new ArrayList<>();
         try {            
                 strSql = "select a.ID_PRODUCTO,A.DESCRIPCION,b.CANTIDAD,A.PRECIO\n" +
",ISNULL(b.CANTIDAD,1)* ISNULL(A.PRECIO,0) AS SUB_TOTAL from PRODUCTO a\n" +
"inner join TMP_PEDIDO b on\n" +
"a.ID_PRODUCTO = b.ID_PRODUCTO\n" +
"where B.ID_CLIENTE=" + cliente +
"AND upper(B.USUARIO)=upper('"+usuario+"')";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelTmpPedido vpedido = new ModelTmpPedido();
                vpedido.setIdProducto(rs.getString("ID_PRODUCTO"));
                vpedido.setDescripcion(rs.getString("DESCRIPCION"));
                vpedido.setCantidad(rs.getInt("CANTIDAD"));
                vpedido.setPrecio(rs.getDouble("PRECIO"));
                vpedido.setSubtotal(rs.getDouble("SUB_TOTAL"));
              
               
                lstPedido.add(vpedido);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstPedido;
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
    public boolean insertar(ModelTmpPedido tmppedido) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "INSERT INTO TMP_PEDIDO (ID_TMP, ID_PRODUCTO, CANTIDAD, ID_CLIENTE,USUARIO) "
                + "VALUES ( (SELECT ISNULL(MAX(ID_TMP),0) + 1 FROM TMP_PEDIDO), " +                   
                 "'" + tmppedido.getIdProducto() + "', " +                 
                "" + tmppedido.getCantidad() + ", " +       
                 "" + tmppedido.getIdCliente() + "," +
                "'" + tmppedido.getUsuario() + "'" +
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
    public boolean modificar(ModelTmpPedido tmppedido) {
         //Se prepara la sentencia SQL a ejecutar en la BD
//      strSql = "UPDATE AREA " +
//                 "SET " +
//                 "DESCRIPCION = '" + area.getDescripcion() + "', " +
//                 "ESTADO = " + area.getEstado()+ ", " + 
//                 "TIPO = '" + area.getTipo() + "'" + 
//                 
//                 "WHERE ID_AREA =  " + area.getIdArea();
                
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
    public boolean eliminar(ModelTmpPedido tmppedido) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "DELETE TMP_PEDIDO "+
        " where ID_CLIENTE=" + tmppedido.getIdCliente() +
" AND upper(USUARIO)=upper('"+ tmppedido.getUsuario() +"')"+
 " AND ID_PRODUCTO='"+ tmppedido.getIdProducto() +"'";       
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
    public int generaPedido(ModelTmpPedido tmppedido) {
        int nopedido=0;
           try {            
                 strSql = "exec genera_pedido "+ tmppedido.getIdCliente() +",'"+tmppedido.getUsuario() +"'"
                         + ","+tmppedido.getMesa() +","+tmppedido.getArea();
            conexion.open();
               
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
               
           nopedido=rs.getInt(1);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return nopedido;
    }

 
}
