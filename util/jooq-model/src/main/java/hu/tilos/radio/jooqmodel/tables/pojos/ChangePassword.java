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
public class ChangePassword implements java.io.Serializable {

	private static final long serialVersionUID = 940283916;

	private java.lang.Integer  id;
	private java.lang.Integer  userId;
	private java.lang.String   token;
	private java.sql.Timestamp created;

	public ChangePassword() {}

	public ChangePassword(
		java.lang.Integer  id,
		java.lang.Integer  userId,
		java.lang.String   token,
		java.sql.Timestamp created
	) {
		this.id = id;
		this.userId = userId;
		this.token = token;
		this.created = created;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getUserId() {
		return this.userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.String getToken() {
		return this.token;
	}

	public void setToken(java.lang.String token) {
		this.token = token;
	}

	public java.sql.Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(java.sql.Timestamp created) {
		this.created = created;
	}
}
