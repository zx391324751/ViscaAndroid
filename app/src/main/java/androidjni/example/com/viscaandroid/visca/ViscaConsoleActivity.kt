package androidjni.example.com.viscaandroid.visca

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidjni.example.com.viscaandroid.R
import androidjni.example.com.viscaandroid.controller.*
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
    private lateinit var etInputIp: EditText
    private lateinit var etInputPort: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visca_console)

        initViews()
    }

    private fun initViews() {
        findViewById<View>(R.id.btnConnect).setOnClickListener {
            config.ipAddress = etInputIp.text.toString().trim()
            config.port = etInputPort.text.toString().trim().toInt()
            viscalUDPSocket?.initConfig(config)
        }
        findViewById<ControllerView>(R.id.controllerView).let {
            it.controllerListener = object : ControllerView.ControllerListener {
                override fun onControl(director: Int, isFinishControl: Boolean) {
                    when (director) {
                        CONTROLLER_STOP -> {
                            viscalUDPSocket?.turnStop()
                        }
                        CONTROLLER_LEFT -> {
                            viscalUDPSocket?.turnLeft()
                        }
                        CONTROLLER_UP -> {
                            viscalUDPSocket?.turnUp()
                        }
                        CONTROLLER_RIGHT -> {
                            viscalUDPSocket?.turnRight()
                        }
                        CONTROLLER_DOWN -> {
                            viscalUDPSocket?.turnDown()
                        }
                        CONTROLLER_LEFT_UP -> {
                            viscalUDPSocket?.turnLeftAndUp()
                        }
                        CONTROLLER_LEFT_DOWN -> {
                            viscalUDPSocket?.turnLeftAndDown()
                        }
                        CONTROLLER_RIGHT_UP -> {
                            viscalUDPSocket?.turnRightAndUp()
                        }
                        CONTROLLER_RIGHT_DOWN -> {
                            viscalUDPSocket?.turnRightAndDown()
                        }
                    }
                }
            }
        }
        etInputIp = findViewById(R.id.etInputIp)
        etInputPort = findViewById(R.id.etInputPort)
    }

}