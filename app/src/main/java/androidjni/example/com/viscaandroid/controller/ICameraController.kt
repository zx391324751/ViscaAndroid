package androidjni.example.com.viscaandroid.controller

/**
 * 2024/3/11
 * created by zhangxu
 */
interface ICameraController {

    //更新配置
    fun initConfig(config: BaseConfig)

    //连接和结束连接
    fun connect() {}  //连接
    fun finish() {}   //结束

    //云台转动
    fun turnLeft() {}   //左转
    fun turnRight() {}  //右转
    fun turnUp() {}   //上转
    fun turnDown() {}  //下转
    fun turnLeftAndUp() {}
    fun turnRightAndUp() {}
    fun turnLeftAndDown() {}
    fun turnRightAndDown() {}
    fun turnStop() {} //停止
    fun turnBack() {} //回到原位

    //镜头拉近拉远
    fun zoomWide() {}
    fun zoomTele() {}
    fun zoomStop() {}

    //对焦相关
    fun focusFar() {}
    fun focusNear() {}
    fun focusMode(isAuto: Boolean) {}
    fun focusStop() {}
}