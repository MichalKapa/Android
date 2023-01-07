package com.example.exercise5.ui.swipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.exercise5.MyPagerAdapter
import com.example.exercise5.R
import com.example.exercise5.databinding.FragmentSlideshowBinding
import com.google.android.material.tabs.TabLayout

class SwipeFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SwipeViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textSlideshow
//        slideshowViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vpAdapter = MyPagerAdapter(requireActivity().supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        val vPager = requireActivity().findViewById<View>(R.id.viewPager) as ViewPager
        vPager.adapter = vpAdapter
        val tabLayout = requireActivity().findViewById<View>(R.id.tabLayout) as TabLayout
        tabLayout.setupWithViewPager(vPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}