package pcs.ChanduCurd.SpringBTmongoDB.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import pcs.ChanduCurd.SpringBTmongoDB.Model.FirstDto;
import pcs.ChanduCurd.SpringBTmongoDB.exceptions.DataCollectionException;

public interface DataService {

	public void createData(FirstDto firstDto) throws ConstraintViolationException, DataCollectionException;

	public List<FirstDto> getAllDataa();
	
	public FirstDto getSingleData(String id) throws DataCollectionException;
	
	public void UpdatingData(String id, FirstDto firstDto) throws DataCollectionException;
	
	public void deleteDataById(String id) throws DataCollectionException;
	
}
