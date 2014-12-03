/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.reportes.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.field;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.sbt;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.PageXofYBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.ListType;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.velocity.Template;

/**
 *
 * @author sponte03
 */
public class ReporteDinamico {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public JasperReportBuilder generarReporteDinamico(DynamicReport dynamicR, String titulo, Collection<?> collection) throws DRException {

        JasperReportBuilder report = report();
        //prueba new 
//        FieldBuilder<Integer> idField = field("id", type.integerType());
//        report.title(Templates.createTitleComponent("Otra cosa"),columnPair("hola", idField));
//        report.titleOnANewPage();
        report
                .setTemplate(Templates.reportTemplate)
                .title(Templates.createTitleComponent(titulo));
   

        DynamicReport dynamicReport = dynamicR;
        
        for (String dyString : (dynamicReport.getSubTitles())) {
            report.addTitle(cmp.text(dyString).setStyle(Templates.bold12CenteredStyle)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER));
        }

        List<DynamicColumn> columns = dynamicReport.getColumns();
        Map<String, TextColumnBuilder> drColumns = new HashMap<String, TextColumnBuilder>();

        List<DynamicColumnTitle> columnTitles = dynamicR.getColumnTitles();
        List<ColumnTitleGroupBuilder> columnTitleGroup = new ArrayList<ColumnTitleGroupBuilder>();
        if (dynamicR.isShowRowNumber()) {
            TextColumnBuilder builder = col.columnRowNumberColumn("No");
            report.columnGrid(builder);

        }

        if (!columnTitles.isEmpty()) {

            for (DynamicColumnTitle title : columnTitles) {
                ColumnTitleGroupBuilder titleGroup = grid.titleGroup(title.getTitulo());
                for (DynamicColumn column : title.getDynamicColumns()) {
                    TextColumnBuilder drColumn = col.column(column.getTitle(), column.getName(), (DRIDataType) type.detectType(column.getType()));
                    if (column.getPattern() != null) {
                        drColumn.setPattern(column.getPattern());
                    }
                    if (column.getFixedColumns() != 0) {
                        drColumn.setFixedColumns(column.getFixedColumns());
                    }
                    if (column.getHorizontalAlignment() != null) {
                        drColumn.setHorizontalAlignment(column.getHorizontalAlignment());
                    }
                    drColumns.put(column.getName(), drColumn);
                    report.columns(drColumn);

                    titleGroup.add(drColumn);
                }
                columnTitleGroup.add(titleGroup);
            }

            ColumnTitleGroupBuilder[] array = new ColumnTitleGroupBuilder[columnTitleGroup.size()];
            columnTitleGroup.toArray(array);
            report.columnGrid(array);
        } else {
            for (DynamicColumn column : columns) {
                TextColumnBuilder drColumn = col.column(column.getTitle(), column.getName(), (DRIDataType) type.detectType(column.getType()));
                if (column.getPattern() != null) {
                    drColumn.setPattern(column.getPattern());
                }
                if (column.getFixedColumns() != 0) {
                    drColumn.setFixedColumns(column.getFixedColumns());
                }
                if (column.getHorizontalAlignment() != null) {
                    drColumn.setHorizontalAlignment(column.getHorizontalAlignment());
                }
                drColumns.put(column.getName(), drColumn);
                report.columns(drColumn);
            }
        }

        for (String group : dynamicReport.getGroups()) {
            ColumnGroupBuilder group2 = grp.group(drColumns.get(group));
            report.groupBy(group2);

            for (String subtotal : dynamicReport.getSubtotals()) {
                report.subtotalsAtGroupFooter(group2, sbt.sum(drColumns.get(subtotal)));
            }
        }

        for (String subtotal : dynamicReport.getSubtotals()) {
            report.subtotalsAtSummary(sbt.sum(drColumns.get(subtotal)));
        }

        if (dynamicReport.getTitle() != null) {
            TextFieldBuilder<String> title = cmp.text(dynamicReport.getTitle())
                    .setStyle(Templates.bold12CenteredStyle)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            report.addTitle(title);
        }
        if (dynamicReport.isShowPageNumber()) {
            PageXofYBuilder pageXofY = cmp.pageXofY()
                    .setStyle(Templates.boldCenteredStyle);
            report.addPageFooter(pageXofY);
        }
        report.setDataSource(collection);

        return report;
    }

    private VerticalListBuilder columnPair(String title, FieldBuilder<?> value) {

        TextFieldBuilder<String> titleCmp = cmp.text(title)
                .setStyle(Templates.columnTitleStyle);
        TextFieldBuilder<?> valueCmp = cmp.text(value);
        return cmp.verticalList(titleCmp, valueCmp);
    }

}
