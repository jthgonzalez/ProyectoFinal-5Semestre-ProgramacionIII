/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;


import java.util.List;
import org.models.ModelMesa;

public interface CrudMesa {
   public List listarMesas(int idArea);
     public List listar();
    public ModelMesa list (int id);
    public String mesaNombre (int id);
    public boolean insertar(ModelMesa mesa);
    public boolean modificar(ModelMesa mesa);
    public boolean eliminar(ModelMesa mesa);
    
}
