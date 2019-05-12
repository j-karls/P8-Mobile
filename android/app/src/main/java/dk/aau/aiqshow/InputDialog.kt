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

class InputDialog : DialogFragment(),AdapterView.OnItemSelectedListener,SuperFragment.InputListener {

    interface DialogListener {
        fun onCancel()
        fun onSend(text: String)
    }

    private val _options: Array<String> = arrayOf("TimeInterval", "Time", "Value", "Status", "Alert")
    private val _fragId: Array<Fragment> = arrayOf(TimeIntervalFragment(),TimeFragment())
    private var _context: Context? = null
    private val TAG: String = "DEBUG_DIALOG"

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        _context = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.input_dialog,container,false)

        val arrayAdapter = ArrayAdapter(_context!!, android.R.layout.simple_spinner_item, _options)
        val spinner = view.findViewById<Spinner>(R.id.test_spinner)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.onItemSelectedListener = this
        spinner.adapter = arrayAdapter
        val cButton = view.findViewById<Button>(R.id.cancelButton)
        val sButton = view.findViewById<Button>(R.id.sendButton)
        cButton.setOnClickListener { v:View -> dialog.cancel() }
        sButton.setOnClickListener { v:View -> send() }
        return view
    }

    fun send() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.Fragment,_fragId[position])
            .commit()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        throw Exception("_option array is empty")
    }

    override fun onItemChange(text: String) {
        Toast.makeText(_context,text,Toast.LENGTH_SHORT).show()
    }

}