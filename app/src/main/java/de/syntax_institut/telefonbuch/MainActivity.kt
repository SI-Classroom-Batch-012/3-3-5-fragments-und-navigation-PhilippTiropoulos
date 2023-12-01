package de.syntax_institut.telefonbuch

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import de.syntax_institut.telefonbuch.adapter.ItemAdapter
import de.syntax_institut.telefonbuch.data.Datasource
import de.syntax_institut.telefonbuch.data.model.Contact
import de.syntax_institut.telefonbuch.databinding.ActivityMainBinding

/**
 * Die Main Activity
 */
class MainActivity : AppCompatActivity() {

    var dataset: List<Contact> = emptyList()
/*    object ContactRepository {
        var contacts: MutableList<Contact> = mutableListOf()

        fun loadContacts() {
            contacts = Datasource().loadContacts()
        }
    }*/
    /**
     * Lifecycle Funktion onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Die View Binding Variable
        val binding: ActivityMainBinding =
            ActivityMainBinding.inflate(layoutInflater)

        // Content View muss geladen werden
        setContentView(binding.root)

        /*val navHost = supportFragmentManager.findFragmentById(R.id.navhost_fragment_container) as NavHostFragment
        val navController = navHost.navController
        binding.toolbar.setupWithNavController(navController)*/
        // hole die Liste mit den Kontakten


        dataset = Datasource().loadContacts(this)

       /* onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.navhostFragmentContainer.findNavController().navigateUp()
            }
        })*/

    }
}
