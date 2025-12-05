package co.kr.datau.cicdpractice

import co.kr.datau.cicdpractice.BuildConfig

object Const {
    val BASE_URL = if (BuildConfig.DEBUG) {
        "dev-www.naver.com"
    } else {
        "www.naver.com"
    }

    const val RELEASE_VALUE_INT = 0
    const val RELEASE_VALUE_STRING = ""
    // test

}