SELECT eq.nombre AS EQUIPO, sum(cl.j_jugados) AS JJ, sum(cl.j_ganados) AS JG,
	sum(cl.j_empatados) AS JE, sum(cl.j_perdidos) AS JP, sum(cl.goles_favor) AS GF,
	sum(cl.goles_contra) AS GC, sum(cl.diferencia) AS Diff, sum(cl.puntos) AS Pts,
	j.alias AS Jornada
	FROM clasificacion cl 	
	JOIN equipo eq ON cl.equipo_id = eq.id 
	JOIN jornada j ON cl.jornada_id = j.id
/*	WHERE cl.jornada_id = 4 */
/*	WHERE cl.is_local = 1 */
	GROUP BY eq.nombre 
	ORDER BY sum(cl.puntos) DESC, sum(cl.goles_favor) DESC;