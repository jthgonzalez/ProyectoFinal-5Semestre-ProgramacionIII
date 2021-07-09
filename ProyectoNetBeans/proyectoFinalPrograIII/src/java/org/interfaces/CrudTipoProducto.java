/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;

import org.models.ModelTipoProducto;


public interface CrudTipoProducto {
    public List listar();
    public ModelTipoProducto list (int id);
    public boolean insertar(ModelTipoProducto tipoproducto);
    public boolean modificar(ModelTipoProducto tipoproducto);
    public boolean eliminar(ModelTipoProducto tipoproducto);
    public String getNombre (int id);
   // public String ModuloId(String id);
}
