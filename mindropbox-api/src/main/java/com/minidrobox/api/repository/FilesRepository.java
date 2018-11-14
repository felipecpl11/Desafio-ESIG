package com.minidrobox.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minidrobox.api.model.Files;
import com.minidrobox.api.repository.file.FilesRepositoryQuery;

public interface FilesRepository extends JpaRepository<Files, Long>, FilesRepositoryQuery{

}
