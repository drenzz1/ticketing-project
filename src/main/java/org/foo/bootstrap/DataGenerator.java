package org.foo.bootstrap;

import org.foo.dto.ProjectDTO;
import org.foo.dto.RoleDTO;
import org.foo.dto.UserDTO;
import org.foo.service.ProjectService;
import org.foo.service.RoleService;
import org.foo.service.UserService;
import org.foo.utils.Gender;
import org.foo.utils.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {
    final UserService  userService;
    final RoleService roleService;
    final ProjectService projectService;
    public DataGenerator(UserService userService, RoleService roleService,ProjectService projectService) {
        this.userService = userService;
        this.roleService = roleService;
        this.projectService=projectService;
    }

    @Override
    public void run(String... args) throws Exception {
        RoleDTO adminRole = new RoleDTO(1L , "Admin");
        RoleDTO managerRole = new RoleDTO(2L , "Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");

        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        UserDTO user1 = new UserDTO("John","Kesy","john@gmail.com","Abc1",true,"7459684532",managerRole, Gender.MALE);
        UserDTO user2 = new UserDTO("Mike","Smith","smith@gmail.com","Abc2",true,"7459684533",adminRole, Gender.MALE);
        UserDTO user3 = new UserDTO("Delisa","Nore","nore@gmail.com","Abc3",true,"7459684534",managerRole, Gender.FEMALE);
        UserDTO user4 = new UserDTO("Craig","Jark","jark@gmail.com","Abc4",true,"7459684535",employeeRole, Gender.MALE);
        UserDTO user5 = new UserDTO("Shaun","Hayns","hayns@gmail.com","Abc5",true,"7459684536",managerRole, Gender.MALE);
        UserDTO user6 = new UserDTO("Elizabeth","Loren","loren@gmail.com","Abc6",true,"7459684537",employeeRole, Gender.FEMALE);
        UserDTO user7 = new UserDTO("Maria","Ada","ada@gmail.com","Abc7",true,"7459684538",employeeRole, Gender.FEMALE);
        UserDTO user8 = new UserDTO("Bill","Matt ","matt@gmail.com","Abc8",true,"7459684539",employeeRole, Gender.MALE);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);

        ProjectDTO projectDTO1 = new ProjectDTO("SPRING MVC","PR001",user1, LocalDate.now(),LocalDate.now().plusDays(25),"Creating Controllers", Status.OPEN);
        ProjectDTO projectDTO2 = new ProjectDTO("SPRING ORM","PR002",user2, LocalDate.now(),LocalDate.now().plusDays(10),"Creating DATABASE", Status.IN_PROGRESS);
        ProjectDTO projectDTO3 = new ProjectDTO("Spring Container","PR003",user1, LocalDate.now(),LocalDate.now().plusDays(32),"Creating Controllers", Status.IN_PROGRESS);

        projectService.save(projectDTO1);
        projectService.save(projectDTO2);
        projectService.save(projectDTO3);
    }
}
