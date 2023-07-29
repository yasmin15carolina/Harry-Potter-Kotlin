import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.harrypotter.CharacterEntity
import com.example.harrypotter.R


class CharacterAdapter(context: Context, characters: List<CharacterEntity>) :
    ArrayAdapter<CharacterEntity>(context, R.layout.activity_list_item, characters) {

    // O método getView é chamado para cada item da lista, definindo como o item deve ser exibido.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.activity_list_item, parent, false)
        }

        val character = getItem(position)

        // Aqui você pode definir os atributos do objeto que deseja exibir na lista.
        // Neste exemplo, estamos exibindo apenas o nome do personagem (name).
        val nameTextView = itemView?.findViewById<TextView>(R.id.nameTextView)
        nameTextView?.text = character?.name

        val photoImageView = itemView?.findViewById<ImageView>(R.id.photoImageView)

        val imageUrl = character?.img// Substitua a URL real da imagem aqui.
        if (photoImageView != null) {
            Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)) // Opcional: para armazenar a imagem em cache.
                .into(photoImageView)
        }

        return itemView!!
    }
    fun updateData(newCharacters: List<CharacterEntity>) {
        clear()
        addAll(newCharacters)
        notifyDataSetChanged()
    }
}
