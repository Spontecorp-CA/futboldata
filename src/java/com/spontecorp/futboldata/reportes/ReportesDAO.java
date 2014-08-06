/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.reportes;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Clasifica;
import com.spontecorp.futboldata.entity.Clasificacion;
import com.spontecorp.futboldata.entity.Club;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jgcastillo
 */
public class ReportesDAO implements Serializable{

    private List<Clasificacion> listClasificacion(Jornada jornada,Grupo grupo , Categoria categoria){
        return null;
    }
   
   
    private List<Clasificacion> listaClasificacion(Jornada jornada,Grupo grupo, Categoria categoria) {
                EntityManager em = Util.getEmf().createEntityManager();

        String query = "Select cl FROM Clasificacion cl "
                + "WHERE cl.jornadaId.id <= :jornada_id "
                + "AND cl.jornadaId.grupoId = :grupo ";
                if(categoria !=null){
                    query = query + "AND cl.partidoId.categoriaId = :categoria";
                }
               
        
        Query q = em.createQuery(query, Clasificacion.class);
        q.setParameter("jornada_id", jornada.getId());
        if(categoria !=null){
            q.setParameter("categoria", categoria);
        }
        q.setParameter("grupo", grupo);
        
        
        // EL resultado obtenido del query en la BD
        List<Clasificacion> lista = q.getResultList();
        return lista;
    }
    
    private Map<Equipo, Clasifica> clasificaXGrupoAndCategoria(List<Clasificacion> lista){


        Clasifica clasifica;

        // Crea el mapa temporal
        Map<Equipo, Clasifica> miMap = new HashMap<Equipo, Clasifica>();
        
        // Se recorre la lista traida de la base de datos
        for (Clasificacion cla : lista) {

            // Si el equipo no está en el mapa, se agrega
            if (!miMap.containsKey(cla.getEquipoId())) {
                clasifica = new Clasifica();

                clasifica.setEquipo(cla.getEquipoId().getNombre());

                clasifica.setJugados(cla.getJJugados());
                clasifica.setGanados(cla.getJGanados());
                clasifica.setEmpatados(cla.getJEmpatados());
                clasifica.setPerdidos(cla.getJPerdidos());
                clasifica.setGolFavor(cla.getGolesFavor());
                clasifica.setGolContra(cla.getGolesContra());
                clasifica.setGolDiferencia(cla.getDiferencia());
                clasifica.setPuntos(cla.getPuntos());

                if (cla.getIsLocal() == 1) {
                    clasifica.setJugadosLocal(cla.getJJugados());
                    clasifica.setGanadosLocal(cla.getJGanados());
                    clasifica.setEmpatadosLocal(cla.getJEmpatados());
                    clasifica.setPerdidosLocal(cla.getJPerdidos());
                    clasifica.setGolFavorLocal(cla.getGolesFavor());
                    clasifica.setGolContraLocal(cla.getGolesContra());
                    clasifica.setGolDiferenciaLocal(cla.getDiferencia());
                    clasifica.setPuntosLocal(cla.getPuntos());
                }

                if (cla.getIsLocal() == 0) {
                    clasifica.setJugadosVisitante(cla.getJJugados());
                    clasifica.setGanadosVisitante(cla.getJGanados());
                    clasifica.setEmpatadosVisitante(cla.getJEmpatados());
                    clasifica.setPerdidosVisitante(cla.getJPerdidos());
                    clasifica.setGolFavorVisitante(cla.getGolesFavor());
                    clasifica.setGolContraVisitante(cla.getGolesContra());
                    clasifica.setGolDiferenciaVisitante(cla.getDiferencia());
                    clasifica.setPuntosVisitante(cla.getPuntos());
                }

                // coloca el nuevo equipo en el mapa
                miMap.put(cla.getEquipoId(), clasifica);
            } else {   // si ya se encuentra en el mapa, actualiza
                clasifica = miMap.get(cla.getEquipoId());

                clasifica.setJugados(clasifica.getJugados() + cla.getJJugados());
                clasifica.setGanados(clasifica.getGanados() + cla.getJGanados());
                clasifica.setEmpatados(clasifica.getEmpatados() + cla.getJEmpatados());
                clasifica.setPerdidos(clasifica.getPerdidos() + cla.getJPerdidos());
                clasifica.setGolFavor(clasifica.getGolFavor() + cla.getGolesFavor());
                clasifica.setGolContra(clasifica.getGolContra() + cla.getGolesContra());
                clasifica.setGolDiferencia(clasifica.getGolDiferencia() + cla.getDiferencia());
                clasifica.setPuntos(clasifica.getPuntos() + cla.getPuntos());

                if (cla.getIsLocal() == 1) {
                    clasifica.setJugadosLocal(clasifica.getJugadosLocal() + cla.getJJugados());
                    clasifica.setGanadosLocal(clasifica.getGanadosLocal() + cla.getJGanados());
                    clasifica.setEmpatadosLocal(clasifica.getEmpatadosLocal() + cla.getJEmpatados());
                    clasifica.setPerdidosLocal(clasifica.getPerdidosLocal() + cla.getJPerdidos());
                    clasifica.setGolFavorLocal(clasifica.getGolFavorLocal() + cla.getGolesFavor());
                    clasifica.setGolContraLocal(clasifica.getGolContraLocal() + cla.getGolesContra());
                    clasifica.setGolDiferenciaLocal(clasifica.getGolDiferenciaLocal() + cla.getDiferencia());
                    clasifica.setPuntosLocal(clasifica.getPuntosLocal() + cla.getPuntos());
                }

                if (cla.getIsLocal() == 0) {
                    clasifica.setJugadosVisitante(clasifica.getJugadosVisitante() + cla.getJJugados());
                    clasifica.setGanadosVisitante(clasifica.getGanadosVisitante() + cla.getJGanados());
                    clasifica.setEmpatadosVisitante(clasifica.getEmpatadosVisitante() + cla.getJEmpatados());
                    clasifica.setPerdidosVisitante(clasifica.getPerdidosVisitante() + cla.getJPerdidos());
                    clasifica.setGolFavorVisitante(clasifica.getGolFavorVisitante() + cla.getGolesFavor());
                    clasifica.setGolContraVisitante(clasifica.getGolContraVisitante() + cla.getGolesContra());
                    clasifica.setGolDiferenciaVisitante(clasifica.getGolDiferenciaVisitante() + cla.getDiferencia());
                    clasifica.setPuntosVisitante(clasifica.getPuntosVisitante() + cla.getPuntos());
                }

                miMap.put(cla.getEquipoId(), clasifica);
            }
        }

        // Se ordena el mapa para que presenta la clasificación deseada
        Map<Equipo, Clasifica> sortedMap  = Clasifica.SortClasifica.sortByValue(miMap);

        return sortedMap;
    }
    
