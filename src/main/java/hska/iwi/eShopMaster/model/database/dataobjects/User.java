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

	private String username;

	private String firstname;

	private String name;

	private String password;

	private int role;

	public User() {
	}

	public User(String username, String firstname, String lastname,
			String password, int role) {
		this.username = username;
		this.firstname = firstname;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
