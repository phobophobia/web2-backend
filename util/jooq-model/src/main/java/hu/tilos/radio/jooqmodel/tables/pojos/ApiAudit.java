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
public class ApiAudit implements java.io.Serializable {

	private static final long serialVersionUID = -559248362;

	private java.lang.Integer  id;
	private java.lang.String   user;
	private java.lang.String   url;
	private java.lang.String   postparams;
	private java.sql.Timestamp calldate;
	private java.lang.String   method;

	public ApiAudit() {}

	public ApiAudit(
		java.lang.Integer  id,
		java.lang.String   user,
		java.lang.String   url,
		java.lang.String   postparams,
		java.sql.Timestamp calldate,
		java.lang.String   method
	) {
		this.id = id;
		this.user = user;
		this.url = url;
		this.postparams = postparams;
		this.calldate = calldate;
		this.method = method;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getUser() {
		return this.user;
	}

	public void setUser(java.lang.String user) {
		this.user = user;
	}

	public java.lang.String getUrl() {
		return this.url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public java.lang.String getPostparams() {
		return this.postparams;
	}

	public void setPostparams(java.lang.String postparams) {
		this.postparams = postparams;
	}

	public java.sql.Timestamp getCalldate() {
		return this.calldate;
	}

	public void setCalldate(java.sql.Timestamp calldate) {
		this.calldate = calldate;
	}

	public java.lang.String getMethod() {
		return this.method;
	}

	public void setMethod(java.lang.String method) {
		this.method = method;
	}
}
