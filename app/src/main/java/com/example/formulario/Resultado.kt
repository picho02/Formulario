package com.example.formulario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.formulario.databinding.ActivityMainBinding
import com.example.formulario.databinding.ActivityResultadoBinding

class Resultado : AppCompatActivity() {
    private lateinit var mBinding: ActivityResultadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(mBinding.root) 

        val bundle = intent.extras

        val magnitud = bundle?.getString("magnitud","")
        val res = bundle?.getFloat("resultado")
        mBinding.tvResultado.text=getString(R.string.txt_resultado,magnitud,res)



    }

    fun click(view: View) {
        intent=Intent(this,MainActivity::class.java)
        startActivity(intent)


    }
}