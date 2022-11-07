package com.happiestminds.tmdbapp

import android.os.Parcel
import android.os.Parcelable

//step 2 - creation of model class based on the response

data class Movies(var id: Int, var title: String?, var vote_average: Double, var release_date: String?, var poster_path: String?, var overview: String?, var adult: Boolean) : Parcelable //properties should match the response available in the docs of the website ie, here TMDB
{
    //parcelable enables us to pass objects from one activity to another through putExtra
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeDouble(vote_average)
        parcel.writeString(release_date)
        parcel.writeString(poster_path)
        parcel.writeString(overview)
        parcel.writeByte(if (adult) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movies> {
        override fun createFromParcel(parcel: Parcel): Movies {
            return Movies(parcel)
        }

        override fun newArray(size: Int): Array<Movies?> {
            return arrayOfNulls(size)
        }
    }
}


data class PopularMovies(val results : MutableList<Movies>)