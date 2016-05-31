package ru.kpfu.itis.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.store.models.Brand;

@Repository
public interface BrandRepository  extends JpaRepository<Brand, Long> {}
