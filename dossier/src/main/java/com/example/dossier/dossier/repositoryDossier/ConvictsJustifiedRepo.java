package com.example.dossier.dossier.repositoryDossier;


import com.example.dossier.dossier.modelsRisk.ConvictsJustified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConvictsJustifiedRepo extends JpaRepository<ConvictsJustified, Long> {
    @Query(value= "select * from imp_risk.convicts_justified where iin = ?1", nativeQuery = true)
    List<ConvictsJustified> getconvicts_justifiedByByIIN(String IIN);
}