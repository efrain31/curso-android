package com.example.overflow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class menuActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_order -> {
                val intent = Intent(this, orderActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_products -> {
                val intent = Intent(this, productActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_my_orders -> {
                val intent = Intent(this, comprasActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_about_us -> {
                val intent = Intent(this, nosotrosActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_logout -> {
                logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun logout() {
        // Aquí limpias las SharedPreferences y cierras la sesión
        val sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        // Volver al LoginActivity
        val intent = Intent(this, MainActivity::class.java)
        // Limpiar el stack de actividades para no poder volver al presionar atrás
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}