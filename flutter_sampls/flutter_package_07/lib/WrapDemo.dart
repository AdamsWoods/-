library flutter_package_07;

import 'package:flutter/material.dart';

class WrapDemo extends StatefulWidget {
  _WrapState createState() => _WrapState();
}

class _WrapState extends State<WrapDemo> {

  List<Widget> list;

  @override
  void initState() {
    super.initState();
    list = List<Widget>()..add(buildAddButton());
  }

  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;

    return Scaffold(
      appBar: AppBar(
        title: Text("流式布局"),
      ),
      body: Center(
        child: Opacity(
          opacity: 0.8,
          child: Container(
            padding: EdgeInsets.all(20),
            height: height/2,
            width: width,
            color: Colors.black12,
            child: Wrap(
              children: list,
              spacing: 20.0,
            ),
          ),
        ),
      ),
    );
  }

  Widget buildAddButton(){
    return GestureDetector(
      onTap: (){
        if(list.length < 9){
          setState(() {
            list.insert(list.length-1, buildPhoto());
          });
        }
      },
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Container(
          height: 80.0,
          width: 80.0,
          color: Colors.black38,
          child: Icon(Icons.add, color: Colors.white,),
        )
      ),
    );
  }

  Widget buildPhoto(){
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Container(
        width: 80.0,
        height: 80.0,
        color: Colors.black38,
        child: Text("照片"),
      ),
    );
  }
}
