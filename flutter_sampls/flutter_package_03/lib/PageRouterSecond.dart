library flutter_package_03;

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_package_03/PageRouterFirst.dart';

class PageRouterSecond extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blueAccent,
      appBar: AppBar(
        title: Text("Second Page"),
        elevation: 0.0,
      ),
      body: Center(
        child: MaterialButton(
            child: Icon(Icons.arrow_back_ios, color: Colors.white,),
            onPressed: (){
              Navigator.of(context).pop();
            }),
      ),
    );
  }
}
