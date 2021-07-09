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
import org.interfaces.CrudOpcionMenu;
import org.models.ModelOpcionMenu;



public class DaoOpcionMenu implements CrudOpcionMenu {

    ModelOpcionMenu opcionmenu = new ModelOpcionMenu();
    String strSql =  "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;
     
    
    @Override
    public List listar() {
        ArrayList<ModelOpcionMenu> lstOpcionMenu = new ArrayList<>();
         try {            
            strSql = "SELECT * FROM OPCION_MENU where tipo='O'";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelOpcionMenu opcionmain = new ModelOpcionMenu();
                opcionmain.setModulo(rs.getString("modulo"));
                opcionmain.setOpcion(rs.getString("opcion"));
                opcionmain.setVisible(rs.getString("visible"));
                opcionmain.setLink(rs.getString("link"));
                opcionmain.setDescripcion(rs.getString("descripcion"));  
                lstOpcionMenu.add(opcionmain);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstOpcionMenu;
    }

    @Override
    public ModelOpcionMenu list(String id) {
        try {            
            strSql = "SELECT * FROM OPCION_MENU WHERE opcion = '" + id+"'";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                opcionmenu.setOpcion(rs.getString("opcion"));
                opcionmenu.setModulo(rs.getString("modulo"));
                opcionmenu.setDescripcion(rs.getString("descripcion"));
                opcionmenu.setLink(rs.getString("link"));
                opcionmenu.setVisible(rs.getString("visible"));
       
              
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return opcionmenu;
    }

    @Override
    public boolean insertar(ModelOpcionMenu opcionmain) {
        strSql = "INSERT INTO OPCION_MENU (tipo,opcion_padre,opcion, descripcion, modulo,visible,link) "
                + "VALUES ('O'," +  
               "'" + opcionmain.getModulo()+ "M1', " + 
                "'" + opcionmain.getOpcion()+ "', " + 
                "'" + opcionmain.getDescripcion()+ "', " + 
               "'" + opcionmain.getModulo()+ "', " + 
               "'" + opcionmain.getVisible()+ "', " +
                "'" + opcionmain.getLink()+ "' " +           
                  ")";
        
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean modificar(ModelOpcionMenu opcionmain) {
        strSql = "UPDATE OPCION_MENU " +
                 "SET " +
                 "descripcion  = '" + opcionmain.getDescripcion()+ "', " + 
                "visible= '" + opcionmain.getVisible()+ "', " + 
                "link= '" + opcionmain.getLink()+ "' " + 
                 "WHERE opcion='" + opcionmain.getOpcion()+ "' ";
        
        try {

            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(ModelOpcionMenu opcionmain) {
      
        strSql = "DELETE OPCION_MENU WHERE opcion='" + opcionmain.getOpcion()+ "' ";
        
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    @Override
    public List opcionesPadre(String pusuario,int idEmpresa) {
                ArrayList<ModelOpcionMenu> lstOpcionPadre = new ArrayList<>();
         try {            

            strSql=" Select Distinct\n" +
"    seo.opcion,seo.modulo,\n" +
"	seo.descripcion,\n" +
"    seo.icon\n" +
"     From\n" +
"       OPCION_MENU seo\n" +
"       Where\n" +
"        seo.tipo         = 'M'\n" +
"    And IsNull(\n" +
"     (Select Distinct Count(*)\n" +
"     From OPCION_MENU\n" +
"      Where visible='S' AND opcion_padre = seo.opcion And (tipo in ('M','S') or  \n" +
"                        (tipo = 'O' And opcion IN (Select Distinct A.opcion From ROL_OPCION A, rol_usuario B,MODULO_EMPRESA C\n" +
"                                                   Where C.modulo= A.modulo and A.id_rol = B.id_rol And a.id_empresa = b.id_empresa And b.id_empresa =" + idEmpresa +
"                                                   And upper(B.USUARIO) =upper('"+pusuario+"')\n" +
"                                                   )\n" +
"                        ))\n" +
"       ),0) >   0\n" +
"         And seo.opcion_padre Is Null order by seo.modulo";
                
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelOpcionMenu opcionmain = new ModelOpcionMenu();
                opcionmain.setOpcion(rs.getString("opcion"));
                opcionmain.setModulo(rs.getString("modulo"));
                opcionmain.setDescripcion(rs.getString("descripcion"));
                opcionmain.setIcon(rs.getString("icon"));
                lstOpcionPadre.add(opcionmain);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstOpcionPadre;

    }

    @Override
    public List opcionesHijo(String pusuario,String padre,int idEmpresa) {
                       ArrayList<ModelOpcionMenu> lstOpcionHijo = new ArrayList<>();
         try {            

            strSql="   SELECT DISTINCT opcion,descripcion,link\n" +
"	From OPCION_MENU \n" +
"	where visible='S' and opcion_padre='"+padre+"'\n" +
"    and ( tipo in ('M','S') or \n" +
"                     ( tipo='O' and opcion IN (select DISTINCT A.opcion \n" +
"                     from ROL_OPCION A,ROL_USUARIO B \n" +
"                     WHERE A.id_rol = B.id_rol and a.id_empresa = b.id_empresa and b.id_empresa=" + idEmpresa +
"                     AND upper(B.USUARIO) =upper('"+pusuario+"') ))) order by opcion";
            
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                ModelOpcionMenu opcionmain = new ModelOpcionMenu();
                opcionmain.setOpcion(rs.getString("opcion"));
                opcionmain.setDescripcion(rs.getString("descripcion"));
                opcionmain.setLink(rs.getString("link"));
                lstOpcionHijo.add(opcionmain);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DaoOpcionMenu.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstOpcionHijo;
    }

 
}
