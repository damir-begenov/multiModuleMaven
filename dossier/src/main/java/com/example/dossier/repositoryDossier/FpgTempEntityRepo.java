package com.example.dossier.repositoryDossier;


import com.example.dossier.modelsRisk.FpgTempEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FpgTempEntityRepo extends JpaRepository<FpgTempEntity, Long> {
    @Query(value= "select * from imp_kfm_fl.fpg_temp where bin = ?1 ", nativeQuery = true)
    List<FpgTempEntity> getUsersByLike(String iin);
}