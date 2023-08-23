package net.iessochoa.joseantoniolopez.intents_permisos

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import net.iessochoa.joseantoniolopez.intents_permisos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnWeb.setOnClickListener() {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gva.es/"))
            )

        }
        binding.btnTelefono.setOnClickListener() {
            startActivity(
                Intent(Intent.ACTION_DIAL, Uri.parse("tel:966912260"))
            )
        }

        binding.btnFoto.setOnClickListener {
            startActivity(
                Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            )
        }

        binding.btnCorreo.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "asunto")
                    putExtra(Intent.EXTRA_TEXT, "texto del correo")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("pepe@edu.gva.es"))
                })
        }
        binding.btnMaps.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "geo:38.278855,-0.716400?22"
                    )
                )
            )
        }
        binding.btnPermisos.setOnClickListener {
            startActivity(
                Intent(this,PermisosActivity::class.java)
            )
        }
    }

}