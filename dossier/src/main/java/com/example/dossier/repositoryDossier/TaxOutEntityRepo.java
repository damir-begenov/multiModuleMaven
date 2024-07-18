package com.example.dossier.repositoryDossier;

import com.example.dossier.modelsDossier.TaxOutEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaxOutEntityRepo extends JpaRepository<TaxOutEntity, Long>, PagingAndSortingRepository<TaxOutEntity,Long> {
    @Query(value= "select * from ser.tax_out  where \"бин\" = ?1", nativeQuery = true)
    Page<TaxOutEntity> getUsersByLike(String iin, PageRequest pageRequest);

    @Query(value = "select count(*) from ser.tax_out  where \"бин\" = ?1", nativeQuery = true)
    Integer getTaxAmount(String bin);
    List<TaxOutEntity> getAllByBin(String iin);


}
