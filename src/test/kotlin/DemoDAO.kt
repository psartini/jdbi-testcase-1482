package eu.psartini.issues.jdbi

import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import java.util.*

interface DemoDAO : SqlObject {

    @SqlUpdate("INSERT INTO demotableenum (enumlist) VALUES (:enumList)")
    @GetGeneratedKeys
    fun insertKotlinEnumList(enumList: List<MyEnum>)
		: DemoTableEnumWithKotlinList

	@SqlUpdate("INSERT INTO demotableuuid (uuidlist) VALUES (:uuidList)")
	@GetGeneratedKeys
	fun insertKotlinUUIDList(uuidList: List<UUID>)
		: DemoTableUUIDWithKotlinList

	@SqlUpdate("INSERT INTO demotableenum (enumlist) VALUES (:enumList)")
    @GetGeneratedKeys
    fun insertJavaEnumList(enumList: java.util.List<MyEnum>)
		: DemoTableEnumWithJavaList

	@SqlUpdate("INSERT INTO demotableuuid (uuidlist) VALUES (:uuidList)")
	@GetGeneratedKeys
	fun insertJavaUUIDList(uuidList: java.util.List<UUID>)
		: DemoTableUUIDWithJavaList
}
