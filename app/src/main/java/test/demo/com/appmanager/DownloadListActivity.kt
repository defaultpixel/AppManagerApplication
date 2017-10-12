package test.demo.com.appmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class DownloadListActivity : AppCompatActivity() {

    var str: String = "DownloadList"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_download_fragment_layout)
    }
}
