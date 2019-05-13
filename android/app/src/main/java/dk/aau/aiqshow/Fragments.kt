package dk.aau.aiqshow
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TimePicker
import dk.aau.iaqlibrary.MyBluetoothService


open class SuperFragment : Fragment() {
    protected lateinit var callback: DialogListener
    protected lateinit var activityCallback: InputListener
    protected val _gasArray = arrayOf("CO","CO2","NO2","Humidity","Temperature")
    protected val _compArray = arrayOf("=",">","<")
    protected val _alertArray = arrayOf("predicted", "immediate")

    interface InputListener {
        fun onSend(text: String)
    }

    interface DialogListener {
        fun onEnd()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            activityCallback = context as InputListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context?.toString()
                    + " must implement DialogListener")
        }

        try {
            callback = parentFragment as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(parentFragment?.toString()
                    + " must implement InputListener")
        }
    }

}

class TimeFragment : SuperFragment() {
    private lateinit var _gas : Spinner
    private lateinit var _comp : Spinner
    private lateinit var _time : TimePicker

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.time_fragment, container, false)

        val gasArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,_gasArray)
        gasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _gas = view.findViewById(R.id.TimeFragmentGas)
        _gas.adapter = gasArrayAdapter

        val comparatorArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,_compArray)
        comparatorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _comp = view.findViewById(R.id.TimeFragmentComparator)
        _comp.adapter = comparatorArrayAdapter

        //TODO(NEED DIALOG FOR DATETIME)

        view.findViewById<Button>(R.id.TimeFragmentPosBut).setOnClickListener { send() }
        view.findViewById<Button>(R.id.TimeFragmentNegBut).setOnClickListener { callback.onEnd() }

        return view
    }

    private fun send() {
        val message: String = MyBluetoothService.getTime(_gas.selectedItem.toString(),_comp.selectedItem.toString())
        activityCallback.onSend(message)
        callback.onEnd()
    }

}

class TimeIntervalFragment : SuperFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.time_fragment, container, false)

        //val b = view.findViewById<Button>(R.id.testButton)
        //b.text = "TimeInterval"
        //b.setOnClickListener { v: View -> buttonClicked(v)}
        return view
    }


    //private fun buttonClicked(view: View) {
    //    callback?.onItemChange(testButton.text.toString())
    //}

}