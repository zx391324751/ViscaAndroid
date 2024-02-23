package androidjni.example.com.viscaandroid.visca

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidjni.example.com.viscaandroid.R
import androidjni.example.com.viscaandroid.controller.BaseConfig
import androidjni.example.com.viscaandroid.controller.ControllerFactory
import androidjni.example.com.viscaandroid.controller.visca.ViscaIPConfig
import androidx.appcompat.app.AppCompatActivity


/**
 * 2024/2/23
 * created by zhangxu
 */
class ViscaConsoleActivity : AppCompatActivity() {

    private val config = ViscaIPConfig(BaseConfig.PROTOCOL_VISCA_IP_UDP, 0x01)
    private val viscalUDPSocket by lazy {
        ControllerFactory().createController(config)
    }
    private lateinit var btnLeft: Button
    private lateinit var btnRight: Button
    private lateinit var btnTop: Button
    private lateinit var btnBottom: Button
    private lateinit var etInputIp: EditText
    private lateinit var etInputPort: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visca_console)

        initViews()
    }

    private fun initViews() {
        btnLeft = findViewById(R.id.btnTurnLeft)
        btnRight = findViewById(R.id.btnTurnRight)
        btnTop = findViewById(R.id.btnTurnTop)
        btnBottom = findViewById(R.id.btnTurnBottom)
        btnLeft.setOnClickListener {
            viscalUDPSocket?.turnLeft()
        }
        btnRight.setOnClickListener {
            viscalUDPSocket?.turnRight()
        }
        btnTop.setOnClickListener {
            viscalUDPSocket?.turnTop()
        }
        btnBottom.setOnClickListener {
            viscalUDPSocket?.turnBottom()
        }
        findViewById<View>(R.id.btnConnect).setOnClickListener {
            config.ipAddress = etInputIp.text.toString().trim()
            config.port = etInputPort.text.toString().trim().toInt()
            viscalUDPSocket?.initConfig(config)
        }
        etInputIp = findViewById(R.id.etInputIp)
        etInputPort = findViewById(R.id.etInputPort)
    }

}