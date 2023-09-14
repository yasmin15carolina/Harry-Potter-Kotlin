package com.example.harrypotter.Fragments

import RetrofitClient
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.harrypotter.Adapters.SpellAdapter
import com.example.harrypotter.Entities.SpellEntity
import com.example.harrypotter.R
import com.example.harrypotter.Services.SpellsService
import com.example.harrypotter.databinding.FragmentHousesBinding
import com.example.harrypotter.databinding.FragmentSpellsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SpellsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpellsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSpellsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSpellsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        var spellListState = mutableListOf<SpellEntity>()

        val adapter = SpellAdapter(this.requireContext(),spellListState)
        binding.SpellsListView.adapter = adapter
        val progressBar = binding.progressBar
        progressBar.visibility = View.VISIBLE



        val service  = RetrofitClient.createService(SpellsService::class.java)
        val call: Call<List<SpellEntity>> =  service.listSpells()

        call.enqueue(
            object:Callback<List<SpellEntity>>{
                override fun onResponse(
                    call: Call<List<SpellEntity>>,
                    response: Response<List<SpellEntity>>
                ) {
                    var list = response.body()
                    list?.let{
                        spellListState = it.toMutableList()
                        adapter
                            .updateData(spellListState)
                    }
                    progressBar.visibility = View.GONE

                }

                override fun onFailure(call: Call<List<SpellEntity>>, t: Throwable) {
                    progressBar.visibility = View.GONE

                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SpellsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpellsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}