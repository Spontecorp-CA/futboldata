/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.test;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Clasificacion;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.utilities.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jgcastillo
 */
public class TestQuery {
    
    public static void main(String[] args) {
        Jornada jornada = new Jornada(8);
        Grupo grupo = new Grupo(2);
        Categoria categoria = new Categoria(8);
        
        EntityManager em = Util.getEmf().createEntityManager();
        
        String query = "Select cl FROM Clasificacion cl "
                + "WHERE cl.jornadaId.id <= :jornada_id "
                + "AND cl.jornadaId.grupoId = :grupo "
                + "AND cl.partidoId.categoriaId = :categoria";

        String queryT = "SELECT cl.equipoId.nombre AS EQUIPO, SUM(cl.jJugados), "
                + "SUM(cl.jGanados), SUM(cl.jEmpatados), SUM(cl.jPerdidos), "
                + "SUM(cl.golesFavor) AS GF, SUM(cl.golesContra) AS GC , SUM(cl.diferencia) AS GDif,"
                + "SUM(cl.puntos) AS PTS "
                + "FROM Clasificacion cl "
                + "WHERE cl.jornadaId.id <= :jornada_id "
                + "AND cl.jornadaId.grupoId = :grupo "
                + "AND cl.partidoId.categoriaId = :categoria "
                + "GROUP BY EQUIPO "
                + "ORDER BY PTS DESC, Gdif DESC, GF DESC, GC ASC";
        
        String queryL = "SELECT SUM(cl.jJugados), "
                + "SUM(cl.jGanados), SUM(cl.jEmpatados), SUM(cl.jPerdidos), "
                + "SUM(cl.golesFavor) AS GF, SUM(cl.golesContra) AS GC , SUM(cl.diferencia) AS GDif,"
                + "SUM(cl.puntos) AS PTS "
                + "FROM Clasificacion cl "
                + "WHERE cl.jornadaId.id <= :jornada_id "
                + "AND cl.jornadaId.grupoId = :grupo "
                + "AND cl.partidoId.categoriaId = :categoria "
                + "AND cl.isLocal = :local "
                + "GROUP BY cl.equipoId.nombre "
                + "ORDER BY PTS DESC, Gdif DESC, GF DESC, GC ASC";
        
        // Lista total
        Query q = em.createQuery(queryT);
        q.setParameter("jornada_id", jornada.getId());
        q.setParameter("grupo", grupo);
        q.setParameter("categoria", categoria);
        
        List<Object[]> listaT = q.getResultList();

        // Lista Local
        q = em.createQuery(queryL);
        q.setParameter("jornada_id", jornada.getId());
        q.setParameter("grupo", grupo);
        q.setParameter("categoria", categoria);
        q.setParameter("local", 1);
        
        List<Object[]> listaL = q.getResultList();
        
        // Lista Visitante
        q = em.createQuery(queryL);
        q.setParameter("jornada_id", jornada.getId());
        q.setParameter("grupo", grupo);
        q.setParameter("categoria", categoria);
        q.setParameter("local", 0);
        
        List<Object[]> listaV = q.getResultList();
        
        printResultObject(listaT, listaL, listaV);
    }
    
    private static void printResultObject(List<Object[]> lista, List<Object[]> listaL, List<Object[]> listaV) {
        
        List<Object[]> otraList = new ArrayList<Object[]>();
        for(int i = 0; i < lista.size() ; i++){
            Object[] arrayT = lista.get(i);
            Object[] arrayL = listaL.get(i);
            Object[] arrayV = listaV.get(i);
            
            Object[] resultArray = (Object[]) join(arrayT, arrayL, arrayV);
            otraList.add(resultArray);
        }
        
        System.out.println("EQUIPO\t\t\t\tJJ\tJG\tJE\tJP\tGF\tGC\tDif\tPts"
                + "\tJJL\tJGL\tJEL\tJPL\tGFL\tGCL\tDifL\tPtsL"
                + "\tJJV\tJGV\tJEV\tJPV\tGFV\tGCV\tDifV\tPtsV");
        for(Object[] array : otraList){
            System.out.print(array[0]+ "\t\t\t");
            System.out.print(array[1] + "\t");
            System.out.print(array[2]  + "\t");
            System.out.print(array[3] + "\t");
            System.out.print(array[4] + "\t");
            System.out.print(array[5] + "\t");
            System.out.print(array[6] + "\t");
            System.out.print(array[7] + "\t");
            System.out.print(array[8] + "\t");
            System.out.print(array[9] + "\t");
            System.out.print(array[10] + "\t");
            System.out.print(array[11] + "\t");
            System.out.print(array[12] + "\t");
            System.out.print(array[13] + "\t");
            System.out.print(array[14] + "\t");
            System.out.print(array[15] + "\t");
            System.out.print(array[16] + "\t");
            System.out.print(array[17] + "\t");
            System.out.print(array[18] + "\t");
            System.out.print(array[19] + "\t");
            System.out.print(array[20] + "\t");
            System.out.print(array[21] + "\t");
            System.out.print(array[22] + "\t");
            System.out.print(array[23] + "\t");
            System.out.println(array[24] + "\t");
        }
    }
    
    private static Object[] join(Object[]... parms) {
    // calculate size of target array
    int size = 0;
        for (Object[] array : parms) {
            size += array.length;
        }

        Object[] result = new Object[size];

        int j = 0;
        for (Object[] array : parms) {
            for (Object s : array) {
                result[j++] = s;
            }
        }
        return result;
    }
}
