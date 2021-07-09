/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelFactura;


public interface CrudFactura {
    
    public List listar();
    public ModelFactura list (int id);
    public boolean insertar(ModelFactura rol);
    public boolean modificar(ModelFactura rol);
    public boolean eliminar(ModelFactura rol);  
    
}
