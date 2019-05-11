package dk.aau.aiqshow
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.time_fragment.*


open class SuperFragment : Fragment() {
    protected var callback: InputListener? = null

    interface InputListener {
        fun onButtonClick(text: String)
    }

    /*override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            callback = context as InputListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context?.toString()
                    + " must implement InputListener")
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            callback = parentFragment as InputListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context?.toString() + " " + e.toString() + " "
                    + " must implement InputListener")
        }
    }
}

class TimeFragment : SuperFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.time_fragment, container, false)

        val b = view.findViewById<Button>(R.id.testButton)
        b.text = "Time"
        b.setOnClickListener { v: View -> buttonClicked(v)}
        return view
    }

    private fun buttonClicked(view: View) {
        callback?.onButtonClick(testButton.text.toString())
    }

}

class TimeIntervalFragment : SuperFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.time_fragment, container, false)

        val b = view.findViewById<Button>(R.id.testButton)
        b.text = "TimeInterval"
        b.setOnClickListener { v: View -> buttonClicked(v)}
        return view
    }


    private fun buttonClicked(view: View) {
        callback?.onButtonClick(testButton.text.toString())
    }

}