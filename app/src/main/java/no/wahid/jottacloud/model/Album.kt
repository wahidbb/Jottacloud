package no.wahid.jottacloud.model

import com.google.gson.annotations.SerializedName

data class Album(
    val collectionId: Int = 0,
    val collectionType: Int = 0,
    val username: String? = null,
    val title: String? = null,
    val description: String? = null,
    val lastModified: Long = 0L,
    val total: Int = 0,
    val maxCapturedDate: Long = 0L,
    val minCapturedDate: Long = 0L,
    val photos: Array<Photo>? = null,
    val meta: String? = null,
    val shareInfo: ShareInfo? = null,
    val coverPhoto: Photo? = null,
    val createdDate: Long = 0L,
    val id: String? = null
)

data class Photo(
    val id: String? = null,
    val md5: String? = null,
    val username: String? = null,
    val ownerFullName: String? = null,
    val capturedDate: Long = 0L,
    val createdISO08601: String? = null,
    val jfsDate: String? = null,
    val path: String? = null,
    val location: String? = null,
    val insertedIntoIndexDate: Int = 0,
    val filename: String? = null,
    val filesize: Long = 0L,
    val title: String? = null,
    val description: String? = null,
    val mimetype: String? = null,
    val content: String? = null,
    val width: Int = 0,
    val height: Int = 0,
    val orientation: String? = null,
    val codec: String? = null,
    val duration: String? = null,
    val keywords: Array<String>? = null,
    val geoHash: String? = null,
    val geoAddress: String? = null,
    val gpsCoords: String? = null,
    val camera: String? = null,
    val iso: Double = 0.0,
    val focalLength: String? = null,
    val exposure: String? = null,
    val adultContent: Boolean = false,
    val geoAddressRaw: GeoAddressRaw? = null,
    val rawExif: RawExif? = null,
    val clusterHostMapId: String? = null,
    val excludeInfo: String? = null,
    @SerializedName("file_url") val fileUrl: String? = null,
    @SerializedName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerializedName("video_url") val videoUrl: String? = null,
    @SerializedName("live_photo_url") val livePhotoUrl: String? = null,
    @SerializedName("live_photo_type") val livePhotoType: String? = null,
    val hidden: Boolean = false,
    val excluded: Boolean = false,
    @SerializedName("collection_photo_order") val collectionPhotoOrder: Int = 0,
    val tempId: String? = null,
    val publicId: String? = null,
    val nsfwScore: Int = 0,
    val livePhoto: LivePhoto? = null,
    val timestamp: String? = null
)

data class LivePhoto(
    val location: String? = null,
    val mimeType: String? = null,
    val deviceType: String? = null
)

data class ShareInfo(
    val removeGeoData: Boolean = false,
    val secret: String? = null,
    val nonPublic: Boolean = false,
    @SerializedName("subscribed_collection_publicId") val subscribedCollectionPublicId: String? = null,
    val authorization: String? = "READ",
    val subscribers: Array<Subscriber>? = null,
    val uri: String? = null,
    val shareDate: Long = 0L,
    val subscriptionDate: Long = 0L,
    val owner: String? = null,
    val ownerFullName: String? = null,
    val admin: Boolean = false
)

class Subscriber
class GeoAddressRaw
class RawExif

