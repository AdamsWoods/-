library flutter_package_03;

import 'package:flutter/material.dart';
import 'CustomRoute.dart';
import 'PageRouterSecond.dart';

class PageRouterFirst extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.pinkAccent,
      appBar: AppBar(
        title: Text("First Page"),
        elevation: 0.0,
      ),
      body: Center(
        child: MaterialButton(
            child: Icon(Icons.arrow_forward_ios, color: Colors.white,),
            onPressed: (){
              Navigator.of(context).push(CustomRoute(PageRouterSecond()));
            }),
      ),
    );
  }
}
