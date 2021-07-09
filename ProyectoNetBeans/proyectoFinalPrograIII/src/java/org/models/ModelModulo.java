/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.models;


public class ModelModulo {
    
    /**
     * @return the id_modulo
     */
    public int getId_modulo() {
        return id_modulo;
    }

    /**
     * @param id_modulo the id_modulo to set
     */
    public void setId_modulo(int id_modulo) {
        this.id_modulo = id_modulo;
    }

    /**
     * @return the modulo
     */
    public String getModulo() {
        return modulo;
    }

    /**
     * @param modulo the modulo to set
     */
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the ayuda
     */
    public String getAyuda() {
        return ayuda;
    }

    /**
     * @param ayuda the ayuda to set
     */
    public void setAyuda(String ayuda) {
        this.ayuda = ayuda;
    }
    private int id_modulo;
    private String modulo;
    private String descripcion;
    private String ayuda;
    
}
