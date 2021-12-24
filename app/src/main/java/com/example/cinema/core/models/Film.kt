package com.example.cinema.core.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Film(
    val id: Long,
    @SerializedName("localized_name")
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Double?,
    @SerializedName("image_url")
    val imageUrl: String?,
    val description: String?,
    val genres: ArrayList<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList() as ArrayList<String>
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(localizedName)
        parcel.writeString(name)
        parcel.writeInt(year)
        parcel.writeValue(rating)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeStringList(genres)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}