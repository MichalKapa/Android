package com.example.exercise3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

var frag1: Fragment1? = null
var frag2: Fragment2? = null

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCenter.newInstance] factory method to
 * create an instance of this fragment.
 */

class FragmentCenter : Fragment(), RadioGroup.OnCheckedChangeListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var myTrans: FragmentTransaction? = null
    private val TAG_F1 = "Fragment1"
    private val TAG_F2 = "Fragment2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        if (savedInstanceState == null) {
            frag1 = Fragment1.newInstance("a", "b")
            frag2 = Fragment2.newInstance("a", "b")
            myTrans = childFragmentManager.beginTransaction()
            myTrans!!.add(R.id. centerFrameLayout, frag1!!, this.TAG_F1)
            myTrans!!.detach(frag1!!)
            myTrans!!.add(R.id. centerFrameLayout, frag2!!, this.TAG_F2)
            myTrans!!.detach(frag2!!)
            myTrans!!.commit()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_center, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
        }
        else {
            frag1 = childFragmentManager.findFragmentByTag(this.TAG_F1) as Fragment1
            frag2 = childFragmentManager.findFragmentByTag(this.TAG_F2) as Fragment2
        }

        (requireActivity().findViewById(R.id.centerRadioGroup) as RadioGroup)
            .setOnCheckedChangeListener(this)

        childFragmentManager.setFragmentResultListener("msgfromchild",
            viewLifecycleOwner) { key, bundle ->
            val result = bundle.getString("msg1")
            (requireActivity().findViewById<View>(R.id.centerResult) as TextView).text = result
        }

        val some_view: View = view.findViewById(R.id.frameLayout3)
        val viewButton1 = some_view.findViewById<Button>(R.id.buttonCenter)
        viewButton1.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragmentCenter_to_fragmentRight)
        }

        setFragmentResultListener("valuefromchild") { key, bundle ->
            val result = bundle.getString("value")
            (requireActivity().findViewById<View>(R.id.centerResult) as TextView).text = result
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentCenter.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCenter().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        myTrans = childFragmentManager.beginTransaction()
        when (checkedId) {
            R.id.centerRadioButton1 -> {
                frag2?.let { myTrans!!.detach(it) }
                frag1?.let { myTrans!!.attach(it) }
            }
            R.id.centerRadioButton2 -> {
                frag1?.let { myTrans!!.detach(it) }
                frag2?.let { myTrans!!.attach(it) }
            }
        }
        myTrans!!.commit()
    }
}