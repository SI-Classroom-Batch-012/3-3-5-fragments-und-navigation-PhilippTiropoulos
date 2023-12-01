package de.syntax_institut.telefonbuch.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import de.syntax_institut.telefonbuch.MainActivity
import de.syntax_institut.telefonbuch.R
import de.syntax_institut.telefonbuch.adapter.ItemAdapter
import de.syntax_institut.telefonbuch.databinding.FragmentEditBinding

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private val args : EditFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.button2.setBackgroundColor(R.color.buttonDisabledColor)



        val mainActivity = activity as MainActivity
        val contacts = mainActivity.dataset
        val pos = args.position
        val item = contacts[pos]

        binding.ivEditPicture.setImageURI(item.imageUri)
        binding.tilName.editText?.setText(item.name)
        binding.tilPhoneNumber.editText?.setText(item.number)
        binding.button2.isEnabled = false


        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // nichts
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.button2.isEnabled = true
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }

        binding.tilName.editText?.addTextChangedListener(textWatcher)
        binding.tilPhoneNumber.editText?.addTextChangedListener(textWatcher)

        binding.button2.setOnClickListener{
            val newName = binding.tilName.editText?.text.toString()
            val newNumber = binding.tilPhoneNumber.editText?.text.toString()

            //Aktualisieren
            item.name = newName
            item.number = newNumber

            findNavController().navigate(EditFragmentDirections.actionEditFragmentToMainFragment())
           // if (binding.tilName.editText.cha)
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val item = mainActivity.dataset[pos]
                val data: Intent? = result.data
                val selectedImageUri = data?.data
                val itemAdapter = ItemAdapter(mainActivity.dataset)
                if (selectedImageUri != null) {
                    itemAdapter.changeImage(item, selectedImageUri)
                    binding.ivEditPicture.setImageURI(selectedImageUri)
                }

            }
        }

        binding.ivEditPicture.setOnClickListener {
            val popup = PopupMenu(it.context, it)
            popup.menu.add("Bild ändern")
            popup.menu.add("Bild löschen")


            val itemAdapter = ItemAdapter(mainActivity.dataset)

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.title) {
                    "Bild ändern" -> {

                        val intent = Intent(Intent.ACTION_PICK)
                        intent.type = "image/*"

                        resultLauncher.launch(intent)

                    }
                    "Bild löschen" -> {
                        itemAdapter.standardImage(contacts[pos])
                        binding.ivEditPicture.setImageResource(R.drawable.avatar)
                    }
                }
                true
            }
            popup.show()
        }
    }




}