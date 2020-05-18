package app.imuuzak.aac_paging_sample.repository

import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.data.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubRepository {
    @GET("users")
    suspend fun getOwners(@Query("since") since: Int = 0, @Query("per_page") perPage: Int = 30): List<Owner>

    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("owner") username: String): List<Repo>
}