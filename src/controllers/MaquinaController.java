package controllers;

import java.util.*;

import models.Maquina;

public class MaquinaController {

    public static Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> resultado = new Stack<>();
        for (Maquina m : maquinas) {
            if (m.getSubred() < umbral) {
                resultado.push(m);
            }
        }
        return resultado;
    }

    public static TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        TreeSet<Maquina> conjunto = new TreeSet<>(new Comparator<Maquina>() {
            @Override
            public int compare(Maquina m1, Maquina m2) {
                int cmp = Integer.compare(m1.getSubred(), m2.getSubred());
                if (cmp != 0) return cmp;
                return m1.getNombre().compareTo(m2.getNombre());
            }
        });
        conjunto.addAll(pila);
        return conjunto;
    }

    public static TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        TreeMap<Integer, Queue<Maquina>> mapa = new TreeMap<>();
        for (Maquina m : maquinas) {
            int riesgo = m.getRiesgo();
            mapa.putIfAbsent(riesgo, new LinkedList<>());
            mapa.get(riesgo).add(m);
        }
        return mapa;
    }

    public static Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
        int mayorCantidad = -1;
        int mayorRiesgo = -1;
        Queue<Maquina> grupoSeleccionado = null;

        for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            int riesgo = entry.getKey();
            Queue<Maquina> grupo = entry.getValue();
            int cantidad = grupo.size();

            if (cantidad > mayorCantidad || (cantidad == mayorCantidad && riesgo > mayorRiesgo)) {
                mayorCantidad = cantidad;
                mayorRiesgo = riesgo;
                grupoSeleccionado = grupo;
            }
        }

        Stack<Maquina> resultado = new Stack<>();
        if (grupoSeleccionado != null) {
            for (Maquina m : grupoSeleccionado) {
                resultado.push(m);
            }
        }
        return resultado;
    }
}