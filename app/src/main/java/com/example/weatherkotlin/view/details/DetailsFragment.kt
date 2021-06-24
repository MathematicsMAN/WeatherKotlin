package com.example.weatherkotlin.view.details

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherkotlin.CircleTransformation
import com.example.weatherkotlin.R
import com.example.weatherkotlin.databinding.FragmentDetailsBinding
import com.example.weatherkotlin.model.Weather
import com.example.weatherkotlin.model.WeatherDTO
import com.example.weatherkotlin.utils.showSnackBar
import com.example.weatherkotlin.viewmodel.AppState
import com.example.weatherkotlin.viewmodel.DetailsViewModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*

const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val DETAILS_INTENT_EMPTY_EXTRA = "INTENT IS EMPTY"
const val DETAILS_DATA_EMPTY_EXTRA = "DATA IS EMPTY"
const val DETAILS_RESPONSE_EMPTY_EXTRA = "RESPONSE IS EMPTY"
const val DETAILS_REQUEST_ERROR_EXTRA = "REQUEST ERROR"
const val DETAILS_REQUEST_MESSAGE_EXTRA = "REQUEST ERROR MESSAGE"
const val DETAILS_URL_MALFORMED_EXTRA = "URL MALFORMED"
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "RESPONSE SUCCESS"
const val DETAILS_TEMP_EXTRA = "TEMPERATURE"
const val DETAILS_FEELS_LIKE_EXTRA = "FEELS LIKE"
const val DETAILS_CONDITIONS_EXTRA = "CONDITION"
private const val TEMP_INVALID = -100
private const val FEELS_LIKE_INVALID = -100
private const val PROCESS_ERROR = "Обработка ошибки"
private const val MAIN_LINK = "https://api.weather.yandex.ru/v2/informers?"
private const val REQUEST_API_KEY = "X-Yandex-API-Key"

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
//    private val loadResultReceiver: BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            when (intent?.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
//                DETAILS_INTENT_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_DATA_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_RESPONSE_EMPTY_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_REQUEST_ERROR_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_REQUEST_MESSAGE_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_URL_MALFORMED_EXTRA -> TODO(PROCESS_ERROR)
//                DETAILS_RESPONSE_SUCCESS_EXTRA -> renderData(
//                    WeatherDTO(FactDTO(intent.getIntExtra(DETAILS_TEMP_EXTRA, TEMP_INVALID),
//                                       intent.getIntExtra(DETAILS_FEELS_LIKE_EXTRA, FEELS_LIKE_INVALID),
//                                       intent.getStringExtra(DETAILS_CONDITIONS_EXTRA)))
//                )
//                else -> TODO(PROCESS_ERROR)
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    private val onLoadListener: WeatherLoader.WeatherLoaderListener =
        object : WeatherLoader.WeatherLoaderListener {
            override fun onLoaded(weatherDTO: WeatherDTO) {
                displayWeather(weatherDTO)
            }

            override fun onFailed(throwable: Throwable) {
                Log.d("", throwable.toString())
            }
        }

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    //    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
//        getWeather()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        context?.let {
//            LocalBroadcastManager
//                .getInstance(it)
//                .registerReceiver(loadResultReceiver,
//                                 IntentFilter(DETAILS_INTENT_FILTER) )
//        }
//    }
//
//    override fun onDestroy() {
//        context?.let {
//            LocalBroadcastManager
//                .getInstance(it)
//                .unregisterReceiver(loadResultReceiver)
//        }
//        super.onDestroy()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun displayWeather(weatherDTO: WeatherDTO) {
        with(binding) {
            mainView.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE
            val city = weatherBundle.city
            cityName.text = city.city
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            weatherCondition.text = weatherDTO.fact?.condition
            temperatureValue.text = weatherDTO.fact?.temp.toString()
            feelsLikeValue.text = weatherDTO.fact?.feels_like.toString()
        }
    }

    /*
        private fun getWeather() {
            binding.mainView.visibility = View.GONE
            binding.loadingLayout.visibility = View.VISIBLE

            val client = OkHttpClient()
            val builder : Request.Builder = Request.Builder()
            builder.header(REQUEST_API_KEY, BuildConfig.WEATHER_API_KEY)
            builder.url(MAIN_LINK + "lat=${weatherBundle.city.lat}&lon=${weatherBundle.city.lon}")
            val request: Request = builder.build()
            val call: Call = client.newCall(request)
            call.enqueue(object : Callback {
                val handler: Handler = Handler()

                @Throws(IOException::class)
                override fun onResponse(call: Call?, response: Response) {
                    val serverResponse: String? = response.body()?.string()
                    if (response.isSuccessful && serverResponse != null) {
                        handler.post {
                            renderData(Gson().fromJson(serverResponse, WeatherDTO::class.java))
                        }
                    } else {
                        TODO(PROCESS_ERROR)
                    }
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    TODO(PROCESS_ERROR)
                }
            })
    //        context?.let {
    //            it.startService(Intent(it, DetailsService::class.java).apply {
    //                putExtra(LATITUDE_EXTRA, weatherBundle.city.lat)
    //                putExtra(LONGITUDE_EXTRA, weatherBundle.city.lon)
    //            })
    //        }
        }
    */
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                setWeather(appState.weatherData[0])
            }
            is AppState.Loading -> {
                binding.mainView.visibility = View.GONE
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                binding.mainView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.getWeatherFromRemoteSource(
                            weatherBundle.city.lat,
                            weatherBundle.city.lon
                        )
                    }
                )
            }
        }
        /*
            binding.mainView.visibility = View.VISIBLE
            binding.loadingLayout.visibility = View.GONE

            val fact = weatherDTO.fact
    //        val temp = fact!!.temp
    //        val feelsLike = fact.feels_like
    //        val condition = fact.condition
            if (fact?.temp == null || fact.feels_like == null || fact.condition.isNullOrEmpty()) {
                TODO(PROCESS_ERROR)
            } else {
                val city = weatherBundle.city
                binding.cityName.text = city.city
                binding.cityCoordinates.text = String.format(
                    getString(R.string.city_coordinates),
                    city.lat.toString(),
                    city.lon.toString()
                )
                binding.temperatureValue.text = fact.temp.toString()
                binding.feelsLikeValue.text = fact.feels_like.toString()
                binding.weatherCondition.text = fact.condition
            }
         */
    }

    private fun setWeather(weather: Weather) {
        val city = weatherBundle.city
        binding.cityName.text = city.city
        binding.cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            city.lat.toString(),
            city.lon.toString()
        )

        weather.icon?.let {
            GlideToVectorYou.justLoadImage(
                activity,
                Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${it}.svg"),
                weatherIcon
            )
            binding.temperatureValue.text = weather.temperature.toString()
            binding.feelsLikeValue.text = weather.feelsLike.toString()
            binding.weatherCondition.text = weather.condition
        }

        Picasso.get()
            .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
            .transform(CircleTransformation())
            .into(headerIcon)
    }
}