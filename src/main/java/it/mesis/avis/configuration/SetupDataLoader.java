package it.mesis.avis.configuration;

import java.sql.Timestamp;

import it.mesis.avis.model.User;
import it.mesis.avis.model.UserProfile;
import it.mesis.avis.model.UserProfileType;
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
        
        String ssoId = "test@test.com";
        User user = userService.findBySso(ssoId);
        
        if (alreadySetup = user != null) {
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
        
        user = new User();
        user.setSsoId(ssoId);
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
    private final UserProfile createRoleIfNotFound(final UserProfileType type) {
    	UserProfile userProfile = userProfileService.findByType(type.getUserProfileType());
        if (userProfile == null) {
        	userProfile = new UserProfile(type);
        	userProfileService.save(userProfile);
        	System.out.println("creato " + userProfile.toString());
        }
        return userProfile;
    }

}