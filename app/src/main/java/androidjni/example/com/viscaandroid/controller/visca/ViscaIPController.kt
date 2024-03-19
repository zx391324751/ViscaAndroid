package androidjni.example.com.viscaandroid.controller.visca

import androidjni.example.com.viscaandroid.controller.BaseConfig
import androidjni.example.com.viscaandroid.controller.ICameraController

/**
 * 2024/3/11
 * created by zhangxu
 */
abstract class ViscaIPController : ICameraController {

    protected var netConfig: ViscaIPConfig? = null

    override fun initConfig(config: BaseConfig) {
        this.netConfig = config as? ViscaIPConfig
    }

    override fun connect() {
        super.connect()
        if (netConfig == null) {
            throw RuntimeException("camera controller config is null")
        }
    }

    protected fun getTurnLeftCommand(cameraID: Int): ByteArray {
        return ViscaCommand.getTurnLeftCommandData(cameraID)
    }

    protected fun getTurnRightCommand(cameraID: Int): ByteArray {
        return ViscaCommand.getTurnRightCommandData(cameraID)
    }

    protected fun getTurnUpCommand(cameraID: Int): ByteArray {
        return ViscaCommand.getTurnUpCommandData(cameraID)
    }

    protected fun getTurnDownCommand(cameraID: Int): ByteArray {
        return ViscaCommand.getTurnDownCommandData(cameraID)
    }
}