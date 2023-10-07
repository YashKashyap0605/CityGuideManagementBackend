package com.cityguide.cityguidemanagement.service;

import org.springframework.stereotype.Service;

import com.cityguide.cityguidemanagement.entity.TypeOfAttraction;

@Service
public interface TypeOfAttractionService {
	public TypeOfAttraction addTypeOfAttraction(TypeOfAttraction type);

	public void deleteTypeOfAttraction(int id);

	public TypeOfAttraction getTypeById(int id);
}
