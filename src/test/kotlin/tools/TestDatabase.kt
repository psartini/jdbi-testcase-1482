package eu.psartini.issues.jdbi.tools

import com.opentable.db.postgres.embedded.EmbeddedPostgres
import org.jdbi.v3.core.Jdbi
import java.net.URL
import javax.sql.DataSource

object TestDatabase {

    private val embeddedPostgres: EmbeddedPostgres
    private val dataSource: DataSource

    init {
        embeddedPostgres = EmbeddedPostgres.builder().setPort(5432).start()
        dataSource = embeddedPostgres.postgresDatabase
        initDB(dataSource)
    }

    fun getDataSource(): DataSource {
        return dataSource
    }

    private fun initDB(ds: DataSource) {
        ds.connection.use {
            val jdbi = Jdbi.open(it)
            jdbi.createUpdate(
                """CREATE EXTENSION IF NOT EXISTS pgcrypto
                    WITH SCHEMA pg_catalog"""
            ).execute()
            jdbi.createUpdate(
                """
                CREATE TABLE demotable (
    					demo_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    					enumlist VARCHAR[] NOT NULL )
                """
            ).execute()
            jdbi.close()
        }
    }

    private fun executeScript(url: URL) {
        val script = url.readText(Charsets.UTF_8)
        executeScript(script)
    }

    fun executeScript(script: String) {
        dataSource.connection.use {
            val jdbi = Jdbi.open(it)
            jdbi.createScript(script).execute()
            jdbi.close()
        }
    }
}
