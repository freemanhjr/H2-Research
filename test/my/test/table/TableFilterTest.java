package my.test.table;

import my.test.TestBase;

public class TableFilterTest extends TestBase {
	public static void main(String[] args) throws Exception {
		new TableFilterTest().start();
	}

	@Override
	public void init() throws Exception {
		//prop.setProperty("PAGE_SIZE", "2048");

		//prop.setProperty("mode", "Derby");
		//prop.setProperty("mode", "oracle");
	}

	@Override
	public void startInternal() throws Exception {
		getBestPlanItem();
	}

	public void getBestPlanItem() throws Exception {
		stmt.executeUpdate("DROP TABLE IF EXISTS TableFilterTest");
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS TableFilterTest(id int, name varchar(500), b boolean)");
		//stmt.executeUpdate("CREATE INDEX IF NOT EXISTS TableFilterTestIndex ON TableFilterTest(id DESC, name ASC) ");
		stmt.executeUpdate("CREATE Unique INDEX IF NOT EXISTS TableFilterTestIndex1 ON TableFilterTest(id) ");

		stmt.executeUpdate("CREATE INDEX IF NOT EXISTS TableFilterTestIndex2 ON TableFilterTest(name) ");

		stmt.executeUpdate("insert into TableFilterTest(id, name, b) values(10, 'a1', true)");
		stmt.executeUpdate("insert into TableFilterTest(id, name, b) values(20, 'b1', true)");
		stmt.executeUpdate("insert into TableFilterTest(id, name, b) values(30, 'a2', false)");
		stmt.executeUpdate("insert into TableFilterTest(id, name, b) values(40, 'a2', true)");
		stmt.executeUpdate("insert into TableFilterTest(id, name, b) values(50, 'a3', false)");
		stmt.executeUpdate("insert into TableFilterTest(id, name, b) values(60, 'b3', true)");
		stmt.executeUpdate("insert into TableFilterTest(id, name, b) values(70, 'b3', true)");

		sql = "select * from TableFilterTest where id> 40 AND name<'b3'";
		executeQuery();
	}
}
