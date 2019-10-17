library flutter_package_04;

import 'dart:ui';

import 'package:flutter/material.dart';

class FrostedGlassDemo extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("FrostedGlassDemo"),
      ),
      body: Stack(
        children: <Widget>[
          ConstrainedBox(//裁剪长方形
            constraints: const BoxConstraints.expand(),
            child: Image.asset("images/back.png", package: 'flutter_package_04',),),
          Center(
            child: ClipRect(//背景滤镜
              child: BackdropFilter(
                filter: ImageFilter.blur(sigmaX: 5.0, sigmaY: 5.0),
                child: Opacity( //透明控件
                    opacity: 0.5,
                    child: Container( //容器
                      width: 300,
                      height: 300,
                      decoration: BoxDecoration(color: Colors.grey.shade200),
                      child: Center(
                        child: Text("毛玻璃", style: Theme.of(context).textTheme.display3,),
                      ),
                    ),),
              ),
            ),
          )
        ],
      ),
    );
  }

}