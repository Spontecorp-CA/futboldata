/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.reportes.template;

import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Convocado;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Partido;
import com.spontecorp.futboldata.entity.PartidoArbitro;
import com.spontecorp.futboldata.entity.PartidoEvento;
import com.spontecorp.futboldata.entity.Staff;
import com.spontecorp.futboldata.utilities.Util;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import static net.sf.dynamicreports.report.builder.DynamicReports.asc;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.field;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ComponentColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.LineDirection;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author sponte03
 */
public class InformeArbitralReport {

    private static FieldBuilder<?> titularField;
    private static Partido partido;

    public static JasperReportBuilder crearReporte(Partido partido, List<Convocado> equipoLocal,
            List<Convocado> equipoVisitante, List<Staff> staffLocal, List<Staff> staffVisitante,
            List<PartidoArbitro> arbitros, List<PartidoEvento> partidoEventos) {

        List<PartidoEvento> partidoEventosL = getPartidoEvento(partido.getEquipoLocalId(), partidoEventos);
        List<PartidoEvento> partidoEventosV = getPartidoEvento(partido.getEquipoVisitanteId(), partidoEventos);

        InformeArbitralReport.partido = partido;

        SubreportBuilder subreportPartido = reportePartido();

        SubreportBuilder subreportEquipoL = reporteEquipo("Equipo Local(" + partido.getEquipoLocalId().getNombre() + ")")
                .setDataSource(createDataEquipo(equipoLocal));
        SubreportBuilder subreportEquipoV = reporteEquipo("Equipo Visitante("+partido.getEquipoVisitanteId().getNombre()+")").setDataSource(createDataEquipo(equipoVisitante));

        SubreportBuilder subreportStaffVisitante = reporteStaff("Cuerpo Técnico Visitante").setDataSource(createDataStaff(staffVisitante));
        SubreportBuilder subreportStaffLocal = reporteStaff("Cuerpo Técnico Local").setDataSource(createDataStaff(staffLocal));

        SubreportBuilder subreportArbitro = reporteArbitro().setDataSource(createDataArbitro(arbitros));

        SubreportBuilder subreportCambioL = reporteCambios("Equipo Local").setDataSource(createDataCambio(partidoEventosL));
        SubreportBuilder subreportCambioSalidaL = reporteCambiosSalida("").setDataSource(createDataCambioSalida(partidoEventosL));

        SubreportBuilder subreportCambioV = reporteCambios("Equipo Visitante").setDataSource(createDataCambio(partidoEventosV));
        SubreportBuilder subreportCambioSalidaV = reporteCambiosSalida("").setDataSource(createDataCambioSalida(partidoEventosV));

        SubreportBuilder subreportTarjetaL = reporteTarjetas("Tarjeta Equipo Local").setDataSource(createDataTarjeta(getTarjetas(partidoEventosL)));
        SubreportBuilder subreportTarjetaV = reporteTarjetas("Tarjeta Equipo Visitante").setDataSource(createDataTarjeta(getTarjetas(partidoEventosV)));

        SubreportBuilder subreportGolesL = reporteGoles("Goles Equipo Local").setDataSource(createDataGoles(partidoEventosL));
        SubreportBuilder subreportGolesV = reporteGoles("Goles Equipo Visitante").setDataSource(createDataGoles(partidoEventosV));
        JasperReportBuilder builder;
        builder = null;
        builder = report()
                .setTemplate(Templates.reportTemplate)
                .detailFooter(cmp.verticalGap(20))
                .pageFooter(Templates.footerComponent)
                .title(Templates.createTitleComponent("Informe Arbitral"),
                        cmp.verticalList(subreportPartido,
                                cmp.line().setDirection(LineDirection.BOTTOM_UP),
                                cmp.verticalGap(20),
                                subreportEquipoL,
                                subreportStaffLocal,
                                cmp.verticalGap(20),
                                subreportEquipoV,
                                subreportStaffVisitante,
                                cmp.verticalGap(20),
                                subreportArbitro))
                .setDataSource(createDataPartido(partido))
                .title(cmp.text("Detalles Partido"),
                        cmp.horizontalList(subreportCambioL, subreportCambioSalidaL),
                        cmp.verticalGap(20),
                        cmp.horizontalList(subreportCambioV, subreportCambioSalidaV),
                        cmp.verticalGap(20),
                        cmp.horizontalList(subreportTarjetaL, cmp.horizontalGap(5), subreportTarjetaV),
                        cmp.verticalGap(20),
                        cmp.horizontalList(subreportGolesL, cmp.horizontalGap(5), subreportGolesV))
                .columns(col.componentColumn("Observaciones Arbitro", cmp.text(partido.getObservaciones())));

        return builder;
    }

