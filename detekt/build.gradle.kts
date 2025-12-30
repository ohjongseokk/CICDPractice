plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
//    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    compileOnly("io.gitlab.arturbosch.detekt:detekt-api:1.19.0")
}
