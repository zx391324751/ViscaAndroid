package androidjni.example.com.viscaandroid.visca

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
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
    private var isAuto = false

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
        findViewById<View>(R.id.tvResetTurn).setOnClickListener {
            viscalUDPSocket?.turnBack()
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

        initZoomView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initZoomView() {
        findViewById<View>(R.id.tvZoomTele).setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    viscalUDPSocket?.zoomTele()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    viscalUDPSocket?.zoomStop()
                }
            }
            true
        }
        findViewById<View>(R.id.tvZoomWide).setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    viscalUDPSocket?.zoomWide()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    viscalUDPSocket?.zoomStop()
                }
            }
            true
        }

        findViewById<View>(R.id.tvFocusFar).setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    viscalUDPSocket?.focusFar()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    viscalUDPSocket?.focusStop()
                }
            }
            true
        }
        findViewById<View>(R.id.tvFocusNear).setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    viscalUDPSocket?.focusNear()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    viscalUDPSocket?.focusStop()
                }
            }
            true
        }
        findViewById<View>(R.id.tvFocusMode).setOnClickListener { view ->
            isAuto = !isAuto
            viscalUDPSocket?.focusMode(isAuto)
            (view as? TextView)?.let {
                if (isAuto) {
                    it.text = "Auto Focus(Open)"
                } else {
                    it.text = "Auto Focus(Close)"
                }
            }
        }
    }

}