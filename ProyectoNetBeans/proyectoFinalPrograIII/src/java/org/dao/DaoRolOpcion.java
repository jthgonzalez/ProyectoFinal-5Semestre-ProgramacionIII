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
import org.clases.funciones;
import org.config.Conexion;
import org.interfaces.CrudRolOpcion;
import org.models.ModelModulo;
import org.models.ModelRolOpcion;
import org.models.ModelRoles;


public class DaoRolOpcion implements CrudRolOpcion {
    ModelRolOpcion rolopcion = new ModelRolOpcion();
    String strSql =  "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;
    
    DaoUsuario daoUsuario = new DaoUsuario();
    
    @Override
    public List listar(ModelRoles rol,ModelModulo modulo) {
          ArrayList<ModelRolOpcion> lstRolOpcion = new ArrayList<>();
         try {            
            strSql = "select a.opcion,a.descripcion,a.modulo,\n" +
"(select count(*) from ROL_OPCION x \n" +
"where x.opcion = a.opcion and x.modulo = a.modulo and x.id_rol="+rol.getIdRol()+") as existe\n" +
" from OPCION_MENU a\n" +
" where  a.modulo = '" +modulo.getModulo()+ "'\n" +
" and a.tipo = 'O'";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelRolOpcion opcionrol = new ModelRolOpcion();
                opcionrol.setModulo(rs.getString("modulo"));
                opcionrol.setOpcion(rs.getString("opcion"));
                opcionrol.setExiste(rs.getInt("existe"));
                opcionrol.setDescripcion(rs.getString("descripcion"));  
                lstRolOpcion.add(opcionrol);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRolOpcion.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoRolOpcion.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstRolOpcion;
    }

    @Override
    public ModelRolOpcion list(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertar(ModelRolOpcion rolopcion) {
              strSql = "INSERT INTO ROL_OPCION (ID_ROL,ID_EMPRESA,OPCION,MODULO) "
                + "VALUES (" +                      
                 "" + rolopcion.getId_rol() + ", " +
                 "" + daoUsuario.getRolEmpresa(rolopcion.getId_rol()) + "," +  
                 "'" + rolopcion.getOpcion()+ "'," +
                 "'" + rolopcion.getModulo()+ "'" + 
                  ")";
     
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoRoles.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelRolOpcion rolopcion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(ModelRolOpcion rolopcion) {
    strSql = "delete ROL_OPCION\n" +
"WHERE opcion = '"+ rolopcion.getOpcion() +"'\n" +
"and id_rol=" + rolopcion.getId_rol() +
"             ";
        
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoRolOpcion.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoRolOpcion.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public int getExisteModulo(ModelModulo modulo,ModelRoles roles) {
        int existe=0;
        strSql = "select count(*) from ROL_OPCION\n" +
"WHERE opcion = '"+modulo.getModulo()+ "M1' and id_rol="+roles.getIdRol();
   
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
