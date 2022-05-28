package com.apsareena.lunchtray.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apsareena.lunchtray.R
import com.apsareena.lunchtray.databinding.FragmentSummaryBinding
import com.apsareena.lunchtray.model.OrderViewModel

class SummaryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentSummaryBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        _binding = fragmentSummaryBinding
        binding.handler = myHandlersListener
        return fragmentSummaryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    private val myHandlersListener: MyHandler = object : MyHandler {
        override fun goToNextScreen() {
            findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
        }

        override fun cancelOrder() {
            sharedViewModel.resetOrder()
            findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}