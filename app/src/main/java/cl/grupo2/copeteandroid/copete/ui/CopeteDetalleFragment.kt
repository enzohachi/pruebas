package cl.grupo2.copeteandroid.copete.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cl.grupo2.copeteandroid.copete.domain.Copete
import cl.grupo2.copeteandroid.R
import cl.grupo2.copeteandroid.copete.data.local.LocalFavoritosCopeteRepository
import cl.grupo2.copeteandroid.copete.data.network.RemoteCopeteRepository
import cl.grupo2.copeteandroid.copete.domain.AgregarAFavoritosUseCase
import cl.grupo2.copeteandroid.copete.domain.CopeteUseCase
import cl.grupo2.copeteandroid.copete.presentation.MainViewModel
import cl.grupo2.copeteandroid.copete.presentation.VMFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_copete_detalle.*

class CopeteDetalleFragment : Fragment(R.layout.fragment_copete_detalle) {
    private lateinit var copete: Copete
    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            CopeteUseCase(
                RemoteCopeteRepository()
            ),
            AgregarAFavoritosUseCase(
                LocalFavoritosCopeteRepository()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            copete = it.getParcelable("drink")!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(copete.imagen).centerCrop().into(ivImagenItem)
        tvTituloItem.text = copete.nombre
        tvDescripcionDetalle.text = copete.descripcion
        btnFavoritosDetalle.setOnClickListener {

        }
    }


}