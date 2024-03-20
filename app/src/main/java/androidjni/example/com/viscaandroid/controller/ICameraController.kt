package androidjni.example.com.viscaandroid.controller

/**
 * 2024/3/11
 * created by zhangxu
 */
interface ICameraController {

    fun initConfig(config: BaseConfig)

    fun connect() {}  //连接

    fun finish() {}   //结束

    fun turnLeft() {}   //左转

    fun turnRight() {}  //右转

    fun turnUp() {}   //上转

    fun turnDown() {}  //下转

    fun turnLeftAndUp() {}

    fun turnRightAndUp() {}

    fun turnLeftAndDown() {}

    fun turnRightAndDown() {}

    fun turnStop() {} //停止

}