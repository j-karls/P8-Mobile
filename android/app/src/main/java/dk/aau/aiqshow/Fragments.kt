package dk.aau.aiqshow

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.lang.Exception
import dk.aau.iaqlibrary.BluetoothService.Companion as get
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


open class SuperFragment : Fragment() {

    protected val gasArray = arrayOf("CO","CO2","Humidity","Temperature")
    protected val compArray = arrayOf("Equal to","More than","Less than")
    protected val compValueArray = arrayOf("=",">","<")
    protected lateinit var callback: DialogListener
    private lateinit var activityCallback: InputListener

    interface InputListener {
        fun onGET(text: String)
        fun onSET(text: String)
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
                    + " must implement InputListener")
        }

        try {
            callback = parentFragment as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(parentFragment?.toString()
                    + " must implement DialogListener")
        }
    }

    fun sendGET(message : String) {
        activityCallback.onGET(message)
        callback.onEnd()
    }

    fun sendSET(message : String) {
        activityCallback.onSET(message)
        callback.onEnd()
    }

}

class TimeIntervalFragment : SuperFragment() {

    private lateinit var mmGas : Spinner
    private lateinit var mmTimePickerFrom : TimePickerDialog
    private lateinit var mmTimePickerTo : TimePickerDialog
    private val mmDateListenerFrom = DateTimePicker()
    private val mmDateListenerTo = DateTimePicker()
    private val mmTimeNow = LocalDateTime.now()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.timeinterval_fragment, container, false)

        val gasArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,gasArray)
        gasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mmGas = view.findViewById(R.id.TimeIntervalFragmentGas)
        mmGas.adapter = gasArrayAdapter

        val datePickerFrom = DatePickerDialog(context!!,R.style.DialogTheme)
        datePickerFrom.setOnDateSetListener(mmDateListenerFrom)
        mmTimePickerFrom = TimePickerDialog(context!!,R.style.DialogTheme,mmDateListenerFrom,
            mmTimeNow.hour,mmTimeNow.minute,true)
        mmDateListenerFrom.setTimePicker(mmTimePickerFrom)

        val datePickerTo = DatePickerDialog(context!!,R.style.DialogTheme)
        datePickerTo.setOnDateSetListener(mmDateListenerTo)
        mmTimePickerTo = TimePickerDialog(context!!,R.style.DialogTheme,mmDateListenerTo,
            mmTimeNow.hour,mmTimeNow.minute,true)
        mmDateListenerTo.setTimePicker(mmTimePickerTo)


        view.findViewById<Button>(R.id.TimeIntervalFragmentFrom).setOnClickListener { datePickerFrom.show() }
        view.findViewById<Button>(R.id.TimeIntervalFragmentTo).setOnClickListener { datePickerTo.show() }
        view.findViewById<Button>(R.id.TimeIntervalFragmentPosBut).setOnClickListener { send() }
        view.findViewById<Button>(R.id.TimeIntervalFragmentNegBut).setOnClickListener { callback.onEnd() }

        return view
    }

    private fun send() {
        val message: String = get.getTimeInterval(mmGas.selectedItem.toString(), mmDateListenerFrom.getTime(),
            mmDateListenerTo.getTime())
        sendGET(message)
    }
}

class TimeFragment : SuperFragment() {
    private lateinit var mmGas : Spinner
    private lateinit var mmComp : Spinner
    private lateinit var mmTime : TimePickerDialog
    private val mmDateListener = DateTimePicker()
    private val mmTimeNow = LocalDateTime.now()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.time_fragment, container, false)

        val gasArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,gasArray)
        gasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mmGas = view.findViewById(R.id.TimeFragmentGas)
        mmGas.adapter = gasArrayAdapter

        val comparatorArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,compArray)
        comparatorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mmComp = view.findViewById(R.id.TimeFragmentComparator)
        mmComp.adapter = comparatorArrayAdapter

        val datePicker = DatePickerDialog(context!!,R.style.DialogTheme)
        datePicker.setOnDateSetListener(mmDateListener)
        mmTime = TimePickerDialog(context!!,R.style.DialogTheme,mmDateListener,mmTimeNow.hour,mmTimeNow.minute, true)
        mmDateListener.setTimePicker(mmTime)

        view.findViewById<Button>(R.id.TimeFragmentTime).setOnClickListener { datePicker.show() }
        view.findViewById<Button>(R.id.TimeFragmentPosBut).setOnClickListener { send() }
        view.findViewById<Button>(R.id.TimeFragmentNegBut).setOnClickListener { callback.onEnd() }

        return view
    }

    private fun send() {
        val gas = mmGas.selectedItem.toString()
        val comp = compValueArray[mmComp.selectedItemPosition]
        val time = mmDateListener.getTime()

        val message: String = get.getTime(gas,comp,time)

        sendGET(message)
    }
}

