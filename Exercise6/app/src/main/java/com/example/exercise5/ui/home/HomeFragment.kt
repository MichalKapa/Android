package com.example.exercise5.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.exercise5.R
import com.example.exercise5.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: SharedPreferences? = this.requireActivity().getSharedPreferences("image",
            AppCompatActivity.MODE_PRIVATE
        )
        val img: ImageView = view.findViewById(R.id.imageView2)

        val choice = data?.getInt("img", -1)
        when (choice) {
            0 -> img.setImageResource(R.drawable.ic_baseline_wb_sunny_24)
            1 -> img.setImageResource(R.drawable.ic_baseline_local_fire_department_24)
            2 -> img.setImageResource(R.drawable.ic_baseline_tsunami_24)
            3 -> img.setImageResource(R.drawable.ic_baseline_wind_power_24)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}