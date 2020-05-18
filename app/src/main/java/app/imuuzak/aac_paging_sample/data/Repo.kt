package app.imuuzak.aac_paging_sample.data

data class Repo(
    val id: Int,
    val name: String,
    val htmlUrl: String,
    val stargazersCount: Int,
    val owner: Owner
)