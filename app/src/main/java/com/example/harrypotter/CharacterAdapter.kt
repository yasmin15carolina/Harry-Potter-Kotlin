import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.harrypotter.CharacterActivity
import com.example.harrypotter.Entities.CharacterEntity
import com.example.harrypotter.R

class CharacterAdapter(context: Context, characters: List<CharacterEntity>) :
    ArrayAdapter<CharacterEntity>(context, R.layout.charcter_list_item, characters) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.charcter_list_item, parent, false)
        }

        val character = getItem(position)

        val nameTextView = itemView?.findViewById<TextView>(R.id.nameTextView)
        nameTextView?.text = character?.name

        val birthTextView = itemView?.findViewById<TextView>(R.id.birthtxt)
        birthTextView?.text = character?.birth

        val photoImageView = itemView?.findViewById<ImageView>(R.id.photoImageView)

        val imageUrl = character?.img
        if (photoImageView != null) {



            Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .error(R.drawable.baseline_camera_alt_24)
                .into(photoImageView)

            photoImageView.layoutParams.height = 300;
            photoImageView.scaleType = ImageView.ScaleType.CENTER_INSIDE

        }

        itemView?.setOnClickListener {
            onNavigateButtonClick(character)
        }

        return itemView!!
    }

    private fun onNavigateButtonClick(character: CharacterEntity?) {
        try {
            val intent = Intent(context, CharacterActivity::class.java)
            intent.putExtra("characterId", character?.id) // Passar algum ID ou dados relevantes para a próxima atividade
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e("ActivityStartError", "Error starting CharacterActivity", e)
        }
    }

    fun updateData(newCharacters: List<CharacterEntity>) {
        clear()
        addAll(newCharacters)
        notifyDataSetChanged()
    }
}
