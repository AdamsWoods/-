
import 'package:flutter/material.dart';

import 'Item.dart';

class ExpansionTileDemo extends StatefulWidget{
  _ExpansionTileState createState() => _ExpansionTileState();
}

class _ExpansionTileState extends State<ExpansionTileDemo> {

  //报存列表item
  List<Item> _data;

  //生成Item
  List<Item> generateItems(int numCount){
    return List.generate(numCount, (int index){
      return Item(
        headerValue: "Panel $index",
        expanedValue: "This is item number $index");
    });
  }

  @override
  void initState() {
    super.initState();
    //列表item初始化
    _data = generateItems(12);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("ExpansionTile"),
      ),
      body: SingleChildScrollView(
        child: Column(
          children: <Widget>[
            ExpansionTile(
            leading: Icon(Icons.ac_unit),
            title: Text("Expansion"),
            backgroundColor: Colors.black38,
            initiallyExpanded: false,
            trailing: Icon(Icons.expand_more),
            children: <Widget>[
              Icon(Icons.print),
              Text("我是你啊！"),
              ListTile(
                title: Text("我要钱"),
                subtitle: Text("我要很多钱！"),
              )
            ],),
            Divider(),
            SingleChildScrollView(
//            children: <Widget>[
//              SingleChildScrollView(
                scrollDirection: Axis.vertical,
                    reverse: false,
                    physics: BouncingScrollPhysics(),
                    child: Column(
                      children: <Widget>[
                        Text("下面是ExpansionPanelList列表"),
                        ExpansionPanelList(
                          expansionCallback: (int index, bool isExpaned){
                            setState(() {
                              _data[index].isExpaned = !isExpaned;
                            });
                          },
                          children: _data.map<ExpansionPanel>((Item item){
                            return ExpansionPanel(
                                headerBuilder: (BuildContext context, bool isExpaned){
                                  return ListTile(
                                    title: Text(item.headerValue),
                                  );
                                },
                                body: ListTile(
                                  title: Text(item.headerValue),
                                  subtitle: Text('to delete this panel, tap the trash can icon'),
                                  trailing: Icon(Icons.delete),
                                  onTap: (){
                                    _data.removeWhere((currentItem) => item == currentItem);},
                                ),
                                isExpanded: item.isExpaned);
                      }).toList(),
                    )
                  ],
                ),
              ),
          ],
        ))
//        ],
//      ),
    );
  }
}