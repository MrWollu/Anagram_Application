package com.michaelw.anagram_application

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
//import sun.jvm.hotspot.utilities.IntArray


class MainActivity : AppCompatActivity() {

    @BindView(R.id.input)
    var input: EditText? = null
    @BindView(R.id.validate)
    var validate: Button? = null
    @BindView(R.id.result)
    var result: TextView? = null
    private var waitDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this);

        // we disable validate button because words are not loaded from assets
        validate?.setEnabled(Anagram.isLoaded());

        // we need to load words from assets
        loadWords();
    }

    private fun loadWords() {
        Thread(Runnable {
            Anagram.loadWords(this@MainActivity)
            runOnUiThread { validate!!.isEnabled = Anagram.isLoaded() }
        }).start()
    }

    private fun showWaitDialog() {
        waitDialog = AlertDialog.Builder(this).setMessage("Wait").create()
    }
}
