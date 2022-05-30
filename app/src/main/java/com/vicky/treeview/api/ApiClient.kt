package com.vicky.treeview.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import org.simpleframework.xml.strategy.Strategy
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

//    staging
    private const val baseUrl = "https://stgapp.leadergroup.com/EAmanaMobileService/"
    private const val baseUrl_inspectionService = "https://stgapp.leadergroup.com/EPM940InspectionService/"

    //    Beta
//    private const val baseUrl = "https://940.eamana.gov.sa/EAmanaMobileServiceBeta/"
//    private const val baseUrl_inspectionService =
//        "https://940.eamana.gov.sa/inspectionservicebeta/20210804/"
//    const val voice_play_base_url =
//        "https://940app.eamana.gov.sa/callrecordings/complaints/"


//    private val BASE_URL_FILES = "https://stgapp.leadergroup.com/AutomaxFileUploads/IMSAttachments/"
//    private const val baseUrl_mobile_service = "https://stgapp.leadergroup.com/EAmanaMobileService/ManageIncidents.asmx"
//    private const val baseUrl_ispection_service = "https://stgapp.leadergroup.com/EPM940InspectionService/Contractor.asmx"

    //    const val voice_play_base_url ="https://stgapp.leadergroup.com/AutomaxFileUploads/Classification/"
//    const val classification_image_base_url ="https://stgapp.leadergroup.com/EPM940CallRecordings/Complaints/"
//    private const val baseUrl_mobile_service = "https://940.eamana.gov.sa/EAmanaMobileServiceBeta/manageincidents.asmx"
//    private const val baseUrl_ispection_service = "https://940.eamana.gov.sa/inspectionservicebeta/20210804/general.asmx "
//    private const val baseUrl_InspectionService = "https://940.eamana.gov.sa/inspectionservicebeta/20210804/"
    private var retrofit: Retrofit? = null
    private var apiInterface: ApiInterface? = null
    private var retrofitAttachment: Retrofit? = null
    private var retrofitinspectionservice: Retrofit? = null
    private var apiInterfaceAttachment: ApiInterface? = null
    private var apiInterfaceinspectionservice: ApiInterface? = null

    fun getApiInterface(context: Context): ApiInterface? {
        if (apiInterface == null) {
            apiInterface = getClient(context)!!.create(ApiInterface::class.java)
        }
        return apiInterface
    }

    private fun getClient(context: Context): Retrofit? {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)


            val okHttpClient = httpClient.build()
            val strategy: Strategy = AnnotationStrategy()
            val serializer: Serializer = Persister(strategy)
            retrofit = Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))//To get result as XML
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //necessary for rx java retrofit api calls, else error pops up
                .addConverterFactory(ScalarsConverterFactory.create()) //To get result as a string
                .addConverterFactory(GsonConverterFactory.create()) //To get result as such in a model class (POJO)
                .client(okHttpClient)
                .build()
        }
        return retrofit
    }

}