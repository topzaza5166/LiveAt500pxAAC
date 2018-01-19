package com.vertice.teepop.liveat500pxaac.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by VerDev06 on 1/19/2018.
 */
data class PhotoItemCollection(
        @SerializedName("success") var success: Boolean = false,
        @SerializedName("data") var data: MutableList<PhotoItem> = ArrayList()) {

}