package dk.aau.aiqshow
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.content.Context
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.write_fragment.*
import kotlinx.android.synthetic.main.write_fragment.view.*


class WriteFragment : Fragment() {
    var activityCallback: WriteFragment.WriteListener? = null

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
        val view = inflater.inflate(R.layout.write_fragment, container, false)

        button1.setOnClickListener { v: View -> buttonClicked(v)}
        return view
    }


    private fun buttonClicked(view: View) {
        activityCallback?.onButtonClick(editText1.text.toString())
    }



}