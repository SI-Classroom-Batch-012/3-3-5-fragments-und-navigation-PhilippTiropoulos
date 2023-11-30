package de.syntax_institut.telefonbuch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import de.syntax_institut.telefonbuch.MainActivity
import de.syntax_institut.telefonbuch.adapter.ItemAdapter
import de.syntax_institut.telefonbuch.data.model.Contact
import de.syntax_institut.telefonbuch.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        val contacts = mainActivity.dataset


        val itemAdapter = ItemAdapter(contacts)
        binding.recyclerView.adapter = itemAdapter

        // Button fügt einen Listeneintrag hinzu
        binding.btnAdd.setOnClickListener {
            // Hole den Inhalt aus den Textfeldern
            val name = binding.inName.text.toString()
            val number = binding.inPhoneNumber.text.toString()

            // Falls in beiden Textfeldern etwas steht
            if (name != "" && number != "") {
                val position = contacts.size // kann auch ein anderer Index <= contacts.size sein
                contacts.add(position, Contact(name, number))
                itemAdapter.notifyItemInserted(position)
                binding.inName.setText("")
                binding.inPhoneNumber.setText("")
            }
        }
    }



}