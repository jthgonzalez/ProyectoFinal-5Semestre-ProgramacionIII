/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelRoles;


public interface CrudRoles {
    public List listar();
    public ModelRoles list (int id);
    public boolean insertar(ModelRoles rol);
    public boolean modificar(ModelRoles rol);
    public boolean eliminar(ModelRoles rol);    
}