    private Map<Club, Clasifica> clasificaXGrupoAndClub(List<Clasificacion> lista){


        Clasifica clasifica;

        // Crea el mapa temporal
        Map<Club, Clasifica> miMap = new HashMap<Club, Clasifica>();
        
        // Se recorre la lista traida de la base de datos
        for (Clasificacion cla : lista) {

            // Si el equipo no está en el mapa, se agrega
            if (!miMap.containsKey(cla.getEquipoId().getClubId())) {
                clasifica = new Clasifica();

                clasifica.setEquipo(cla.getEquipoId().getNombre());

                clasifica.setJugados(cla.getJJugados());
                clasifica.setGanados(cla.getJGanados());
                clasifica.setEmpatados(cla.getJEmpatados());
                clasifica.setPerdidos(cla.getJPerdidos());
                clasifica.setGolFavor(cla.getGolesFavor());
                clasifica.setGolContra(cla.getGolesContra());
                clasifica.setGolDiferencia(cla.getDiferencia());
                clasifica.setPuntos(cla.getPuntos());

                if (cla.getIsLocal() == 1) {
                    clasifica.setJugadosLocal(cla.getJJugados());
                    clasifica.setGanadosLocal(cla.getJGanados());
                    clasifica.setEmpatadosLocal(cla.getJEmpatados());
                    clasifica.setPerdidosLocal(cla.getJPerdidos());
                    clasifica.setGolFavorLocal(cla.getGolesFavor());
                    clasifica.setGolContraLocal(cla.getGolesContra());
                    clasifica.setGolDiferenciaLocal(cla.getDiferencia());
                    clasifica.setPuntosLocal(cla.getPuntos());
                }

                if (cla.getIsLocal() == 0) {
                    clasifica.setJugadosVisitante(cla.getJJugados());
                    clasifica.setGanadosVisitante(cla.getJGanados());
                    clasifica.setEmpatadosVisitante(cla.getJEmpatados());
                    clasifica.setPerdidosVisitante(cla.getJPerdidos());
                    clasifica.setGolFavorVisitante(cla.getGolesFavor());
                    clasifica.setGolContraVisitante(cla.getGolesContra());
                    clasifica.setGolDiferenciaVisitante(cla.getDiferencia());
                    clasifica.setPuntosVisitante(cla.getPuntos());
                }

                // coloca el nuevo equipo en el mapa
                miMap.put(cla.getEquipoId().getClubId(), clasifica);
            } else {   // si ya se encuentra en el mapa, actualiza
                clasifica = miMap.get(cla.getEquipoId().getClubId());

                clasifica.setJugados(clasifica.getJugados() + cla.getJJugados());
                clasifica.setGanados(clasifica.getGanados() + cla.getJGanados());
                clasifica.setEmpatados(clasifica.getEmpatados() + cla.getJEmpatados());
                clasifica.setPerdidos(clasifica.getPerdidos() + cla.getJPerdidos());
                clasifica.setGolFavor(clasifica.getGolFavor() + cla.getGolesFavor());
                clasifica.setGolContra(clasifica.getGolContra() + cla.getGolesContra());
                clasifica.setGolDiferencia(clasifica.getGolDiferencia() + cla.getDiferencia());
                clasifica.setPuntos(clasifica.getPuntos() + cla.getPuntos());

                if (cla.getIsLocal() == 1) {
                    clasifica.setJugadosLocal(clasifica.getJugadosLocal() + cla.getJJugados());
                    clasifica.setGanadosLocal(clasifica.getGanadosLocal() + cla.getJGanados());
                    clasifica.setEmpatadosLocal(clasifica.getEmpatadosLocal() + cla.getJEmpatados());
                    clasifica.setPerdidosLocal(clasifica.getPerdidosLocal() + cla.getJPerdidos());
                    clasifica.setGolFavorLocal(clasifica.getGolFavorLocal() + cla.getGolesFavor());
                    clasifica.setGolContraLocal(clasifica.getGolContraLocal() + cla.getGolesContra());
                    clasifica.setGolDiferenciaLocal(clasifica.getGolDiferenciaLocal() + cla.getDiferencia());
                    clasifica.setPuntosLocal(clasifica.getPuntosLocal() + cla.getPuntos());
                }

                if (cla.getIsLocal() == 0) {
                    clasifica.setJugadosVisitante(clasifica.getJugadosVisitante() + cla.getJJugados());
                    clasifica.setGanadosVisitante(clasifica.getGanadosVisitante() + cla.getJGanados());
                    clasifica.setEmpatadosVisitante(clasifica.getEmpatadosVisitante() + cla.getJEmpatados());
                    clasifica.setPerdidosVisitante(clasifica.getPerdidosVisitante() + cla.getJPerdidos());
                    clasifica.setGolFavorVisitante(clasifica.getGolFavorVisitante() + cla.getGolesFavor());
                    clasifica.setGolContraVisitante(clasifica.getGolContraVisitante() + cla.getGolesContra());
                    clasifica.setGolDiferenciaVisitante(clasifica.getGolDiferenciaVisitante() + cla.getDiferencia());
                    clasifica.setPuntosVisitante(clasifica.getPuntosVisitante() + cla.getPuntos());
                }

                miMap.put(cla.getEquipoId().getClubId(), clasifica);
            }
        }

        // Se ordena el mapa para que presenta la clasificación deseada
        Map<Club, Clasifica> sortedMap  = Clasifica.SortClasifica.sortByValue(miMap);

        return sortedMap;
    }
    
