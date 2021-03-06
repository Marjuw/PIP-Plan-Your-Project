package com.example.pip


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import com.example.REST_API_Client.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [FiveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FiveFragment : Fragment() {

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
        val v: View= inflater.inflate(com.example.pip.R.layout.activity_profile, container, false)


        //beteiligteprojekte_eigen => divider
        var beteiligteProjekteBanner: TextView = v.findViewById(R.id.beteiligteprojekte_eigen)
        beteiligteProjekteBanner.visibility = ImageView.GONE

        var teiler: View = v.findViewById(R.id.divider)
        teiler.visibility = ImageView.GONE

        // Einen GET auf einen spezifischen Nutzer ausführen
        val getSpecificUserString = okHttpGet(ressource_users ,main.loggedinUserID) // hole den JSON String vom spezifischen User aus der Ressource Users
        val loggedinUser = gson.fromJson(getSpecificUserString, One_User::class.java) // speichere den User in eine Variable vom Typ One_User

        //Ab hier alle Profildaten den Mustereinträgen überschreiben
        val nicknameProfil :TextView =  v.findViewById(R.id.nickname_eigen_feld)   // nickname Textfeld identifizieren
        nicknameProfil.setText(loggedinUser.nickname)  //Mustereintrag von Nickname zu dem Eintrag von Nickname der Get Anfrage überschreiben

        val nameProfil :TextView =  v.findViewById(R.id.name_eigen_feld)
        nameProfil.setText(loggedinUser.vorname)

        val nachnameProfil :TextView =  v.findViewById(R.id.nachname_eigen_feld)
        nachnameProfil.setText(loggedinUser.name)

        val beschreibungProfil :TextView =  v.findViewById(R.id.beschreibung_eigen_feld)
        beschreibungProfil.setText(loggedinUser.beschreibung)

        val kommunikationProfil :TextView =  v.findViewById(R.id.kommunikation_eigen_feld)
        kommunikationProfil.setText(loggedinUser.kommunikation)


        val interessenString = TagID_inStringList(loggedinUser.interessen)
        val faehigkeitenString = TagID_inStringList(loggedinUser.faehigkeiten)


        val interessenProfil :TextView =  v.findViewById(R.id.interessen_eigen_feld)
        interessenProfil.setText(interessenString.joinToString(separator = ", "))

        val faehigkeitenProfil :TextView =  v.findViewById(R.id.fähigkeiten_eigen_feld)
        faehigkeitenProfil.setText(faehigkeitenString.joinToString(separator = ", "))


        //Beim klicken auf Edit wird das Layout gewechselt, damit man Text editieren kann
        var edit:ImageView= v.findViewById(com.example.pip.R.id.editicon)
        edit.setOnClickListener(  View.OnClickListener {

                var profileEditFragment: ProfileEditFragment = ProfileEditFragment()
                var transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(this.id, profileEditFragment)
                transaction.commit()


        })


        var bundle=getArguments().toString();  //Ab hier werden gesendete Nachrichten von anderen Fragmenten entgen genommen
        var v1: String= getArguments()?.getString("key").toString()

        var x : TextView= v.findViewById(com.example.pip.R.id.name_eigen_feld)

        if (v1 != "null") {
            x.setText(v1)
        }


        return v


    }


    fun TagID_inStringList(numList: List<Int>? = null): MutableList<String>{
        val stringListe = mutableListOf<String>()
        numList?.forEach { x ->
            main.tagListe?.forEach { y -> if(y.id == x) stringListe.add(y.bezeichnung) }
        }
        return stringListe
    }

    fun ProjekteDynamischErzeugen(v: View,
                                  scrollbar: LinearLayout,
                                  Projektliste: MutableList<One_Project>){

        for(projekt in Projektliste) {

            val projektListenAnforderungenString = TagID_inStringList(projekt.anforderung)
            val projektListenKategorieString = TagID_inStringList(projekt.kategorie)
            val projektListenZweckString = TagID_inStringList(projekt.zweck)

            //eine Project-Ansicht erzeugen
            var projectsview: View= layoutInflater.inflate(com.example.pip.R.layout.projects,null, false)

            var projektEintragName: TextView = projectsview.findViewById(R.id.projektname)
            projektEintragName.setText(projekt.name)

            var projektEintragOrt: TextView = projectsview.findViewById(R.id.projektort)
            projektEintragOrt.setText(projekt.ausfuehrungsort)

            var projektEintragSkills: TextView = projectsview.findViewById(R.id.projektskills)
            projektEintragSkills.setText(projektListenAnforderungenString.joinToString(separator = ", "))

            var projektEintragKategorie: TextView = projectsview.findViewById(R.id.projektkategorie)
            projektEintragKategorie.setText(projektListenKategorieString.joinToString(separator = ", "))



            // wenn eingeloggter Nutzer Projektleiter ist: zeige Admin Logo in der Projektliste
            if( main.loggedinUserID == projekt.projektleiter)
            {
                var projektEintragAdmin: ImageView = projectsview.findViewById(R.id.projectadmin)
                projektEintragAdmin.visibility = ImageView.VISIBLE
            }


            // der ID Wert des Projekts, den du benötigst wenn du auf den Pfeil klickst um das spezifische Projekt dann zu öffnen
            var openProjectButton : ImageView = projectsview.findViewById(R.id.openproject)
            // hier wird die Detailansicht für ein Projekt dynamisch erzeugt
            openProjectButton.setOnClickListener(View.OnClickListener {

                main.detailProjektID = projekt._id.toString()

                var projektDetailAnsicht: ProjektDetailAnsichtFragment = ProjektDetailAnsichtFragment()
                var transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(this.id, projektDetailAnsicht)
                transaction.commit()
            })

            scrollbar.addView(projectsview)  //Projects erzeugen in diesem Bereich der LayoutListe
        }
    }



    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FiveFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FiveFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }

}