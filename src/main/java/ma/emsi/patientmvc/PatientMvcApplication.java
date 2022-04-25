package ma.emsi.patientmvc;

import ma.emsi.patientmvc.entities.Medecin;
import ma.emsi.patientmvc.entities.Patient;
import ma.emsi.patientmvc.repositories.MedecinRepository;
import ma.emsi.patientmvc.repositories.PatientRepository;
import ma.emsi.patientmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return  args -> {
            //patientRepository.save(new Patient(null,"Hassan",new Date(),false,12));
            //patientRepository.save(new Patient(null,"Mohammed",new Date(),true,321));
            //patientRepository.save(new Patient(null,"Yasmine",new Date(),true,65));
            //patientRepository.save(new Patient(null,"Hanae",new Date(),false,32));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("mohamed","1234","1234");
            securityService.saveNewUser("hassan","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("mohamed","USER");
            securityService.addRoleToUser("hassan","ADMIN");
            securityService.addRoleToUser("hassan","USER");
        };
    }

    //@Bean
    CommandLineRunner commandLineRunner(MedecinRepository medecinRepository){
        return  args -> {
            medecinRepository.save(new Medecin(null,"Hassan","cardiologue",new Date(),true));
            medecinRepository.save(new Medecin(null,"mehdi","dermatologue",new Date(),false));
            medecinRepository.save(new Medecin(null,"hamza","Dentiste",new Date(),false));
            medecinRepository.save(new Medecin(null,"youssra","générale",new Date(),true));
            medecinRepository.save(new Medecin(null,"achraf","pneumologue",new Date(),false));
            medecinRepository.save(new Medecin(null,"mohamed","Dentiste",new Date(),false));
            medecinRepository.save(new Medecin(null,"soukaina","cardiologue",new Date(),true));

            medecinRepository.findAll().forEach(m->{
                System.out.println(m.getNom());
            });
        };
    }
}
