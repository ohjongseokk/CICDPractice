package co.kr.datau.cicdpractice

object Const {
    val BASE_URL = if (BuildConfig.DEBUG) {
        "dev-www.naver.com"
    } else {
        "www.naver.com"
    }

    const val RELEASE_VALUE_INT = 1
    const val RELEASE_VALUE_STRING = ""
    // testestset
}