    private static VerticalListBuilder columnPair(String title, FieldBuilder<?> value, String patter) {

        TextFieldBuilder<String> titleCmp = cmp.text(title)
                .setStyle(Templates.columnTitleStyle);
        TextFieldBuilder<?> valueCmp = cmp.text(value).setHorizontalAlignment(HorizontalAlignment.CENTER);
        if (patter != null) {
            valueCmp.setPattern(patter);
        }

        return cmp.verticalList(titleCmp, valueCmp);
    }

    private static HorizontalListBuilder hColumnPair(String title, FieldBuilder<?> value) {

        TextFieldBuilder<String> titleCmp = cmp.text(title)
                .setStyle(Templates.columnTitleStyle);
        TextFieldBuilder<?> valueCmp = cmp.text(value);
        return cmp.horizontalList(titleCmp, valueCmp);
    }

    private static SubreportBuilder reportePartido() {

        FieldBuilder<String> competicion = field("competicion", type.stringType());
        FieldBuilder<Integer> numeroPartido = field("numero", type.integerType());
        FieldBuilder<String> categoria = field("categoria", type.stringType());
        FieldBuilder<?> local = field("local", type.stringType());
        FieldBuilder<?> visitante = field("visitante", type.stringType());
        FieldBuilder<Date> fecha = field("fecha", type.dateType());
        FieldBuilder<String> grupo = field("grupo", type.stringType());
        FieldBuilder<Date> hora = field("hora", type.timeHourToFractionType());
        FieldBuilder<?> estadio = field("estadio", type.stringType());
        FieldBuilder<String> temporada = field("temporada", type.stringType());
        FieldBuilder<String> fase = field("fase", type.stringType());
        FieldBuilder<Integer> jornada = field("jornada", type.integerType());
        FieldBuilder<?> golL = field("golL", type.integerType());
        FieldBuilder<?> golV = field("golV", type.integerType());
        FieldBuilder<?> statusP = field("status", type.stringType());
        FieldBuilder<?> llave = field("llave", type.stringType());
        FieldBuilder<?> ciudad = field("ciudad", type.stringType());
        FieldBuilder<?> listadoN = field("listadoN", type.stringType());
        FieldBuilder<?> asistencia = field("asistencia", type.integerType());

        StyleBuilder textStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());

