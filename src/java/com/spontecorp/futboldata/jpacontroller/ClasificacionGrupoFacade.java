 /*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.jpacontroller;

import com.spontecorp.futboldata.entity.Clasificacion;
import com.spontecorp.futboldata.entity.ClasificacionGrupo;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.utilities.Util;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class ClasificacionGrupoFacade extends AbstractFacade<ClasificacionGrupo> {

    ClasificacionFacade clasificacionFacade = new ClasificacionFacade();
    private static final Logger logger = LoggerFactory.getLogger(ClasificacionGrupoFacade.class);

    public ClasificacionGrupoFacade() {
        super(ClasificacionGrupo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return Util.getEmf().createEntityManager();
    }

    public ClasificacionGrupo findClasificacion(Grupo grupo, Equipo equipo) {
        EntityManager em = getEntityManager();
        ClasificacionGrupo clasificacion = null;
        try {
            String query = null;
            Query q = null;
            query = " SELECT c FROM ClasificacionGrupo  c WHERE  c.grupoId = :grupo "
                    + "AND c.equipoId =:equipo ";
            q = em.createQuery(query, ClasificacionGrupo.class);
            q.setParameter("grupo", grupo);
            q.setParameter("equipo", equipo);
            clasificacion = (ClasificacionGrupo) q.getSingleResult();

        } catch (Exception e) {
            logger.debug("No encontro clasificacions x grupo", e.getCause());
        } finally {
            em.close();

        }
        return clasificacion;
    }

    public void actulizar(ClasificacionGrupo clasificacionGrupo) {
        List<Clasificacion> clasificaciones;
        ClasificacionGrupo clasificacionTotal;
        logger.debug("Se modifica el equipo "+ clasificacionGrupo.getEquipoId().getNombre());
        int jGanados = 0;
        int jJugados = 0;
        int jEmpatados = 0;
        int jPerdidos = 0;
        int golesFavor = 0;
        int golesContra = 0;
        int diferencia = 0;
        int puntos = 0;
        clasificacionTotal = clasificacionGrupo;
        if (clasificacionTotal.getStatus() == 0) {
            logger.debug("Paso 1");
            clasificaciones = clasificacionFacade.findClasificaciones(clasificacionTotal);
            for (Clasificacion clasificacion : clasificaciones) {
                jGanados += clasificacion.getJGanados();
                jJugados += clasificacion.getJJugados();
                jEmpatados += clasificacion.getJEmpatados();
                jPerdidos += clasificacion.getJPerdidos();
                golesFavor += clasificacion.getGolesFavor();
                golesContra += clasificacion.getGolesContra();
                diferencia += clasificacion.getDiferencia();
                puntos += clasificacion.getPuntos();
            }
            logger.debug("Los Juegos perdidos "+jPerdidos);
            clasificacionTotal.setDiferencia(diferencia);
            clasificacionTotal.setGolesContra(golesContra);
            clasificacionTotal.setGolesFavor(golesFavor);
            clasificacionTotal.setJEmpatados(jEmpatados);
            clasificacionTotal.setJGanados(jGanados);
            clasificacionTotal.setJJugados(jJugados);
            clasificacionTotal.setJPerdidos(jPerdidos);
            clasificacionTotal.setPuntos(puntos);
            clasificacionTotal.setStatus(1);
            edit(clasificacionTotal);
            logger.debug("Se edito con exito la Clasificacion x Grupo");
        
        }
    }
}
