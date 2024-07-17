package com.example.dossier.dossier.repositoryDossier;

import com.example.dossier.dossier.modelsRisk.Adm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdmRepo extends JpaRepository<Adm, Long> {
    @Query(value= "select * from imp_kfm_fl.adm where iin = ?1 ", nativeQuery = true)
    List<Adm> getUsersByLike(String iin);
    @Query(value= "select * from imp_kfm_fl.adm  where bin = ?1 ", nativeQuery = true)
    List<Adm> getUsersByLikeBin(String iin);

}
