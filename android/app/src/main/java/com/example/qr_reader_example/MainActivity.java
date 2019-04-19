package com.example.qr_reader_example;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Base64OutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Map;

import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "com.rq-exam/decode";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);

    new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(new MethodChannel.MethodCallHandler() {
      @TargetApi(Build.VERSION_CODES.KITKAT)
      @Override
      public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

        final Map<String, Object> arguments = methodCall.arguments();

        if (methodCall.method.equals("decodeQr")) {
          System.out.println("INSIDE DECODE QR ---------------------------------------------------------");
          String message = arguments.get("data").toString();
          Charset utf8charset = Charset.forName("UTF-8");
          Charset iso88591charset = Charset.forName("ISO-8859-1");
//          ByteBuffer bb = null;
//          bb = ByteBuffer.wrap(message.getBytes(StandardCharsets.ISO_8859_1)).order(ByteOrder.LITTLE_ENDIAN);
////          bb.order(ByteOrder.LITTLE_ENDIAN);
//          System.out.println(Arrays.toString(bb.array()));
//            try {
//                byte[] bytes = message.getBytes("ISO-8859-1");
//                for(int i:bytes){
//                  System.out.println(i);
//                }
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }

            // Charset utf8 = Charset.forName("UTF-8");
          // byte[] data = new byte[message.size()];
          // for (int i = 0; i < data.length; i++) {
          //   data[i] = (byte) message.get(i);
          //   System.out.println(data[i]);
          // }
//          System.out.println(new String(message, utf8));
          // try {
          ByteBuffer b = null;
          try {
            b = ByteBuffer.wrap(message.getBytes("ISO-8859-1"));
//            String stttr = new String(message.getBytes("ISO-8859-1"),iso88591charset);
//            System.out.println(stttr);
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }
          b.order(ByteOrder.LITTLE_ENDIAN);
             CharBuffer outputBuffer = utf8charset.decode(b);

          //   CharsetDecoder encoder=iso88591charset.newDecoder();
          //   CharBuffer a = encoder.decode(b);
          //   System.out.println(Base64.encodeToString(message.getBytes("ISO-8859-1"),Base64.DEFAULT));
             System.out.println(Arrays.toString(outputBuffer.array()));
          for(int i:outputBuffer.array()){
                  System.out.println((byte)i & 0xff);
                }
          System.out.println((byte)outputBuffer.charAt(0));
          //   System.out.println(Arrays.toString(message.getBytes("UTF-8")));
          //   System.out.println(Arrays.toString(message.getBytes("ISO-8859-1")));
          //   System.out.println(Arrays.toString(message.getBytes("US-ASCII")));
          //   System.out.println(Arrays.toString(message.getBytes("UTF-16BE")));
          //   System.out.println(Arrays.toString(message.getBytes("UTF-16LE")));
          //   System.out.println(Arrays.toString(message.getBytes("UTF-16")));
          // } catch (UnsupportedEncodingException e) {
          //   e.printStackTrace();
          // } catch (CharacterCodingException e) {
          //   e.printStackTrace();
          // }
        }

      }
    });
  }
}
