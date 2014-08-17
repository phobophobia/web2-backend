/**
 * This class is generated by jOOQ
 */
package hu.tilos.radio.jooqmodel.tables.daos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ShowUrlDao extends org.jooq.impl.DAOImpl<hu.tilos.radio.jooqmodel.tables.records.ShowUrlRecord, hu.tilos.radio.jooqmodel.tables.pojos.ShowUrl, org.jooq.Record2<java.lang.Integer, java.lang.Integer>> {

	/**
	 * Create a new ShowUrlDao without any configuration
	 */
	public ShowUrlDao() {
		super(hu.tilos.radio.jooqmodel.tables.ShowUrl.SHOW_URL, hu.tilos.radio.jooqmodel.tables.pojos.ShowUrl.class);
	}

	/**
	 * Create a new ShowUrlDao with an attached configuration
	 */
	public ShowUrlDao(org.jooq.Configuration configuration) {
		super(hu.tilos.radio.jooqmodel.tables.ShowUrl.SHOW_URL, hu.tilos.radio.jooqmodel.tables.pojos.ShowUrl.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected org.jooq.Record2<java.lang.Integer, java.lang.Integer> getId(hu.tilos.radio.jooqmodel.tables.pojos.ShowUrl object) {
		return compositeKeyRecord(object.getRadioshowId(), object.getUrlId());
	}

	/**
	 * Fetch records that have <code>radioshow_id IN (values)</code>
	 */
	public java.util.List<hu.tilos.radio.jooqmodel.tables.pojos.ShowUrl> fetchByRadioshowId(java.lang.Integer... values) {
		return fetch(hu.tilos.radio.jooqmodel.tables.ShowUrl.SHOW_URL.RADIOSHOW_ID, values);
	}

	/**
	 * Fetch records that have <code>url_id IN (values)</code>
	 */
	public java.util.List<hu.tilos.radio.jooqmodel.tables.pojos.ShowUrl> fetchByUrlId(java.lang.Integer... values) {
		return fetch(hu.tilos.radio.jooqmodel.tables.ShowUrl.SHOW_URL.URL_ID, values);
	}

	/**
	 * Fetch a unique record that has <code>url_id = value</code>
	 */
	public hu.tilos.radio.jooqmodel.tables.pojos.ShowUrl fetchOneByUrlId(java.lang.Integer value) {
		return fetchOne(hu.tilos.radio.jooqmodel.tables.ShowUrl.SHOW_URL.URL_ID, value);
	}
}