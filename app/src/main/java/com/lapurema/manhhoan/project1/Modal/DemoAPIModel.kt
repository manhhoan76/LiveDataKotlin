package com.lapurema.manhhoan.project1.Modal

object DemoAPIModel {

    data class User(
        val login: String,
        val id: Long,
        val url: String,
        val html_url: String,
        val followers_url: String,
        val following_url: String,
        val starred_url: String,
        val gists_url: String,
        val type: String,
        val score: Int
    )

    data class Result(val total_count: Int, val incomplete_results: Boolean, val items: List<User>)

}

object Model {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}

object ObjLogin {
    data class ResultLogin(
        val totalhits: Int,
        val facode: String,
        val created_at: Int,
        val currency_unit_id: Int,
        val email: String,
        val is_auth: Boolean,
        val locale: String,
        val status: Int,
        val token: String,
        val updated_at: Int,
        val user_name: String,
        val username: String,
        val wallet_id: String,
        val id: String,
        val firebase_token: String
    )
}