/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelEmpresa;


public interface CrudEmpresa {
    public List listar();
    public ModelEmpresa list (int id);
    public boolean insertar(ModelEmpresa empresa);
    public boolean modificar(ModelEmpresa empresa);
    public boolean eliminar(ModelEmpresa empresa);
    public String getNombre(int id);
    
}
