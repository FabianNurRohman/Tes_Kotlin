package com.dicoding.charaproseka

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvCharas: RecyclerView
    private var list = ArrayList<Chara>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvCharas = findViewById(R.id.rv_charas)
        rvCharas.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListHeroes(): ArrayList<Chara> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listHero = ArrayList<Chara>()
        for (i in dataName.indices) {
            val chara = Chara(dataName[i], dataDescription[i], dataPhoto[i])
            listHero.add(chara)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvCharas.layoutManager = LinearLayoutManager(this)
        val listCharaAdapter = ListCharaAdapter(list)
        rvCharas.adapter = listCharaAdapter

        listCharaAdapter.setOnItemClickCallback(object : ListCharaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Chara) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(chara: Chara) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("name", chara.name)
            putExtra("description", chara.description)
            putExtra("photo", chara.photo)
        }
        startActivity(intent)
    }
}