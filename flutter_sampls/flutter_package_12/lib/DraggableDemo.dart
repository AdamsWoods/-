library flutter_package_12;

import 'package:flutter/material.dart';

import 'DraggableWidget.dart';

/// A Calculator.
class DraggableDemo extends StatefulWidget {
  _DraggableState createState() => _DraggableState();
}

class _DraggableState extends State<DraggableDemo>{

  Color _draggableColor = Colors.grey;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: <Widget>[
          DraggableWidget(
            offset: Offset(80.0, 80),
            widgetColor: Colors.tealAccent
          ),
          DraggableWidget(
              offset: Offset(200.0, 80),
              widgetColor: Colors.redAccent
          ),
          Center(
            child: DragTarget(
              onAccept: (Color color){ //接收来自Draggable的data参数
                _draggableColor = color;
              },
              builder: (context, candidateData, rejecteData){
                return Container(
                  width: 200.0,
                  height: 200.0,
                  color: _draggableColor,
                );
              },
            ),
          )
        ],
      ),
    );
  }

}
