/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelModulo;
import org.models.ModelRolOpcion;
import org.models.ModelRoles;


public interface CrudRolOpcion {
    public List listar(ModelRoles rol,ModelModulo modulo);
    public ModelRolOpcion list (int id);
    public boolean insertar(ModelRolOpcion rolopcion);
    public boolean modificar(ModelRolOpcion rolopcion);
    public boolean eliminar(ModelRolOpcion rolopcion);  
    public int getExisteModulo(ModelModulo modulo,ModelRoles roles);
}
