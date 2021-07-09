/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.clases.funciones;
import org.config.Conexion;
import org.interfaces.CrudModuloEmpresa;
import org.models.ModelModulo;
import org.models.ModelModuloEmpresa;


public class DaoModuloEmpresa implements CrudModuloEmpresa{
    String strSql =  "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;
    @Override
    public List listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModelModuloEmpresa list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertar(ModelModuloEmpresa moduloempresa) {
               
        strSql = "INSERT INTO MODULO_EMPRESA(MODULO,ID_EMPRESA)\n" +
            "values('"+moduloempresa.getModulo()+ "',"+moduloempresa.getId_empresa()+")";
        
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
    public boolean modificar(ModelModuloEmpresa moduloempresa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(ModelModuloEmpresa moduloempresa) {
           
        strSql = "DELETE MODULO_EMPRESA\n" +
            "WHERE modulo = '"+moduloempresa.getModulo()+ "' and id_empresa="+moduloempresa.getId_empresa();
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
    public int existeModulo(ModelModulo modulo, int empresa) {
        int existe=0;
        strSql = "select count(*) from MODULO_EMPRESA\n" +
            "WHERE modulo = '"+modulo.getModulo()+ "' and id_empresa="+empresa;
   
        try {
            conexion.open();
             existe =Integer.parseInt(conexion.onedato(strSql));
           conexion.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRolOpcion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoRolOpcion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoRolOpcion.class.getName()).log(Level.SEVERE, null, ex);
        }
     return existe;
    }

    
 
}
