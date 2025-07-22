package models;

import java.util.*;

public class Maquina {
    private String nombre;
    private String ip;
    private List<Integer> codigos;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public int getSubred() {
        String[] partes = ip.split("\\.");
        return Integer.parseInt(partes[3]);
    }

    public int getRiesgo() {
        int suma = 0;
        for (int codigo : codigos) {
            if (codigo % 3 == 0) {
                suma += codigo;
            }
        }
        String nombreSinEspacios = nombre.replace(" ", "");
        Set<Character> unicos = new HashSet<>();
        for (char c : nombreSinEspacios.toCharArray()) {
            unicos.add(c);
        }
        return suma * unicos.size();
    }

    @Override
    public String toString() {
        return nombre + " [" + ip + "]";
    }
}