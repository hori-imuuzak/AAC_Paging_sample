package app.imuuzak.aac_paging_sample.service

import app.imuuzak.aac_paging_sample.data.Owner
import app.imuuzak.aac_paging_sample.data.Repo
import app.imuuzak.aac_paging_sample.repository.GithubRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.lang.IllegalArgumentException

const val baseUrl = "https://api.github.com"

class GithubService {
    private val githubRepository: GithubRepository = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().apply {
                    setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                }.create()
            )
        )
        .build()
        .create(GithubRepository::class.java)

    suspend fun getOwners(page: Int, perPage: Int): List<Owner> {
        if (page < 0) throw IllegalArgumentException("page")
        if (perPage < 0) throw IllegalArgumentException("perPage")

        val since = (page - 1) * perPage
        return githubRepository.getOwners(since, perPage)
    }

    suspend fun getRepos(owner: Owner, page: Int, perPage: Int): List<Repo> {
        if (page < 0) throw IllegalArgumentException("page")
        if (perPage < 0) throw IllegalArgumentException("perPage")

        return githubRepository.getRepos(owner.login, page, perPage)
    }
}