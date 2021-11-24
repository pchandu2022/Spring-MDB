package pcs.ChanduCurd.SpringBTmongoDB.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pcs.ChanduCurd.SpringBTmongoDB.Model.FirstDto;
import pcs.ChanduCurd.SpringBTmongoDB.exceptions.DataCollectionException;
import pcs.ChanduCurd.SpringBTmongoDB.repository.FirstRepository;

@Service
public class DataServiceImpl implements DataService {

	@Autowired	private FirstRepository firstRepo;

	@Override
	public void createData(FirstDto firstDto) throws ConstraintViolationException, DataCollectionException {
		Optional<FirstDto> optionalFNamedData = firstRepo.findByFName(firstDto.getFirstName());
		if (optionalFNamedData.isPresent()) {
			throw new DataCollectionException(DataCollectionException.DataAlreadyExistes());
		} else {
			firstDto.setCreatedAt(new Date(System.currentTimeMillis()));
			firstDto.setUpdatedAt(new Date(System.currentTimeMillis()));
			firstRepo.save(firstDto);
		}
	}

	@Override
	public List<FirstDto> getAllDataa() {
		List<FirstDto> datas = firstRepo.findAll();
		if (datas.size() > 0) {
			return datas;
		}else {
			return new ArrayList<FirstDto>();
		}
	}

	@Override
	public FirstDto getSingleData(String id) throws DataCollectionException {
		Optional<FirstDto> optionalData = firstRepo.findById(id);
		if (!optionalData.isPresent()) {
			throw new DataCollectionException(DataCollectionException.NotFoundException(id));
		}else {
			return optionalData.get();
		}
	}

	@Override
	public void UpdatingData(String id, FirstDto firstDto) throws DataCollectionException {
		Optional<FirstDto> dataUpdateWithId = firstRepo.findById(id);
		Optional<FirstDto> DataWithFName = firstRepo.findByFName(firstDto.getFirstName());
		
		if (dataUpdateWithId.isPresent()) {
			
			if (DataWithFName.isPresent() && dataUpdateWithId.get().getId().equals(id)) {
				throw new DataCollectionException(DataCollectionException.DataAlreadyExistes());
			}
			
			FirstDto dataToUpdate = dataUpdateWithId.get();
			
			dataToUpdate.setFirstName(firstDto.getFirstName());
			dataToUpdate.setLastName(firstDto.getLastName());
			dataToUpdate.setEmaiId(firstDto.getEmaiId());
			dataToUpdate.setTodo(firstDto.getTodo());
			dataToUpdate.setDescription(firstDto.getDescription());
			dataToUpdate.setCompleted(firstDto.getCompleted());
			firstRepo.save(dataToUpdate);
		} else {
			throw new DataCollectionException(DataCollectionException.NotFoundException(id));
		}
	}

	@Override
	public void deleteDataById(String id) throws DataCollectionException {
		Optional<FirstDto> optionalData = firstRepo.findById(id);
		if (!optionalData.isPresent()) {
			throw new DataCollectionException(DataCollectionException.NotFoundException(id));
		}else {
			firstRepo.deleteById(id);
		}
			
	}

}
