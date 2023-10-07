package com.cityguide.cityguidemanagement.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cityguide.cityguidemanagement.entity.Attractions;
import com.cityguide.cityguidemanagement.exception.AttractionAlreadyExistsException;
import com.cityguide.cityguidemanagement.exception.AttractionNotFoundException;
import com.cityguide.cityguidemanagement.model.AttractionOutputModel;

@Service
public interface AttractionsService {
	public Attractions getAttractionById(int id);

	public List<String> getAllNamesOfAttractions();

	public Attractions addAttraction(Attractions att) throws AttractionAlreadyExistsException;

	public void deleteAttraction(int id);

	public List<AttractionOutputModel> getAllAttractions() throws AttractionNotFoundException;

	public List<AttractionOutputModel> getAllAttractionsByRatings(float ratings) throws AttractionNotFoundException;

	public List<AttractionOutputModel> getAllAttractionsByLikes(int likes) throws AttractionNotFoundException;

	public AttractionOutputModel getAttractionByName(String name) throws AttractionNotFoundException;

	public List<AttractionOutputModel> getAttractionByReports(int reports) throws AttractionNotFoundException;

	public List<AttractionOutputModel> searchByKeyword(String key) throws AttractionNotFoundException;

	public List<AttractionOutputModel> findClosestAttractionsToStations() throws AttractionNotFoundException;

	public List<AttractionOutputModel> findAllAttractionsByUser(int id) throws AttractionNotFoundException;

	public List<AttractionOutputModel> getAttractionsOfType(int id) throws AttractionNotFoundException;

	public void likeAttraction(int id);

	public void reportAttraction(int id);
}
