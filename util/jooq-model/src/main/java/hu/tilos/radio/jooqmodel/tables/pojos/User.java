/**
 * This class is generated by jOOQ
 */
package hu.tilos.radio.jooqmodel.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 492024432;

	private java.lang.Integer id;
	private java.lang.Integer roleId;
	private java.lang.String  username;
	private java.lang.String  password;
	private java.lang.String  email;
	private java.lang.String  salt;

	public User() {}

	public User(
		java.lang.Integer id,
		java.lang.Integer roleId,
		java.lang.String  username,
		java.lang.String  password,
		java.lang.String  email,
		java.lang.String  salt
	) {
		this.id = id;
		this.roleId = roleId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.salt = salt;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(java.lang.Integer roleId) {
		this.roleId = roleId;
	}

	public java.lang.String getUsername() {
		return this.username;
	}

	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getSalt() {
		return this.salt;
	}

	public void setSalt(java.lang.String salt) {
		this.salt = salt;
	}
}
