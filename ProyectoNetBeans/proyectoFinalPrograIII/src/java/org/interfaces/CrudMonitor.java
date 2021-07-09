/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;

import java.util.List;
import org.models.ModelMonitor;




public interface CrudMonitor {
    public List listar(int tipoproducto);
    public ModelMonitor list(int id);
    public boolean insertar(ModelMonitor monitor);
    public boolean modificar(ModelMonitor monitor);
    public boolean eliminar(ModelMonitor monitor);    
}
