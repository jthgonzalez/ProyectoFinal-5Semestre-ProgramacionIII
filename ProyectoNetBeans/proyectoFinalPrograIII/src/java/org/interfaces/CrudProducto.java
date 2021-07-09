/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelProducto;


public interface CrudProducto {
     public List listar();
     public List listartmp(String q,int t);
    public ModelProducto list (int id);
    public boolean insertar(ModelProducto pro);
    public boolean modificar(ModelProducto pro);
    public boolean eliminar(ModelProducto pro);
}
