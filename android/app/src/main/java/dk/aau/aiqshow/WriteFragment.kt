package dk.aau.aiqshow
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.widget.Button
import kotlinx.android.synthetic.main.time_fragment.*



class WriteFragment : Fragment() {
    var activityCallback: WriteListener? = null

    interface WriteListener {
        fun onButtonClick(text: String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            activityCallback = context as WriteListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context?.toString()
                    + " must implement ToolbarListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.time_fragment, container, false)

        view.findViewById<Button>(R.id.testButton).setOnClickListener { v: View -> buttonClicked(v)}
        return view
    }


    private fun buttonClicked(view: View) {
        activityCallback?.onButtonClick(testButton.text.toString())
    }

}