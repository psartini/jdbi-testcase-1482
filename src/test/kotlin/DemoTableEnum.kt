package eu.psartini.issues.jdbi

import java.util.*

data class DemoTableEnumWithJavaList(
    val demoId: UUID,
    val enumlist: java.util.List<MyEnum>
)

data class DemoTableEnumWithKotlinList(
    val demoId: UUID,
    val enumlist: List<MyEnum>
)
