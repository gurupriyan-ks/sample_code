package com.sample.emmarsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sample.emmarsample.databinding.FragmentSecondBinding
import com.sample.emmarsample.models.servicemodels.Results
import java.text.SimpleDateFormat
import java.util.*


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setScreenTitle(viewModel.selectedUser.name.toString())
        setImage(viewModel.selectedUser)
        binding.tvAge.text = viewModel.selectedUser.dob?.age.toString()
        binding.tvEmailId.text = viewModel.selectedUser.email.toString()
        binding.tvCity.text = "City: ${viewModel.selectedUser.location?.city}"
        binding.tvDOB.text = "DOB: ${formatDate(viewModel.selectedUser.dob?.date.toString())}"
        binding.tvCountry.text = "Country: ${viewModel.selectedUser.location?.country}"
        binding.tvState.text = "State: ${viewModel.selectedUser.location?.state}"
        binding.tvPostal.text = "Postal: ${viewModel.selectedUser.location?.postcode}"
        binding.tvDateOfJoining.text = "Date Joined: ${viewModel.selectedUser.registered}"
    }

    private fun formatDate(date: String): String {
        val myFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val myDate: Date? = myFormat.parse(date)
        return myDate.toString()
    }

    private fun setImage(results: Results) {
        Glide.with(requireContext())
            .load(results.picture?.large)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.ivUserImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
