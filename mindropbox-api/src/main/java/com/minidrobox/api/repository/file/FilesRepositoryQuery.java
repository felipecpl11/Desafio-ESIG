package com.minidrobox.api.repository.file;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.minidrobox.api.model.Files;
import com.minidrobox.api.repository.filter.FileFilter;

public interface FilesRepositoryQuery{
	
	public Page<Files> filter(FileFilter fileFilter, Pageable pageabele);

}
