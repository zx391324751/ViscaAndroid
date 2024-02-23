package androidjni.example.com.viscaandroid.controller

/**
 * 2024/3/11
 * created by zhangxu
 */
open class BaseConfig(val protocol: String, val cameraID: Int) {

    companion object {
        const val PROTOCOL_VISCA_IP_TCP = "visca_over_ip_tcp"
        const val PROTOCOL_VISCA_IP_UDP = "visca_over_ip_udp"
    }
}