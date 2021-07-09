/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelModulo;
import org.models.ModelModuloEmpresa;


public interface CrudModuloEmpresa {
    public List listar();
    public ModelModuloEmpresa list (int id);
    public boolean insertar(ModelModuloEmpresa moduloempresa);
    public boolean modificar(ModelModuloEmpresa moduloempresa);
    public boolean eliminar(ModelModuloEmpresa moduloempresa);
    public int existeModulo(ModelModulo modulo,int empresa);
}
