package com.obaid.mvvm.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.obaid.mvvm.R
import com.obaid.mvvm.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val safeArgs: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun displayData() {
        binding.image.load(safeArgs.hitData.largeImageURL)
        binding.userName.text = context?.getString(R.string.user_name, safeArgs.hitData.user) ?: ""
        binding.extraInfo.text = context?.getString(R.string.tags, safeArgs.hitData.tags) ?: ""
    }

    private fun handleToolBar() {
        binding.toolBar.setNavigationIcon(R.drawable.back)
        binding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayData()
        handleToolBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}