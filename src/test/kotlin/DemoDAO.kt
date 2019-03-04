package eu.psartini.issues.jdbi

import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface DemoDAO : SqlObject {

    @SqlUpdate("INSERT INTO demotable (enumlist) VALUES (:enumList)")
    @GetGeneratedKeys
    fun insertKotlinList(enumList: List<MyEnum>): DemoTableWithKotlinList

    @SqlUpdate("INSERT INTO demotable (enumlist) VALUES (:enumList)")
    @GetGeneratedKeys
    fun insertJavaList(enumList: java.util.List<MyEnum>): DemoTableWithJavaList

}