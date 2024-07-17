package com.example.dossier.dossier.repositoryDossier;


import com.example.dossier.dossier.modelsRisk.Dormant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DormantRepo extends JpaRepository<Dormant, Long> {
    @Query(value= "select * from imp_risk.dormant dormant0_ where dormant0_.iin_bin = ?1 ", nativeQuery = true)
    List<Dormant> getUsersByLike(String iin);

}
