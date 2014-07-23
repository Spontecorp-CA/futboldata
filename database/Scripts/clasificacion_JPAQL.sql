/* Selecciona la clasificación de un grupo y una jornada determinada */

SELECT eq.nombre AS Equipo, SUM(cl.jJugados) AS JJ, SUM(cl.jGanados) AS JG,
	SUM(cl.jEmpatados) AS JE, SUM(cl.jPerdidos) AS JP, SUM(cl.golesFavor) AS GF,
	SUM(cl.golesContra) AS GC, SUM(cl.diferencia) AS Diff, SUM(cl.puntos) AS Pts
    FROM Clasificacion cl JOIN cl.equipoId eq JOIN cl.jornadaId jo JOIN cl.jornadaId.grupoId gr
    WHERE gr.id = 36 AND jo.id <= 74
    GROUP BY eq.nombre
    ORDER BY SUM(cl.puntos) DESC, SUM(cl.golesFavor) DESC