package net.iessochoa.joseantoniolopez.intents_permisos

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import net.iessochoa.joseantoniolopez.intents_permisos.databinding.ActivityMainBinding
import net.iessochoa.joseantoniolopez.intents_permisos.databinding.ActivityPermisosBinding
import android.Manifest
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class PermisosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPermisosBinding
    //3-Solicitaremos los permisos si no los tenemos
    private val solicitudPermisosLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission has been granted. Start camera preview Activity.
                borrarLlamada()
            } else {
                // Permission request was denied.
                Snackbar.make(binding.lytPrincipal, "Es necesario el permiso de \"administrar llamadas\" para  borrar llamadas del registro",
                    Snackbar.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPermisosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBorrarLlamadas.setOnClickListener {
            borrarLlamada()
            //2-Comprobamos los permidos antes de borrar la llamada
            /*if(permisosAceptados())
                borrarLlamada()*/
            //4-Si no tenemos los permisos, solicitamos los permisos
           /* if(permisosAceptados())
                borrarLlamada()
            else
                solicitudPermisosLauncher.launch(Manifest.permission.WRITE_CALL_LOG)*/
            //5-Solicitud educada de los permisos
            /*when{
                permisosAceptados()-> borrarLlamada()
                //si solicita por segunda vez los permisos, daremos una explicación más
                //detallada
                shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CALL_LOG)->{
                   explicarPermisos()
                }
                //es la primera vez que se solicitan los permisos
                else-> solicitudPermisosLauncher.launch(Manifest.permission.WRITE_CALL_LOG)
            }*/
        }
    }
    fun borrarLlamada() {
        contentResolver.delete(
            CallLog.Calls.CONTENT_URI,
            "number='555555555'", null)
        Snackbar.make(binding.lytPrincipal, "Llamadas borradas del registro.",
            Snackbar.LENGTH_SHORT).show()
    }
    //1-Permite comprobar si tenemos los permisos asignados
    fun permisosAceptados()=
        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) == PackageManager.PERMISSION_GRANTED

    //Solicitud educada de los permisos
    fun explicarPermisos(){
        AlertDialog.Builder(this)
            .setTitle(android.R.string.dialog_alert_title)
            //recuerda: todo el texto en string.xml
            .setMessage("Es necesario el permiso de \"administrar llamadas\" para  borrar llamadas del registro. Tiene que aceptar este permiso para esta funcionalidad")
            //acción si pulsa si
            .setPositiveButton(android.R.string.ok) { v, _ ->
                solicitudPermisosLauncher.launch(Manifest.permission.WRITE_CALL_LOG)
                //cerramos el dialogo
                v.dismiss()
            }
            //accion si pulsa no
            .setNegativeButton(android.R.string.cancel) { v, _ ->
                Snackbar.make(binding.lytPrincipal, "No se puede realizar la tarea sin los permisos",
                    Snackbar.LENGTH_SHORT).show()

                v.dismiss() }
            .setCancelable(false)
            .create()
            .show()
    }
}