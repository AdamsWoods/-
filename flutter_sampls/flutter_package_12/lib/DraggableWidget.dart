import 'package:flutter/material.dart';

class DraggableWidget extends StatefulWidget{

  final Offset offset;
  final Color widgetColor;

  const DraggableWidget({Key key, this.offset, this.widgetColor}):super(key: key);

  _DraggableWidgetState createState() => _DraggableWidgetState();
}

class _DraggableWidgetState extends State<DraggableWidget>{

  Offset offset = Offset(0.0, 0.0);

  @override
  void initState() {
    super.initState();
    offset = widget.offset;
  }

  @override
  Widget build(BuildContext context) {
    return Positioned(
        left: offset.dx,
        top: offset.dy,
        child: Draggable(
          data: widget.widgetColor, //会传递给DragTarget的参数
          child: Container(
              width: 100,
              height: 100,
              color: widget.widgetColor,
            ),
            feedback: Container( //正在拖动时，控件的状态
              width: 100,
              height: 100,
              color: widget.widgetColor.withOpacity(0.5),
            ),
            onDraggableCanceled: (Velocity velocity, Offset offset){ //拖动松开时，经常用来改变推拽时到达的位置
              setState(){
                this.offset = offset;
              }
            },));
  }

}