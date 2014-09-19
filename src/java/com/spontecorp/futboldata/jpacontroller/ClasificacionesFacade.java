/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Clasifica;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class ClasificacionesFacade implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(ClasificacionesFacade.class);

    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public List<String> getClasificacionByGrupoYJornada(Grupo grupo, Jornada jornada) {
        List<String> clasificacion = new ArrayList<String>();
        EntityManager em = getEntityManager();
        
        // Trae el total de la clasificación
        String query1 = "SELECT eq.nombre AS Equipo, SUM(cl.jJugados) AS JJ, SUM(cl.jGanados) AS JG, "
                + "SUM(cl.jEmpatados) AS JE, SUM(cl.jPerdidos) AS JP, SUM(cl.golesFavor) AS GF, "
                + "SUM(cl.golesContra) AS GC, SUM(cl.diferencia) AS Diff, SUM(cl.puntos) AS Pts "
                + "FROM Clasificacion "
                + "WHERE cl.jornadaId.grupoId = :grupo AND cl.jornadaId <= :jornada "
                + "GROUP BY eq.nombre "
                + "ORDER BY SUM(cl.puntos) DESC, SUM(cl.golesFavor) DESC";
        
        // Clasificación como local
        String query2 = "SELECT eq.nombre AS Equipo, SUM(cl.jJugados) AS JJ, SUM(cl.jGanados) AS JG, "
                + "SUM(cl.jEmpatados) AS JE, SUM(cl.jPerdidos) AS JP, SUM(cl.golesFavor) AS GF, "
                + "SUM(cl.golesContra) AS GC, SUM(cl.diferencia) AS Diff, SUM(cl.puntos) AS Pts "
                + "FROM Clasificacion cl "
                + "WHERE cl.jornadaId.grupoId = :grupo AND cl.jornadaId <= :jornada AND cl.isLocal = 1"
                + "GROUP BY eq.nombre "
                + "ORDER BY SUM(cl.puntos) DESC, SUM(cl.golesFavor) DESC";
        
        // Clasificación como visitante
        String query3 = "SELECT eq.nombre AS Equipo, SUM(cl.jJugados) AS JJ, SUM(cl.jGanados) AS JG, "
                + "SUM(cl.jEmpatados) AS JE, SUM(cl.jPerdidos) AS JP, SUM(cl.golesFavor) AS GF, "
                + "SUM(cl.golesContra) AS GC, SUM(cl.diferencia) AS Diff, SUM(cl.puntos) AS Pts "
                + "FROM Clasificacion cl "
                + "WHERE cl.jornadaId.grupoId = :grupo AND cl.jornadaId <= :jornada AND cl.isLocal = 0"
                + "GROUP BY eq.nombre "
                + "ORDER BY SUM(cl.puntos) DESC, SUM(cl.golesFavor) DESC";
        
        // lista de clasificacion total
        Query query = em.createQuery(query1);
        query.setParameter("grupo", grupo);
        query.setParameter("jornada", jornada);        
        List<Object> listaTotal = query.getResultList();

        // lista de clasificacion local
        query = em.createQuery(query2);
        query.setParameter("grupo", grupo);
        query.setParameter("jornada", jornada);
        List<Object> listaLocal = query.getResultList();
        
        // lista de clasificacion visitante
        query = em.createQuery(query3);
        query.setParameter("grupo", grupo);
        query.setParameter("jornada", jornada);
        List<Object> listaVisitante = query.getResultList();
        
        Clasifica clasifica = new Clasifica();
        for(Object item : listaTotal){
            
        }
        
        return clasificacion;
    }
}
