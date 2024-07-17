package com.example.dossier.dossier.service;

import com.example.dossier.dossier.modelsDossier.PhotoDb;
import com.example.dossier.dossier.repositoryDossier.NewPhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
    @Autowired
    NewPhotoRepo newPhotoRepo;

    public PhotoDb getPhotoByIIN(String iin){
        return newPhotoRepo.findById(iin).orElse(null);
    }
}
