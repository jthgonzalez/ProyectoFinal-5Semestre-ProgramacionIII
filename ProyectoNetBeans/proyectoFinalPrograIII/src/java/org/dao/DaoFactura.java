/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.clases.var;
import org.config.Conexion;
import org.models.ModelFactura;



public class DaoFactura {
     //Se crea un objeto publico del Cliente
    ModelFactura fac = new ModelFactura();
    String strSql =  "";
    //Se crea un obejto de tipo conexión para manejar la persistencia hacia la base de datos
    Conexion conexion = new Conexion();
    //Obtiene el resultado de las consultas SQL
    ResultSet rs = null;
    //flag para retornar si la sentencia SQL fue satisfactorio o no
    boolean respuesta = false;
    
    //Método que obtiene la consulta de la tabla Clliente
    public List <ModelFactura> getListaFac() {
        List <ModelFactura> lstFac = new ArrayList<ModelFactura>() ;
        try {            
            strSql = "SELECT * FROM VW_FACTURA WHERE ESTADO='G'";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
          
            while (rs.next()) {
                ModelFactura fac = new ModelFactura();
                fac.setId(Integer.parseInt(rs.getString("ID_FACTURA")));
                fac.setSerie(rs.getString("SERIE"));
                fac.setFecha(rs.getString("FECHA_FAC"));
                fac.setIdCliente(Integer.valueOf(rs.getString("ID_CLIENTE")));
                fac.setTotal(Double.valueOf(rs.getString("TOTAL")));
                fac.setPedido(rs.getInt("ID_PEDIDO"));
                fac.setEstado(rs.getString("ESTADO"));
                fac.setArea(rs.getInt("ID_AREA"));
                fac.setMesa(rs.getInt("ID_MESA"));
                fac.setCliente(rs.getString("CLIENTE"));
      
                lstFac.add(fac);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        return lstFac;
    }
    
    //Método para hacer la inserción del tipofac en la BD
    public boolean insertarFac(ModelFactura fac,DefaultTableModel model) throws Exception{
        //Se prepara la sentencia SQL a ejecutar en la BD
    
    
        try {
            //se abre una conexión hacia la BD
            conexion.open();
           
   
            
            String   sql = " select isnull(MAX(ID_FACTURA),0)+1 as id from POS.FACTURA";
            
            int idfac = Integer.parseInt(conexion.onedato(sql));
            strSql = "INSERT INTO POS.FACTURA (ID_FACTURA,SERIE,FECHA,ID_CLIENTE,TOTAL,TIPO_PAGO) "
                + "VALUES ( "
                
                + "" + idfac + " ," +
                  "'" + fac.getSerie() + " '," +
                " GETDATE() ," +
                  + fac.getIdCliente()+ ", " +
                  + fac.getTotal() + ", " +
                 "" + fac.getTipopago() + "" +  
         ")";
        
        
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            
            insertaFacDetalle(model,idfac);
          
String total = " update POS.FACTURA set TOTAL=(";
 total+="select SUM(SUBTOTAL) from POS.DETALLE_FACTURA ";
 total+=" where ID_FACTURA ="+ idfac;
 total +=") ";
 total+="  where  ID_FACTURA ="+ idfac;
                

        conexion.executeSql(total);
          var.setG_Cliente(fac.getIdCliente());
          var.setSerie("A");
          var.setG_NoFac(idfac);
        
        
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
       
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
          
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);            
        }finally{
            
        }
        return respuesta;
    }
    
    private void insertaFacDetalle(DefaultTableModel model,int idfac) throws SQLException, Exception{
        
      for(int i = 0; i<model.getRowCount(); i++)  {
          
          
          
          
         String sql ="insert into POS.DETALLE_FACTURA(ID_FACTURA,SERIE,"
                 + "ID_DETALLE_FACTURA,ID_PRODUCTO,PRECIO,CANTIDAD,SUBTOTAL)\n" +
                    "VALUES("+ idfac +",'A',"
                 + ""+ conexion.onedato(" select isnull(MAX(ID_DETALLE_FACTURA),0)+1 as id from POS.DETALLE_FACTURA") +","
                 + ""+ model.getValueAt(i, 0).toString() +","
                + ""+ model.getValueAt(i, 2).toString() +","
                 + ""+ model.getValueAt(i, 3).toString() +","
                 + ""+  (Double.valueOf(model.getValueAt(i, 2).toString()) * Integer.parseInt(model.getValueAt(i, 3).toString()))  +")";
          
         conexion.executeSql(sql);
         
         String update= " update POS.PRODUCTO set EXISTENCIA = EXISTENCIA - "+ model.getValueAt(i, 3).toString() +"" +
                " where ID_PRODUCTO ="+model.getValueAt(i, 0).toString();
         conexion.executeSql(update);
         
      }
        
    }
    
    
    //Método para hacer la modificación del tipofac en la BD
    public boolean modificaFac(ModelFactura fac){
        
        strSql = "UPDATE POS.FACTURA "
                + "SET " +
               
                    " SERIE= '" + fac.getSerie() + "' ," +
                    " ID_CLIENTE= " + fac.getIdCliente() + " ," +  
                    " TOTAL= " + fac.getTotal() + " ," +  
                    " TIPO_PAGO = " + fac.getTipopago() + ""+
                    " WHERE "  +
                    " ID_PRODUCTO= "+ fac.getId();
        
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
    
    //Método para hacer la eliminación del tipofac en la BD
    public boolean eliminaProd(ModelFactura fac){
        
        strSql = "DELETE POS.PRODUCTO " +                           
                    " WHERE "  +
                    " ID_PRODUCTO ="+ fac.getId();    
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoFactura.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
}
