package com.obaid.mvvm.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.obaid.mvvm.R
import com.obaid.mvvm.data.models.responses.ImagesDetails
import com.obaid.mvvm.databinding.FragmentHomeListingBinding
import com.obaid.mvvm.utils.NetworkResponse
import com.obaid.mvvm.utils.RecyclerItemClickListener
import com.obaid.mvvm.viewmodels.ListingViewModel
import com.obaid.mvvm.views.adapters.ListingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListingFragment : Fragment(), RecyclerItemClickListener {

    private var _binding: FragmentHomeListingBinding? = null
    private val binding get() = _binding!!
    private val listingViewModel by viewModels<ListingViewModel>()
    private val listingAdapter by lazy { ListingAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listingViewModel.fetchImages()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleAdapter()

        listingViewModel.responseLiveData.observe(viewLifecycleOwner) { res ->
            when (res) {
                is NetworkResponse.Error -> {
                    showError(res.msg ?: getString(R.string.something_went_wrong))
                }

                is NetworkResponse.Success -> {
                    res.data?.let {
                        listingViewModel.fetchImages()
                        updateListData(it)
                    } ?: kotlin.run {
                        showError(getString(R.string.something_went_wrong))
                    }
                }
            }
        }
    }

    private fun updateListData(imagesDetails: ImagesDetails) {
        listingAdapter.setData(imagesDetails.hits)
    }

    private fun handleAdapter() {
        binding.listData.layoutManager = LinearLayoutManager(requireContext())
        binding.listData.adapter = listingAdapter
    }

    private fun showError(msg: String) {
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setMessage(msg)
        alertBuilder.setPositiveButton(getString(R.string.ok)) { _, _ -> }
        alertBuilder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Int) {
        val data = listingViewModel.getItemClickData(position)
        data?.let {
            val direction = ListingFragmentDirections.actionListingFragmentToDetailsFragment(data)
            findNavController().navigate(direction)
        } ?: Toast.makeText(
            requireContext(),
            context?.getString(R.string.no_data),
            Toast.LENGTH_SHORT
        ).show()
    }
}