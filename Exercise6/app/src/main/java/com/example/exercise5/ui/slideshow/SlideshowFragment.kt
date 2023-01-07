package com.example.exercise5.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise5.*
import com.example.exercise5.databinding.FragmentSlideshowBinding
import com.example.exercise5.databinding.ListRowBinding

class SlideshowFragment : Fragment() {

    lateinit var recView : RecyclerView
    private var _binding: FragmentSlideshowBinding? = null
    lateinit var dataRepo: MyRepository
    lateinit var adapter : MyAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataRepo = MyRepository.getinstance(requireContext())
        adapter = MyAdapter(dataRepo.getData()!!)

        parentFragmentManager.setFragmentResultListener("item_added", this) {
                requestKey, _ ->
            adapter.data = dataRepo.getData()!!
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

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

        val recView = binding.myRecView
        recView.layoutManager = LinearLayoutManager(requireContext())
        recView.adapter = adapter

        val fab = binding.fab2
        fab.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.frameLayout)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    inner class MyAdapter(var data: MutableList<DBItem>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        inner class MyViewHolder(viewBinding: ListRowBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {
            val tv1: TextView = viewBinding.lrowTv1
            val tv2: TextView = viewBinding.lrowTv2
            val img: ImageView = viewBinding.lrowImage
            val cBox: CheckBox = viewBinding.lrowCheckBox
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val viewBinding = ListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
            return MyViewHolder(viewBinding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.tv1.text = data[position].text_main
//            holder.tv2.text = data[position].text_2 + data[position].item_value
//            holder.cBox.isChecked = data[position].item_checked
            holder.itemView.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    "You clicked: " + (position + 1),
                    Toast.LENGTH_SHORT
                ).show()
            }
            holder.itemView.setOnLongClickListener {
                if (dataRepo.deleteItem(data[position])) {
                    data = dataRepo.getData()!!
                    notifyDataSetChanged()
                }
                true
            }

            holder.cBox.setOnClickListener { v ->
//                data[position].item_checked = (v as CheckBox).isChecked
                Toast.makeText(
                    requireContext(),
                    "Selected/Unselected: " + (position + 1),
                    Toast.LENGTH_SHORT
                ).show()
            }
            when (data[position].item_type) {
                false -> holder.img.setImageResource(R.drawable.ic_img1)
                true -> holder.img.setImageResource(R.drawable.ic_img2)
            }
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }
}