import 'package:flutter/material.dart';

class CollectionBooks extends StatefulWidget{
  _CollectionBooksState createState() => _CollectionBooksState();
}

class _CollectionBooksState extends State<CollectionBooks> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("My Books Collected"),
      ),
      body: Center(
        child: Text("My Books Collected"),
      ),
    );
  }

}