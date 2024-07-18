package com.example.dossier.repositoryDossier;

import com.example.dossier.modelsDossier.Logs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface LogsRepository extends JpaRepository<Logs, LocalDateTime> {

}
