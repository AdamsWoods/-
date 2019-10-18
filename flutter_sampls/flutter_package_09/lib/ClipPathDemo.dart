library flutter_package_09;

import 'package:flutter/material.dart';
import 'package:flutter_package_09/BottomClipperTers.dart';

class ClipPathDemo extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("ClipPathDemo"),
      ),
      body: Column(
        children: <Widget>[
          ClipPath(
            clipper: BottomClipperTest(),
            child: Container(
              color: Colors.black,
              height: 200,
            ),
          )
        ],
      ),
    );
  }
  
}