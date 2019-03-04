package eu.psartini.issues.jdbi.tools

import eu.psartini.issues.jdbi.MyEnum
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.postgres.PostgresPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import java.util.logging.Level
import java.util.logging.Logger
import javax.sql.DataSource

class JdbiProvider(private val dataSource: (schema: String) -> DataSource) {

	private val logger = Logger.getLogger(JdbiProvider::class.qualifiedName)
	private lateinit var jdbi: Jdbi

	fun get(): Jdbi {
		if (!this::jdbi.isInitialized) {
			try {
				val schema = System.getenv("DBSCHEMA") ?: "public"
				jdbi = Jdbi.create(dataSource(schema))
				jdbi.installPlugin(PostgresPlugin())
				jdbi.installPlugin(KotlinPlugin())
				jdbi.installPlugin(SqlObjectPlugin())
				jdbi.installPlugin(KotlinSqlObjectPlugin())

				// ####
				// REGISTER ENUM ARRAY TYPE

				jdbi.registerArrayType(MyEnum::class.java, "varchar") { p -> p.name }
				// ####
			} catch (ex: Exception) {
				logger.log(Level.SEVERE, "Error connecting to Database", ex)
			}
		}
		return jdbi
	}
}
