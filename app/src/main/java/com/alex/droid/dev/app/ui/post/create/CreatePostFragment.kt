package com.alex.droid.dev.app.ui.post.create

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.base.BaseFragment
import com.alex.droid.dev.app.databinding.FragmentCreatePostBinding
import com.alex.droid.dev.app.ext.fetchImage
import com.alex.droid.dev.app.model.data.Address
import com.alex.droid.dev.app.model.routes.CreatePostRoute
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.fragment_create_post.*
import timber.log.Timber
import java.util.*


class CreatePostFragment : BaseFragment<CreatePostViewModel, CreatePostRoute>() {

    override fun getLayoutRes() = R.layout.fragment_create_post

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding: FragmentCreatePostBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_post, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeNullable(viewModel.onSelectMedia) {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), RC_PICK_MEDIA)
        }

        subscribeNullable(viewModel.onSelectLocation) {
            val context = context
            context ?: return@subscribeNullable

            if (!Places.isInitialized()) {
                Places.initialize(context, "AIzaSyCwLW5Rp9WBJ48ogN4vSuDTJ4KMs768dAw");
            }

            // Set the fields to specify which types of place data to return.
            val fields: List<Place.Field> = Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME, Place.Field.ADDRESS)

            // Start the autocomplete intent.
            val intent: Intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .setTypeFilter(TypeFilter.ADDRESS)
                .build(context)
            startActivityForResult(intent, RC_LOCATION)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_PICK_MEDIA -> {
                if (resultCode == Activity.RESULT_OK) {
                    viewModel.imageLiveData.value = data?.fetchImage()?.toString()
                }
            }
            RC_LOCATION -> {
                if (resultCode == Activity.RESULT_OK) {
                    data ?: return
                    val place = Autocomplete.getPlaceFromIntent(data)
                    val lat = place.latLng?.latitude
                    val lon = place.latLng?.longitude
                    val name = place.name
                    val address = place.address

                    Timber.d("Place: ${place.latLng?.latitude}, ${place.latLng?.longitude}, ${place.name}")

                    if (lat != null && lon != null && name != null) {
                        Timber.d("set address")
                        viewModel.addressLiveData.value = Address(
                            lat = lat,
                            lng = lon,
                            name = name,
                            address = address
                        )
                    }
                } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                    data ?: return
                    val status: Status = Autocomplete.getStatusFromIntent(data)
                    Toast.makeText(context, status.statusMessage, Toast.LENGTH_SHORT).show()
                    Timber.e(status.statusMessage)
                }
            }
        }
    }

    companion object {
        const val RC_PICK_MEDIA = 1
        const val RC_LOCATION = 2
    }
}
