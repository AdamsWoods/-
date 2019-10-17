library flutter_package_06;

import 'package:flutter/material.dart';
import 'package:flutter_package_06/MySearchDelegate.dart';

/// A Calculator.
class SearchBarDemo extends StatefulWidget {
  _SearchBarState createState() => _SearchBarState();
}

class _SearchBarState extends State<SearchBarDemo> {

  bool _harder;

  @override
  void initState() {
    _harder = false;
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.black87,
        title: Text("SearchBar"),
        actions: <Widget>[
          IconButton(
              icon: Icon(Icons.search, color: Colors.white,),
              onPressed: (){
                showSearch(context: context, delegate: MySearchDelegate());
              }),
          IconButton(icon: Icon(Icons.add_a_photo, color: Colors.white,), onPressed: null,),
          IconButton(icon: Icon(Icons.account_box, color: Colors.white,), onPressed: null,),
          PopupMenuButton(
              onSelected: (result){
                switch(result){
                  case 'harder':
                    setState(() {
                      _harder = !_harder;
                      SnackBar(content: Text("Work a lot harder"));
                    });
                    break;
                }
              },
              itemBuilder: (BuildContext context) => <PopupMenuEntry>[
                CheckedPopupMenuItem(
                    value: 'harder',
                    checked: _harder,
                    child: const Text("Work a lot harder")),
                const PopupMenuDivider(height: 8.0,),
                const PopupMenuItem(
                    value: 'selfStarter',
                    child: Text("Being a self-starter")),
                const PopupMenuDivider(height: 8.0,),
                const PopupMenuItem(
                    value: 'smarter',
                    child: Text("Being a smarter")),
                const PopupMenuDivider(height: 8.0,),
                const PopupMenuItem(
                    value: 'tradingCharter',
                    child: ListTile(leading: Icon(Icons.add, color: Colors.black87, ), title: Text("Placed in "),)),
//                const IconButton(icon: Icon(Icon), onPressed: null)
              ]),
        ],
      ),
      body: Column(

      ),
    );
  }

}
