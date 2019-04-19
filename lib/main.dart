import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:qr/qr.dart';
import 'package:qr_reader/qr_reader.dart';
import 'package:qr_flutter/qr_flutter.dart';
import 'package:flutter/services.dart';

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
  String iso;

  @override
  void initState() {
    iso = 'AU6PXA5Oj0PAm8BEAAACynx6z3lcpQAAAAACAAIAAAA=';
    var ba64 = base64.decode(iso);
    print(ba64);
    // print(ascii.decode(ba64));
    // var byte = utf8.encode(iso);
    // print(byte);
    // // getData(ba64);
    iso = latin1.decode(ba64);
    print(iso);
    super.initState();
  }

  getData(String code) async {
    print(code);
    const platform = const MethodChannel('com.rq-exam/decode');
    var result = await platform.invokeMethod('decodeQr', {'data': code});
  }

  getUnicode(int code) {
    print(code.toRadixString(code));
  }

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
              // setState(() {});
              print(snapshot.data);

              var lat = Latin1Codec(allowInvalid: true);
              print(lat.encode('ÿþýü'));
              print('-----------------------------------' +
                  lat.encode(snapshot.data).toString());
              getData(snapshot.data);

//               var utf8st = utf8.encode(snapshot.data);
//               print(utf8st);
//               utf8st.forEach((byte){
//
//                 print(byte&0xFF);
//               });
//               var utf8Str = latin1.encode(snapshot.data);
//               print(utf8Str);
              // var base64Str = base64.encode(utf8Str);
              // print(base64Str);
              // var iso = latin1.decode(base64Str, allowInvalid: true);
              // print(iso);
              print(
                  '----------------------------------------------------------');
              print(
                  '----------------------------------------------------------');
            }

            return snapshot.data != null
                ? Text(snapshot.data)
                : new QrImage(
                    // version: 4,
                    data: iso,
                    size: 200.0,
                    // errorCorrectionLevel: QrErrorCorrectLevel.L,
                  );
          },
        ),
      ),
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
