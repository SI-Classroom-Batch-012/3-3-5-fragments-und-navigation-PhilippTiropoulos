package de.syntax_institut.telefonbuch.data.model

import android.net.Uri

/**
 * Diese Klasse stellt einen Listeneintrag dar
 */
data class Contact(var name: String, var number: String, var imageUri: Uri = Uri.parse(""))
