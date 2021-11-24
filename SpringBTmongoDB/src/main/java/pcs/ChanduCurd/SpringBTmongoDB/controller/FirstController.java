package pcs.ChanduCurd.SpringBTmongoDB.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pcs.ChanduCurd.SpringBTmongoDB.Model.FirstDto;
import pcs.ChanduCurd.SpringBTmongoDB.exceptions.DataCollectionException;
import pcs.ChanduCurd.SpringBTmongoDB.repository.FirstRepository;
import pcs.ChanduCurd.SpringBTmongoDB.service.DataService;

@RestController
public class FirstController {
	
	@Autowired  private FirstRepository firstRepository;
	@Autowired  private MongoTemplate mongoTemplate;
	@Autowired	private DataService dataService;

	@ApiOperation(value = "Getting All Data", nickname = "Getting All Data", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Getting All Data successfully"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Failed while Getting All Data") })
	@GetMapping("/firstDbAllDataApi")
	public ResponseEntity<?> getAllData() {
		List<FirstDto> firstDbList = dataService.getAllDataa();
		return new ResponseEntity<>(firstDbList, firstDbList.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Incerting Data", nickname = "Incerting Data", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Incerting Data successfully"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Failed while Incerting Data") })
	@PostMapping("/incertingFirstDbData")
	public ResponseEntity<?> createData(@RequestBody FirstDto firstDt) {
		try {
			dataService.createData(firstDt);
			return new ResponseEntity<FirstDto>(firstDt, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (DataCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@ApiOperation(value = "Getting Single Data By Id", nickname = "Getting Single Data By Id", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Getting Single Data By Id successfully"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Failed while Getting Single Data By Id") })
	@GetMapping("/singleTodo/{id}")
	public ResponseEntity<?> getSingleData(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(dataService.getSingleData(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Updating Data By Id", nickname = "Updating Data By Id", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Updating Data By Id successfully"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Failed while Updating Data By Id") })
	@GetMapping("/updatingTodo/{id}")
	public ResponseEntity<?> updateData(@PathVariable("id") String id, @RequestBody FirstDto firstDto) {
		try {
			dataService.UpdatingData(id, firstDto);
			return new ResponseEntity<>("updated successfully data with id = " + id, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (DataCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Deleting data by id", nickname = "Deleting data by id", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Deleting data by id successfully"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Failed while Deleting data by id") })
	@DeleteMapping("/deleteDataById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
		try {
			dataService.deleteDataById(id);
			return new ResponseEntity<>("successfully deleted with id = " + id, HttpStatus.OK);
		} catch (DataCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findAllData/{First_Name}")
	public List<FirstDto> findAllData(@PathVariable(value = "First_Name") String fName){
		Query query=new Query();
		query.addCriteria(Criteria.where("First_Name").is(fName));
		return mongoTemplate.find(query, FirstDto.class);
		
	}
	
	@GetMapping("/findByFNameAndLName/{First_Name}/{Last_Name}")
	public List<FirstDto> findingDataByFandLNames(@PathVariable(value = "First_Name") String fName,
			@PathVariable(value = "Last_Name") String LName){
		System.out.println(fName+":"+LName);
		return firstRepository.findByfNameAndLName(fName, LName);
	}
	
	
	
	
	
	
	
	
	
}