        JasperReportBuilder reporte = report()
                .setColumnStyle(textStyle)
                .columnGrid(ListType.HORIZONTAL_FLOW)
                .fields(competicion, numeroPartido, categoria, local, visitante, fecha, grupo, hora, estadio, temporada,
                        fase, jornada, golL, golV, statusP, asistencia, llave, ciudad, listadoN)
                .columns(
                        col.componentColumn(columnPair("Competicion", competicion, null)),
                        col.componentColumn(columnPair("n°Partido", numeroPartido, null)),
                        col.componentColumn(columnPair("Categoria", categoria, null)),
                        col.componentColumn(columnPair("Local", local, null)),
                        col.componentColumn(columnPair("Visitante", visitante, null)),
                        col.componentColumn(columnPair("Fecha", fecha, "EEE dd/MM/yyyy")),
                        col.componentColumn(columnPair("Grupo", grupo, null)),
                        col.componentColumn(columnPair("Hora", hora, "hh:mm a")),
                        col.componentColumn(columnPair("Estadio", estadio, null)),
                        col.componentColumn(columnPair("Temporada", temporada, null)),
                        col.componentColumn(columnPair("Fase", fase, null)),
                        col.componentColumn(columnPair("Jornada", jornada, null)),
                        col.componentColumn(columnPair("Goles L", golL, null)),
                        col.componentColumn(columnPair("Goles V", golV, null)),
                        col.componentColumn(columnPair("Status", statusP, null)),
                        col.componentColumn(columnPair("Asistencia", asistencia, null)),
                        col.componentColumn(columnPair("Llave", llave, null)),
                        col.componentColumn(columnPair("Ciudad", ciudad, null)),
                        col.componentColumn(columnPair("Lista n°", listadoN, null))
                )
                .detailFooter(cmp.verticalGap(20))
                .setDataSource(createDataPartido(partido));
        SubreportBuilder subreport = cmp.subreport(reporte);
        return subreport;

    }

    private static SubreportBuilder reporteEquipo(String localOVisitante) {

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                .setBorder(stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);

        StyleBuilder textStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());

        titularField = field("titular", Integer.class);
        FieldBuilder<Integer> capitanField = field("capitan", Integer.class);

        TextColumnBuilder<String> nombre = col.column("Nombre", "nombre", type.stringType());
        TextColumnBuilder<String> apellido = col.column("Apellido", "apellido", type.stringType());
        TextColumnBuilder<Integer> camisa = col.column("n° Cam", "camisa", type.integerType()).setFixedColumns(2);
        TextColumnBuilder<String> capitan = col.column("Cap", new ExpressionColumnCap()).setFixedColumns(2);
        TextColumnBuilder<String> titular = col.column("Tit", new ExpressionColumnTitu()).setFixedColumns(2);
        TextColumnBuilder<String> sumplente = col.column("Sup", new ExpressionColumnSuple()).setFixedColumns(2);
        TextColumnBuilder<Integer> correlativo = col.reportRowNumberColumn("n°").setFixedColumns(2);
        TextColumnBuilder<String> nacionalidad = col.column("Nac", "nacionalidad", type.stringType()).setFixedColumns(7);
        TextColumnBuilder<String> cedula = col.column("Cedula", "cedula", type.stringType()).setFixedColumns(7);
        TextColumnBuilder<?> fechaNacimiento = col.column("Fecha N.", "fechaN", type.dateType())
                .setFixedColumns(7).setPattern("dd/MM/yyyy");
        TextColumnBuilder<?> ficha = col.column("Ficha", "ficha", type.stringType()).setFixedColumns(6);

        ColumnTitleGroupBuilder tituloAlioneacion = grid.titleGroup("Alineacion", camisa, titular, sumplente, capitan);
        ColumnTitleGroupBuilder tituloEquipoLocal = grid.titleGroup(localOVisitante, nombre, apellido, nacionalidad, cedula, fechaNacimiento, ficha);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .fields(titularField, capitanField)
                .columnGrid(ListType.HORIZONTAL_FLOW, correlativo, tituloAlioneacion, tituloEquipoLocal)
                .columns(titular, sumplente, capitan,
                        correlativo, camisa,
                        nombre, apellido, nacionalidad, cedula, fechaNacimiento, ficha);
        SubreportBuilder subreport = cmp.subreport(reporte);
        return subreport;

    }

    private static SubreportBuilder reporteStaff(String localOVisitante) {

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                .setBorder(stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);

        StyleBuilder textStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());

        TextColumnBuilder<String> nombre = col.column("Nombre", "personaId.nombre", type.stringType());
        TextColumnBuilder<String> apellido = col.column("Apellido", "personaId.apellido", type.stringType());
        TextColumnBuilder<String> cargo = col.column("Cargo", "cargoId.nombre", type.stringType());
        TextColumnBuilder<String> cedula = col.column("Cedula", "personaId.documentoIdentidad", type.stringType());
        TextColumnBuilder<String> cefvf = col.column("C.E.F.V.F.", "cefvf", type.stringType());

        ColumnTitleGroupBuilder tituloStaff
                = grid.titleGroup(localOVisitante, nombre, apellido, cargo, cedula, cefvf);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .columnGrid(ListType.HORIZONTAL_FLOW, tituloStaff)
                .columns(nombre, apellido, cargo, cedula, cefvf);

        SubreportBuilder subreportBuilder = cmp.subreport(reporte);
        return subreportBuilder;

    }

    private static SubreportBuilder reporteArbitro() {

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                .setBorder(stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);

        StyleBuilder textStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());

        TextColumnBuilder<String> nombre = col.column("Nombre", "arbitroId.personaId.nombre", type.stringType());
        TextColumnBuilder<String> apellido = col.column("Apellido", "arbitroId.personaId.apellido", type.stringType());
        TextColumnBuilder<String> tipo = col.column("Tipo", "tipoArbitroId.nombre", type.stringType());
        TextColumnBuilder<String> asociacion = col.column("Asociación", "arbitroId.asociacionId.nombre", type.stringType());
        TextColumnBuilder<String> liga = col.column("Liga", "liga", type.stringType());

        ColumnTitleGroupBuilder tituloStaff
                = grid.titleGroup("Cuerpo Arbitral", nombre, apellido, tipo, asociacion, liga);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .columnGrid(ListType.HORIZONTAL_FLOW, tituloStaff)
                .columns(nombre, apellido, tipo, asociacion, liga);

        SubreportBuilder subreportBuilder = cmp.subreport(reporte);
        return subreportBuilder;

    }

    private static SubreportBuilder reporteCambios(String localOVisitante) {

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                .setBorder(stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);

        StyleBuilder textStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());

        ComponentColumnBuilder entrada = col.componentColumn("Cambio",cmp.text("Entrada"));
        TextColumnBuilder<String> tipo = col.column("Cambio", "tipo", type.stringType());
        TextColumnBuilder<String> nombre = col.column("Nombre", "nombre", type.stringType());
        TextColumnBuilder<String> apellido = col.column("Apellido", "apellido", type.stringType());
        TextColumnBuilder<?> minuto = col.column("Min", "min", type.integerType());
        TextColumnBuilder<Integer> correlativo = col.reportRowNumberColumn("n°").setFixedColumns(2);

        ColumnTitleGroupBuilder tituloEquipoLocal = grid.titleGroup(localOVisitante, entrada, nombre, apellido, minuto);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .columnGrid(correlativo, tituloEquipoLocal)
                .columns(tipo, entrada,
                        correlativo, minuto,
                        nombre, apellido)
                .sortBy(asc(minuto));
        SubreportBuilder subreport = cmp.subreport(reporte);
        return subreport;
    }

    private static SubreportBuilder reporteCambiosSalida(String localOVisitante) {

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                .setBorder(stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);

        StyleBuilder textStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());

        ComponentColumnBuilder salida = col.componentColumn("Cambio",cmp.text("Salida"));
        TextColumnBuilder<String> tipo = col.column("Cambio", "tipo", type.stringType());
        TextColumnBuilder<String> nombre = col.column("Nombre", "nombre", type.stringType());
        TextColumnBuilder<String> apellido = col.column("Apellido", "apellido", type.stringType());
        TextColumnBuilder<?> minuto = col.column("Min", "min", type.integerType());

        ColumnTitleGroupBuilder tituloEquipoLocal = grid.titleGroup(localOVisitante, salida, nombre, apellido, minuto);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .columnGrid(tituloEquipoLocal)
                .columns(tipo, salida,
                        minuto,
                        nombre, apellido)
                .sortBy(asc(minuto));
        SubreportBuilder subreport = cmp.subreport(reporte);
        return subreport;
    }

    private static SubreportBuilder reporteTarjetas(String localOVisitante) {

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                .setBorder(stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);

        StyleBuilder textStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());

        TextColumnBuilder<String> tarjeta = col.column("Tarjeta", "tarjeta", type.stringType());
        TextColumnBuilder<String> nombre = col.column("Nombre", "nombre", type.stringType());
        TextColumnBuilder<String> apellido = col.column("Apellido", "apellido", type.stringType());
        TextColumnBuilder<?> minuto = col.column("Min", "min", type.integerType());
        TextColumnBuilder<?> numeroCamisa = col.column("N° camisa", "camisa", type.integerType());
        TextColumnBuilder<Integer> correlativo = col.reportRowNumberColumn("n°").setFixedColumns(2);

        ColumnTitleGroupBuilder tituloEquipoLocal = grid.titleGroup(localOVisitante, tarjeta, nombre, apellido, minuto, numeroCamisa);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .columnGrid(correlativo, tituloEquipoLocal)
                .columns(tarjeta,
                        correlativo, minuto,
                        nombre, apellido, numeroCamisa)
                .sortBy(asc(minuto));
        SubreportBuilder subreport = cmp.subreport(reporte);
        return subreport;
    }

    private static SubreportBuilder reporteGoles(String localOVisitante) {

        StyleBuilder boldStyle = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);
        StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle)
                .setBorder(stl.pen1Point())
                .setBackgroundColor(Color.LIGHT_GRAY);

        StyleBuilder textStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());

        TextColumnBuilder<String> nombre = col.column("Nombre", "nombre", type.stringType());
        TextColumnBuilder<String> gol = col.column("Gol", new ExpressionColumnGol()).setFixedColumns(3);
        TextColumnBuilder<String> autogol = col.column("Autogol", new ExpressionColumnAutogol()).setFixedColumns(4);

        TextColumnBuilder<String> apellido = col.column("Apellido", "apellido", type.stringType());
        TextColumnBuilder<?> minuto = col.column("Min", "min", type.integerType());
        TextColumnBuilder<?> numeroCamisa = col.column("N° camisa", "camisa", type.integerType());
        TextColumnBuilder<Integer> correlativo = col.reportRowNumberColumn("n°").setFixedColumns(2);

        ColumnTitleGroupBuilder tituloEquipoLocal = grid.titleGroup(localOVisitante, gol, autogol, nombre, apellido, minuto, numeroCamisa);

        FieldBuilder<String> eventoField = field("evento", String.class);
        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .fields(eventoField)
                .columnGrid(correlativo, tituloEquipoLocal)
                .columns(gol, autogol,
                        correlativo, minuto,
                        nombre, apellido, numeroCamisa)
                .sortBy(asc(minuto));
        SubreportBuilder subreport = cmp.subreport(reporte);
        return subreport;
    }

    private static JRDataSource createDataPartido(Partido partido) {
        DRDataSource dataSource = new DRDataSource("competicion", "numero", "categoria",
                "local", "visitante", "fecha", "grupo", "hora", "estadio", "temporada",
                "fase", "jornada", "golL", "golV", "status", "llave", "ciudad", "listadoN", "asistencia");
        dataSource.add(Util.getCompeticion(partido).getNombre(), partido.getNumero(), partido.getCategoriaId().getNombre(),
                partido.getEquipoLocalId().getNombre(), partido.getEquipoVisitanteId().getNombre(), partido.getFecha(),
                Util.getGrupo(partido).getNombre(), partido.getHoraInicio(), partido.getCanchaId().getNombre(),
                Util.getTemporada(partido).getNombre(), Util.getFase(partido).getNombre(),
                Util.getJornada(partido).getNumero(), partido.getGolesEquipoLocal(),
                partido.getGolesEquipoVisitante(), partido.getStatusPartidoId().getNombre(),
                Util.getLlave(partido).getNombre(), Util.getCiudad(partido).getCiudad(),
                partido.getAprobacionNomina(), partido.getAsistencia());

        return dataSource;
    }

    private static JRDataSource createDataEquipo(List<Convocado> equipo) {
        DRDataSource dataSource = new DRDataSource("nombre", "apellido", "camisa", "titular", "nacionalidad", "cedula", "fechaN", "ficha", "capitan");
        for (Convocado equi : equipo) {
            dataSource.add(equi.getJugadorId().getPersonaId().getNombre(),
                    equi.getJugadorId().getPersonaId().getApellido(),
                    equi.getCamiseta(), equi.getTitular(),
                    equi.getJugadorId().getPersonaId().getNacionalidad(),
                    equi.getJugadorId().getPersonaId().getDocumentoIdentidad(),
                    equi.getJugadorId().getPersonaId().getFechaNacimiento(),
                    Util.getFicha(equi), equi.getCapitan());
        }

        return dataSource;
    }

    private static JRDataSource createDataStaff(List<Staff> staff) {
//        convocado.getConvocatoriaId().getEquipoId();
        DRDataSource dataSource = new DRDataSource(
                "personaId.nombre", "personaId.apellido", "cargoId.nombre", "personaId.documentoIdentidad", "cefvf");
        for (Staff staffElem : staff) {
            dataSource.add(staffElem.getPersonaId().getNombre(),
                    staffElem.getPersonaId().getApellido(),
                    staffElem.getCargoId().getNombre(),
                    staffElem.getPersonaId().getDocumentoIdentidad(),
                    staffElem.getPersonaId().getCefvf());
        }
        return dataSource;
    }

    private static JRDataSource createDataArbitro(List<PartidoArbitro> arbitros) {
        DRDataSource dataSource = new DRDataSource(
                "arbitroId.personaId.nombre", "arbitroId.personaId.apellido",
                "tipoArbitroId.nombre", "arbitroId.asociacionId.nombre", "liga");
        for (PartidoArbitro arbitro : arbitros) {
            dataSource.add(arbitro.getArbitroId().getPersonaId().getNombre(),
                    arbitro.getArbitroId().getPersonaId().getApellido(),
                    arbitro.getTipoArbitroId().getNombre(),
                    getAsociacion(arbitro), getLiga(arbitro));

        }
        return dataSource;
    }

    private static String getAsociacion(PartidoArbitro arbitro) {
        if (arbitro.getArbitroId().getAsociacionId() == null) {
            return "";
        } else {
            return arbitro.getArbitroId().getAsociacionId().getNombre();
        }
    }

    private static String getLiga(PartidoArbitro arbitro) {
        if (arbitro.getArbitroId().getCompeticionId() == null) {
            return "";
        } else {
            return arbitro.getArbitroId().getCompeticionId().getNombre();
        }
    }

    private static JRDataSource createDataCambio(List<PartidoEvento> eventos) {
        DRDataSource dataSource = new DRDataSource(
                "nombre", "apellido", "min");
        for (PartidoEvento evento : getEntrada(eventos)) {
            dataSource.add(evento.getConvocadoId().getJugadorId().getPersonaId().getNombre(),
                    evento.getConvocadoId().getJugadorId().getPersonaId().getApellido(),
                    evento.getMinuto());
        }
        return dataSource;
    }

    private static List<PartidoEvento> getPartidoEvento(Equipo equipo, List<PartidoEvento> eventos) {
        List<PartidoEvento> list = new ArrayList<>();
        for (PartidoEvento evento : eventos) {
            if (evento.getConvocadoId().getConvocatoriaId().getEquipoId().equals(equipo)) {
                list.add(evento);
            }
        }
        return list;
    }

    private static JRDataSource createDataCambioSalida(List<PartidoEvento> eventos) {
        DRDataSource dataSource = new DRDataSource(
                "nombre", "apellido", "min");
        for (PartidoEvento evento : getSalida(eventos)) {
            dataSource.add(evento.getConvocadoId().getJugadorId().getPersonaId().getNombre(),
                    evento.getConvocadoId().getJugadorId().getPersonaId().getApellido(),
                    evento.getMinuto());
        }
        return dataSource;
    }

    private static List<PartidoEvento> getSalida(List<PartidoEvento> eventos) {
        List<PartidoEvento> salidas = new ArrayList<>();
        for (PartidoEvento evento : eventos) {
            if (evento.getEventoId().getNombre().equals("Salida") && evento.getCantidad() == 0) {
                salidas.add(evento);
            }
        }
        return salidas;
    }

    private static List<PartidoEvento> getEntrada(List<PartidoEvento> eventos) {
        List<PartidoEvento> entradas = new ArrayList<>();
        for (PartidoEvento evento : eventos) {
            if (evento.getEventoId().getNombre().equals("Entrada") && evento.getCantidad() == 0) {
                entradas.add(evento);
            }
        }
        return entradas;
    }

    private static JRDataSource createDataTarjeta(List<PartidoEvento> eventos) {
        DRDataSource dataSource = new DRDataSource(
                "nombre", "apellido",
                "tarjeta", "min", "camisa");

        for (PartidoEvento evento : eventos) {
            dataSource.add(evento.getConvocadoId().getJugadorId().getPersonaId().getNombre(),
                    evento.getConvocadoId().getJugadorId().getPersonaId().getApellido(),
                    evento.getEventoId().getNombre(),
                    evento.getMinuto(),
                    evento.getConvocadoId().getCamiseta());
        }
        return dataSource;
    }

    private static List<PartidoEvento> getTarjetas(List<PartidoEvento> eventos) {
        List<PartidoEvento> list = new ArrayList<>();
        for (PartidoEvento evento : eventos) {
            String nombre = evento.getEventoId().getNombre();
            if (evento.getCantidad() == 0) {
                if (nombre.equals("Roja") || nombre.equals("Amarilla") || nombre.equals("Roja Directa")) {
                    list.add(evento);
                }
            }

        }
        return list;
    }

    private static JRDataSource createDataGoles(List<PartidoEvento> eventos) {
        DRDataSource dataSource = new DRDataSource(
                "nombre", "apellido", "min", "camisa", "evento");
        for (PartidoEvento evento : getGoles(eventos)) {
            dataSource.add(evento.getConvocadoId().getJugadorId().getPersonaId().getNombre(),
                    evento.getConvocadoId().getJugadorId().getPersonaId().getApellido(),
                    evento.getMinuto(),
                    evento.getConvocadoId().getCamiseta(),
                    evento.getEventoId().getNombre());
        }
        return dataSource;
    }

    private static List<PartidoEvento> getGoles(List<PartidoEvento> eventos) {
        List<PartidoEvento> list = new ArrayList<>();
        for (PartidoEvento evento : eventos) {
            String nombre = evento.getEventoId().getNombre();
            if (nombre.equals("Gol") || nombre.equals("Autogol")) {
                list.add(evento);
            }
        }
        return list;
    }

    private static class ExpressionColumnGol extends AbstractSimpleExpression<String> {

        @Override
        public String evaluate(ReportParameters reportParameters) {
            String evento;
            evento = reportParameters.getValue("evento");
            if (evento.equals("Gol")) {
                return " X";
            } else {

                return "";
            }
        }
    }

    private static class ExpressionColumnAutogol extends AbstractSimpleExpression<String> {

        @Override
        public String evaluate(ReportParameters reportParameters) {
            String titular;
            titular = reportParameters.getValue("evento");
            if (titular.equals("Autogol")) {
                return " X";
            } else {

                return "";
            }
        }
    }

    private static class ExpressionColumnTitu extends AbstractSimpleExpression<String> {

        @Override
        public String evaluate(ReportParameters reportParameters) {
            int titular;
            titular = reportParameters.getValue("titular");
            if (titular == 1) {
                return " X";
            } else {

                return "";
            }
        }
    }

    private static class ExpressionColumnSuple extends AbstractSimpleExpression<String> {

        @Override
        public String evaluate(ReportParameters reportParameters) {
            int titular;
            titular = reportParameters.getValue("titular");
            if (titular == 1) {
                return "";
            } else {

                return " X";
            }
        }
    }

    private static class ExpressionColumnCap extends AbstractSimpleExpression<String> {

        @Override
        public String evaluate(ReportParameters reportParameters) {
            int titular;
            titular = reportParameters.getValue("capitan");
            if (titular == 1) {
                return " X";
            } else {

                return " ";
            }
        }
    }

}
