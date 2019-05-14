package dk.aau.aiqshow

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import dk.aau.iaqlibrary.MyBluetoothService
import java.time.LocalDateTime


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

class TimeIntervalFragment : SuperFragment() {

    private lateinit var _gas : Spinner
    private lateinit var time1: TimePicker
    private lateinit var time2: TimePicker

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.timeinterval_fragment, container, false)

        val gasArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,_gasArray)
        gasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _gas = view.findViewById(R.id.TimeIntervalFragmentGas)
        _gas.adapter = gasArrayAdapter

        view.findViewById<Button>(R.id.TimeIntervalFragmentPosBut).setOnClickListener { send() }
        view.findViewById<Button>(R.id.TimeIntervalFragmentPosBut).setOnClickListener { callback.onEnd() }

        return view
    }


    private fun send() {
        val message: String = MyBluetoothService.getTimeInterval(_gas.selectedItem.toString(), LocalDateTime.now().minusMinutes(5L),
            LocalDateTime.now())
        activityCallback.onSend(message)
        callback.onEnd()
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

class ValueFragment : SuperFragment() {
    private lateinit var _gas : Spinner
    private lateinit var _comp : Spinner
    private lateinit var _value : EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.value_fragment, container, false)

        _value = view.findViewById(R.id.ValueFragmentValue)

        val gasArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,_gasArray)
        gasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _gas = view.findViewById(R.id.ValueFragmentGas)
        _gas.adapter = gasArrayAdapter

        val comparatorArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,_compArray)
        comparatorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _comp = view.findViewById(R.id.ValueFragmentComparator)
        _comp.adapter = comparatorArrayAdapter


        view.findViewById<Button>(R.id.ValueFragmentPosBut).setOnClickListener { send() }
        view.findViewById<Button>(R.id.ValueFragmentNegBut).setOnClickListener { callback.onEnd() }

        return view
    }

    private fun send() {
        val message: String = MyBluetoothService.getValue(
            _gas.selectedItem.toString(),
            _comp.selectedItem.toString(),
            _value.text.toString().toFloat())

        activityCallback.onSend(message)
        callback.onEnd()
    }

}

class AlertFragment : SuperFragment() {
    private lateinit var _gas : Spinner
    private lateinit var _alert : Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.alert_fragment, container, false)

        val gasArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,_gasArray)
        gasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _gas = view.findViewById(R.id.AlertFragmentGas)
        _gas.adapter = gasArrayAdapter

        val alertArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,_alertArray)
        alertArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _alert = view.findViewById(R.id.AlertFragmentAlerts)
        _alert.adapter = alertArrayAdapter


        view.findViewById<Button>(R.id.AlertFragmentPosBut).setOnClickListener { send() }
        view.findViewById<Button>(R.id.AlertFragmentNegBut).setOnClickListener { callback.onEnd() }

        return view
    }

    private fun send() {
        val message: String = MyBluetoothService.getAlerts(
            _gas.selectedItem.toString(),
            _alert.selectedItem.toString())

        activityCallback.onSend(message)
        callback.onEnd()
    }

}

class StatusFragment : SuperFragment() {
    private lateinit var _gas : Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.status_fragment, container, false)

        val gasArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,_gasArray)
        gasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _gas = view.findViewById(R.id.StatusFragmentGas)
        _gas.adapter = gasArrayAdapter

        view.findViewById<Button>(R.id.StatusFragmentPosBut).setOnClickListener { send() }
        view.findViewById<Button>(R.id.StatusFragmentNegBut).setOnClickListener { callback.onEnd() }

        return view
    }

    private fun send() {
        val message: String = MyBluetoothService.getStatus(_gas.selectedItem.toString())

        activityCallback.onSend(message)
        callback.onEnd()
    }

}