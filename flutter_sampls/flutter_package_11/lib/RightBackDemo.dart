library flutter_package_11;

import 'package:flutter/cupertino.dart';

/// A Calculator.
class RightBackDemo extends StatelessWidget {

  /**
   * 使用CuptertinoPageRoute打开的子页面，可以向右滑动返回
   */
  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
      backgroundColor: CupertinoColors.white,
      child: Center(
        child: Container(
          width: 200,
          height: 200,
          color: CupertinoColors.black,
          child: CupertinoButton(
              child: Icon(CupertinoIcons.add, size: 100, color: CupertinoColors.white,),
              onPressed: (){
                Navigator.of(context).push(
                    CupertinoPageRoute(builder: (BuildContext context){
                      return RightBackDemo();
                    })
                );
              },),
        ),
      ),
    );
  }
}
