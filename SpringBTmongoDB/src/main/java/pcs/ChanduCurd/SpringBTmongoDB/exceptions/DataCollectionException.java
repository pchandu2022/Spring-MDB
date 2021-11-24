package pcs.ChanduCurd.SpringBTmongoDB.exceptions;

public class DataCollectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataCollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Data with "+id+" not found";
	}
	
	public static String DataAlreadyExistes() {
		return "Data with Given Name Already exists";
	}
}
