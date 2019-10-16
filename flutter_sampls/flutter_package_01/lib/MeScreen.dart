import 'package:flutter/material.dart';

class MeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("MeScreen"),
      ),
      body: Center(
        child: Text("Me"),
      ),
    );
  }
}