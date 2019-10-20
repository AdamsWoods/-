
import 'package:flutter/material.dart';

import 'HomePage.dart';

class SplashPage extends StatefulWidget{
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> with SingleTickerProviderStateMixin{

  bool flag = false;
  Animation _animation;
  AnimationController _animationController;

  @override
  void initState() {
    super.initState();
    _animationController = new AnimationController(vsync: this, duration: Duration(milliseconds: 3000));
    _animation = Tween(begin: 0.0, end: 1.0).animate(_animationController);

    /**
     * 添加动画监听事件
     * 可监听动画的执行状态，
     * 如果动画结束，则进入homepage页面
     */
    _animation.addStatusListener((status){
      if(status == AnimationStatus.completed){
        if(!flag) {
          Navigator.of(context).pushAndRemoveUntil(
              MaterialPageRoute(builder: (BuildContext context) => HomePage()),
                  (route) => route == null
          );
//          Navigator.pop(context);
        } else {
//          Navigator.of(context).pushAndRemoveUntil(
//              MaterialPageRoute(builder: (BuildContext context) => MyHomePage()),
//                  (route) => route == null
//          );
          Navigator.pop(context);
          flag = true;
        }
      }
    });
    //播放动画
    _animationController.forward();
  }

  @override
  void dispose() {
    _animationController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return FadeTransition(
        opacity: _animation,
        child: Image.asset("images/back.png",
          package: 'flutter_package_10',
          scale: 2.0,
          fit: BoxFit.cover,),
      );
  }

}
