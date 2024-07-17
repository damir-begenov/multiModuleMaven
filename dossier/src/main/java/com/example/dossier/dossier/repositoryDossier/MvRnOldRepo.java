package com.example.dossier.dossier.repositoryDossier;

import com.example.dossier.dossier.modelsDossier.MvRnOld;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MvRnOldRepo extends JpaRepository<MvRnOld, Long> {
    @Query(value= "select * from imp_rn.mv_rn where owner_iin_bin = ?1", nativeQuery = true)
    List<MvRnOld> getUsersByLike(String iin);
}