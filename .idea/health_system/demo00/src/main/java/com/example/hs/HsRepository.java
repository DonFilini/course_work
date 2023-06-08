package com.example.hs;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HsRepository extends JpaRepository<Hs, Long> {
    @Query("select p from Hs p where concat(p.id, '', p.type, '', p.location, '', p.funding, '', p.residents_registered," +
            " '', p.residents_per_month, '', p.average_score) like %?1%")
    List<Hs> search(String keyword);
}

