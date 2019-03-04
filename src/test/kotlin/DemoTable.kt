package eu.psartini.issues.jdbi

import java.util.*

data class DemoTableWithJavaList(
    val demoId: UUID,
    val enumlist: java.util.List<MyEnum>
)

data class DemoTableWithKotlinList(
    val demoId: UUID,
    val enumlist: List<MyEnum>
)
