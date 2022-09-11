package com.appcent.todoist.repository;

import com.appcent.todoist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByIdentityNo(Long identityNo);

}
