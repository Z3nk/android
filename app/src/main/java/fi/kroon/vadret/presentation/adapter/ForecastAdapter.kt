package fi.kroon.vadret.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fi.kroon.vadret.R
import fi.kroon.vadret.data.weather.model.TimeSerie
import fi.kroon.vadret.di.scope.VadretApplicationScope
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.TextStyle
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

const val TAGA = "ForecastAdapter"

@VadretApplicationScope
class ForecastAdapter @Inject constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal var collection: List<Any> by Delegates.observable(listOf()) {
        _, _, _ -> notifyDataSetChanged()
    }

    companion object {
        const val TYPE_WEEKDAY = 0
        const val TYPE_FORECAST = 1
    }

    class WeekdayViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(localDate: LocalDate) {
            itemView.weekDay.text = localDate
                    .dayOfWeek
                    .getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())
                    .toUpperCase()
        }
    }

    class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(timeSerie: TimeSerie) {

            Log.d(TAGA, "TimeSerie: ${timeSerie.parameters}")

            itemView.time.text = OffsetDateTime.parse(timeSerie.validTime).toLocalTime().toString()

            timeSerie.parameters.map {

                if (it.name == "t") {
                    Log.d(TAGA, "t: ${it.values[0]}")
                    itemView.temperature.text = it.values[0].toString()
                } else if (it.name == "r") {
                    Log.d(TAGA, "r: ${it.values[0]}")
                    itemView.r.text = it.values[0].toInt().toString()
                } else if (it.name == "gust") {
                    Log.d(TAGA, "gust: ${it.values[0]}")
                    itemView.gust.text = it.values[0].toString()
                } else if (it.name == "Wsymb2") {
                    Log.d(TAGA, "wsymb2: ${it.values[0]}")
                    itemView.wsymb2.setText(handleWsymb2(it.values[0].toInt()))
                }
            }
        }

        private fun handleWsymb2(index: Int): Int {
            return when (index) {
                1 -> R.string.wsymb2_clear_sky
                2 -> R.string.wsymb2_nearly_clear_sky
                3 -> R.string.wsymb2_variable_cloudiness
                4 -> R.string.wsymb2_halfclear_sky
                5 -> R.string.wsymb2_cloudy_sky
                6 -> R.string.wsymb2_overcast
                7 -> R.string.wsymb2_fog
                8 -> R.string.wsymb2_light_rain_showers
                9 -> R.string.wsymb2_moderate_rain_showers
                10 -> R.string.wsymb2_heavy_rain_showers
                11 -> R.string.wsymb2_thunderstorm
                12 -> R.string.wsymb2_light_sleet_showers
                13 -> R.string.wsymb2_moderate_sleet_showers
                14 -> R.string.wsymb2_heavy_sleet_showers
                15 -> R.string.wsymb2_light_snow_showers
                16 -> R.string.wsymb2_moderate_snow_showers
                17 -> R.string.wsymb2_heavy_snow_showers
                18 -> R.string.wsymb2_light_rain
                19 -> R.string.wsymb2_moderate_rain
                20 -> R.string.wsymb2_heavy_rain
                21 -> R.string.wsymb2_thunder
                22 -> R.string.wsymb2_light_sleet
                23 -> R.string.wsymb2_moderate_sleet
                24 -> R.string.wsymb2_heavy_sleet
                25 -> R.string.wsymb2_light_snowfall
                26 -> R.string.wsymb2_moderate_snowfall
                27 -> R.string.wsymb2_heavy_snowfall
                else -> {
                    R.string.wsymb2_clear_sky
                }
            }
        }

        fun handlePrSort(prSort: Int) {
            when (prSort) {
                0 -> R.string.prsort_no_precipitation
                1 -> R.string.prsort_snow
                2 -> R.string.prsort_snow_and_rain
                3 -> R.string.prsort_rain
                4 -> R.string.prsort_drizzle
                5 -> R.string.prsort_freezing_rain
                6 -> R.string.prsort_freezing_drizzle
            }
        }
    }
    
    override fun getItemViewType(position: Int): Int {
        return when (collection[position]) {
            is LocalDate -> TYPE_WEEKDAY
            else -> TYPE_FORECAST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_WEEKDAY -> WeekdayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false))
            else -> ForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_FORECAST -> (holder as ForecastViewHolder).bind(collection[position] as TimeSerie)
            TYPE_WEEKDAY -> (holder as WeekdayViewHolder).bind(collection[position] as LocalDate)
        }
    }

    override fun getItemCount(): Int = collection.size
}