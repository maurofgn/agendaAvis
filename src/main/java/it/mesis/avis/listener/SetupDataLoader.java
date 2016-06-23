package it.mesis.avis.listener;

import java.sql.Timestamp;

import it.mesis.avis.bean.jpa.UserEntity;
import it.mesis.avis.bean.jpa.UserProfileEntity;
import it.mesis.avis.enu.UserProfileType;
import it.mesis.avis.service.UserProfileService;
import it.mesis.avis.service.UserService;
import it.mesis.util.model.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;
    
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PrivilegeRepository privilegeRepository;
//
    @Autowired
    private PasswordEncoder passwordEncoder;

    // API

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        
        Long userCount = userService.count();
        
//        String ssoId = "test@test.com";
//        UserEntity user = userService.findBySso(ssoId);
        
        if (alreadySetup = userCount > 0) {
            return;
        }

//        // == create initial privileges
//        final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//
        // == create initial roles
//        final List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);


        for (UserProfileType userProfileType : UserProfileType.values()) {
            createRoleIfNotFound(userProfileType);
		}
        
        UserEntity user = new UserEntity();
        user.setSsoId("test@test.com");
        user.setPassword(passwordEncoder.encode("1"));
        user.setAssoAvis("Y");
        user.setState(State.ACTIVE);
        user.setLastChangePsw(new Timestamp(System.currentTimeMillis()));
        userService.save(user);
        
//        final Role adminRole = roleRepository.findByName("ROLE_ADMIN");
//        user = new User();
//        user.setFirstName("Test");
//        user.setLastName("Test");
//        user.setPassword(passwordEncoder.encode("test"));
//        user.setEmail(email);
//        user.setRoles(Arrays.asList(adminRole));
//        user.setEnabled(true);
//        userRepository.save(user);

        alreadySetup = true;
    }

//    @Transactional
//    private final Privilege createPrivilegeIfNotFound(final String name) {
//        Privilege privilege = privilegeRepository.findByName(name);
//        if (privilege == null) {
//            privilege = new Privilege(name);
//            privilegeRepository.save(privilege);
//        }
//        return privilege;
//    }

    @Transactional
    private final UserProfileEntity createRoleIfNotFound(final UserProfileType type) {
    	UserProfileEntity userProfile = userProfileService.findByType(type.getUserProfileType());
        if (userProfile == null) {
        	userProfile = new UserProfileEntity(type);
        	userProfileService.save(userProfile);
        	System.out.println("creato " + userProfile.toString());
        }
        return userProfile;
    }

}