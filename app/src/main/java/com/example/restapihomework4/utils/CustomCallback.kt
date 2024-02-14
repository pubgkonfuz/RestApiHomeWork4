package com.example.restapihomework4.utils

interface CustomCallback <T>{

    fun onResponse(data: T)

    fun onFailure(t: Throwable)
}