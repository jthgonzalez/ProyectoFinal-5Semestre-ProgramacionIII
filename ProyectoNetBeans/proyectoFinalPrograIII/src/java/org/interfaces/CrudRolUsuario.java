/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelRolUsuario;


public interface CrudRolUsuario {
    public List listar();
    public ModelRolUsuario list (int id);
    public boolean insertar(ModelRolUsuario rolusuario);
    public boolean modificar(ModelRolUsuario rolusuario);
    public boolean eliminar(ModelRolUsuario rolusuario);    
}
