package com.example.suitmediatest.presentation.third_page.rv

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.UserCardBinding
import com.example.suitmediatest.mainViewModel
import com.example.suitmediatest.model.User


class UserRvAdapter(
    private val context: FragmentActivity,
    private val users: List<User>
): RecyclerView.Adapter<UserRvAdapter.UserRvViewHolder>() {

    class UserRvViewHolder (userCard: UserCardBinding) : RecyclerView.ViewHolder(userCard.root){
        val img: ImageView
        val name: TextView
        val email: TextView

        init {
            img = userCard.usercardIv
            name = userCard.usercardNameTv
            email = userCard.usercardEmailTv
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRvViewHolder {
        val binding = UserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserRvViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mainViewModel.selectedUser.postValue(users[position])
            Toast.makeText(context, "${users[position].first_name} ${users[position].last_name} Berhasil dipilih", Toast.LENGTH_SHORT).show()
            context.findNavController(R.id.navhost).popBackStack()
        }
        Glide.with(holder.img.context).load(users[position].avatar) to holder.img
        holder.name.setText("${users[position].first_name} ${users[position].last_name}")
        holder.email.setText(users[position].email)
    }
}