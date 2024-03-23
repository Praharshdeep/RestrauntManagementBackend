package com.praharsh.CafeManagement.repositries;

import com.praharsh.CafeManagement.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAllByNameContaining(String title);
}
