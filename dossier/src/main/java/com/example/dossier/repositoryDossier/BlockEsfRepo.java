package com.example.dossier.repositoryDossier;


import com.example.dossier.modelsRisk.BlockEsf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlockEsfRepo extends JpaRepository<BlockEsf, Long> {
    @Query(value= "select * from imp_risk.block_esf where iin_bin = ?1", nativeQuery = true)
    List<BlockEsf> getblock_esfByIIN(String IIN);
}