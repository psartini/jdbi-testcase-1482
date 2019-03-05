package eu.psartini.issues.jdbi

import eu.psartini.issues.jdbi.tools.JdbiProvider
import eu.psartini.issues.jdbi.tools.TestDatabase
import org.jdbi.v3.sqlobject.kotlin.onDemand
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.util.*


class DAOTest {
    private val ds = TestDatabase.getDataSource()
    private val jdbi = JdbiProvider { ds }.get()
    private val demoDAO: DemoDAO = jdbi.onDemand()

    @Test
    fun `insert java_util_List Enum into VARCHAR ARRAY`() {
        val sqlInsert = """
    	DELETE FROM demotableenum CASCADE;
		"""
        TestDatabase.executeScript(sqlInsert)

        val created = demoDAO.insertJavaEnumList(
            enumList = listOf(MyEnum.VALUE1, MyEnum.VALUE2) as java.util.List<MyEnum>
        )

        print(created)
        assertNotNull(created)
        assertNotNull(created.demoId)
    }

    @Test
    fun `insert kotlin_collections_List Enum into VARCHAR ARRAY`() {
        val sqlInsert = """
    	DELETE FROM demotableenum CASCADE;
		"""
        TestDatabase.executeScript(sqlInsert)

        val created = demoDAO.insertKotlinEnumList(
            enumList = listOf(MyEnum.VALUE1, MyEnum.VALUE2)
        )

        print(created)
        assertNotNull(created)
        assertNotNull(created.demoId)
    }

	@Test
	fun `insert kotlin_collections_MutableList Enum into VARCHAR ARRAY`() {
		val sqlInsert = """
    	DELETE FROM demotableenum CASCADE;
		"""
		TestDatabase.executeScript(sqlInsert)

		val created = demoDAO.insertKotlinEnumMutableList(
			enumList = mutableListOf(MyEnum.VALUE1, MyEnum.VALUE2)
		)

		assertNotNull(created)
		assertNotNull(created.demoId)
	}

	@Test
	fun `insert java_util_List UUID into VARCHAR ARRAY`() {
		val sqlInsert = """
    	DELETE FROM demotableuuid CASCADE;
		"""
		TestDatabase.executeScript(sqlInsert)

		val created = demoDAO.insertJavaUUIDList(
			uuidList = listOf(UUID.randomUUID(), UUID.randomUUID())
				as java.util.List<UUID>
		)

		print(created)
		assertNotNull(created)
		assertNotNull(created.demoId)
	}

	@Test
	fun `insert kotlin_collections_List UUID into VARCHAR ARRAY`() {
		val sqlInsert = """
    	DELETE FROM demotableuuid CASCADE;
		"""
		TestDatabase.executeScript(sqlInsert)

		val created = demoDAO.insertKotlinUUIDList(
			uuidList = listOf(UUID.randomUUID(), UUID.randomUUID())
		)

		print(created)
		assertNotNull(created)
		assertNotNull(created.demoId)
	}
}
