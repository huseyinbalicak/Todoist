package com.appcent.todoist.repository;

import com.appcent.todoist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findTodoByCategoryId(Long categoryId);
//    List<Todo> findByCompletedTrue();


}
