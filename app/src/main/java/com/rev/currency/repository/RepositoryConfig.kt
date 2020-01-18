package com.rev.currency.repository

import android.content.Context
import android.util.Log
import androidx.multidex.BuildConfig
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.rev.currency.R

class RepositoryConfig (context: Context) {
    private val networkTag = "RevOKHttp"

    init {
        FuelManager.instance.basePath = context.resources.getString(R.string.base_url)
        FuelManager.instance.addRequestInterceptor(tokenRequestInterceptor())
        FuelManager.instance.addResponseInterceptor(tokenResponseInterceptor())
    }

    private fun tokenRequestInterceptor() = { next: (Request) -> Request ->
        { req: Request ->
            val headers = req.headers
            req.header(headers)

            if(BuildConfig.DEBUG){
                Log.d(networkTag, req.url.toString())
                Log.d(networkTag, req.toString())
            }
            next(req)
        }
    }

    private fun tokenResponseInterceptor() = { next: (Request, Response) -> Response ->
        { req: Request, res: Response ->
            if(BuildConfig.DEBUG){
                Log.d(networkTag, res.toString())
            }
            next(req, res)
        }
    }
}