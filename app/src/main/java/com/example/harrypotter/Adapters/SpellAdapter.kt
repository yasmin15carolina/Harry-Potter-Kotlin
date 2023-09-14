package com.example.harrypotter.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.harrypotter.Entities.SpellEntity
import com.example.harrypotter.R

class SpellAdapter(context: Context, spells: List<SpellEntity>) :
    ArrayAdapter<SpellEntity>(context, R.layout.spell_list_item, spells) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView =  convertView;

        if(itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.spell_list_item,parent,false)
        }

        val spell = getItem(position);

        val spellNameText = itemView?.findViewById<TextView>(R.id.spell_name_txt)
        spellNameText?.text ="Name: ${spell?.name}";

        val spellDescriptionText = itemView?.findViewById<TextView>(R.id.spell_description_txt)
        spellDescriptionText?.text ="Description: ${spell?.description}";
        return itemView!!
    }
    fun updateData(newSpells: List<SpellEntity>) {
        clear()
        addAll(newSpells)
        notifyDataSetChanged()
    }
}