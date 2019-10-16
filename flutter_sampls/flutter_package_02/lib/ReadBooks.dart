import 'package:flutter/material.dart';

class ReadBooks extends StatefulWidget{
  _ReadBooksState createState() => _ReadBooksState();
}

class _ReadBooksState extends State<ReadBooks> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("My Books Readed"),
      ),
      body: Center(
        child: Text("My Books Readed"),
      ),
    );
  }

}