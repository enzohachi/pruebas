package cl.grupo2.copeteandroid.copete.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.grupo2.copeteandroid.R
import cl.grupo2.copeteandroid.copete.data.local.LocalFavoritosCopeteRepository
import cl.grupo2.copeteandroid.copete.data.network.RemoteCopeteRepository
import cl.grupo2.copeteandroid.copete.domain.AgregarAFavoritosUseCase
import cl.grupo2.copeteandroid.copete.domain.Copete
import cl.grupo2.copeteandroid.copete.domain.CopeteUseCase
import cl.grupo2.copeteandroid.copete.presentation.MainAdapter
import cl.grupo2.copeteandroid.copete.presentation.MainViewModel
import cl.grupo2.copeteandroid.copete.presentation.VMFactory
import cl.grupo2.copeteandroid.copete.presentation.Resource
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(R.layout.fragment_main), MainAdapter.OnCopeteClickListener {

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(
            CopeteUseCase(
                RemoteCopeteRepository()
            ),
            AgregarAFavoritosUseCase(
                LocalFavoritosCopeteRepository()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rvCopete.adapter = MainAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "No se descargaron los datos ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.setCopete(p0!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    override fun onCopeteClick(copete: Copete) {
        val bundle = Bundle()
        bundle.putParcelable("drink", copete)
        findNavController().navigate(R.id.action_mainFragment_to_tragosDetalleFragment, bundle)
    }

    private fun setupRecyclerView() {
        rvCopete.layoutManager = LinearLayoutManager(requireContext())
        rvCopete.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}