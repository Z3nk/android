package fi.kroon.vadret.data.nominatim.model

import com.squareup.moshi.Json

data class Address(

    @Json(name = "building")
    val building: String,

    @Json(name = "house_number")
    val houseNumber: String,

    @Json(name = "road")
    val road: String,

    @Json(name = "neighbourhood")
    val neighbourhood: String,

    @Json(name = "suburb")
    val suburb: String,

    @Json(name = "city_district")
    val cityDistrict: String,

    @Json(name = "city")
    val city: String,

    @Json(name = "state")
    val state: String,

    @Json(name = "post_code")
    val postCode: String,

    @Json(name = "country")
    val country: String,

    @Json(name = "country_code")
    val countryCode: String
)