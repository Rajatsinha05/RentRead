package com.RentRead.Repository;

import com.RentRead.Models.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookStore, Long> {
    boolean existsByTitle(String title);

}
