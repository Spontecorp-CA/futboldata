/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.reportes.template;

import com.spontecorp.futboldata.entity.Convocado;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.field;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
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
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author sponte03
 */
public class InformeArbitralReport {
    private static FieldBuilder<?> titularField;

    public static JasperReportBuilder crearReporte(String comentario) {

        SubreportBuilder subreportEquipoL = reporteEquipo("Equipo Local").setDataSource(createDataSource2());
        SubreportBuilder subreportEquipoV = reporteEquipo("Equipo Visitante").setDataSource(createDataSource3());

        SubreportBuilder subreportStaffVisitante = reporteStaff("Staff Visitante").setDataSource(createDataStaff());
        SubreportBuilder subreportStaffLocal = reporteStaff("Staff Local").setDataSource(createDataStaff());

        SubreportBuilder subreportArbitro = reporteArbitro().setDataSource(createDataArbitro());

        SubreportBuilder subreportCambioL = reporteCambios("Equipo Local").setDataSource(createDataCambio()).setWidth(100);
        SubreportBuilder subreportCambioV = reporteCambios("Equipo Visitante").setDataSource(createDataCambio()).setWidth(100);

        SubreportBuilder subreportTarjetaL = reporteTarjetas("Tarjeta Equipo Local").setDataSource(createDataTarjeta());
        SubreportBuilder subreportTarjetaV = reporteTarjetas("Tarjeta Equipo Visitante").setDataSource(createDataTarjeta());

        SubreportBuilder subreportGolesL = reporteGoles("Goles Equipo Local").setDataSource(createDataGoles());
        SubreportBuilder subreportGolesV = reporteGoles("Goles Equipo Visitante").setDataSource(createDataGoles());
        comentario = "Holatregdfgfffffffffffffffffgggggggggggggggddddddddddddggggggdff";
        JasperReportBuilder builder;
        builder = null;
        builder = report()
                .setTemplate(Templates.reportTemplate)
                .detailFooter(cmp.verticalGap(20))
                .pageFooter(Templates.footerComponent)
                .title(Templates.createTitleComponent("Otro Intento"),
                        cmp.verticalList(reportePartido(),
                                cmp.line().setDirection(LineDirection.BOTTOM_UP),
                                cmp.verticalGap(20),
                                subreportEquipoL,
                                subreportStaffLocal,
                                cmp.verticalGap(20),
                                subreportEquipoV,
                                subreportStaffVisitante,
                                cmp.verticalGap(20),
                                subreportArbitro))
                .setDataSource(createDataSource())
                .title(cmp.text("Detalles Partido"),
                        cmp.horizontalList(subreportCambioL, cmp.horizontalGap(5), subreportCambioV),
                        cmp.verticalGap(20),
                        cmp.horizontalList(subreportTarjetaL, cmp.horizontalGap(5), subreportTarjetaV),
                        cmp.verticalGap(20),
                        cmp.horizontalList(subreportGolesL, cmp.horizontalGap(5), subreportGolesV))
                .columns(col.componentColumn("Comentario", cmp.text(comentario)));

        return builder;
    }

    private static VerticalListBuilder columnPair(String title, FieldBuilder<?> value) {

        TextFieldBuilder<String> titleCmp = cmp.text(title)
                .setStyle(Templates.columnTitleStyle);
        TextFieldBuilder<?> valueCmp = cmp.text(value);
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
        FieldBuilder<Date> fecha = field("orderdate", type.dateType());
        FieldBuilder<String> grupo = field("grupo", type.stringType());
        FieldBuilder<Date> hora = field("orderdate", type.timeHourToFractionType());
        FieldBuilder<?> estadio = field("estadio", type.stringType());
        FieldBuilder<?> temporada = field("temporada", type.stringType());
        FieldBuilder<?> fase = field("fase", type.stringType());
        FieldBuilder<?> jornada = field("jornada", type.integerType());
        FieldBuilder<?> golL = field("golL", type.stringType());
        FieldBuilder<?> golV = field("golV", type.stringType());
        FieldBuilder<?> statusP = field("statusPartidoId.nombre", type.stringType());
        FieldBuilder<?> llave = field("llave", type.stringType());
        FieldBuilder<?> ciudad = field("ciudadId.nombre", type.stringType());
        FieldBuilder<?> listadoN = field("listadoN", type.stringType());

        StyleBuilder textStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());

