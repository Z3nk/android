package fi.kroon.vadret.data.nominatim.net

import fi.kroon.vadret.data.nominatim.model.Nominatim
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NominatimApi {

    @GET("search/")
    fun get(
        @Query("city") city: String,
        @Query("format") format: String,
        @Query("countrycodes") countryCodes: String,
        @Query("limit") limit: Int,
        @Query("namedetails") nameDetails: Int
    ): Single<Response<List<Nominatim>>>

    @GET("reverse/")
    fun getReverse(): Single<Response<Nominatim>>
}