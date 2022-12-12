package com.example.exercise3

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLeft.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentLeft : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var checkbox1 = false
    private var checkbox2 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_left, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val some_view: View = view.findViewById(R.id.frameLayout)
        val viewButton1 = some_view.findViewById<Button>(R.id.buttonLeft)
        viewButton1.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragmentLeft_to_fragmentCenter)
        }

        registerForContextMenu(requireActivity().findViewById(R.id.buttonLeft2))
        registerForContextMenu(requireActivity().findViewById(R.id.textView))
        registerForContextMenu(requireActivity().findViewById(R.id.buttonLeft3))
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        if(v.id == R.id.buttonLeft2)
        {
            requireActivity().menuInflater.inflate(R.menu.context_menu_button1, menu)
        }
        else if(v.id == R.id.textView)
        {
            requireActivity().menuInflater.inflate(R.menu.context_menu_textview, menu)
        }
        else if(v.id == R.id.buttonLeft3)
        {
            requireActivity().menuInflater.inflate(R.menu.context_menu_button2, menu)
            menu.findItem(R.id.fontStylePos1).isChecked = checkbox1
            menu.findItem(R.id.fontStylePos2).isChecked = checkbox2
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val contextMenu1 = requireActivity().findViewById<TextView>(R.id.buttonLeft2)
        val contextMenu2 = requireActivity().findViewById<TextView>(R.id.textView)
        val contextMenu3 = requireActivity().findViewById<TextView>(R.id.buttonLeft3)
        val fontI = Typeface.defaultFromStyle(Typeface.ITALIC)
        val fontB = Typeface.defaultFromStyle(Typeface.BOLD)
        val fontIB = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
        val fontN = Typeface.defaultFromStyle(Typeface.NORMAL)

        when (item.itemId){
            R.id.colorPos1 -> {
                contextMenu1.setBackgroundColor(requireActivity().resources.getColor(R.color.red))
//                requireActivity().theme.applyStyle(R.style.contextColorRed, true)
            }
            R.id.colorPos2 -> {
                contextMenu1.setBackgroundColor(requireActivity().resources.getColor(R.color.green))
//                requireActivity().theme.applyStyle(R.style.contextColorGreen, true)
            }
            R.id.fontSizePos1 -> {
                contextMenu2.textSize = 30.0F
//                requireActivity().theme.applyStyle(R.style.contextFontSize30, true)
            }
            R.id.fontSizePos2 -> {
                contextMenu2.textSize = 20.0F
//                requireActivity().theme.applyStyle(R.style.contextFontSize20, true)
            }
            R.id.fontStylePos1 -> {
                if (item.isChecked) {
                    item.isChecked = false
                    checkbox1 = false
                    if (contextMenu3.typeface.isItalic) {
                        contextMenu3.typeface = fontI
                    }
                    else {
                        contextMenu3.typeface = fontN
                    }
                }
                else {
                    item.isChecked = true
                    checkbox1 = true
                    if (contextMenu3.typeface.isItalic) {
                        contextMenu3.typeface = fontIB
                    }
                    else {
                        contextMenu3.typeface = fontB
                    }
//                    requireActivity().theme.applyStyle(R.style.contextFontStyle1, true)
                }
            }
            R.id.fontStylePos2 -> {
                if (item.isChecked) {
                    item.isChecked = false
                    checkbox2 = false
                    if (contextMenu3.typeface.isBold) {
                        contextMenu3.typeface = fontB
                    }
                    else {
                        contextMenu3.typeface = fontN
                    }

                }
                else {
                    item.isChecked = true
                    checkbox2 = true
                    if (contextMenu3.typeface.isBold) {
                        contextMenu3.typeface = fontIB
                    }
                    else {
                        contextMenu3.typeface = fontI
                    }
//                    requireActivity().theme.applyStyle(R.style.contextFontStyle2, true)
                }
            }


            else -> {
                super.onContextItemSelected(item)
            }
        }
        return true

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentLeft.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentLeft().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
