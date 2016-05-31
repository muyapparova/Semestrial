package ru.kpfu.itis.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.store.models.Billing;

/**
 * Created by perfect on 5/9/16.
 */
@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
}
