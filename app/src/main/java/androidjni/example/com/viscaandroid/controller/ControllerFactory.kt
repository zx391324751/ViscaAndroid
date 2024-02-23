package androidjni.example.com.viscaandroid.controller

import androidjni.example.com.viscaandroid.controller.visca.ViscaTCPController
import androidjni.example.com.viscaandroid.controller.visca.ViscaUDPController

/**
 * 2024/3/11
 * created by zhangxu
 */
class ControllerFactory : IControllerFactory {
    override fun createController(config: BaseConfig): ICameraController? {
        when (config.protocol) {
            BaseConfig.PROTOCOL_VISCA_IP_TCP -> {
                return ViscaTCPController()
            }
            BaseConfig.PROTOCOL_VISCA_IP_UDP -> {
                return ViscaUDPController()
            }
        }
        return null

    }

}