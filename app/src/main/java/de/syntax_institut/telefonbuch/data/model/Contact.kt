package de.syntax_institut.telefonbuch.data.model

import de.syntax_institut.telefonbuch.R

/**
 * Diese Klasse stellt einen Listeneintrag dar
 */
data class Contact(var name: String, var number: String, val imageResource: Int = R.drawable.avatar)
