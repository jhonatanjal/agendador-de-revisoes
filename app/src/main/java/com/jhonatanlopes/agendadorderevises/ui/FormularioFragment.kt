package com.jhonatanlopes.agendadorderevises.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.jhonatanlopes.agendadorderevises.R

class FormularioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulario, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_formulario, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
