package com.alex.droid.dev.app.ui.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.alex.droid.dev.app.MainActivity

import com.alex.droid.dev.app.R
import com.alex.droid.dev.app.model.routes.ChatRoute
import com.alex.droid.dev.app.ui.chat.ChatFragment
import kotlinx.android.synthetic.main.fragment_contacts.*

/**
 * A simple [Fragment] subclass.
 */
class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn.setOnClickListener {
            (activity as MainActivity).router.replaceWithStack(ChatRoute, null, "main")
        }
    }
}