        JasperReportBuilder reporte = report()
                .setColumnStyle(textStyle)
                .columnGrid(ListType.HORIZONTAL_FLOW)
                .fields(competicion, numeroPartido, categoria, local, visitante, fecha, grupo, hora, estadio, temporada,
                        fase, jornada, golL, golV, statusP, llave, ciudad, listadoN)
                .columns(
                        col.componentColumn(columnPair("Competicion", competicion)),
                        col.componentColumn(columnPair("n°Partido", numeroPartido)),
                        col.componentColumn(columnPair("Categoria", categoria)),
                        col.componentColumn(columnPair("Local", local)),
                        col.componentColumn(columnPair("Visitante", visitante)),
                        col.componentColumn(columnPair("Fecha", fecha)),
                        col.componentColumn(columnPair("Grupo", grupo)),
                        col.componentColumn(columnPair("Hora", hora)),
                        col.componentColumn(columnPair("Estadio", estadio)),
                        col.componentColumn(columnPair("Temporada", temporada)),
                        col.componentColumn(columnPair("Fase", fase)),
                        col.componentColumn(columnPair("Jornada", jornada)),
                        col.componentColumn(columnPair("Goles V", golV)),
                        col.componentColumn(columnPair("Goles L", golL)),
                        col.componentColumn(columnPair("Status", statusP)),
                        col.componentColumn(columnPair("Llave", llave)),
                        col.componentColumn(columnPair("Ciudad", ciudad)),
                        col.componentColumn(columnPair("Lista n°", listadoN))
                )
                .detailFooter(cmp.verticalGap(20))
                .setDataSource(createDataSource());
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

        TextColumnBuilder<String> nombre = col.column("Nombre", "item2", type.stringType());
        TextColumnBuilder<String> apellido = col.column("Apellido", "item2", type.stringType());
        TextColumnBuilder<Integer> camisa = col.column("n° Camisa", "camisa", type.integerType()).setFixedColumns(2);
        TextColumnBuilder<String> titular = col.column("Tit",new ExpressionColumn()).setFixedColumns(2);
        TextColumnBuilder<Integer> sumplente = col.column("Sup", "suplente", type.integerType()).setFixedColumns(2);
        TextColumnBuilder<Integer> correlativo = col.pageRowNumberColumn("n°").setFixedColumns(2);
        TextColumnBuilder<String> equipo = col.column("Equipo", "equipo", type.stringType());
        TextColumnBuilder<String> nacionalidad = col.column("Nac", "nacionalidad", type.stringType()).setFixedColumns(2);
        TextColumnBuilder<String> cedula = col.column("Cedula", "documentoIdentidad", type.stringType()).setFixedColumns(11);
        TextColumnBuilder<?> fechaNacimiento = col.column("Fecha N.", "fechaNac", type.dateType()).setFixedColumns(8);

        ColumnTitleGroupBuilder tituloAlioneacion = grid.titleGroup("Alineacion", camisa, titular, sumplente);
        ColumnTitleGroupBuilder tituloEquipoLocal = grid.titleGroup(localOVisitante, nombre, apellido, nacionalidad, cedula, fechaNacimiento);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .fields(titularField = field("titular", Integer.class))
                .columnGrid(ListType.HORIZONTAL_FLOW, correlativo, tituloAlioneacion, tituloEquipoLocal)
                .columns(equipo, titular, sumplente,
                        correlativo, camisa,
                        nombre, apellido, nacionalidad, cedula, fechaNacimiento);
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
        TextColumnBuilder<String> cedula = col.column("Asociacion", "arbitroId.asociacionId.nombre", type.stringType());

        ColumnTitleGroupBuilder tituloStaff
                = grid.titleGroup(localOVisitante, nombre, apellido, cargo, cedula);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .columnGrid(ListType.HORIZONTAL_FLOW, tituloStaff)
                .columns(nombre, apellido, cargo, cedula);

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

        ColumnTitleGroupBuilder tituloStaff
                = grid.titleGroup("Cuerpo Arbitral", nombre, apellido, tipo, asociacion);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .columnGrid(ListType.HORIZONTAL_FLOW, tituloStaff)
                .columns(nombre, apellido, tipo, asociacion);

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

        TextColumnBuilder<String> tipo = col.column("Cambio", "tipo", type.stringType());
        TextColumnBuilder<String> nombre = col.column("Nombre", "nombre", type.stringType());
        TextColumnBuilder<String> apellido = col.column("Apellido", "apellido", type.stringType());
        TextColumnBuilder<?> minuto = col.column("Min", "min", type.integerType());
        TextColumnBuilder<Integer> correlativo = col.pageRowNumberColumn("n°").setFixedColumns(2);

        ColumnTitleGroupBuilder tituloEquipoLocal = grid.titleGroup(localOVisitante, tipo, nombre, apellido, minuto);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .columnGrid(correlativo, tituloEquipoLocal)
                .columns(tipo,
                        correlativo, minuto,
                        nombre, apellido);
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
        TextColumnBuilder<Integer> correlativo = col.pageRowNumberColumn("n°").setFixedColumns(2);

