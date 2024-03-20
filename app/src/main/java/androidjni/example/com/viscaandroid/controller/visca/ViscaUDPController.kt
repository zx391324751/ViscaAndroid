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
internal class ViscaUDPController : ViscaIPController() {

    private val socket: DatagramSocket by lazy {
        DatagramSocket()
    }

    override fun finish() {
        super.finish()
        socket.close()
    }

    override fun turnBack() {
        super.turnBack()
        sendViscaCommand(getTurnBackCommand(netConfig!!.cameraID))
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

    override fun zoomWide() {
        super.zoomWide()
        sendViscaCommand(getZoomWideCommand(netConfig!!.cameraID))
    }

    override fun zoomTele() {
        super.zoomTele()
        sendViscaCommand(getZoomTeleCommand(netConfig!!.cameraID))
    }

    override fun zoomStop() {
        super.zoomStop()
        sendViscaCommand(getZoomStopCommand(netConfig!!.cameraID))
    }

    override fun focusFar() {
        super.focusFar()
        sendViscaCommand(getFocusFarCommand(netConfig!!.cameraID))
    }

    override fun focusNear() {
        super.focusNear()
        sendViscaCommand(getFocusNearCommand(netConfig!!.cameraID))
    }

    override fun focusMode(isAuto: Boolean) {
        super.focusMode(isAuto)
        sendViscaCommand(getFocusModeCommand(netConfig!!.cameraID, isAuto))
    }

    override fun focusStop() {
        super.focusStop()
        sendViscaCommand(getFocusStopCommand(netConfig!!.cameraID))
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