class ValueFragment : SuperFragment() {
    private lateinit var mmGas : Spinner
    private lateinit var mmComp : Spinner
    private lateinit var mmValue : EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.value_fragment, container, false)

        mmValue = view.findViewById(R.id.ValueFragmentValue)

        val gasArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,gasArray)
        gasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mmGas = view.findViewById(R.id.ValueFragmentGas)
        mmGas.adapter = gasArrayAdapter

        val comparatorArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,compArray)
        comparatorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mmComp = view.findViewById(R.id.ValueFragmentComparator)
        mmComp.adapter = comparatorArrayAdapter


        view.findViewById<Button>(R.id.ValueFragmentPosBut).setOnClickListener { send() }
        view.findViewById<Button>(R.id.ValueFragmentNegBut).setOnClickListener { callback.onEnd() }

        return view
    }

    private fun send() {
        val gas = mmGas.selectedItem.toString()
        val comp = compValueArray[mmComp.selectedItemPosition]
        val value: Float = if (mmValue.text.toString().isNotEmpty()) mmValue.text.toString().toFloat() else 0F

        val message: String = get.getValue(gas,comp,value)

        sendGET(message)
    }

}

class AlertFragment : SuperFragment() {
    private lateinit var prefs : SharedPreferences
    private var alert : Boolean? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.alert_fragment, container, false)
        prefs = this.activity?.getSharedPreferences(PREFERENCES,0)!!
        alert = prefs.getBoolean("alert",false)
        val posBut = view.findViewById<Button>(R.id.AlertFragmentPosBut)
        try {
            posBut.text =
                if (alert!!)
                    "Unsubscribe"
                else
                    "Subscribe"
        } catch (e: Exception) {
            throw NullPointerException("$e: Preferences do not exists")
        }

        posBut.setOnClickListener { send() }
        view.findViewById<Button>(R.id.AlertFragmentNegBut).setOnClickListener { callback.onEnd() }

        return view
    }

    private fun send() {
        val message : String?
        try {
            message =
                if (alert!!)
                    get.unSubAlerts()
                else
                    get.subAlerts()


        } catch (e: Exception) {
            throw NullPointerException("$e: Preferences do not exists")
        }


        sendSET(message)
    }

}

class StatusFragment : SuperFragment() {
    private lateinit var mmGas : Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.status_fragment, container, false)

        val gasArrayAdapter = ArrayAdapter<String>(context!!,android.R.layout.simple_spinner_item,gasArray)
        gasArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mmGas = view.findViewById(R.id.StatusFragmentGas)
        mmGas.adapter = gasArrayAdapter

        view.findViewById<Button>(R.id.StatusFragmentPosBut).setOnClickListener { send() }
        view.findViewById<Button>(R.id.StatusFragmentNegBut).setOnClickListener { callback.onEnd() }

        return view
    }

    private fun send() {
        val message: String = get.getStatus(mmGas.selectedItem.toString())

        sendGET(message)
    }

}

class DateTimePicker : DialogFragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private var mmYear : Int = 1970
    private var mmMonth : Int = 1
    private var mmDay : Int = 1
    private var mmHour : Int = 1
    private var mmMinute : Int = 1
    private lateinit var mmTimePicker : TimePickerDialog

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        mmYear = year
        mmMonth = month
        mmDay = dayOfMonth
        mmTimePicker.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        mmHour = hourOfDay
        mmMinute = minute
    }

    fun getTime() : LocalDateTime {
        return LocalDateTime.of(LocalDate.of(mmYear,mmMonth,mmDay), LocalTime.of(mmHour,mmMinute))
    }

    fun setTimePicker(tp : TimePickerDialog) {
        mmTimePicker = tp
    }
}