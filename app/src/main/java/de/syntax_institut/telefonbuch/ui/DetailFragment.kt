package de.syntax_institut.telefonbuch.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
import de.syntax_institut.telefonbuch.databinding.FragmentDetailBinding
import de.syntax_institut.telefonbuch.databinding.FragmentMainBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args : DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Dataset "rüberholen"
        val mainActivity = activity as MainActivity
        val contacts = mainActivity.dataset
        // Position Parameter lesen
        val pos = args.position
        // Ausgewähltes Element des Sets zwischenspeichern
        val item = contacts[pos]

        // textViews usw. befüllen
        binding.ivEditPicture.setImageURI(item.imageUri)
        binding.tvDetailName.text = item.name
        binding.tvPhoneNumber.text = item.number




        binding.button.setOnClickListener{
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToEditFragment(pos))
        }




    }
}