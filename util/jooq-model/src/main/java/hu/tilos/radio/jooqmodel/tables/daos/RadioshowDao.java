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
public class RadioshowDao extends org.jooq.impl.DAOImpl<hu.tilos.radio.jooqmodel.tables.records.RadioshowRecord, hu.tilos.radio.jooqmodel.tables.pojos.Radioshow, java.lang.Integer> {

	/**
	 * Create a new RadioshowDao without any configuration
	 */
	public RadioshowDao() {
		super(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW, hu.tilos.radio.jooqmodel.tables.pojos.Radioshow.class);
	}

	/**
	 * Create a new RadioshowDao with an attached configuration
	 */
	public RadioshowDao(org.jooq.Configuration configuration) {
		super(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW, hu.tilos.radio.jooqmodel.tables.pojos.Radioshow.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected java.lang.Integer getId(hu.tilos.radio.jooqmodel.tables.pojos.Radioshow object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public java.util.List<hu.tilos.radio.jooqmodel.tables.pojos.Radioshow> fetchById(java.lang.Integer... values) {
		return fetch(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public hu.tilos.radio.jooqmodel.tables.pojos.Radioshow fetchOneById(java.lang.Integer value) {
		return fetchOne(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW.ID, value);
	}

	/**
	 * Fetch records that have <code>name IN (values)</code>
	 */
	public java.util.List<hu.tilos.radio.jooqmodel.tables.pojos.Radioshow> fetchByName(java.lang.String... values) {
		return fetch(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW.NAME, values);
	}

	/**
	 * Fetch records that have <code>definition IN (values)</code>
	 */
	public java.util.List<hu.tilos.radio.jooqmodel.tables.pojos.Radioshow> fetchByDefinition(java.lang.String... values) {
		return fetch(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW.DEFINITION, values);
	}

	/**
	 * Fetch records that have <code>alias IN (values)</code>
	 */
	public java.util.List<hu.tilos.radio.jooqmodel.tables.pojos.Radioshow> fetchByAlias(java.lang.String... values) {
		return fetch(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW.ALIAS, values);
	}

	/**
	 * Fetch records that have <code>banner IN (values)</code>
	 */
	public java.util.List<hu.tilos.radio.jooqmodel.tables.pojos.Radioshow> fetchByBanner(java.lang.String... values) {
		return fetch(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW.BANNER, values);
	}

	/**
	 * Fetch records that have <code>description IN (values)</code>
	 */
	public java.util.List<hu.tilos.radio.jooqmodel.tables.pojos.Radioshow> fetchByDescription(java.lang.String... values) {
		return fetch(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW.DESCRIPTION, values);
	}

	/**
	 * Fetch records that have <code>type IN (values)</code>
	 */
	public java.util.List<hu.tilos.radio.jooqmodel.tables.pojos.Radioshow> fetchByType(java.lang.Integer... values) {
		return fetch(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW.TYPE, values);
	}

	/**
	 * Fetch records that have <code>status IN (values)</code>
	 */
	public java.util.List<hu.tilos.radio.jooqmodel.tables.pojos.Radioshow> fetchByStatus(java.lang.Integer... values) {
		return fetch(hu.tilos.radio.jooqmodel.tables.Radioshow.RADIOSHOW.STATUS, values);
	}
}
