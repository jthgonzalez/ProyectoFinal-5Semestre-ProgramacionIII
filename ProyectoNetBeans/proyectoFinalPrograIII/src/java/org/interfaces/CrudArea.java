/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.interfaces;
import java.util.List;
import org.models.ModelArea;

public interface CrudArea {
    public List listar();
    public List areasMesas();
    public ModelArea list (int id);
    public String areaNombre (int id);
    public boolean insertar(ModelArea area);
    public boolean modificar(ModelArea area);
    public boolean eliminar(ModelArea area);
}
