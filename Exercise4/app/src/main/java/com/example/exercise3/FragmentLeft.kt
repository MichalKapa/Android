package com.example.exercise3

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
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
    private var fontStyle: String? = null
    private var fontSize: String? = null
    private var color: String? = null
    private val fontI = Typeface.defaultFromStyle(Typeface.ITALIC)
    private val fontB = Typeface.defaultFromStyle(Typeface.BOLD)
    private val fontIB = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
    private val fontN = Typeface.defaultFromStyle(Typeface.NORMAL)

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

        val button1: Button = view.findViewById(R.id.buttonLeft2)
        val textView: TextView = view.findViewById(R.id.textView)
        val button2: Button = view.findViewById(R.id.buttonLeft3)

        val data : SharedPreferences = requireActivity().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE)
        color = data.getString("color", "none")
        fontSize = data.getString("font_size", "none")
        fontStyle = data.getString("font_style", "none")

        if (color == "red") {
            button1.setBackgroundColor(Color.RED)
        }
        else if (color == "green") {
            button1.setBackgroundColor(Color.GREEN)
        }

        if (fontSize == "30") {
            textView.textSize = 30F
        }
        else if (fontSize == "20") {
            textView.textSize = 20F
        }

        if (fontStyle == "IB") {
            button2.typeface = fontIB
        }
        else if (fontStyle == "B") {
            button2.typeface = fontB
        }
        else if (fontStyle == "I") {
            button2.typeface = fontI
        }
        else if (fontStyle == "N") {
            button2.typeface = fontN
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

            val data : SharedPreferences = requireActivity().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE)
            checkbox1 = data.getBoolean("is_checked1", false)
            checkbox2 = data.getBoolean("is_checked2", false)
//            fontStyle = data.getString("font_style", "fontN")

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


        when (item.itemId){
            R.id.colorPos1 -> {
                contextMenu1.setBackgroundColor(requireActivity().resources.getColor(R.color.red))
                color = "red"
//                requireActivity().theme.applyStyle(R.style.contextColorRed, true)
            }
            R.id.colorPos2 -> {
                contextMenu1.setBackgroundColor(requireActivity().resources.getColor(R.color.green))
                color = "green"
//                requireActivity().theme.applyStyle(R.style.contextColorGreen, true)
            }
            R.id.fontSizePos1 -> {
                contextMenu2.textSize = 30.0F
                fontSize = "30"
//                requireActivity().theme.applyStyle(R.style.contextFontSize30, true)
            }
            R.id.fontSizePos2 -> {
                contextMenu2.textSize = 20.0F
                fontSize = "20"
//                requireActivity().theme.applyStyle(R.style.contextFontSize20, true)
            }
            R.id.fontStylePos1 -> {
                if (item.isChecked) {
                    item.isChecked = false
                    checkbox1 = false
                    if (contextMenu3.typeface.isItalic) {
                        contextMenu3.typeface = fontI
                        fontStyle = "I"
                    }
                    else {
                        contextMenu3.typeface = fontN
                        fontStyle = "N"
                    }
                }
                else {
                    item.isChecked = true
                    checkbox1 = true
                    if (contextMenu3.typeface.isItalic) {
                        contextMenu3.typeface = fontIB
                        fontStyle = "IB"
                    }
                    else {
                        contextMenu3.typeface = fontB
                        fontStyle = "B"
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
                        fontStyle = "B"
                    }
                    else {
                        contextMenu3.typeface = fontN
                        fontStyle = "N"
                    }

                }
                else {
                    item.isChecked = true
                    checkbox2 = true
                    if (contextMenu3.typeface.isBold) {
                        contextMenu3.typeface = fontIB
                        fontStyle = "IB"
                    }
                    else {
                        contextMenu3.typeface = fontI
                        fontStyle = "I"
                    }
//                    requireActivity().theme.applyStyle(R.style.contextFontStyle2, true)
                }
            }


            else -> {
                super.onContextItemSelected(item)
            }
        }
        val data: SharedPreferences = requireActivity().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE)
        val editor :SharedPreferences.Editor = data.edit()
        editor.putBoolean("is_checked1", checkbox1)
        editor.putBoolean("is_checked2", checkbox2)
        editor.putString("color", color)
        editor.putString("font_size", fontSize)
        editor.putString("font_style", fontStyle)

        editor.apply()

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
