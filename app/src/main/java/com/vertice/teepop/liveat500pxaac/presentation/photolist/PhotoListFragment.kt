package com.vertice.teepop.liveat500pxaac.presentation.photolist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vertice.teepop.liveat500pxaac.MainApplicaition
import com.vertice.teepop.liveat500pxaac.R
import com.vertice.teepop.liveat500pxaac.presentation.viewmodel.PhotoItemViewModel
import kotlinx.android.synthetic.main.fragment_photo_list.*

/**
 * Created by nuuneoi on 11/16/2014.
 */
class PhotoListFragment : Fragment() {

    private val TAG: String = this::class.java.simpleName

    val photoListAdapter: PhotoListAdapter by lazy {
        PhotoListAdapter(activity!!)
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(PhotoItemViewModel::class.java).also {
            MainApplicaition.component.inject(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstances(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        // Init Fragment level's variable(s) here

    }

    private fun initInstances(savedInstanceState: Bundle?) {
        // Init 'View' instance(s) with rootView.findViewById here

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoListAdapter
        }

        viewModel.getPhotoItem().observe(this, Observer {
            it.let {
                photoListAdapter.setList(it)

                if (progressBar.visibility == View.VISIBLE)
                    progressBar.visibility = View.GONE
            }
        })
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    /*
     * Save Instance State Here
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    private fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // Restore Instance State here
    }

    companion object {

        fun newInstance(): PhotoListFragment {
            return PhotoListFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}
