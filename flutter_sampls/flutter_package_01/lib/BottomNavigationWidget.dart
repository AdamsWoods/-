library flutter_package_01;

import 'package:flutter/material.dart';
import 'HomeScreen.dart';
import 'EmailScreen.dart';
import 'WalletScreen.dart';
import 'MeScreen.dart';

/// A BottomNavigationWidget.
class BottomNavigationWidget extends StatefulWidget {
    _BottomNavigationWidgetState createState() => _BottomNavigationWidgetState();
}

class _BottomNavigationWidgetState extends State<BottomNavigationWidget> {

//  final _BottomNavigationColor = Colors.black38;
  final _BottomNavigationColor = null;
  final itemSelectColor = Colors.blue;
  final itemUnSelectColor = Colors.black38;
  int _currentIndex = 0;
  final List<Widget> tabPages = List();

  /*
  初始化页面
   */
  @override
  void initState() {
    tabPages
      ..add(HomeScreen())
      ..add(EmailScreen())
      ..add(WalletScreen())
      ..add(MeScreen());
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
//      appBar: AppBar(
//        title: Text("BottomNavigationBar"),
//
//      ),
      body: tabPages[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
          items: [
            BottomNavigationBarItem(
                icon: Icon(Icons.home, color: _BottomNavigationColor),
                title: Text("Home", style: TextStyle(color: _BottomNavigationColor),)),
            BottomNavigationBarItem(
                icon: Icon(Icons.email, color: _BottomNavigationColor),
                title:Text("Email", style: TextStyle(color: _BottomNavigationColor),)),
            BottomNavigationBarItem(
                icon: Icon(Icons.account_balance_wallet, color: _BottomNavigationColor),
                title: Text("Wallet", style: TextStyle(color: _BottomNavigationColor),)),
            BottomNavigationBarItem(
                icon: Icon(Icons.account_box, color: _BottomNavigationColor),
                title: Text("Me", style: TextStyle(color: _BottomNavigationColor),)),
          ],
          currentIndex: _currentIndex,
          onTap: (int index){
            setState(() {
              _currentIndex = index;
            });
          },
          selectedItemColor: itemSelectColor,
          unselectedItemColor: itemUnSelectColor,
          type: BottomNavigationBarType.fixed,
      ),
    );
  }

}