    /**
     * Genera el reporte de la clasificación por Grupo en una Categoría, ya que
     * la Temporada está asociada a la Categoría, lo que hace falta es la
     * jornada y el grupo al que se desea obtener la clasificación
     *
     * @param jornada
     * @param grupo
     * @param categoria
     * @return
     */
    public List<Clasifica> clasificaXGrupoAndJornada(Jornada jornada, Grupo grupo, 
            Categoria categoria) {
        List<Clasificacion> clasificacions = listaClasificacion(jornada, grupo, categoria);
        List<Clasifica> clasificaList = new ArrayList<Clasifica>();
        Map<Equipo,Clasifica> clasificaMap = clasificaXGrupoAndCategoria(clasificacions);
        for(Map.Entry<Equipo, Clasifica> clasificaEquipo : clasificaMap.entrySet()){
            clasificaList.add(clasificaEquipo.getValue());
        }
        
        return clasificaList;
    }
    
        public List<Clasifica> clasificaXGrupoAndClub(Jornada jornada, Grupo grupo, 
            Categoria categoria) {
        List<Clasificacion> clasificacions = listaClasificacion(jornada, grupo, categoria);
        List<Clasifica> clasificaList = new ArrayList<Clasifica>();
        Map<Club,Clasifica> clasificaMap = clasificaXGrupoAndClub(clasificacions);
        for(Map.Entry<Club, Clasifica> clasificaEquipo : clasificaMap.entrySet()){
            clasificaList.add(clasificaEquipo.getValue());
        }
        
        return clasificaList;
    }
}
