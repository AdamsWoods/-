import 'package:flutter/material.dart';

class NewPage extends StatefulWidget{

  String _title;
  NewPage(this._title);

  _NewPageStateWidget createState() => _NewPageStateWidget();
}

class _NewPageStateWidget extends State<NewPage>{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget._title),
      ),
      body: Center(
        child: Text(widget._title),
      ),
    );
  }
}