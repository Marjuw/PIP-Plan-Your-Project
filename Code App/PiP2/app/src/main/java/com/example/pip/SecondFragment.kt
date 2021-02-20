package com.example.pip

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.REST_API_Client.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SeecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v: View= inflater.inflate(com.example.pip.R.layout.activity_search, container, false)



        var scrollbar: LinearLayout =v.findViewById(com.example.pip.R.id.suchresultat_layout) //Layout zum hinzugügen der Suchergebnisse

        var suchenachprojectButton: Button = v.findViewById(com.example.pip.R.id.projekt_button)   //ProjektButton
        var suchenachpersonButton: Button = v.findViewById(com.example.pip.R.id.person_button)   //PersonButton

        var isProjectButtonGreen = true

        suchenachprojectButton.setOnClickListener(  View.OnClickListener {   //Suche Nach Buttons Onclick

            if (isProjectButtonGreen) {

                suchenachprojectButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_green));
                suchenachpersonButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_grey));

            }
            else {
                isProjectButtonGreen == false
                suchenachprojectButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_grey));
                suchenachpersonButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_green));

            }


        })

        suchenachpersonButton.setOnClickListener(  View.OnClickListener {   //Suche Nach Buttons Onclick

            if (suchenachpersonButton.background==getResources().getDrawable(R.drawable.rounded_corner_grey)) {

                suchenachprojectButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_green));
                suchenachpersonButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_grey));

            }

            else {

                suchenachprojectButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_grey));
                suchenachpersonButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_green));

            }

        })


        var ortinPersonButton: Button = v.findViewById(com.example.pip.R.id.inperson_button)   //ProjektButton
        var ortinOnlineButton: Button = v.findViewById(com.example.pip.R.id.online_button)   //PersonButton

        var isOrtButtonGreen = true

        ortinPersonButton.setOnClickListener(  View.OnClickListener {   //Suche Nach Buttons Onclick

            if (isProjectButtonGreen) {

                ortinPersonButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_green));
                ortinOnlineButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_grey));

            }
            else {
                isProjectButtonGreen == false
                ortinPersonButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_grey));
                ortinOnlineButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_green));

            }


        })

        ortinOnlineButton.setOnClickListener(  View.OnClickListener {   //Suche Nach Buttons Onclick

            if (ortinOnlineButton.background==getResources().getDrawable(R.drawable.rounded_corner_grey)) {

                ortinPersonButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_green));
                ortinOnlineButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_grey));

            }

            else {

                ortinPersonButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_grey));
                ortinOnlineButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_green));

            }

        })



        var showprojects: Button = v.findViewById(com.example.pip.R.id.suchen_button)   //Suchbutton
        showprojects.setOnClickListener(  View.OnClickListener {   //suchbutton onclick

            var projectsview: View= layoutInflater.inflate(com.example.pip.R.layout.projects,null, false)  //ein Project erzeugen

            scrollbar.addView(projectsview)  //Projects erzeugen in diesem Bereich der LayoutListe


            //person_button

        })

        var clearsearch: Button = v.findViewById(com.example.pip.R.id.zurücksetzen_button)   //Button Projektesuche & Einstellungen zurücksetzen lassen
        clearsearch.setOnClickListener(  View.OnClickListener {  //Button Projektesuche & Einstellungen zurücksetzen lassen On click

            scrollbar.removeAllViews()  //Suchresultate löschen

            var fähigkeiten: EditText = v.findViewById(com.example.pip.R.id.fähigkeiten_eingabe) //Hier z.b Fähigkeiten zurücksetzen
            fähigkeiten.setText("Musterfähigkeiten")



        })

    return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SeecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}