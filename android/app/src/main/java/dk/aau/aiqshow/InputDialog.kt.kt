package dk.aau.aiqshow

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.lang.Exception

class `InputDialog.kt` : DialogFragment(),AdapterView.OnItemSelectedListener {

    private val _options: Array<String> = arrayOf("TimeInterval", "Time", "Value", "Status", "Alert")
    private val _fragId: Array<Fragment> = arrayOf(TimeIntervalFragment(),TimeFragment())
    private var _context: Context? = null
    private val TAG: String = "DEBUG_DIALOG"

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        _context = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.input_dialog,container,false)

        val aa = ArrayAdapter(_context!!, android.R.layout.simple_spinner_item, _options)
        val spin = v.findViewById<Spinner>(R.id.test_spinner)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.onItemSelectedListener = this
        spin.adapter = aa
        return v
    }

    //unused
    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val v = activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.input_dialog, null))
                // Add action buttons
                .setPositiveButton("Send"
                ) { dialog, id ->
                    // sign in the user ...
                }
                .setNegativeButton("Cancel"
                ) { dialog, id ->
                    getDialog().cancel()
                }

            builder.create()


        } ?: throw IllegalStateException("Activity cannot be null")

        val aa = ArrayAdapter(_context!!, android.R.layout.simple_spinner_item, _options)
        val spin = v.findViewById<Spinner>(R.id.test_spinner)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.onItemSelectedListener = this
        spin.adapter = aa
        return v
    }*/

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.Fragment,_fragId[position])
            .commit()

        Toast.makeText(_context,_options[position],Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        throw Exception("what the fuck")
    }

}