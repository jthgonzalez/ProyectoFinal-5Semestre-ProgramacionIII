/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelSucursal;


public interface CrudSucursal {
    
    public List listar();
    public ModelSucursal list (int id);
    public boolean insertar(ModelSucursal sucursal);
    public boolean modificar(ModelSucursal sucursal);
    public boolean eliminar(ModelSucursal sucursal);
}

