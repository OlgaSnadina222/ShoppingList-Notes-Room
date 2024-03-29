package com.shopinglist.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.shopinglist.R
import com.shopinglist.databinding.EditListItemDialogBinding
import com.shopinglist.databinding.NewListDialogBinding
import com.shopinglist.entities.ShopListItem

object EditListItemDialog {
    fun showDialog(context: Context, item: ShopListItem, listener: Listener){
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = EditListItemDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            edName.setText(item.name)
            edInfo.setText(item.itemInfo)
            if (item.itemType ==1) edInfo.visibility = View.GONE
            bUpdate.setOnClickListener {
                if (edName.text.toString().isNotEmpty()){
                    listener.onClick(item.copy(name = edName.text.toString(), itemInfo = edInfo.text.toString()))
                }
                dialog?.dismiss()
            }
        }
        dialog = builder.create()
        // чтобы убрать стандарстоное всплавающее окно диалога и заменить на собственное
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }

    interface Listener{
        fun onClick(item: ShopListItem)
    }
}