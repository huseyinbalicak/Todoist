package com.appcent.todoist.repository;

import com.appcent.todoist.security.dto.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	RefreshToken findByUserId(Long userId);
	
}