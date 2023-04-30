package com.together.togetherpj.repository;

import com.together.togetherpj.domain.Applying;
import com.together.togetherpj.domain.id.ApplyingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyingRepository extends JpaRepository<Applying, ApplyingId> {
}
