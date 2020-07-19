package com.jhurtado.lastfm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jhurtado.lastfm.R

/**
 * @author jhurtado
 * Date: 17/07/20
 * LasfFM test for Valid.com
 */
abstract class BaseListFragment : Fragment() {

    lateinit var fragmentTitle: TextView
    lateinit var viewpager: RecyclerView
    lateinit var viewModel: ViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentTitle = view.findViewById(R.id.tv_list_fragment_title)
        viewpager = view.findViewById(R.id.vp_list_fragment)
        viewpager.layoutManager = LinearLayoutManager(context)
        viewpager.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

}