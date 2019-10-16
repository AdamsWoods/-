import 'package:flutter/material.dart';
import 'package:flutter_package_01/BottomNavigationWidget.dart';
import 'package:flutter_package_02/BottomAppBarDemo.dart';
import 'package:flutter_package_03/PageRouterFirst.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.teal,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      drawer: Drawer(
        child: ListView(
          padding: const EdgeInsets.only(),
          children: <Widget>[
            new UserAccountsDrawerHeader(
              accountName: Text("zr"),
              accountEmail: Text("2243463124@qq.com"),
              currentAccountPicture: new CircleAvatar(
                backgroundImage: new AssetImage('images/head_image.png'),
                child: new Image.asset("images/head_image.png"),
                backgroundColor: Colors.white,

              ),
            ),
            new ListTile(
              trailing: Icon(Icons.location_on),
              title: Text("01 底部导航栏 BottomNavigationBar Demo"),
              onTap: () {
                Navigator.pop(context);
                Navigator.push(context, MaterialPageRoute(
                    builder: (context) => new BottomNavigationWidget()));
              }
            ), new Divider(),
            new ListTile(
              title: Text("02 不规则底部导航栏 BottomAppBar Demo"),
              trailing: Icon(Icons.location_on),
                onTap: () {
                  Navigator.pop(context);
                  Navigator.push(context, MaterialPageRoute(
                      builder: (context) => new BottomAppBarDemo()));
                }
            ),new Divider(),
            new ListTile(
              title: Text("03 路由动画 PageRouterBuilder"),
              trailing: Icon(Icons.location_on),
                onTap: () {
                  Navigator.pop(context);
                  Navigator.push(context, MaterialPageRoute(
                      builder: (context) => new PageRouterFirst()));
                }
            ),new Divider(),
            new ListTile(
              title: Text("04 底部tab"),
              trailing: Icon(Icons.location_on),
                onTap: () {
                  Navigator.pop(context);
                  Navigator.push(context, MaterialPageRoute(
                      builder: (context) => new BottomNavigationWidget()));
                }
            ),new Divider(),
            new ListTile(
              title: Text("05 底部tab"),
              trailing: Icon(Icons.location_on),
                onTap: () {
                  Navigator.pop(context);
                  Navigator.push(context, MaterialPageRoute(
                      builder: (context) => new BottomNavigationWidget()));
                }
            ),new Divider(),
            new ListTile(
              title: Text("06 底部tab"),
              trailing: Icon(Icons.location_on),
                onTap: () {
                  Navigator.pop(context);
                  Navigator.push(context, MaterialPageRoute(
                      builder: (context) => new BottomNavigationWidget()));
                }
            ),new Divider(),
            new ListTile(
              title: Text("07 底部tab"),
              trailing: Icon(Icons.location_on),
                onTap: () {
                  Navigator.pop(context);
                  Navigator.push(context, MaterialPageRoute(
                      builder: (context) => new BottomNavigationWidget()));
                }
            ),new Divider(),
            new ListTile(
              title: Text("08 底部tab"),
              trailing: Icon(Icons.location_on),
                onTap: () {
                  Navigator.pop(context);
                  Navigator.push(context, MaterialPageRoute(
                      builder: (context) => new BottomNavigationWidget()));
                }
            ),new Divider(),
            new ListTile(
              title: Text("09 底部tab"),
              trailing: Icon(Icons.location_on),
                onTap: () {
                  Navigator.pop(context);
                  Navigator.push(context, MaterialPageRoute(
                      builder: (context) => new BottomNavigationWidget()));
                }
            ),new Divider(),
            new ListTile(
              title: Text("10 底部tab"),
              trailing: Icon(Icons.location_on),
                onTap: () {
                  Navigator.pop(context);
                  Navigator.push(context, MaterialPageRoute(
                      builder: (context) => new BottomNavigationWidget()));
                }
            ),
          ],
        )
      ),
      body: Center(
        child: ListView(
          children: <Widget>[
            new ListTile(
              title: Text("01 底部导航栏 BottomNavigationBar"),
              trailing: Icon(Icons.location_on),
              onTap: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) => new BottomNavigationWidget()));
              }
            ),new Divider(),
            new ListTile(
              title: Text("02 不规则底部导航栏 BottomAppBar"),
              trailing: Icon(Icons.location_on),
              onTap: (){
                Navigator.push(context, MaterialPageRoute(builder: (context)=> new BottomAppBarDemo()));
              },
            ),new Divider(),
             new ListTile(
              title: Text("03 路由动画 PageRouterBuilder"),
              trailing: Icon(Icons.location_on),
               onTap: (){
                Navigator.push(context, MaterialPageRoute(builder: (context)=> new PageRouterFirst()));
               },
            ),new Divider(),
             new ListTile(
              title: Text("04 底部tab"),
              trailing: Icon(Icons.location_on),
            ),new Divider(),
             new ListTile(
              title: Text("05 底部tab"),
              trailing: Icon(Icons.location_on),
            ),new Divider(),
             new ListTile(
              title: Text("06 底部tab"),
              trailing: Icon(Icons.location_on),
            ),new Divider(),
             new ListTile(
              title: Text("07 底部tab"),
              trailing: Icon(Icons.location_on),
            ),new Divider(),
             new ListTile(
              title: Text("08 底部tab"),
              trailing: Icon(Icons.location_on),
            ),new Divider(),
             new ListTile(
              title: Text("09 底部tab"),
              trailing: Icon(Icons.location_on),
            ),new Divider(),
             new ListTile(
              title: Text("10 底部tab"),
              trailing: Icon(Icons.location_on),
            ),new Divider(),
             new ListTile(
              title: Text("11 底部tab"),
              trailing: Icon(Icons.location_on),
            ),new Divider(),
             new ListTile(
              title: Text("12 底部tab"),
              trailing: Icon(Icons.location_on),
            ),new Divider(),

          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
