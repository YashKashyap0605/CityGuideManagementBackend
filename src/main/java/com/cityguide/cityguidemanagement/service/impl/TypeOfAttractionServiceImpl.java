package com.cityguide.cityguidemanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityguide.cityguidemanagement.dao.TypeOfAttractionRepository;
import com.cityguide.cityguidemanagement.entity.TypeOfAttraction;
import com.cityguide.cityguidemanagement.service.TypeOfAttractionService;

import jakarta.transaction.Transactional;

@Service
public class TypeOfAttractionServiceImpl implements TypeOfAttractionService {
	@Autowired
	TypeOfAttractionRepository typeOfAttractionRepository;

	// ADD A TYPE OF ATTRACTION

	@Transactional
	@Override
	public TypeOfAttraction addTypeOfAttraction(TypeOfAttraction type) {
		return typeOfAttractionRepository.save(type);
	}

	// DELETE TYPE OF ATRACTION USING ID

	@Transactional
	@Override
	public void deleteTypeOfAttraction(int id) {
		typeOfAttractionRepository.deleteById(id);
	}

	// GET TYPE OF ATTRACTION BY ID

	@Transactional
	@Override
	public TypeOfAttraction getTypeById(int id) {
		TypeOfAttraction t = typeOfAttractionRepository.findById(id).orElse(null);
		return t;
	}
}
