package androidjni.example.com.viscaandroid.controller.visca

import androidjni.example.com.viscaandroid.controller.BaseConfig

/**
 * 2024/3/11
 * created by zhangxu
 */
class ViscaIPConfig(protocol: String, cameraID: Int) : BaseConfig(protocol, cameraID) {

    var ipAddress = ""
    var port = -1

}