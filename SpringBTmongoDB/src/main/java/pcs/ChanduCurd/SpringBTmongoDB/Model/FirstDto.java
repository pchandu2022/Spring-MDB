package pcs.ChanduCurd.SpringBTmongoDB.Model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(value = "First_Table")
public class FirstDto {

	@Id
	private String id;
	
	@NotNull(message = "FirstName can not be null")
	@Field(value ="First_Name")
	@Indexed(unique = true)
	private String firstName;
	
	@NotNull(message = "LastName can not be null")
	@Field(value ="Last_Name")
	private String lastName;
	
	@NotNull(message = "Email can not be null")
	@Field(value ="Email_Id")
	private String emaiId;
	
	@NotNull(message = "age can not be null")
	@Field(value = "Age")
	private String age;
	
	@NotNull(message = "todo can not be null")
	@Field(value = "Todo")
	private String todo;
	
	@NotNull(message = "Decription can not be null")
	private String description;

	private Boolean completed;
	
	@NotNull(message = "Created Date can not be null")
	private Date createdAt;
	
	@NotNull(message = "Updated Date can not be null")
	private Date updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmaiId() {
		return emaiId;
	}

	public void setEmaiId(String emaiId) {
		this.emaiId = emaiId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

		
}
