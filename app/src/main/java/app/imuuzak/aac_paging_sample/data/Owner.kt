package app.imuuzak.aac_paging_sample.data

import java.io.Serializable

data class Owner(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String
): Serializable