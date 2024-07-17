package com.example.dossier.dossier.repositoryDossier;

import com.example.dossier.dossier.modelsDossier.NdsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NdsEntityRepo extends JpaRepository<NdsEntity, Long> {
    @Query(value = "select * from imp_risk.nds where iin_bin = ?1 ", nativeQuery = true)
    List<NdsEntity> getUsersByLike(String iin);
}