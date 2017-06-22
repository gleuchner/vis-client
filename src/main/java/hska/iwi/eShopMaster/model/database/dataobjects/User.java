package hska.iwi.eShopMaster.model.database.dataobjects;



/**
 * This class contains the users of the webshop.
 */
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int userId;

	private String userName;

	private String firstName;

	private String name;

	private String password;

	private int role;

	public User() {
	}

	public User(String username, String firstname, String lastname,
			String password, int role) {
		this.userName = username;
		this.firstName = firstname;
		this.name = lastname;
		this.password = password;
		this.role = role;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
