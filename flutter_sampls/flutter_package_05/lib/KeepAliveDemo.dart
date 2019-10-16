library flutter_package_05;

import 'package:flutter/material.dart';
import 'TabPage.dart';

class KeepAliveDemo extends StatefulWidget {
  _KeepAliveState createState() => _KeepAliveState();
}


/*
with是dart的关键字，意思是混入的意思，
就是说可以将一个或者多个类的功能添加到自己的类无需继承这些类，
避免多重继承导致的问题。
SingleTickerProviderStateMixin 主要是我们初始化TabController时，
需要用到vsync ，垂直属性，然后传递this
*/
class _KeepAliveState extends State<KeepAliveDemo> with SingleTickerProviderStateMixin{

  TabController _controller;

  @override
  void initState() {
    super.initState();
    _controller = TabController(length: 3, vsync: this);
  }

  //销毁
  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Keep Alive"),
        bottom: TabBar(
          controller: _controller,
          tabs: [
            Tab(icon: Icon(Icons.phone),),
            Tab(icon: Icon(Icons.phone_bluetooth_speaker),),
            Tab(icon: Icon(Icons.phone_forwarded),)
        ]),
      ),
      body: TabBarView(
        controller: _controller,
        children: <Widget>[
          TabPage(),
          TabPage(),
          TabPage(),
        ],
      ),
    );
  }

}