/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.reportes.view;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.entity.Clasifica;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Fase;
import com.spontecorp.futboldata.entity.Grupo;
import com.spontecorp.futboldata.entity.Jornada;
import com.spontecorp.futboldata.entity.Llave;
import com.spontecorp.futboldata.entity.Temporada;
import com.spontecorp.futboldata.jpacontroller.CompeticionFacade;
import com.spontecorp.futboldata.jpacontroller.FaseFacade;
import com.spontecorp.futboldata.jpacontroller.GrupoFacade;
import com.spontecorp.futboldata.jpacontroller.JornadaFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaCategoriaFacade;
import com.spontecorp.futboldata.jpacontroller.TemporadaFacade;
import com.spontecorp.futboldata.reportes.ReportesDAO;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@Named(value = "reportesBean")
@SessionScoped
public class ReportesBean implements Serializable {

    private Competicion liga;
    private Temporada temporada;
    private Fase fase;
    private Llave llave;
    private Jornada jornada;
    private Grupo grupo;
    private Categoria categoria;
    private List<Clasifica> clasificacion;
    private final ReportesDAO dao;
    private JasperPrint jasperPrint;

    private List<Competicion> ligas;
    private List<Temporada> temporadaList;
    private List<Fase> fases;
    private List<Grupo> grupos;
    private List<Jornada> jornadas;
    private List<Categoria> categorias;
    private List<Equipo> equipos;

    private final CompeticionFacade ligaController;
    private final TemporadaFacade temporadaFacade;
    private final FaseFacade faseFacade;
    private final GrupoFacade grupoFacade;
    private final JornadaFacade jornadaFacade;
    private final TemporadaCategoriaFacade temporadaCategoriaFacade;

    private StreamedContent file;

    private static final Logger logger = LoggerFactory.getLogger(ReportesBean.class);

    /**
     * Creates a new instance of ReportesBean
     */
    public ReportesBean() {
        dao = new ReportesDAO();
        this.ligaController = new CompeticionFacade();
        this.temporadaFacade = new TemporadaFacade();
        this.faseFacade = new FaseFacade();
        this.grupoFacade = new GrupoFacade();
        this.jornadaFacade = new JornadaFacade();
        this.temporadaCategoriaFacade = new TemporadaCategoriaFacade();
    }

    public Competicion getLiga() {
        return liga;
    }

    public void setLiga(Competicion liga) {
        this.liga = liga;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Llave getLlave() {
        return llave;
    }

    public void setLlave(Llave llave) {
        this.llave = llave;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Clasifica> getClasificacion() {
        return clasificacion;
    }

    public void makeClasificaXGrupo() {
        if (categoria != null) {
            clasificacion = dao.clasificaXGrupoAndJornada(jornada, grupo, categoria);
        } else {
            clasificacion = dao.clasificaXGrupoAndClub(jornada, grupo, categoria);
        }

    }

    public List<Competicion> getItems() {
        if (ligas == null) {
            ligas = ligaController.findAll();
        }
        return ligas;
    }

    public void ligaSelected() {
        recreateModelTemporada();
        temporadaList = null;
    }

    public void temporadaSelected() {
        recreateModelFase();
    }

    public void faseSelected() {
        recreateModelGrupo();
    }

    public void grupoSelected() {
        recreateModelCategoria();
    }

    public void categoriaSelected() {
        recreateModelJornada();
    }

    public List<Temporada> getTemporadas() {
        if (temporadaList == null) {
            temporadaList = new ArrayList<Temporada>(temporadaFacade.findTemporadaxLiga(liga));
        }
        return temporadaList;
    }

    public List<Fase> getFases() {
        if (fases == null) {
            fases = new ArrayList<Fase>(faseFacade.findFasexTemporada(temporada));
        }
        return fases;
    }

    public List<Grupo> getGrupos() {
        if (grupos == null) {
            grupos = grupoFacade.findGruposXFase(fase);
        }
        return grupos;
    }

    public List<Jornada> getJornadas() {
        if (jornadas == null) {
            jornadas = jornadaFacade.findJornadasxGrupo(grupo);
        }
        return jornadas;
    }

    public List<Categoria> getCategorias() {
        if (categorias == null) {
            categorias = temporadaCategoriaFacade.getCategorias(temporada);
        }
        return categorias;
    }

    public void recreateModelTemporada() {
        temporada = null;
        temporadaList = null;
        recreateModelFase();
    }

    public void recreateModelFase() {
        fase = null;
        fases = null;
        recreateModelGrupo();
    }

    private void recreateModelGrupo() {
        grupo = null;
        grupos = null;
        recreateModelCategoria();
    }

    private void recreateModelCategoria() {
        categoria = null;
        categorias = null;
        recreateModelJornada();
    }

    private void recreateModelJornada() {
        jornada = null;
        jornadas = null;
    }

    public void init() {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(clasificacion);
        try {
            jasperPrint = JasperFillManager.fillReport("C:\\Users\\sponte03.SPONTECORP\\Documents\\Felix_Angulo\\futboldata\\src\\java\\com\\spontecorp\\futboldata\\reportes\\printed\\exampletemplate.jasper",
                    new HashMap(), beanCollectionDataSource);

        } catch (JRException ex) {
            java.util.logging.Logger.getLogger(ReportesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PDF(ActionEvent actionEvent) throws IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        ServletOutputStream outputStream;
        try {
            String filePath1 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + File.separator + "sample_report.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, filePath1);

            outputStream = httpServletResponse.getOutputStream();
            httpServletResponse.reset();
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setHeader("Content-Type", "application/pdf");
            httpServletResponse.addHeader("Content-Disposition", "inline; filename=\"" + "reporte.pdf" + "\"");
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ReportesBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            java.util.logging.Logger.getLogger(ReportesBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    public StreamedContent getFile() {
        return file;
    }
}
