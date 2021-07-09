/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelOpcionMenu;
import org.models.ModelUsuario;


public interface CrudOpcionMenu {
    public List listar();
    public ModelOpcionMenu list (String id);
    public boolean insertar(ModelOpcionMenu opcionmenu);
    public boolean modificar(ModelOpcionMenu opcionmenu);
    public boolean eliminar(ModelOpcionMenu opcionmenu);
    public List opcionesPadre(String pusuario,int idEmpresa);
    public List opcionesHijo(String pusuario,String padre,int idEmpresa);
}
