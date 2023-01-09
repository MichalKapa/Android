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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var dataRepo: MyRepository

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
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAddSave = requireActivity().findViewById<Button>(R.id.button2)

        val buttonCancel = requireActivity().findViewById<Button>(R.id.button)

        buttonCancel.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val data: SharedPreferences? = this.requireActivity()?.getSharedPreferences("item",
            AppCompatActivity.MODE_PRIVATE
        )
        var item = DBItem()
        val isNew = data?.getBoolean("new", false)
        if (isNew == false) {
            buttonAddSave.text = "SAVE"
            if(data?.getInt("position", -1)!=-1) {
                val pos = data?.getInt("position", -1)!!
                val itemList = dataRepo.getData()!!
                item = itemList[pos]

                val name = view.findViewById<TextView>(R.id.editTextTextPersonName)
                val name2 = view.findViewById<TextView>(R.id.editTextTextPersonName2)
                val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
                val seekBar = view.findViewById<SeekBar>(R.id.seekBar)

                name.setText(item.text_main)
                name2.setText(item.text_2)
                ratingBar.rating = item.item_value.toFloat()
                seekBar.progress = item.item_value2
                if(item.item_type){
                    view.findViewById<RadioButton>(R.id.radioButton1).isChecked = true
                }
                else{
                    view.findViewById<RadioButton>(R.id.radioButton4).isChecked = true
                }
            }
        }
        else {
            buttonAddSave.text = "ADD"
        }

        val buttonSave = requireActivity().findViewById<Button>(R.id.button2)
        buttonSave.setOnClickListener {
            val itemName = view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            val secondaryText = view.findViewById<EditText>(R.id.editTextTextPersonName2).text.toString()
            val ratingBarValue = view.findViewById<RatingBar>(R.id.ratingBar).rating
            val seekBarValue = view.findViewById<SeekBar>(R.id.seekBar).progress
            val checkedId = view.findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId

            item.text_main = itemName
            item.text_2 = secondaryText
            item.item_value = ratingBarValue.toInt()
            item.item_value2 = seekBarValue
            item.item_type = checkedId == R.id.radioButton1

            if(isNew == false) {
                if (MyRepository.getinstance(requireContext()).updateItem(item,
                        data.getInt("position", -1)
                    ))
                    parentFragmentManager.setFragmentResult("item_added", Bundle.EMPTY)
                requireActivity().onBackPressed()
            }
            else {
                if (MyRepository.getinstance(requireContext()).addItem(item))
                    parentFragmentManager.setFragmentResult("item_added", Bundle.EMPTY)
            }

            val editor = data?.edit()!!
            editor.putBoolean("new", true)

            requireActivity().onBackPressed()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment addFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            addFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}