package com.example.exercise5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

public class MyPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            Fragment(R.layout.fragment_gallery)
        } else {
            Fragment(R.layout.fragment_slideshow)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            "List1 fragment"

        } else {
            "List2 fragment"
        }
    }

    override fun getCount(): Int {
        return 2
    }
}