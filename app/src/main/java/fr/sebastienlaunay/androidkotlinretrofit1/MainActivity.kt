package fr.sebastienlaunay.androidkotlinretrofit1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.sebastienlaunay.androidkotlinretrofit1.model.TanStop
import fr.sebastienlaunay.androidkotlinretrofit1.remote.api.ITanApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val tanUrl = "http://open.tan.fr/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(tanUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ITanApi::class.java)
        val tanStops = service.getTanStops()


        // Solution 1
        /*tanStops.enqueue(object: Callback<List<TanStop>> {

            override fun onResponse(call: Call<List<TanStop>>, response: Response<List<TanStop>>) {
                val allTanStop = response.body()

                allTanStop?.let {

                    for( tanStop in it) {
                        Log.d("TAN","Arret Tan ${tanStop.libelle}")
                    }
                }
            }

            override fun onFailure(call: Call<List<TanStop>>, t: Throwable) {
                Log.e("TAN", "Error : $t")
            }
        })*/


        //Solution 2
        tanStops.enqueue(tanStopsCallback())

    }

    // Solution 2
    private fun tanStopsCallback(): Callback<List<TanStop>> {
        return object : Callback<List<TanStop>> {

            override fun onResponse(call: Call<List<TanStop>>, response: Response<List<TanStop>>) {
                val allTanStop = response.body()

                allTanStop?.let {

                    for (tanStop in it) {
                        Log.d("TAN", "Stop Tan ${tanStop.libelle}")
                    }
                }
            }

            override fun onFailure(call: Call<List<TanStop>>, t: Throwable) {
                Log.e("TAN", "Error : $t")
            }
        }
    }
}
