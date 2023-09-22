package com.alpesh1.practice_firebase_database

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SetAdapter(private val datalist: ArrayList<ModelClass>):RecyclerView.Adapter<SetAdapter.SetHolder>() {

    lateinit var context: Context

   inner class SetHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        var name1 : TextView
        var surname1 : TextView
        var imgMenu : ImageView

        init {
            name1 = itemView.findViewById(R.id.txtName)
            surname1 = itemView.findViewById(R.id.txtSurName)
            imgMenu = itemView.findViewById(R.id.imgMore)


            imgMenu.setOnClickListener { popupMenus(it) }
        }


        @SuppressLint("MissingInflatedId")
        private fun popupMenus(v:View) {

            val position = datalist[adapterPosition]

            var popupMenus = PopupMenu(context,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {

                when(it.itemId){

                    R.id.edit_item ->{

                        var v =LayoutInflater.from(context).inflate(R.layout.update_list_item,null)
                        var name2 = v.findViewById<EditText>(R.id.edtName)
                        var surname2 = v.findViewById<EditText>(R.id.edtSurname)

                        AlertDialog.Builder(context)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                dialog,_->
                                position.name = name2.text.toString()
                                position.surname = surname2.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(context, "Data Update", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()

                            }
                            .setNegativeButton("Cancel"){
                                dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    R.id.delete_item ->{
                        AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setIcon(R.drawable.baseline_delete_24)
                            .setMessage("Are you sure delete this list")
                            .setPositiveButton("Yes"){

                                dialog,_->
                                datalist.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(context, "Deleted Data", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetHolder {

        context = parent.context

        var setxml = LayoutInflater.from(parent.context).inflate(R.layout.getdata_item,parent,false)
        return SetHolder(setxml)

    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: SetHolder, position: Int) {

        var setItem = datalist[position]

        holder.name1.text = setItem.name
        holder.surname1.text = setItem.surname
    }

}