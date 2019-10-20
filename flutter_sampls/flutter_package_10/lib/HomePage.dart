
import 'package:flutter/material.dart';

class HomePage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
//        leading: IconButton(icon: Icon(Icons.arrow_back),
//            onPressed: (){
////              Navigator.of(context).push(MaterialPageRoute(builder: (BuildContext context)=>MyHomePage()));
//              Navigator.pop(context);
//            }),
        title: Text("Home Page"),
      ),
      body: Center(
        child: Tooltip(
            message: "你好啊，我没钱了，你可以给我很多钱吗？",
            child: Image.asset("images/back.png", package: "flutter_package_10")),
      ),
    );
  }

}