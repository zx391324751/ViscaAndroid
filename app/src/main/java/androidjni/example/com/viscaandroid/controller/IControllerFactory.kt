package androidjni.example.com.viscaandroid.controller

/**
 * 2024/3/11
 * created by zhangxu
 */
interface IControllerFactory {

    fun createController(config: BaseConfig): ICameraController?  //创建相机控制器

}