/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Cargo;
import com.spontecorp.futboldata.entity.Club;
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
    private List<Cargo> cargoList;
    private List<Staff> staffList;
    private List<Staff> staffListAll;
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
    }

    public void create() {
        persona = staff.getPersonaId();
        staff = new Staff();
        staff.setPersonaId(persona);
        staff.setStatus(ACTIVO);
        staff.setCargoId(cargo);
        staff.setClubId(club);
        controllerStaff.create(staff);
    }

    public Staff getStaff() {
        if (staff == null) {
            staff = new Staff();
        }
        return staff;
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
        return staffList;
    }

    public List<Staff> getStaffListAll() {
        staffListAll = controllerStaff.findAll();
        return staffListAll;
    }

    public void setStaffListAll(List<Staff> staffListAll) {
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
        return "staffinclub/listStaff?faces-redirect=true";

    }

    public String gotoClubPage() {
        return "/admin/club/list";

    }
}
