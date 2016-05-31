package ru.kpfu.itis.store.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.store.models.Brand;
import ru.kpfu.itis.store.models.Good;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {


    List<Good> findByBrand(Brand brand);

    @Query("Select g from Good g where g.name LIKE %:searchTerm% or g.description LIKE %:searchTerm%")
    Page<Good> findAll(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query(value = "select * from good limit 5", nativeQuery = true)
    List<Good> top();
}