package ro.myclass.billingmanagementapi.username.service;


import org.springframework.stereotype.Service;
import ro.myclass.billingmanagementapi.username.models.Username;

import java.util.List;

@Service
public interface UsernameQuerryService {

    List<Username> getAllUsernames();

    Username getUsernameById(long id);

    Username getUsernameByUsername(String username);

    Username getUsernameByEmail(String email);

}
