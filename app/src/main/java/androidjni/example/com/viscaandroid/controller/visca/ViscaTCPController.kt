package androidjni.example.com.viscaandroid.controller.visca

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

/**
 * 2024/3/11
 * created by zhangxu
 */
internal class ViscaTCPController : ViscaIPController() {

    private val tcpSocket: Socket by lazy {
        Socket()
    }

    override fun connect() {
        super.connect()
        tcpSocket.connect(InetSocketAddress(InetAddress.getByName(netConfig!!.ipAddress), netConfig!!.port))
    }

    override fun finish() {
        super.finish()
        tcpSocket.close()
    }
}