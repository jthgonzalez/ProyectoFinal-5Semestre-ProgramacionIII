/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelProducto;
import org.models.ModelTmpPedido;


public interface CrudTmpPedido {
    public List listar(int cliente,String usuario);
    public ModelProducto list(int id);
    public boolean insertar(ModelTmpPedido tmppedido);
    public int generaPedido(ModelTmpPedido tmppedido);
    public boolean modificar(ModelTmpPedido tmppedido);
    public boolean eliminar(ModelTmpPedido tmppedido);    
}
