package com.vertice.teepop.liveat500pxaac.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by VerDev06 on 1/19/2018.
 */
data class PhotoItem(@SerializedName("id")
                     var id: Int = 0,
                     @SerializedName("link")
                     var link: String? = null,
                     @SerializedName("image_url")
                     var imageUrl: String? = null,
                     @SerializedName("caption")
                     var caption: String? = null,
                     @SerializedName("user_id")
                     var userId: Int = 0,
                     @SerializedName("username")
                     var username: String? = null,
                     @SerializedName("profile_picture")
                     var profilePicture: String? = null,
                     @SerializedName("tags")
                     var tags: List<String> = ArrayList(),
                     @SerializedName("created_time")
                     var createdTime: Date? = null,
                     @SerializedName("camera")
                     var camera: String? = null,
                     @SerializedName("lens")
                     var lens: String? = null,
                     @SerializedName("focal_length")
                     var focalLength: String? = null,
                     @SerializedName("iso")
                     var iso: String? = null,
                     @SerializedName("shutter_speed")
                     var shutterSpeed: String? = null,
                     @SerializedName("aperture")
                     var aperture: String? = null) {


}