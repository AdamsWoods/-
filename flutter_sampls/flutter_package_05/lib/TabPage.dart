import 'package:flutter/material.dart';

class TabPage extends StatefulWidget{
  _TabPageState createState() => _TabPageState();
}

class _TabPageState extends State<TabPage> with AutomaticKeepAliveClientMixin{

  int _count = 0;

  void _incrementCounter(){
    setState((){
      _count ++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text("点一下，加一下，漂浮按钮"),
            Text("$_count", style: Theme.of(context).textTheme.display1,),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
          child: Icon(Icons.add),
          tooltip: 'Increment',
          onPressed: (){
            _incrementCounter();
          }),
    );
  }

  //记忆开关
  @override
  bool get wantKeepAlive => true;

}