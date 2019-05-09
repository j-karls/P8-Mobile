package dk.aau.aiqshow

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.test_dialog.*
import org.w3c.dom.Text

class TestDialog : DialogFragment()  {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.test_dialog, null))
                // Add action buttons
                .setPositiveButton("In",
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                    })
                .setNegativeButton("Out",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog().cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}