package cl.grupo2.copeteandroid.tragos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.grupo2.copeteandroid.R
import cl.grupo2.copeteandroid.tragos.data.DataSource
import cl.grupo2.copeteandroid.tragos.data.model.Drink
import cl.grupo2.copeteandroid.tragos.domain.RepoImpl
import cl.grupo2.copeteandroid.tragos.ui.viewmodel.MainAdapter
import cl.grupo2.copeteandroid.tragos.ui.viewmodel.MainViewModel
import cl.grupo2.copeteandroid.tragos.ui.viewmodel.VMFactory
import cl.grupo2.copeteandroid.tragos.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), MainAdapter.OnTragoClickListener {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource()))}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer {result ->
            when (result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rvTragos.adapter = MainAdapter(requireContext(),result.data, this)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),"No se pudieron traer los datos ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupSearchView (){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.setTrago(p0!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    override fun onTragoClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_mainFragment_to_tragosDetalleFragment, bundle)
    }

    private fun setupRecyclerView() {
        rvTragos.layoutManager = LinearLayoutManager(requireContext())
        rvTragos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }



}