package androidjni.example.com.viscaandroid.controller.visca

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

    override fun turnUp() {
        super.turnUp()
        sendViscaCommand(getTurnUpCommand(netConfig!!.cameraID))
    }

    override fun turnDown() {
        super.turnDown()
        sendViscaCommand(getTurnDownCommand(netConfig!!.cameraID))
    }

    override fun turnLeftAndUp() {
        super.turnLeftAndUp()
        sendViscaCommand(getTurnLeftAndUpCommand(netConfig!!.cameraID))
    }

    override fun turnRightAndUp() {
        super.turnRightAndUp()
        sendViscaCommand(getTurnRightAndUpCommand(netConfig!!.cameraID))
    }

    override fun turnLeftAndDown() {
        super.turnLeftAndDown()
        sendViscaCommand(getTurnLeftAndDownCommand(netConfig!!.cameraID))
    }

    override fun turnRightAndDown() {
        super.turnRightAndDown()
        sendViscaCommand(getTurnRightAndDownCommand(netConfig!!.cameraID))
    }

    override fun turnStop() {
        super.turnStop()
        sendViscaCommand(getTurnStopCommand(netConfig!!.cameraID))
    }

    private fun sendViscaCommand(command: ByteArray) {
        if (netConfig == null) throw RuntimeException("ip地址为空")
        GlobalScope.launch {
            val ip = netConfig?.ipAddress ?: return@launch
            val port = netConfig?.port ?: return@launch
            Log.i("ViscaUDPSocket", "ip = ${ip}, port = ${port}")
            val address = InetAddress.getByName(ip)
            val packet = DatagramPacket(command, command.size, address, port)
            try {
                socket.send(packet)
                val receiveBuffer = ByteArray(32)
                val responsePacket = DatagramPacket(receiveBuffer, receiveBuffer.size)
                socket.receive(responsePacket)
                val response = responsePacket.data
                Log.i("ViscaUDPSocket", "response = ${ViscaCommand.printByteArray(response)}")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
//                socket.close()
            }
        }
    }
}