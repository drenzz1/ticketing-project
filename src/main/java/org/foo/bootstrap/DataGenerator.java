package org.foo.bootstrap;

import org.foo.dto.RoleDTO;
import org.foo.dto.UserDto;
import org.foo.service.RoleService;
import org.foo.service.UserService;
import org.foo.utils.Gender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {
    final UserService  userService;
    final RoleService roleService;

    public DataGenerator(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        RoleDTO adminRole = new RoleDTO(1L , "Admin");
        RoleDTO managerRole = new RoleDTO(2L , "Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");

        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        UserDto user1 = new UserDto("John","Kesy","john@gmail.com","Abc1",true,"7459684532",managerRole, Gender.MALE);
        UserDto user2 = new UserDto("Mike","Smith","smith@gmail.com","Abc2",true,"7459684533",adminRole, Gender.MALE);
        UserDto user3 = new UserDto("Delisa","Nore","nore@gmail.com","Abc3",true,"7459684534",managerRole, Gender.FEMALE);
        UserDto user4 = new UserDto("Craig","Jark","jark@gmail.com","Abc4",true,"7459684535",employeeRole, Gender.MALE);
        UserDto user5 = new UserDto("Shaun","Hayns","hayns@gmail.com","Abc5",true,"7459684536",managerRole, Gender.MALE);
        UserDto user6 = new UserDto("Elizabeth","Loren","loren@gmail.com","Abc6",true,"7459684537",employeeRole, Gender.FEMALE);
        UserDto user7 = new UserDto("Maria","Ada","ada@gmail.com","Abc7",true,"7459684538",employeeRole, Gender.FEMALE);
        UserDto user8 = new UserDto("Bill","Matt ","matt@gmail.com","Abc8",true,"7459684539",employeeRole, Gender.MALE);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);

    }
}
