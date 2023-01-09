package com.example.exercise5

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var dataRepo: MyRepository

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        dataRepo = MyRepository.getinstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancelButton: Button = view.findViewById<Button>(R.id.buttonD)
        cancelButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val data: SharedPreferences? = this.requireActivity().getSharedPreferences("item",
            AppCompatActivity.MODE_PRIVATE
        )
        val index = data?.getInt("details", -1)
        val name = view.findViewById<TextView>(R.id.textView5D)
        val name2 = view.findViewById<TextView>(R.id.textView8D)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBarD)
        val seekBar = view.findViewById<SeekBar>(R.id.seekBarD)
        val type = view.findViewById<TextView>(R.id.textView10D)

        if(index != -1) {
            val itemList = dataRepo.getData()!!
            name.text = itemList[index!!].text_main
            name2.text = itemList[index].text_2
            ratingBar.rating = itemList[index].item_value.toFloat()
            seekBar.progress = itemList[index].item_value2
            if (itemList[index].item_type) {
                type.text = "Male"
            }
            else {
                type.text = "Female"
            }
        }
        ratingBar.isEnabled = false
        seekBar.isEnabled = false
        val modButton: Button = view.findViewById<Button>(R.id.button2D)
        modButton.setOnClickListener {
            val data2: SharedPreferences = context?.getSharedPreferences("item",
                AppCompatActivity.MODE_PRIVATE
            )!!
            val editor = data2.edit()!!
            editor.putBoolean("new", false)
            if(index != -1) {
                editor.putInt("position", index)
            }
            editor.apply()
            findNavController().navigate(R.id.frameLayout)
        }



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}