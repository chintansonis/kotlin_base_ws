package com.app.kotlinbasews.api.responsepojos

import java.io.Serializable

class BaseResponse<T> : Serializable {
    var status: String = ""
    var responseData: T? = null
    var message: String? = null
}
