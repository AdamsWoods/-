import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_package_02/NewPage.dart';
import 'package:flutter_package_02/CollectionBooks.dart';
import 'package:flutter_package_02/ReadBooks.dart';

class BottomAppBarDemo extends StatefulWidget{
  _BottomAppBarDemo createState() => _BottomAppBarDemo();
}

class _BottomAppBarDemo extends State<BottomAppBarDemo>{
  
  List<Widget> pages = List();
  int _index = 0;
  
  @override
  void initState() {
    pages
      ..add(NewPage('books'))
      ..add(NewPage('readed'));
    super.initState();
  }
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
//      appBar: AppBar(
//      ),
      body: pages[_index],
      floatingActionButton: FloatingActionButton(
        tooltip: "新增页", 
        onPressed: (){
          Navigator.of(context).push(MaterialPageRoute(builder: (context)=> new NewPage('New Page')));},
        child: Icon(Icons.add, color: Colors.white,),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      bottomNavigationBar: BottomAppBar(
        color: Colors.teal,
        shape: CircularNotchedRectangle(),
        child: Row(
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: <Widget>[
            IconButton(
                icon: Icon(Icons.book, color: Colors.white,),
                onPressed: (){
                  setState(() {
                    _index = 0;
                  });
                }),
            IconButton(
                icon: Icon(Icons.chrome_reader_mode, color: Colors.white,),
                onPressed: (){
                  setState(() {
                    _index = 1;
                  });
                })
          ],
        ),
      ),
    );
  }
}
