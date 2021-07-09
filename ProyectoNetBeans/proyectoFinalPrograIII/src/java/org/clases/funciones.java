
package org.clases;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class funciones {
   private static  int empresa;
    
    public int getEmpresa() {
        return empresa;
    }

   
    public void setEmpresa(int aEmpresa) {
        empresa = aEmpresa;
    }

    public String Encode(String cadena) throws UnsupportedEncodingException{
    return Base64.getEncoder().encodeToString(cadena.getBytes("utf-8"));
    }
    public String Decode(String cadena) throws UnsupportedEncodingException{
    byte[] decode = Base64.getDecoder().decode(cadena.getBytes());
    return new String(decode,"utf-8");
            
    }

    


   
}

