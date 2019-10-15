library flutter_package_01;

import 'package:flutter/material.dart';

/// A BottomNavigationWidget.
class BottomNavigationWidget extends StatefulWidget {
    _BottomNavigationWidgetState createState() => _BottomNavigationWidgetState();
}

class _BottomNavigationWidgetState extends State<BottomNavigationWidget> {

  final _BottomNavigationColor = Colors.blue;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("BottomNavigationBar"),

      ),
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
          type: BottomNavigationBarType.fixed,
      ),
    );
  }

}
