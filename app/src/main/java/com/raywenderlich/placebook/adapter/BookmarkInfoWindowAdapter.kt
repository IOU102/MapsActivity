// 1
package com.raywenderlich.placebook.adapter

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.raywenderlich.placebook.R
import com.raywenderlich.placebook.ui.MapsActivity
import com.raywenderlich.placebook.viewmodel.MapsViewModel

// 2
class BookmarkInfoWindowAdapter(val context: Activity) :
    GoogleMap.InfoWindowAdapter {
    // 3
    private val contents: View

    init {
        contents = context.layoutInflater.inflate(
            R.layout.content_bookmark_info, null)
    }
    // 4
    override fun getInfoWindow(marker: Marker): View? {
        // This function is required, but can return null if
        // not replacing the entire info window
        return null
    }
    // 5
    override fun getInfoContents(marker: Marker): View? {

        val titleView = contents.findViewById<TextView>(R.id.title)
        titleView.text = marker.title ?: ""

        val phoneView = contents.findViewById<TextView>(R.id.phone)
        phoneView.text = marker.snippet ?: ""

        val imageView = contents.findViewById<ImageView>(R.id.photo)
        when (marker.tag) {
            // 1
            is MapsActivity.PlaceInfo -> {
                imageView.setImageBitmap(
                    (marker.tag as MapsActivity.PlaceInfo).image)
            }
            // 2
            is MapsViewModel.BookmarkView -> {
                val bookMarkview = marker.tag as
                        MapsViewModel.BookmarkView
                // Set imageView bitmap here
                imageView.setImageBitmap(bookMarkview.getImage(context))
            }
        }

        return contents
    }
}