        ColumnTitleGroupBuilder tituloEquipoLocal = grid.titleGroup(localOVisitante, tarjeta, nombre, apellido, minuto, numeroCamisa);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .columnGrid(correlativo, tituloEquipoLocal)
                .columns(tarjeta,
                        correlativo, minuto,
                        nombre, apellido, numeroCamisa);
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
        TextColumnBuilder<String> evento = col.column("Evento", "evento", type.stringType());

        TextColumnBuilder<String> apellido = col.column("Apellido", "apellido", type.stringType());
        TextColumnBuilder<?> minuto = col.column("Min", "min", type.integerType());
        TextColumnBuilder<?> numeroCamisa = col.column("N° camisa", "camisa", type.integerType());
        TextColumnBuilder<Integer> correlativo = col.pageRowNumberColumn("n°").setFixedColumns(2);

        ColumnTitleGroupBuilder tituloEquipoLocal = grid.titleGroup(localOVisitante, evento, nombre, apellido, minuto, numeroCamisa);

        JasperReportBuilder reporte = report()
                .setColumnTitleStyle(columnTitleStyle)
                .setColumnStyle(textStyle)
                .columnGrid(correlativo, tituloEquipoLocal)
                .columns(evento,
                        correlativo, minuto,
                        nombre, apellido, numeroCamisa);
        SubreportBuilder subreport = cmp.subreport(reporte);
        return subreport;
    }

    private static JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("id", "item", "orderdate", "quantity", "unitprice");
        dataSource.add(5, "Notebook", new Date(), 1, new BigDecimal(500));

        return dataSource;
    }

    private static JRDataSource createDataSource2() {
        Convocado convocado = new Convocado();
//        convocado.getConvocatoriaId().getEquipoId();
        DRDataSource dataSource = new DRDataSource("item2", "camisa", "equipo","titular");
        dataSource.add("Notebookfdg", 3, "EquipoB",1);
        dataSource.add("Booksdf", 4, "EquipoB",0);
        dataSource.add("PDAsdfds", 6, "EquipoB",0);
        return dataSource;
    }

    private static JRDataSource createDataSource3() {
        Convocado convocado = new Convocado();
//        convocado.getConvocatoriaId().getEquipoId();
        DRDataSource dataSource = new DRDataSource("item2", "camisa", "equipo","titular");
        dataSource.add("Notebookfsdf", 1, "EquipoA",1);
        dataSource.add("Book2", 2, "EquipoA",0);
        dataSource.add("PDA3", 5, "EquipoA",1);
        return dataSource;
    }

    private static JRDataSource createDataStaff() {
//        convocado.getConvocatoriaId().getEquipoId();
        DRDataSource dataSource = new DRDataSource(
                "personaId.nombre", "personaId.apellido", "cargoId.nombre", "personaId.documentoIdentidad");
        dataSource.add("Juan", "Perez", "Director TC", "197236.61");
        return dataSource;
    }

    private static JRDataSource createDataArbitro() {
        DRDataSource dataSource = new DRDataSource(
                "arbitroId.personaId.nombre", "arbitroId.personaId.apellido",
                "tipoArbitroId.nombre", "arbitroId.asociacionId.nombre");
        dataSource.add("Feliz", "Montoya", "Principal", "Duaca");
        return dataSource;
    }

    private static JRDataSource createDataCambio() {
        DRDataSource dataSource = new DRDataSource(
                "nombre", "apellido",
                "tipo", "min");
        dataSource.add("Don", "Juan", "Salida", 10);
        dataSource.add("Mujica", "PEPE", "Entrada", 10);
        return dataSource;
    }

    private static JRDataSource createDataTarjeta() {
        DRDataSource dataSource = new DRDataSource(
                "nombre", "apellido",
                "tarjeta", "min", "camisa");
        dataSource.add("Jose", "Diaz", "Amarilla", 10, 12);
        dataSource.add("Ruben", "Perez", "Roja", 10, 24);
        return dataSource;
    }

    private static JRDataSource createDataGoles() {
        DRDataSource dataSource = new DRDataSource(
                "nombre", "apellido", "min", "camisa", "evento");
        dataSource.add("Goyo", "Diaz", 10, 12, "Gol");
        dataSource.add("Josebas", "Perez", 10, 24, "Autogol");
        return dataSource;
    }

    private static class ExpressionColumn extends AbstractSimpleExpression<String> {

        @Override
        public String evaluate(ReportParameters reportParameters) {
            int titular;
            titular = reportParameters.getValue("titular");
            if (titular == 1) {
                return "T";
            } else {
                
                return "S";
            }
        }
    }

}
