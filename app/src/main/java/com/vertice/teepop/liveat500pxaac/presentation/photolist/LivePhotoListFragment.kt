package com.vertice.teepop.liveat500pxaac.presentation.photolist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
class LivePhotoListFragment : Fragment() {

    private val TAG: String = this::class.java.simpleName

    private val photoListAdapter: PhotoLiveAdapter = PhotoLiveAdapter()

    val linearLayoutManager = LinearLayoutManager(context)

    var loadingMore: Boolean = false

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
        getPhotoList()

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.reloadLivePhotoItem()
        }

        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = photoListAdapter
            addOnScrollListener(scrollListener)
        }

    }

    private fun getPhotoList() {
        viewModel.getLivePhotoItem().observe(this, Observer {
            it?.let {
                photoListAdapter.photoList = it
                onPhotoItemChangedListener()
            }
        })
    }

    private fun onPhotoItemChangedListener() {
        if (swipeRefreshLayout.isRefreshing)
            swipeRefreshLayout.isRefreshing = false

        if (loadingMore)
            loadingMore = false
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

    /**
     *  visibleItemCount = linearLayoutManager.childCount
     *  totalItemCount = linearLayoutManager.itemCount
     *  firstVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()
     */
    private val scrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            if (!loadingMore && dy > 0) {
                linearLayoutManager.run {
                    if (findFirstVisibleItemPosition() + childCount >= itemCount) {
                        viewModel.loadMore()
                        loadingMore = true
                    }
                }
            }
        }
    }

    companion object {

        fun newInstance(): LivePhotoListFragment {
            return LivePhotoListFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}
