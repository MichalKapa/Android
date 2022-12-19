package com.example.exercise5.ui.gallery

import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise5.DataItem
import com.example.exercise5.DataRepo
import com.example.exercise5.R
import com.example.exercise5.databinding.FragmentGalleryBinding
import com.example.exercise5.databinding.ListRowBinding

class GalleryFragment : Fragment() {


    private var _binding: FragmentGalleryBinding? = null
    lateinit var list1 : ListView
    lateinit var txtName: TextView
    var data = DataRepo.getinstance().item_text_list
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val onItemClickListener = AdapterView.OnItemClickListener { parentview,
                                                                    itemView,
                                                                    position, id ->
            var txt = "Clicked " + (position) + " : Checked"
            val check_list: SparseBooleanArray = list1.checkedItemPositions
            for (i in 0 until check_list.size()) {
                if (check_list.valueAt(i)) {
                    val indeks = check_list.keyAt(i)
                    txt += " " + (indeks).toString()
                }
            }
            Toast.makeText(requireContext(), txt, Toast.LENGTH_SHORT).show()
        }
            list1 = binding.listview1
        list1.onItemClickListener = onItemClickListener

        list1 = binding.listview1
        val adapter1 = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_multiple_choice,
            data)
        list1.adapter = adapter1


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
