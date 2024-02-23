package androidjni.example.com.viscaandroid.controller.visca

import android.util.Log
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

/**
 * 2024/3/11
 * created by zhangxu
 */
class ViscaUDPController : ViscaIPController() {

    private val socket: DatagramSocket by lazy {
        DatagramSocket()
    }

    override fun finish() {
        super.finish()
        socket.close()
    }

    override fun turnLeft() {
        super.turnLeft()
        sendViscaCommand(getTurnLeftCommand(netConfig!!.cameraID))
    }

    override fun turnRight() {
        super.turnRight()
        sendViscaCommand(getTurnRightCommand(netConfig!!.cameraID))
    }

    override fun turnTop() {
        super.turnTop()
        sendViscaCommand(getTurnUpCommand(netConfig!!.cameraID))
    }

    override fun turnBottom() {
        super.turnBottom()
        sendViscaCommand(getTurnDownCommand(netConfig!!.cameraID))
    }

    private fun sendViscaCommand(command: ByteArray) {
        if (netConfig == null) throw RuntimeException("ip地址为空")

        val ip = netConfig?.ipAddress ?: return
        val port = netConfig?.port ?: return
        Log.i("ViscaUDPSocket", "ip = ${ip}, port = ${port}")
        val address = InetAddress.getByName(ip)
        val packet = DatagramPacket(command, command.size, address, port)
        try {
            socket.send(packet)
            val receiveBuffer = ByteArray(32)
            val responsePacket = DatagramPacket(receiveBuffer, receiveBuffer.size)
            socket.receive(responsePacket)
//            val response = responsePacket.data
//            Log.i("ViscaUDPSocket", "response = ${toByteArrayString(response)}")
        } catch (e: Exception) {

        } finally {
            socket.close()
        }
    }
}