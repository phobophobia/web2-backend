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
public class Episode implements java.io.Serializable {

	private static final long serialVersionUID = 1170421314;

	private java.lang.Integer  id;
	private java.lang.Integer  radioshowId;
	private java.lang.Integer  textcontentId;
	private java.sql.Timestamp plannedfrom;
	private java.sql.Timestamp plannedto;
	private java.sql.Timestamp realfrom;
	private java.sql.Timestamp realto;

	public Episode() {}

	public Episode(
		java.lang.Integer  id,
		java.lang.Integer  radioshowId,
		java.lang.Integer  textcontentId,
		java.sql.Timestamp plannedfrom,
		java.sql.Timestamp plannedto,
		java.sql.Timestamp realfrom,
		java.sql.Timestamp realto
	) {
		this.id = id;
		this.radioshowId = radioshowId;
		this.textcontentId = textcontentId;
		this.plannedfrom = plannedfrom;
		this.plannedto = plannedto;
		this.realfrom = realfrom;
		this.realto = realto;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getRadioshowId() {
		return this.radioshowId;
	}

	public void setRadioshowId(java.lang.Integer radioshowId) {
		this.radioshowId = radioshowId;
	}

	public java.lang.Integer getTextcontentId() {
		return this.textcontentId;
	}

	public void setTextcontentId(java.lang.Integer textcontentId) {
		this.textcontentId = textcontentId;
	}

	public java.sql.Timestamp getPlannedfrom() {
		return this.plannedfrom;
	}

	public void setPlannedfrom(java.sql.Timestamp plannedfrom) {
		this.plannedfrom = plannedfrom;
	}

	public java.sql.Timestamp getPlannedto() {
		return this.plannedto;
	}

	public void setPlannedto(java.sql.Timestamp plannedto) {
		this.plannedto = plannedto;
	}

	public java.sql.Timestamp getRealfrom() {
		return this.realfrom;
	}

	public void setRealfrom(java.sql.Timestamp realfrom) {
		this.realfrom = realfrom;
	}

	public java.sql.Timestamp getRealto() {
		return this.realto;
	}

	public void setRealto(java.sql.Timestamp realto) {
		this.realto = realto;
	}
}