package ma.emsi.patientmvc.entities;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


public class AppUser {
    private String userid;
    private String username;
    private String password;
    private boolean active;
    private List<AppRole> appRoles=new ArrayList<>();
}
