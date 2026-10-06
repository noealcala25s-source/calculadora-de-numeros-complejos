/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculado;

/**
 *
 * @author noela
 */
public class NumeroComplejo {
              
    private float real;
    private float img;

    public NumeroComplejo(float real, float img) {
        this.real = real;
        this.img = img;
    }

    public NumeroComplejo() {
        this.real = 0;
        this.img = 0;
    }

    public float getReal() {
        return real;
    }

    public void setReal(float real) {
        this.real = real;
    }

    public float getImg() {
        return img;
    }

    public void setImg(float img) {
        this.img = img;
    }

    // --- OPERACIONES ---

    // Suma: (a + bi) + (c + di) = (a + c) + (b + d)i
    public NumeroComplejo suma(NumeroComplejo n1, NumeroComplejo n2) {
        float sr = n1.getReal() + n2.getReal();
        float si = n1.getImg() + n2.getImg();
        return new NumeroComplejo(sr, si);
    }

    // Resta: (a + bi) - (c + di) = (a - c) + (b - d)i
    public NumeroComplejo resta(NumeroComplejo n1, NumeroComplejo n2) {
        float sr = n1.getReal() - n2.getReal();
        float si = n1.getImg() - n2.getImg();
        return new NumeroComplejo(sr, si);
    }

    // Multiplicación: (a + bi) * (c + di) = (ac - bd) + (ad + bc)i
    public NumeroComplejo multiplicacion(NumeroComplejo n1, NumeroComplejo n2) {
        float sr = (n1.getReal() * n2.getReal()) - (n1.getImg() * n2.getImg());
        float si = (n1.getReal() * n2.getImg()) + (n1.getImg() * n2.getReal());
        return new NumeroComplejo(sr, si);
    }

    // División: (a + bi) / (c + di) = [(ac + bd) / (c² + d²)] + [(bc - ad) / (c² + d²)]i
    public NumeroComplejo division(NumeroComplejo n1, NumeroComplejo n2) {
        float divisor = (n2.getReal() * n2.getReal()) + (n2.getImg() * n2.getImg());
        if (divisor == 0) {
            throw new ArithmeticException("División por cero");
        }
        float sr = ((n1.getReal() * n2.getReal()) + (n1.getImg() * n2.getImg())) / divisor;
        float si = ((n1.getImg() * n2.getReal()) - (n1.getReal() * n2.getImg())) / divisor;
        return new NumeroComplejo(sr, si);
    }

    // Formato de salida visual: a + bi / a - bi
    @Override
    public String toString() {
        if (img >= 0) {
            return real + " + " + img + "i";
        } else {
            return real + " - " + Math.abs(img) + "i";
        }
    }
    
    public static NumeroComplejo parsearComplejo(String texto) {
    if (texto == null || texto.trim().isEmpty()) {
        return new NumeroComplejo(0, 0);
    }
    
    texto = texto.replaceAll("\\s+", ""); // Elimina espacios
    
    float real = 0;
    float img = 0;
    
    try {
        // Caso 1: Solo tiene parte imaginaria (ej. "3i", "-i", "i")
        if (texto.endsWith("i") && !texto.contains("+") && (texto.lastIndexOf("-") <= 0)) {
            String parteImg = texto.replace("i", "");
            if (parteImg.isEmpty() || parteImg.equals("+")) img = 1;
            else if (parteImg.equals("-")) img = -1;
            else img = Float.parseFloat(parteImg);
            return new NumeroComplejo(0, img);
        }
        
        // Caso 2: Número complejo completo (ej. "3+2i", "3-2i")
        if (texto.contains("i")) {
            texto = texto.replace("i", "");
            int posSigno = -1;
            
            // Buscar el signo que separa la parte real de la imaginaria
            for (int i = texto.length() - 1; i > 0; i--) {
                char c = texto.charAt(i);
                if (c == '+' || c == '-') {
                    posSigno = i;
                    break;
                }
            }
            
            if (posSigno != -1) {
                String parteReal = texto.substring(0, posSigno);
                String parteImg = texto.substring(posSigno);
                
                real = Float.parseFloat(parteReal);
                if (parteImg.equals("+")) img = 1;
                else if (parteImg.equals("-")) img = -1;
                else img = Float.parseFloat(parteImg);
                
                return new NumeroComplejo(real, img);
            }
        }
        
        // Caso 3: Solo número real (ej. "3", "3.5", "-5")
        real = Float.parseFloat(texto.replace("i", ""));
        return new NumeroComplejo(real, 0);
        
    } catch (Exception e) {
        return new NumeroComplejo(0, 0);
    }
    }
}

















