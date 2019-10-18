import 'package:flutter/material.dart';

class BottomClipperTest extends CustomClipper<Path>{

  //返回裁剪的描述
  @override
  Path getClip(Size size) {
    var path = Path();
    path.lineTo(0, 0);
    path.lineTo(0, size.height-30);
    //单弧
//    var firstControlPoint = Offset(size.width/4, size.height);
//    var firstEndPoint = Offset(size.width/2.25, size.height - 30);
//
//    path.quadraticBezierTo(firstControlPoint.dx, firstControlPoint.dy, firstEndPoint.dx, firstEndPoint.dy);

    //双弧线
    var firstControlPoint = Offset(size.width/4, size.height);
    var firstEndPoint = Offset(size.width/2.25, size.height - 30);

    path.quadraticBezierTo(firstControlPoint.dx, firstControlPoint.dy, firstEndPoint.dx, firstEndPoint.dy);

    var secondControlPoint = Offset(size.width/4*3, size.height-80);
    var secondEndPoint = Offset(size.width, size.height - 40);

    path.quadraticBezierTo(secondControlPoint.dx, secondControlPoint.dy, secondEndPoint.dx, secondEndPoint.dy);

    path.lineTo(size.width, size.height-30);
    path.lineTo(size.width, 0);

    return path;
  }

  //确定getclip是否被回调
  @override
  bool shouldReclip(CustomClipper<Path> oldClipper) {
    return false;
  }

}