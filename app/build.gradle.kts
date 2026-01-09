import java.text.SimpleDateFormat
import java.util.Date

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("io.gitlab.arturbosch.detekt")
    id("com.google.gms.google-services")
}

// 날짜를 가져오는 함수 (yy.MM.dd 형식)
fun getFormattedDate(): String = SimpleDateFormat("yy.MM.dd").format(Date())

// 버전을 숫자로만 변환하는 함수 (yyMMdd + 횟수)
fun getVersionCodeFromDate(buildNumber: Int): Int {
    val datePart = SimpleDateFormat("yyMMdd").format(Date())
    // 260109 + 01 = 26010901
    return (datePart + String.format("%02d", buildNumber)).toInt()
}

android {
    val buildNumber = project.findProperty("buildNumber")?.toString()?.toInt() ?: 1

    namespace = "co.kr.datau.cicdpractice"
    compileSdk = 36

    defaultConfig {
        applicationId = "co.kr.datau.cicdpractice"
        minSdk = 30
        targetSdk = 35

        // versionName: 26.01.09.01 형식
        versionName = "${getFormattedDate()}.${String.format("%02d", buildNumber)}"

        // versionCode: 26010901 형식 (숫자)
        versionCode = getVersionCodeFromDate(buildNumber)
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"dev-www.naver.com\""
            )
        }

        release {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"dev-www.naver.com\""
            )

            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    detektPlugins(project(":detekt"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(platform("com.google.firebase:firebase-bom:34.7.0"))
}

tasks.register("detekt-debug") {
    group = "verification"
    description = "Run detekt with debug rules"

    doFirst {
        project.extensions
            .getByType<io.gitlab.arturbosch.detekt.extensions.DetektExtension>()
            .config = files("$rootDir/detekt/detekt-debug.yml")
    }

//    dependsOn("detekt")
}

tasks.register("detekt-release") {
    group = "verification"
    description = "Run detekt with release rules"

    doFirst {
        project.extensions
            .getByType<io.gitlab.arturbosch.detekt.extensions.DetektExtension>()
            .config = files("$rootDir/detekt/detekt-release.yml")
    }

//    dependsOn("detekt")
}


//val detektConfig = when {
//    project.hasProperty("release") ->
//        files("$rootDir/detekt/detekt-release.yml")
//
//    else ->
//        files("$rootDir/detekt/detekt-debug.yml")
//}

detekt {
    toolVersion = "1.23.5"
    config = files("$rootDir/detekt/detekt-release.yml")
    buildUponDefaultConfig = false
    allRules = false
    autoCorrect = true
    ignoreFailures = false
}