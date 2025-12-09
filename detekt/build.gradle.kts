plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
//    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-api:1.19.0")
}

//detekt {
//    toolVersion = "1.23.5"
//    config = files("$rootDir/detekt.yml")
//    buildUponDefaultConfig = true
//    allRules = false
//    autoCorrect = true
//}