/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelModulo;


public interface CrudModulo {
    public List listar();
    public ModelModulo list (int id);
    public boolean insertar(ModelModulo modulo);
    public boolean modificar(ModelModulo modulo);
    public boolean eliminar(ModelModulo modulo);
    public String ModuloId(String id);
}
