package com.rentcar.common;

public interface ConsRent {
    String back = "请输入任意数返回...";
    String errorNotFound = "该车辆不存在！";
    String errorNotEnabled = "该车辆不可用！";

    String welcome = "欢迎使用本系统，请您选择：1.管理员登录 2.VIP登录 3.注册账号";
    String welcome1 = "请输入您的用户名";
    String welcome2 = "请输入您的密码";
    String errorUser = "用户名或密码错误，请检查";
    String welAdmin = "欢迎您，管理员";
    String welUser = "登陆成功！欢迎";
    String Register1 = "请创建您的用户名";
    String RegisterSuccess = "注册成功！";
    String RegisterFail = "注册失败！";
    String RegisterExist = "用户名存在，请重试！";
    String mainUser2 = "请选择您要租车的种类：\n1.卡车 2.轿车 3.公交车 4.退租车辆 5.退出系统";
    String unRent = "请输入您要退的车辆id:";
    String unRemntNone = "您没有租过车辆！";
    String unRemntError = "ID错误！";
    String unRentSuccess = "退租成功！";
    String mainUser1 = "您当前所租车辆有：";
    String errorNoV = "暂时没有该种类型车辆，请重新选择！";
    String selResUser = "ID\t种类\t品牌\t型号\t价格\t";

    String mainAdmin1 = "请选择您要进行的操作：\n1.查看车辆信息 2.修改车辆信息 3.增加车辆 4.删除车辆 5.查看营业额 6.退出系统";
    String mainAdmin2 = "请选择查询对象：\n0.所有车辆 1.卡车 2.轿车 3.公交车";
    //  String selResAdmin = "ID\t种类\t品牌\t型号\t价格\t出借日期\t租借天数\t租借情况\t租借人\t";
    String errorSelAdmin = "输入数字错误，请重试！";
    String do1 = "请输入车辆的id：";
    String do2 = "请输入车辆的kind：";
    String do3 = "请输入车辆的品牌：";
    String do4 = "请输入车辆的型号：";
    String do5 = "请输入车辆的价格：";
    String rentDays = "请输入您要租的天数：";
    String rentcarPay = "正在接受您的付款......\n支付成功！";
    String SUCCEED = "成功！";
    String FAIL = "失败！";

}
