package com.ua.vinn.MyBetaApp.repositories;

import com.ua.vinn.MyBetaApp.models.ERole;
import com.ua.vinn.MyBetaApp.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRole(ERole role);
}
