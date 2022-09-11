package com.appcent.todoist.repository;

import com.appcent.todoist.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findCategoryByUserId(Long userId);

}
