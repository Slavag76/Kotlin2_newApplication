package com.testlslavag76.newapplication2module.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.testlslavag76.newapplication2module.R
import com.testlslavag76.newapplication2module.databinding.FragmentMainBinding
import com.testlslavag76.newapplication2module.viewmodel.PODData
import com.testlslavag76.newapplication2module.viewmodel.PODViewModel

class PODFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    val binding: FragmentMainBinding
        get() {
            return _binding!!
        }

    private val viewModel: PODViewModel by lazy {
        ViewModelProvider(this).get(PODViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.sendServerRequest()
    }

    private fun renderData(data: PODData) {
        when (data) {
            is PODData.Error -> {
                Toast.makeText(context, "PODData.Error", Toast.LENGTH_LONG).show()
            }
            is PODData.Loading -> {
                Toast.makeText(context, "PODData.Loading", Toast.LENGTH_LONG).show()
            }
            is PODData.Success -> {
                binding.imageView.load(data.serverResponseData.url) {
                    error(R.drawable.ic_load_error_vector)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = PODFragment()
    }


}