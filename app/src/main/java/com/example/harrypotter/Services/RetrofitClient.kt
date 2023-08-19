
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.threeten.bp.LocalDate
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.harrypotter.Services.LocalDateDeserializer


class RetrofitClient {
    companion object {
        private lateinit var INSTANCE:Retrofit
        private const val BASE_URL="https://hp-api.onrender.com/api/"

        private fun getRetrofitInstance():Retrofit{
            val http = OkHttpClient.Builder()
            //if(!this::INSTANCE.isInitialized){
         /*  val gson = GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, LocalDateDeserializer())
                .create()*/

            val gson = GsonBuilder().setDateFormat("dd-MM-yyyy").create()
            INSTANCE = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(http.build())
                .build()
            // }
            return INSTANCE;
        }
        fun <S> createService(abc: Class<S>): S {
            return getRetrofitInstance().create(abc)
        }

    }
}