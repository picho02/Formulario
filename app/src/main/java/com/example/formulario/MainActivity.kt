package com.example.formulario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.formulario.databinding.ActivityMainBinding
import java.text.FieldPosition
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val listaOpciones = resources.getStringArray(R.array.opciones)
        val formulas = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item,listaOpciones)
        with(mBinding){
            spFormulas.adapter = formulas
            spFormulas.onItemSelectedListener= object:
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
                    when(posicion){
                        0->{
                            ivFormula.setImageResource(R.drawable.voltaje)
                            tvPrimerValor.text=getString(R.string.ingresar_corriente)
                            tvSegundoValor.text=getString(R.string.ingresar_resistencia)
                            btnCalcular.setOnClickListener(){
                                if(validaCampo()){
                                    val corriente = etValor1.text.toString().toFloat()
                                    val resistencia = etValor2.text.toString().toFloat()
                                    val voltaje = corriente *  resistencia
                                    val intent = Intent(this@MainActivity,Resultado::class.java)
                                    val parametros=Bundle()
                                    parametros.putString("magnitud",getString(R.string.voltaje))
                                    parametros.putFloat("resultado",voltaje)
                                    intent.putExtras(parametros)
                                    startActivity(intent)

                                }
                            }
                        }
                        1->{
                            ivFormula.setImageResource(R.drawable.resistencia)
                            tvPrimerValor.text=getString(R.string.ingresar_voltaje)
                            tvSegundoValor.text=getString(R.string.ingresar_corriente)
                            btnCalcular.setOnClickListener(){
                                if(validaCampo()){
                                    val voltaje = etValor1.text.toString().toFloat()
                                    val corriente = etValor2.text.toString().toFloat()
                                    if (corriente > 0){
                                        val resistencia = voltaje/corriente
                                        val intent = Intent(this@MainActivity,Resultado::class.java)
                                        val parametros=Bundle()
                                        parametros.putString("magnitud",getString(R.string.resistencia))
                                        parametros.putFloat("resultado",resistencia)
                                        intent.putExtras(parametros)
                                        startActivity(intent)
                                    }else{
                                        etValor2.error = getString(R.string.error_indeterminacion,"corriente")
                                    }
                                }
                            }
                        }
                        2->{
                            ivFormula.setImageResource(R.drawable.corriente)
                            tvPrimerValor.text=getString(R.string.ingresar_voltaje)
                            tvSegundoValor.text=getString(R.string.ingresar_resistencia)
                            btnCalcular.setOnClickListener(){
                                if(validaCampo()){
                                    val voltaje = etValor1.text.toString().toFloat()
                                    val resistencia = etValor2.text.toString().toFloat()
                                    if (resistencia > 0){
                                        val corriente = voltaje/resistencia
                                        val intent = Intent(this@MainActivity,Resultado::class.java)
                                        val parametros=Bundle()
                                        parametros.putString("magnitud",getString(R.string.corriente))
                                        parametros.putFloat("resultado",corriente)
                                        intent.putExtras(parametros)
                                        startActivity(intent)
                                    }else{
                                        etValor2.error = getString(R.string.error_indeterminacion,"resistencia")
                                    }
                                }
                            }
                        }


                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        }
    }

    private fun validaCampo(): Boolean {
        if (mBinding.etValor1.text.toString() == ""){
            mBinding.etValor1.error = getString(R.string.valor_requerido)
            mBinding.etValor1.requestFocus()
        }
        if (mBinding.etValor2.text.toString() == ""){
            mBinding.etValor2.error = getString(R.string.valor_requerido)
            mBinding.etValor2.requestFocus()
        }

        return !(mBinding.etValor1.text.toString() == "" || mBinding.etValor2.text.toString() == "")
    }


}