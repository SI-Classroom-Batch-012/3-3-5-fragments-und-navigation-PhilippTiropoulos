package de.syntax_institut.telefonbuch.data

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import de.syntax_institut.telefonbuch.MainActivity
import de.syntax_institut.telefonbuch.R
import de.syntax_institut.telefonbuch.data.model.Contact
import java.io.File
import java.io.FileOutputStream

/**
 * Diese Klasse bereitet die Informationen vor
 */
class Datasource {

    /**
     * Diese Funktion liefert eine Liste von Contact Objekten zurück,
     * in denen die Namen und die Telefonnummern gespeichert sind
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun loadContacts(context: Context): MutableList<Contact> {


        //de.syntax_institut.telefonbuch
        val defaultUri = getUriForAsset(context, "avatar.png")

        val contactUris = mutableMapOf<String, Uri>().apply {
            put("Lionel Messi", getUriForAsset(context, "messii.jpeg"))
            put("Christiano Ronaldo", getUriForAsset(context, "ronaldo.jpeg"))
            put("Franz Beckenbauer", getUriForAsset(context, "becken.jpeg"))
            put("Pelé", getUriForAsset(context, "peele.jpeg"))
            put("Lothar Matthäus", getUriForAsset(context, "matthaeus.jpeg"))
        }


        return mutableListOf(
            Contact(
                "Lionel Messi",
                "+49 1595 9367862",
                 contactUris.getOrDefault("Lionel Messi", defaultUri)
            ),
            Contact(
                "Cristiano Ronaldo",
                "+49 15047 284702",
                contactUris.getOrDefault("Christiano Ronaldo", defaultUri)
            ),
            Contact(
                "Franz Beckenbauer",
                "+49 1598 7361226",
                contactUris.getOrDefault("Franz Beckenbauer", defaultUri)
            ),
            Contact("Pelé", "+49 1513 5007043", contactUris.getOrDefault("Pelé", defaultUri)),
            Contact(
                "Lothar Matthäus",
                "+49 15596 033203",
                contactUris.getOrDefault("Lothar Matthäus", defaultUri)
            ),
            Contact(
                "Paul Breitner",
                "+49 15027 785378",
                contactUris.getOrDefault("Paul Breitner", defaultUri)
            ),
            Contact(
                "Neymar",
                "+49 15574 793889",
                contactUris.getOrDefault("Neymar", defaultUri)),
            Contact(
                "Bastian Schweinsteiger",
                "+49 163 55846780",
                contactUris.getOrDefault("Bastian Schweinsteiger", defaultUri)
            ),
            Contact(
                "Luis Suárez",
                "+49 15655 478518",
                contactUris.getOrDefault("Luis Suárez", defaultUri)
            ),
            Contact(
                "Diego Maradona",
                "+49 15868 293585",
                contactUris.getOrDefault("Diego Maradona", defaultUri)
            ),
            Contact(
                "Sergio Ramos",
                "+49 1526 6618426",
                contactUris.getOrDefault("Sergio Ramos", defaultUri)
            ),
            Contact(
                "Robert Lewandowski",
                "+49 162 6334069",
                contactUris.getOrDefault("Robert Lewandowski", defaultUri)
            ),
            Contact(
                "Philipp Lahm",
                "+49 15880 654128",
                contactUris.getOrDefault("Philipp Lahm", defaultUri)
            ),
            Contact(
                "Mesut Özil",
                "+49 172 32469523",
                contactUris.getOrDefault("Mesut Özil", defaultUri)
            ),
            Contact(
                "Marco Reus",
                "+49 1521 3291920",
                contactUris.getOrDefault("Marco Reus", defaultUri)
            ),
            Contact(
                "Manuel Neuer",
                "+49 1577 5161260",
                contactUris.getOrDefault("Manuel Neuer", defaultUri)
            ),
            Contact(
                "Zinédine Zidane",
                "+49 162 7285734",
                contactUris.getOrDefault("Zinédine Zidane", defaultUri)
            ),
            Contact(
                "Thomas Müller",
                "+49 15818 559583",
                contactUris.getOrDefault("Thomas Müller", defaultUri)
            ),
            Contact(
                "Toni Kroos",
                "+49 162 6544672",
                contactUris.getOrDefault("Toni Kroos", defaultUri)
            ),
            Contact(
                "Mats Hummels",
                "+49 173 30423354",
                contactUris.getOrDefault("Mats Hummels", defaultUri)
            )
        )
    }

    private fun getUriForAsset(context: Context, fileName: String): Uri {
        val file = File(context.cacheDir, fileName)
        if (!file.exists()) {
            context.assets.open(fileName).use { asset ->
                FileOutputStream(file).use { output ->
                    asset.copyTo(output)
                }
            }
        }
        return Uri.fromFile(file)
    }
}
