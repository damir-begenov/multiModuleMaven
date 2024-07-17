package com.example.dossier.dossier.repositoryDossier;


import com.example.dossier.dossier.modelsDossier.FlContacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlContactsRepo extends JpaRepository<FlContacts, Long> {
    @Query(value = "SELECT iin from imp_kfm_fl.contacts where phone = ?1 limit 1", nativeQuery = true)
    List<String> getByPhoneNumber(String phone);
    @Query(value = "SELECT * FROM imp_kfm_fl.contacts WHERE iin = ? ORDER BY id DESC LIMIT 1\n", nativeQuery = true)

    List<FlContacts> findAllByIin(String iin);

}
