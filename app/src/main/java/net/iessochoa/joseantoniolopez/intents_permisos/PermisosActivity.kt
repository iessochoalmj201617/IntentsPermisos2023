package net.iessochoa.joseantoniolopez.intents_permisos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import com.google.android.material.snackbar.Snackbar
import net.iessochoa.joseantoniolopez.intents_permisos.databinding.ActivityMainBinding
import net.iessochoa.joseantoniolopez.intents_permisos.databinding.ActivityPermisosBinding

class PermisosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPermisosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPermisosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBorrarLlamadas.setOnClickListener { borrarLlamada() }
    }
    fun borrarLlamada() {
        contentResolver.delete(
            CallLog.Calls.CONTENT_URI,
            "number='555555555'", null)
        Snackbar.make(binding.lytPrincipal, "Llamadas borradas del registro.",
            Snackbar.LENGTH_SHORT).show()
    }
}