package de.syntax_institut.telefonbuch.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax_institut.telefonbuch.R
import de.syntax_institut.telefonbuch.data.model.Contact
import de.syntax_institut.telefonbuch.databinding.ListItemBinding
import de.syntax_institut.telefonbuch.ui.MainFragmentDirections

/**
 * Diese Klasse organisiert mithilfe der ViewHolder Klasse das Recycling
 */
class ItemAdapter(
    private var dataset: List<Contact>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * hier findet der Recyclingprozess statt
     * die vom ViewHolder bereitgestellten Parameter erhalten die Information des Listeneintrags
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.tvItemName.text = item.name
        holder.binding.tvItemNumber.text = item.number
        holder.binding.ivMainlist.setImageURI(item.imageUri)



        holder.binding.contactCard.setOnClickListener{
            holder.itemView.findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(position))
        }
    }

    fun insertContact(contact: Contact) {
        dataset += contact
        notifyItemInserted(dataset.size)
    }

    fun standardImage(contact: Contact) {
        val pos = dataset.indexOf(contact)
        dataset[pos].imageUri = Uri.parse("file:///android_asset/avatar.png")
        notifyItemChanged(pos)
    }

    fun changeImage(contact: Contact, imageUri: Uri) {
        val pos = dataset.indexOf(contact)
        dataset[pos].imageUri = imageUri
        notifyItemChanged(pos)
    }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return dataset.size
    }
}
