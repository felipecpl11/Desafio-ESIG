package com.minidrobox.api.repository.file;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.minidrobox.api.model.Files;
import com.minidrobox.api.repository.filter.FileFilter;

public class FilesRepositoryImpl implements FilesRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Files> filter(FileFilter fileFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Files> criteria = builder.createQuery(Files.class);
		Root<Files> root = criteria.from(Files.class);
		
		Predicate[] predicates = createRestrictions(fileFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Files> query = manager.createQuery(criteria);
		addRestrictionsOnPage(query, pageable);
		
		//return null;
		return new PageImpl<>(query.getResultList(), pageable, total(fileFilter));
	}

	private Predicate[] createRestrictions(FileFilter fileFilter, CriteriaBuilder builder, Root<Files> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if(fileFilter.getDescription() != null) {
			predicates.add(builder.like(
					builder.lower(root.get("description")), "%" + fileFilter.getDescription().toLowerCase() + "%"));
		}
		
		if(fileFilter.getAuthor() != null) {
			predicates.add(builder.like(
					builder.lower(root.get("author")), "%" + fileFilter.getAuthor().toLowerCase() + "%"));
		}
		
		if(fileFilter.getFile_name() != null) {
			predicates.add(builder.like(
					builder.lower(root.get("file_name")), "%" + fileFilter.getFile_name().toLowerCase() + "%"));
		}
		
		if(fileFilter.getExtension() != null) {
			predicates.add(builder.like(
					builder.lower(root.get("extension")), "%" + fileFilter.getExtension().toLowerCase() + "%"));
		}
		
		if(fileFilter.getUsuarioLogin() != null) {
			predicates.add(builder.equal(
					builder.lower(root.get("usuarioLogin")), fileFilter.getUsuarioLogin().toLowerCase()));
		}
		
		if(fileFilter.getCriadoDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(
					root.get("created"), fileFilter.getCriadoDe()));
		}
		
		if(fileFilter.getCriadoAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(
					root.get("created"), fileFilter.getCriadoAte()));
		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addRestrictionsOnPage(TypedQuery<Files> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(FileFilter fileFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Files> root = criteria.from(Files.class);
		
		Predicate[] predicates = createRestrictions(fileFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
