/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.reportes;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Clasifica;
import com.spontecorp.futboldata.entity.Clasificacion;
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

   
    private Map<Equipo, Clasifica> clasificaXGrupoAndCategoria(Jornada jornada, 
            Grupo grupo, Categoria categoria){
        EntityManager em = Util.getEmf().createEntityManager();

        String query = "Select cl FROM Clasificacion cl "
                + "WHERE cl.jornadaId.id <= :jornada_id "
                + "AND cl.jornadaId.grupoId = :grupo "
                + "AND cl.partidoId.categoriaId = :categoria";
        
        Query q = em.createQuery(query, Clasificacion.class);
        q.setParameter("jornada_id", jornada.getId());
        q.setParameter("grupo", grupo);
        q.setParameter("categoria", categoria);
        
        // EL resultado obtenido del query en la BD
        List<Clasificacion> lista = q.getResultList();

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
    
    /**
     * Genera el reporte de la clasificación por Grupo en una Categoría, ya que
     * la Temporada está asociada a la Categoría, lo que hace falta es la
     * jornada y el grupo al que se desea obtener la clasificación
     *
     * @param jornada
     * @param grupo
     * @return
     */
//    public String[][] clasificaXGrupoAndJornada(Jornada jornada, Grupo grupo){
//        Map<Equipo, Clasifica> clasificaMap = clasificaXGrupoAndCategoria(jornada, grupo);
//        int i = 0;
//        String[][] clasificaArray = new String[clasificaMap.size()][25];
//        for (Map.Entry<Equipo, Clasifica> item : clasificaMap.entrySet()) {
//            clasificaArray[i][0] = item.getValue().getEquipo();
//            clasificaArray[i][1] = String.valueOf(item.getValue().getJugados());
//            clasificaArray[i][2] = String.valueOf(item.getValue().getGanados());
//            clasificaArray[i][3] = String.valueOf(item.getValue().getEmpatados());
//            clasificaArray[i][4] = String.valueOf(item.getValue().getPerdidos());
//            clasificaArray[i][5] = String.valueOf(item.getValue().getgFavor());
//            clasificaArray[i][6] = String.valueOf(item.getValue().getgContra());
//            clasificaArray[i][7] = String.valueOf(item.getValue().getgDiferencia());
//            clasificaArray[i][8] = String.valueOf(item.getValue().getPuntos());
//            clasificaArray[i][9] = String.valueOf(item.getValue().getJugadosLocal());
//            clasificaArray[i][10] = String.valueOf(item.getValue().getGanadosLocal());
//            clasificaArray[i][11] = String.valueOf(item.getValue().getEmpatadosLocal());
//            clasificaArray[i][12] = String.valueOf(item.getValue().getPerdidosLocal());
//            clasificaArray[i][13] = String.valueOf(item.getValue().getgFavorLocal());
//            clasificaArray[i][14] = String.valueOf(item.getValue().getgContraLocal());
//            clasificaArray[i][15] = String.valueOf(item.getValue().getgDiferenciaLocal());
//            clasificaArray[i][16] = String.valueOf(item.getValue().getPuntosLocal());
//            clasificaArray[i][17] = String.valueOf(item.getValue().getJugadosVisitante());
//            clasificaArray[i][18] = String.valueOf(item.getValue().getGanadosVisitante());
//            clasificaArray[i][19] = String.valueOf(item.getValue().getEmpatadosVisitante());
//            clasificaArray[i][20] = String.valueOf(item.getValue().getPerdidosVisitante());
//            clasificaArray[i][21] = String.valueOf(item.getValue().getgFavorVisitante());
//            clasificaArray[i][22] = String.valueOf(item.getValue().getgContraVisitante());
//            clasificaArray[i][23] = String.valueOf(item.getValue().getgDiferenciaVisitante());
//            clasificaArray[i][24] = String.valueOf(item.getValue().getPuntosVisitante());
//            i++;
//        }
//        return clasificaArray;
//    }
    
    public List<Clasifica> clasificaXGrupoAndJornada(Jornada jornada, Grupo grupo, 
            Categoria categoria) {
        List<Clasifica> clasificaList = new ArrayList<Clasifica>();
        Map<Equipo,Clasifica> clasificaMap = clasificaXGrupoAndCategoria(jornada, grupo, categoria);
        for(Map.Entry<Equipo, Clasifica> clasificaEquipo : clasificaMap.entrySet()){
            clasificaList.add(clasificaEquipo.getValue());
        }
        
        return clasificaList;
    }
}
