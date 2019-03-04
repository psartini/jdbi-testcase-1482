package eu.psartini.issues.jdbi

import java.util.*

data class DemoTableUUIDWithJavaList(
    val demoId: UUID,
    val uuidlist: java.util.List<UUID>
)

data class DemoTableUUIDWithKotlinList(
    val demoId: UUID,
    val uuidlist: List<UUID>
)
