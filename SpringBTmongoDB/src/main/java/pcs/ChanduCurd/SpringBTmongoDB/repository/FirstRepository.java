package pcs.ChanduCurd.SpringBTmongoDB.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import pcs.ChanduCurd.SpringBTmongoDB.Model.FirstDto;

@Repository
public interface FirstRepository extends MongoRepository<FirstDto, String> {

	@Query(value = "{'First_Name': ?0}", fields ="{'createdAt':1, 'updatedAt':1}")
	Optional<FirstDto> findByFName(String fName);
	
	@Query("{'First_Name': ?0, 'Last_Name': ?1}")
	List<FirstDto> findByfNameAndLName(String FName, String LName);
	
	@Query("{'First_Name':{'$regex':'?0', '$option':'i'} }")
	List<FirstDto> findByFNAME(String fName);
}
 