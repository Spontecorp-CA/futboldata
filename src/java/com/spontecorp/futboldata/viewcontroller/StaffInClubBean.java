/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Asociacion;
import com.spontecorp.futboldata.entity.Cargo;
import com.spontecorp.futboldata.entity.Club;
import com.spontecorp.futboldata.entity.Competicion;
import com.spontecorp.futboldata.entity.Equipo;
import com.spontecorp.futboldata.entity.Persona;
import com.spontecorp.futboldata.entity.Staff;
import com.spontecorp.futboldata.jpacontroller.CargoFacade;
import com.spontecorp.futboldata.jpacontroller.ClubFacade;
import com.spontecorp.futboldata.jpacontroller.StaffFacade;
import static com.spontecorp.futboldata.utilities.Util.ACTIVO;
import static com.spontecorp.futboldata.utilities.Util.INACTIVO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("staffInClub")
@SessionScoped
public class StaffInClubBean implements Serializable {

    private Staff staff;
    private Club club;
    private Cargo cargo;
    private Persona persona;
    private Equipo equipo;
    private Asociacion asociacion;
    private Competicion competicion;
    private List<Cargo> cargoList;
    private List<Staff> staffList;
    private List<Persona> staffListAll;
    private List<Staff> filterededStaffList;

    private final StaffFacade controllerStaff;
    private final ClubFacade controllerClub;
    private final CargoFacade controllerCargo;

    public void prepareEdit() {
        staff = new Staff();
        cargo = new Cargo();

    }

    public StaffInClubBean() {
        controllerClub = new ClubFacade();
        controllerStaff = new StaffFacade();
        controllerCargo = new CargoFacade();
    }

    public void delete() {
        staff.setStatus(INACTIVO);
        controllerStaff.edit(staff);
        staff = null;
        staffList = null;
    }

    public void create() {

        staff = new Staff();
        staff.setPersonaId(persona);
        staff.setStatus(ACTIVO);
        staff.setCargoId(cargo);
        if (club != null) {
            staff.setClubId(club);
        }
        if (equipo != null) {
            staff.setEquipoId(equipo);
        }
        if (asociacion != null) {
            staff.setAsociacionId(asociacion);
        }
        if (competicion != null) {
            staff.setCompeticionId(competicion);
        }
        controllerStaff.edit(staff);
        staffList = null;
    }

    public Staff getStaff() {
        if (staff == null) {
            staff = new Staff();
        }
        return staff;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Cargo> getCargoList() {
        cargoList = controllerCargo.findAll();
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    public List<Staff> getStaffList() {
        if (staffList == null) {
            if (club != null) {
                staffList = controllerStaff.findStaffListByClub(club);
            }
            if (equipo != null) {
                staffList = controllerStaff.findStaffListByEquipo(equipo);
            }
            if (asociacion != null) {
                staffList = controllerStaff.findStaffListByAsociacion(asociacion);
            }
            if (competicion != null) {
                staffList = controllerStaff.findStaffListByCompeticion(competicion);
            }

        }
        return staffList;
    }

    public List<Persona> getStaffListAll() {
        staffListAll = controllerStaff.findDistinctStaffList();
        return staffListAll;
    }

    public void setStaffListAll(List<Persona> staffListAll) {
        this.staffListAll = staffListAll;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public List<Staff> getFilterededStaffList() {
        return filterededStaffList;
    }

    public void setFilterededStaffList(List<Staff> filterededStaffList) {
        this.filterededStaffList = filterededStaffList;
    }

    public String gotoStaffPage(Club club) {
        staffList = controllerStaff.findStaffListByClub(club);
        this.club = club;
        equipo = null;
        competicion = null;
        asociacion = null;
        return "/admin/staff/listStaff?faces-redirect=true";

    }

    public String gotoStaffPage(Equipo equipo) {
        staffList = controllerStaff.findStaffListByEquipo(equipo);
        this.equipo = equipo;
        asociacion = null;
        competicion = null;
        club = null;
        return "/admin/staff/listStaff?faces-redirect=true";

    }

    public String gotoStaffPage(Asociacion asociacion) {
        staffList = controllerStaff.findStaffListByAsociacion(asociacion);
        this.asociacion = asociacion;
        equipo = null;
        competicion = null;
        club = null;
        return "/admin/staff/listStaff?faces-redirect=true";

    }

    public String gotoStaffPage(Competicion competicion) {
        staffList = controllerStaff.findStaffListByCompeticion(competicion);
        this.competicion = competicion;
        equipo = null;
        asociacion = null;
        club = null;
        return "/admin/staff/listStaff?faces-redirect=true";

    }

    public String gotoClubPage() {
        return "/admin/club/list?faces-redirect=true";

    }

    public String gotoEquipoPage() {
        return "/admin/equipo/equipo/list?faces-redirect=true";

    }

    public String gotoAsociacionPage() {
        return "/admin/asociacion/asociacion/list?faces-redirect=true";

    }

    public String gotoCompeticionPage() {
        return "/admin/liga/competicion/list?faces-redirect=true";

    }
}
