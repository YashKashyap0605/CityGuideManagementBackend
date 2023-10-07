package com.cityguide.cityguidemanagement.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cityguide.cityguidemanagement.dao.TypeOfAttractionRepository;
import com.cityguide.cityguidemanagement.entity.TypeOfAttraction;
import com.cityguide.cityguidemanagement.service.impl.TypeOfAttractionServiceImpl;
public class TypeOfAttractionServiceTest {

	@Mock
	private TypeOfAttractionRepository typeOfAttractionRepository;

	@InjectMocks
	private TypeOfAttractionServiceImpl typeOfAttractionService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddTypeOfAttraction() {
		// Prepare data
		TypeOfAttraction type = new TypeOfAttraction();
		type.setId(1);
		type.setName("Museum");

		Mockito.when(typeOfAttractionRepository.save(Mockito.any(TypeOfAttraction.class))).thenReturn(type);

		// Perform the service call
		TypeOfAttraction result = typeOfAttractionService.addTypeOfAttraction(type);

		// Assertions
		assertNotNull(result);
		assertEquals(type.getId(), result.getId());
		assertEquals(type.getName(), result.getName());

		Mockito.verify(typeOfAttractionRepository, Mockito.times(1)).save(Mockito.any(TypeOfAttraction.class));
	}

	@Test
	public void testDeleteTypeOfAttraction() {
		// Prepare data
		int id = 1;

		// Perform the service call
		typeOfAttractionService.deleteTypeOfAttraction(id);

		// Assertions
		Mockito.verify(typeOfAttractionRepository, Mockito.times(1)).deleteById(id);
	}

	@Test
	public void testGetTypeById() {
		// Prepare data
		int id = 1;
		TypeOfAttraction type = new TypeOfAttraction();
		type.setId(id);
		type.setName("Museum");

		Mockito.when(typeOfAttractionRepository.findById(Mockito.eq(id)))
				.thenReturn(java.util.Optional.ofNullable(type));

		// Perform the service call
		TypeOfAttraction result = typeOfAttractionService.getTypeById(id);

		// Assertions
		assertNotNull(result);
		assertEquals(type.getId(), result.getId());
		assertEquals(type.getName(), result.getName());

		Mockito.verify(typeOfAttractionRepository, Mockito.times(1)).findById(id);
	}

	@Test
	public void testGetTypeById_NotFound() {
		// Prepare data
		int id = 1;

		Mockito.when(typeOfAttractionRepository.findById(Mockito.eq(id))).thenReturn(java.util.Optional.empty());

		// Perform the service call
		TypeOfAttraction result = typeOfAttractionService.getTypeById(id);

		// Assertions
		assertNull(result);

		Mockito.verify(typeOfAttractionRepository, Mockito.times(1)).findById(id);
	}

}
