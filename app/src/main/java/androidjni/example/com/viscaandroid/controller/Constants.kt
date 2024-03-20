package androidjni.example.com.viscaandroid.controller

/**
 * 2024/3/19
 * created by zhangxu
 */

/**云台移动方向*/
const val CONTROLLER_STOP = 0
const val CONTROLLER_LEFT = 1
const val CONTROLLER_RIGHT = 2
const val CONTROLLER_UP = 4
const val CONTROLLER_DOWN = 8
const val CONTROLLER_LEFT_UP = CONTROLLER_LEFT or CONTROLLER_UP
const val CONTROLLER_RIGHT_UP = CONTROLLER_RIGHT or CONTROLLER_UP
const val CONTROLLER_LEFT_DOWN = CONTROLLER_LEFT or CONTROLLER_DOWN
const val CONTROLLER_RIGHT_DOWN = CONTROLLER_RIGHT or CONTROLLER_DOWN