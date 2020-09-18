package cl.grupo2.copeteandroid.autentificacion.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.alert(message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
