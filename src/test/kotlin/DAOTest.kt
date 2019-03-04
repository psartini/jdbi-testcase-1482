package eu.psartini.issues.jdbi

import eu.psartini.issues.jdbi.tools.JdbiProvider
import eu.psartini.issues.jdbi.tools.TestDatabase
import org.jdbi.v3.sqlobject.kotlin.onDemand
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test


class DAOTest {
    private val ds = TestDatabase.getDataSource()
    private val jdbi = JdbiProvider { ds }.get()
    private val demoDAO: DemoDAO = jdbi.onDemand()

    @Test
    fun `insert java list`() {
        val sqlInsert = """
    	DELETE FROM demotable CASCADE;
		"""
        TestDatabase.executeScript(sqlInsert)

        val created = demoDAO.insertJavaList(
            enumList = listOf(MyEnum.VALUE1, MyEnum.VALUE2) as java.util.List<MyEnum>
        )

        print(created)
        assertNotNull(created)
        assertNotNull(created.demoId)
    }

    @Test
    fun `insert kotlin list`() {
        val sqlInsert = """
    	DELETE FROM demotable CASCADE;
		"""
        TestDatabase.executeScript(sqlInsert)

        val created = demoDAO.insertKotlinList(
            enumList = listOf(MyEnum.VALUE1, MyEnum.VALUE2)
        )

        print(created)
        assertNotNull(created)
        assertNotNull(created.demoId)
    }
}
