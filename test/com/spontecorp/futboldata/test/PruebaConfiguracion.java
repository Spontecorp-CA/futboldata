/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.test;

import com.spontecorp.futboldata.entity.Clasifica;
import com.spontecorp.futboldata.entity.Clasificacion;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.reportes.ReportesDAO;
import com.spontecorp.futboldata.utilities.Util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
public class PruebaConfiguracion {

    private static final Logger logger = LoggerFactory.getLogger(PruebaConfiguracion.class);
    
    public static void main(String[] args) {
//        ReportesDAO reportes = new ReportesDAO();
//        
//        Jornada jornada = new Jornada();
//        jornada.setId(35);
//        Grupo grupo = new Grupo();
//        grupo.setId(7);
//        jornada.setGrupoId(grupo);
//        
//        Map<Equipo, Clasifica> sortedMap = reportes.clasificaXGrupoAndCategoria(jornada, grupo);
//        
//        System.out.println("Total\t\t\t\t\t\t\t\t\tLocal\t\t\t\t\t\t\t\t\tVisitante");
//        System.out.println("Equipo\tJJ\tJG\tJE\tJP\tGF\tGC\tDif\tPts\tJJ\tJG\tJE\tJP\tGF\tGC\tDif\tPts\tJJ\tJG\tJE\tJP\tGF\tGC\tDif\tPts");
//        for (Map.Entry<Equipo, Clasifica> item : sortedMap.entrySet()) {
//            System.out.println(item.getValue().getEquipo()
//                    + "\t" + item.getValue().getJugados()
//                    + "\t" + item.getValue().getGanados()
//                    + "\t" + item.getValue().getEmpatados()
//                    + "\t" + item.getValue().getPerdidos()
//                    + "\t" + item.getValue().getgFavor()
//                    + "\t" + item.getValue().getgContra()
//                    + "\t" + item.getValue().getgDiferencia()
//                    + "\t" + item.getValue().getPuntos()
//                    + "\t" + item.getValue().getJugadosLocal()
//                    + "\t" + item.getValue().getGanadosLocal()
//                    + "\t" + item.getValue().getEmpatadosLocal()
//                    + "\t" + item.getValue().getPerdidosLocal()
//                    + "\t" + item.getValue().getgFavorLocal()
//                    + "\t" + item.getValue().getgContraLocal()
//                    + "\t" + item.getValue().getgDiferenciaLocal()
//                    + "\t" + item.getValue().getPuntosLocal()
//                    + "\t" + item.getValue().getJugadosVisitante()
//                    + "\t" + item.getValue().getGanadosVisitante()
//                    + "\t" + item.getValue().getEmpatadosVisitante()
//                    + "\t" + item.getValue().getPerdidosVisitante()
//                    + "\t" + item.getValue().getgFavorVisitante()
//                    + "\t" + item.getValue().getgContraVisitante()
//                    + "\t" + item.getValue().getgDiferenciaVisitante()
//                    + "\t" + item.getValue().getPuntosVisitante());
//        }
//        
    }
}
