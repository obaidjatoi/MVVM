package com.obaid.mvvm.utils

sealed class NetworkResponse<T>(val data: T? = null, val msg: String? = null) {
    class Success<T>(data: T) : NetworkResponse<T>(data)
    class Error<T>(msg: String?, data: T? = null) : NetworkResponse<T>(data, msg)
}
