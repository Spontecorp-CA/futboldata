/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.entity;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Clase usada para generar los datos de la clasificación
 * 
 * @author jgcastillo
 */
public class Clasifica implements Comparable<Clasifica>{
    
    private int equipoId;
    private String equipo;
    private int jugados;
    private int ganados;
    private int empatados;
    private int perdidos;
    private int golFavor;
    private int golContra;
    private int golDiferencia;
    private int puntos;
    private int jugadosLocal;
    private int ganadosLocal;
    private int empatadosLocal;
    private int perdidosLocal;
    private int golFavorLocal;
    private int golContraLocal;
    private int golDiferenciaLocal;
    private int puntosLocal;
    private int jugadosVisitante;
    private int ganadosVisitante;
    private int empatadosVisitante;
    private int perdidosVisitante;
    private int golFavorVisitante;
    private int golContraVisitante;
    private int golDiferenciaVisitante;
    private int puntosVisitante;

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getJugados() {
        return jugados;
    }

    public void setJugados(int jugados) {
        this.jugados = jugados;
    }

    public int getGanados() {
        return ganados;
    }

    public void setGanados(int ganados) {
        this.ganados = ganados;
    }

    public int getEmpatados() {
        return empatados;
    }

    public void setEmpatados(int empatados) {
        this.empatados = empatados;
    }

    public int getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(int perdidos) {
        this.perdidos = perdidos;
    }

    public int getGolFavor() {
        return golFavor;
    }

    public void setGolFavor(int golFavor) {
        this.golFavor = golFavor;
    }

    public int getGolContra() {
        return golContra;
    }

    public void setGolContra(int golContra) {
        this.golContra = golContra;
    }

    public int getGolDiferencia() {
        return golDiferencia;
    }

    public void setGolDiferencia(int golDiferencia) {
        this.golDiferencia = golDiferencia;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getJugadosLocal() {
        return jugadosLocal;
    }

    public void setJugadosLocal(int jugadosLocal) {
        this.jugadosLocal = jugadosLocal;
    }

    public int getGanadosLocal() {
        return ganadosLocal;
    }

    public void setGanadosLocal(int ganadosLocal) {
        this.ganadosLocal = ganadosLocal;
    }

    public int getEmpatadosLocal() {
        return empatadosLocal;
    }

    public void setEmpatadosLocal(int empatadosLocal) {
        this.empatadosLocal = empatadosLocal;
    }

    public int getPerdidosLocal() {
        return perdidosLocal;
    }

    public void setPerdidosLocal(int perdidosLocal) {
        this.perdidosLocal = perdidosLocal;
    }

    public int getGolFavorLocal() {
        return golFavorLocal;
    }

    public void setGolFavorLocal(int golFavorLocal) {
        this.golFavorLocal = golFavorLocal;
    }

    public int getGolContraLocal() {
        return golContraLocal;
    }

    public void setGolContraLocal(int golContraLocal) {
        this.golContraLocal = golContraLocal;
    }

    public int getGolDiferenciaLocal() {
        return golDiferenciaLocal;
    }

    public void setGolDiferenciaLocal(int golDiferenciaLocal) {
        this.golDiferenciaLocal = golDiferenciaLocal;
    }

    public int getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(int puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public int getJugadosVisitante() {
        return jugadosVisitante;
    }

    public void setJugadosVisitante(int jugadosVisitante) {
        this.jugadosVisitante = jugadosVisitante;
    }

    public int getGanadosVisitante() {
        return ganadosVisitante;
    }

    public void setGanadosVisitante(int ganadosVisitante) {
        this.ganadosVisitante = ganadosVisitante;
    }

    public int getEmpatadosVisitante() {
        return empatadosVisitante;
    }

    public void setEmpatadosVisitante(int empatadosVisitante) {
        this.empatadosVisitante = empatadosVisitante;
    }

    public int getPerdidosVisitante() {
        return perdidosVisitante;
    }

    public void setPerdidosVisitante(int perdidosVisitante) {
        this.perdidosVisitante = perdidosVisitante;
    }

    public int getGolFavorVisitante() {
        return golFavorVisitante;
    }

    public void setGolFavorVisitante(int golFavorVisitante) {
        this.golFavorVisitante = golFavorVisitante;
    }

    public int getGolContraVisitante() {
        return golContraVisitante;
    }

    public void setGolContraVisitante(int golContraVisitante) {
        this.golContraVisitante = golContraVisitante;
    }

    public int getGolDiferenciaVisitante() {
        return golDiferenciaVisitante;
    }

    public void setGolDiferenciaVisitante(int golDiferenciaVisitante) {
        this.golDiferenciaVisitante = golDiferenciaVisitante;
    }

    public int getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(int puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    

    /**
     * Utilizados para poder ordenar la lista de clasificación
     * @param cla
     * @return 
     */
    @Override
    public int compareTo(Clasifica cla) {
        int result = 0;
        if(this.getPuntos() < cla.getPuntos()){
            result = 1;
        } else if(this.getPuntos() == cla.getPuntos()){
            if(this.getGolFavor() < cla.getGolFavor()){
                result = 1;
            }
        }else{
            result = -1;
        }
        return result;
    }

    /**
     * Clase que maneja el ordenamiento del Map usado para la clasificación
     */
    public static class SortClasifica {

        public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
            List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());

            Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
                @Override
                public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });

            Map<K, V> result = new LinkedHashMap<K, V>();
            for (Map.Entry<K, V> entry : list) {
                result.put(entry.getKey(), entry.getValue());
            }
            return result;
        }
    }
    
}
