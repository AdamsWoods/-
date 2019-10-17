import 'package:flutter/material.dart';
import 'package:flutter_package_06/assets.dart';

class MySearchDelegate extends SearchDelegate{

  MySearchDelegate({
    String hintText
  }):super (
    searchFieldLabel: "请输入搜索关键字",
    keyboardType: TextInputType.text,
    textInputAction: TextInputAction.search
  );

  /// 右侧的图标，清除
  @override
  List<Widget> buildActions(BuildContext context) {
    return [
      IconButton(
          icon: Icon(Icons.clear, color: Colors.black87,),
          onPressed: (){
            query = '';
          })
    ];
  }

  /// 左边的图标，返回按钮
  @override
  Widget buildLeading(BuildContext context) {
    return IconButton(
        icon: Icon(Icons.arrow_back),
        onPressed: (){
          close(context, null);
        });
  }

  /// 搜索结果展示
  @override
  Widget buildResults(BuildContext context) {
    return Container(
      width: 100,
      height: 100,
      child: Card(
        child: Text(query),
      ),
    );
  }

  /// 推荐显示
  @override
  Widget buildSuggestions(BuildContext context) {
    final suggestionList = query.isEmpty
        ? recentList
        : searchList.where((input)=> input.startsWith(query)).toList();
    return ListView.builder(
        itemCount: suggestionList.length,
        itemBuilder: (context, index) => ListTile(
          title: RichText(text: TextSpan(
            text: suggestionList[index].substring(0, query.length),
            style: TextStyle(color: Colors.black87, fontWeight: FontWeight.bold),
            children: [
              TextSpan(
                text: suggestionList[index].substring(query.length),
                style: TextStyle(color: Colors.grey)
              )
            ],
          )),
        ));
  }

}