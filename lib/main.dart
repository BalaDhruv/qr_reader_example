import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:qr_reader/qr_reader.dart';

void main() {
  runApp(new MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'QR Reader',
      home: new MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  final Map<String, dynamic> pluginParameters = {};

  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Future<String> _barcodeString;

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: const Text('QR Reader'),
      ),
      body: new Center(
          child: new FutureBuilder<String>(
              future: _barcodeString,
              builder: (BuildContext context, AsyncSnapshot<String> snapshot) {
                if (snapshot.data != null) {
                  print(
                      '----------------------------------------------------------');
                  print(
                      '----------------------------------------------------------');
                  // var string = 'AU6Ke0JOikgAkABEAAACyk8BNyRdJAAAAAAUAAEAAAA=';
                  // var ba64 = base64.decode(string);
                  // print(ba64);
                  // var iso = latin1.decode(ba64);
                  // print(iso);
                  print(snapshot.data);
                  var utf8st = utf8.encode(snapshot.data);
                  print(utf8st);
                  // var utf8Str = latin1.decode(utf8st);
                  // print(utf8Str);
                  var base64Str = base64.encode(utf8st);
                  print(base64Str);
                  // var iso = latin1.decode(base64Str, allowInvalid: true);
                  // print(iso);
                  print(
                      '----------------------------------------------------------');
                  print(
                      '----------------------------------------------------------');
                }
                return new Text(snapshot.data != null ? snapshot.data : '');
              })),
      floatingActionButton: new FloatingActionButton(
        onPressed: () {
          setState(() {
            _barcodeString = new QRCodeReader()
                .setAutoFocusIntervalInMs(200)
                .setForceAutoFocus(true)
                .setTorchEnabled(true)
                .setHandlePermissions(true)
                .setExecuteAfterPermissionGranted(true)
                .scan();
          });
        },
        tooltip: 'Reader the QRCode',
        child: new Icon(Icons.add_a_photo),
      ),
    );
  }
}
