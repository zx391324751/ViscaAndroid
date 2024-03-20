package androidjni.example.com.viscaandroid.controller.visca

import android.util.Log

/**
 * Visca 协议命令格式
 *
 * 文档地址:https://www.sony.net/Products/CameraSystem/CA/BRC_X400_SRG_X400/Technical_Document/E042100111.pdf
 *
 * Visca的数据包为16Bytes
 * 1-Byte: Header
 * 14-Byte: Data
 * 1-Byte: 结束符 0xFF
 *
 * 2024/3/11
 * created by zhangxu
 */
object ViscaCommand {

    private const val VISCA_COMMAND: Byte = 0x01  //普通命令
    private const val VISCA_INQUIRY: Byte = 0x09  //查询命令

    private val VISCA_TERMINATOR: Byte = (0xFF).toUInt().toByte()     //结束符
    private const val VISCA_CATEGORY_PAN_TILTER: Byte = 0x06
    private const val VISCA_SPEED: Byte = 0x06

    fun getTurnLeftCommandData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toUInt().toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = VISCA_CATEGORY_PAN_TILTER
        byteArray[3] = 0x01
        byteArray[4] = VISCA_SPEED
        byteArray[5] = VISCA_SPEED
        byteArray[6] = 0x01
        byteArray[7] = 0x03
        byteArray[8] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    fun getTurnRightCommandData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = VISCA_CATEGORY_PAN_TILTER
        byteArray[3] = 0x01
        byteArray[4] = VISCA_SPEED
        byteArray[5] = VISCA_SPEED
        byteArray[6] = 0x02
        byteArray[7] = 0x03
        byteArray[8] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    fun getTurnUpCommandData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = VISCA_CATEGORY_PAN_TILTER
        byteArray[3] = 0x01
        byteArray[4] = VISCA_SPEED
        byteArray[5] = VISCA_SPEED
        byteArray[6] = 0x03
        byteArray[7] = 0x01
        byteArray[8] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    fun getTurnDownCommandData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = VISCA_CATEGORY_PAN_TILTER
        byteArray[3] = 0x01
        byteArray[4] = VISCA_SPEED
        byteArray[5] = VISCA_SPEED
        byteArray[6] = 0x03
        byteArray[7] = 0x02
        byteArray[8] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    fun getTurnUpAndLeftCommandData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = VISCA_CATEGORY_PAN_TILTER
        byteArray[3] = 0x01
        byteArray[4] = VISCA_SPEED
        byteArray[5] = VISCA_SPEED
        byteArray[6] = 0x01
        byteArray[7] = 0x01
        byteArray[8] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    fun getTurnUpAndRightCommandData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = VISCA_CATEGORY_PAN_TILTER
        byteArray[3] = 0x01
        byteArray[4] = VISCA_SPEED
        byteArray[5] = VISCA_SPEED
        byteArray[6] = 0x02
        byteArray[7] = 0x01
        byteArray[8] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    fun getTurnDownAndLeftCommandData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = VISCA_CATEGORY_PAN_TILTER
        byteArray[3] = 0x01
        byteArray[4] = VISCA_SPEED
        byteArray[5] = VISCA_SPEED
        byteArray[6] = 0x01
        byteArray[7] = 0x02
        byteArray[8] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    fun getTurnDownAndRightCommandData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = VISCA_CATEGORY_PAN_TILTER
        byteArray[3] = 0x01
        byteArray[4] = VISCA_SPEED
        byteArray[5] = VISCA_SPEED
        byteArray[6] = 0x02
        byteArray[7] = 0x02
        byteArray[8] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    fun getStopTurnData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = VISCA_CATEGORY_PAN_TILTER
        byteArray[3] = 0x01
        byteArray[4] = VISCA_SPEED
        byteArray[5] = VISCA_SPEED
        byteArray[6] = 0x03
        byteArray[7] = 0x03
        byteArray[8] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    fun getZoomWideData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = 0x04
        byteArray[3] = 0x07
        byteArray[4] = 0x03
        byteArray[5] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    fun getZoomTeleData(cameraID: Int): ByteArray {
        val byteArray = ByteArray(16)
        byteArray[0] = (0x80 or cameraID).toByte()
        byteArray[1] = VISCA_COMMAND
        byteArray[2] = 0x04
        byteArray[3] = 0x07
        byteArray[4] = 0x02
        byteArray[5] = VISCA_TERMINATOR
        printByteArray(byteArray)
        return packViscaHeaderByteArray(byteArray)
    }

    /**
     * 包装Visca Header，所有控制指令的header都相同。
     */
    private fun packViscaHeaderByteArray(temp: ByteArray): ByteArray {
        val byteArray = ByteArray(24)
        byteArray[0] = 0x01
        byteArray[1] = 0x00
        byteArray[2] = 0x00
        byteArray[3] = 0x10
        byteArray[4] = 0x00
        byteArray[5] = 0x00
        byteArray[6] = 0x00
        byteArray[7] = 0x00
        for (i in 8 until 24) {
            byteArray[i] = temp[i - 8]
        }
        return byteArray
    }

    fun printByteArray(byteArray: ByteArray) {
        Log.i("byte data", "------------------ ")
        Log.i("byte data", "start ------------ ")
        for (item in byteArray) {
            printByte(item)
        }
        Log.i("byte data", "end ------------ ")
        Log.i("byte data", "---------------- ")
    }

    private fun printByte(byte: Byte) {
        Log.i("byte data", "${byte.toUByte().toString(2)}, ${byte.toUByte().toUInt()}")
    }

}