package fi.kroon.vadret.data.nominatim.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Nominatim(

    @Json(name = "place_id")
    val placeId: String,

    @Json(name = "licence")
    val licence: String,

    @Json(name = "osm_type")
    val osmType: String,

    @Json(name = "osm_id")
    val osmId: String,

    @Json(name = "boundingbox")
    val boundingBox: List<String>,

    @Json(name = "lat")
    val lat: String,

    @Json(name = "lon")
    val lon: String,

    @Json(name = "display_name")
    val displayName: String,

    @Json(name = "class")
    val klass: String,

    @Json(name = "type")
    val type: String,

    @Json(name = "importance")
    val importance: String,

    @Json(name = "icon")
    val icon: String

)