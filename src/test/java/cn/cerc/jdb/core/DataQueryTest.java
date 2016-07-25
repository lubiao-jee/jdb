package cn.cerc.jdb.core;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DataQueryTest {
	// private static final Logger log = Logger.getLogger(AppQueryTest.class);
	private StubConnection conn = new StubConnection();
	private static final String Account = "Account";
	private DataQuery ds;

	@Before
	public void setUp() {
		ds = new DataQuery(conn);
	}

	@Test
	public void test_open() {
		ds.setMaximum(1);
		ds.add("select Code_,Name_ from %s", Account);
		ds.open();
		assertEquals(ds.size(), 1);
	}

	@Test
	public void test_open_getSelectCommand() {
		String sql;
		sql = String.format("select Code_,Name_ from %s", Account);
		ds.add(sql);
		assertEquals(String.format("%s limit %s", sql, String.valueOf(BigdataException.MAX_RECORDS + 2)),
				ds.getSelectCommand());

		ds.clear();
		sql = String.format("select Code_,Name_ from %s limit 1", Account);
		ds.setMaximum(-1);
		ds.add(sql);
		assertEquals(sql, ds.getSelectCommand());

		ds.clear();
		ds.setMaximum(BigdataException.MAX_RECORDS);
		sql = String.format("select Code_,Name_ from %s", Account);
		ds.add(sql);

		assertEquals(String.format("%s limit %s", sql, String.valueOf(BigdataException.MAX_RECORDS + 2)),
				ds.getSelectCommand());
	}
}
